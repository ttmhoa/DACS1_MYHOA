package org.example.dacs_myhoa;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Room extends Thread implements Initializable {
    @FXML
    private Button btn_send;
    @FXML
    private TextField msgField;
    @FXML
    private TextArea msgRoom;
    @FXML
    private Button close;
    @FXML
    private Button profile;
    @FXML
    private AnchorPane panehome;
    @FXML
    private AnchorPane profileanchor;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;


    public void connectSocket() {
        try {
            socket = new Socket("localhost", 8889);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send() {
        String insertQuery = "INSERT INTO chat_message (message) VALUES (?)";
        INDEXJDBC connection = new INDEXJDBC();
        Connection con= connection.getConnection();
        String msg = msgField.getText();
        writer.println(loginController.userNameLogin + ": " + msg);
        msgRoom.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        msgRoom.appendText("Me: " + msg + "\n");
        String newMsg=loginController.userNameLogin+":" + msg;
        msgField.setText("");
        try{
            preparedStatement =con.prepareStatement(insertQuery);
            preparedStatement.setString(1, newMsg);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(loginController.userNameLogin + ":")) {
                    continue;
                } else if (fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                msgRoom.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessageByKey(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            send();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectSocket();
        showData();
    }
public void showData(){
    String selectQuery = "SELECT message FROM chat_message";
    INDEXJDBC connection = new INDEXJDBC();
    Connection con = connection.getConnection();
    try {

        preparedStatement = con.prepareStatement(selectQuery);
        result = preparedStatement.executeQuery();
        while (result.next()) {
            String message = result.getString("message");
            msgRoom.appendText(message + "\n");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (result != null) {
                result.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    private double x;
    private double y;

    public void closebtaction() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to preview?");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.get().equals(ButtonType.OK)) {
                close.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("DashBoard.Css").toExternalForm());
                stage.initStyle(StageStyle.TRANSPARENT);
                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getScreenX();
                    y = event.getScreenY();
                });
                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                    stage.setOpacity(.8);

                });
                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });
                stage.setScene(scene);
                stage.show();


            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sliderProfile(){
        profileanchor.setVisible(true);
        panehome.setVisible(false);
    }
    public void sliderHome(){
        profileanchor.setVisible(false);
        panehome.setVisible(true);
    }
}
