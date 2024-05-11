package org.example.dacs_myhoa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class dashbroadController implements Initializable {
    @FXML
    private Button Paybtn;

    @FXML
    private Label username;

    @FXML
    private AnchorPane PaymentForm;

    @FXML
    private Button close_dash;

    @FXML
    private TextArea coaches_address;

    @FXML
    private Button coaches_btn_add;

    @FXML
    private Button coaches_btn_delete;

    @FXML
    private Button coaches_btn_reset;

    @FXML
    private Button coaches_btn_update;

    @FXML
    private TableColumn<DataCoach, String> coaches_col_address;

    @FXML
    private TableColumn<DataCoach, String> coaches_col_gender;

    @FXML
    private TableColumn<DataCoach, Integer> coaches_col_id;

    @FXML
    private TableColumn<DataCoach, String> coaches_col_name;

    @FXML
    private TableColumn<DataCoach, Integer> coaches_col_phone;

    @FXML
    private TableColumn<DataCoach, Integer> coaches_col_status;

    @FXML
    private AnchorPane coaches_form;

    @FXML
    private ComboBox<?> coaches_gender;

    @FXML
    private TextField coaches_id;

    @FXML
    private TextField coaches_name;

    @FXML
    private TextField coaches_phone;

    @FXML
    private ComboBox<?> coaches_status;

    @FXML
    private TableView<DataCoach> coaches_tableview;

    @FXML
    private Label dashbroad_NC;

    @FXML
    private Label dashbroad_NM;

    @FXML
    private Label dashbroad_TI;

    @FXML
    private AreaChart<?, ?> dashbroad_datachar;

    @FXML
    private AnchorPane dashbroad_form;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField member_ID;

    @FXML
    private Button member_addbtn;

    @FXML
    private TextArea member_address;

    @FXML
    private Button member_clearbtn;

    @FXML
    private Button member_delete;

    @FXML
    private DatePicker member_enddate;

    @FXML
    private AnchorPane member_form;

    @FXML
    private ComboBox<String> member_gender;

    @FXML
    private TextField member_name;

    @FXML
    private TextField member_phone;

    @FXML
    private ComboBox<?> member_schedule;

    @FXML
    private DatePicker member_startdate;

    @FXML
    private ComboBox<?> member_status;

    @FXML
    private TableView<MembersData> member_tableview;

    @FXML
    private TableColumn<MembersData, String> member_tableview_address;

    @FXML
    private TableColumn<MembersData, String> member_tableview_enddate;

    @FXML
    private TableColumn<MembersData, String> member_tableview_gender;

    @FXML
    private TableColumn<MembersData, String> member_tableview_id;

    @FXML
    private TableColumn<MembersData, String> member_tableview_name;

    @FXML
    private TableColumn<MembersData, String> member_tableview_phone;

    @FXML
    private TableColumn<MembersData, String> member_tableview_schedule;

    @FXML
    private TableColumn<MembersData, String> member_tableview_startdate;

    @FXML
    private TableColumn<MembersData, String> member_tableview_status;

    @FXML
    private Button member_update;

    @FXML
    private Button minize_dash;

    @FXML
    private TableColumn<MembersData, String> pay_col_cusid;

    @FXML
    private TableColumn<MembersData, String> pay_col_enddate;

    @FXML
    private TableColumn<MembersData, String> pay_col_name;

    @FXML
    private TableColumn<MembersData, String> pay_col_startdate;

    @FXML
    private TableColumn<MembersData, String> pay_col_status;

    @FXML
    private TextField payamount;

    @FXML
    private Label paychange;

    @FXML
    private ComboBox<?> paycustomerid;

    @FXML
    private ComboBox<?> payname;

    @FXML
    private TableView<MembersData> paytableview;

    @FXML
    private Label paytotal;
    @FXML
    private Button payment;
    @FXML
    private Button members;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button coaches;

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;
    private int totalDay;
    private float price;
    public ObservableList<MembersData> paymenDataList(){
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        ObservableList<MembersData> listdata= FXCollections.observableArrayList();
        String sql= "select *from member";
        try {
            MembersData md;
            preparedStatement= conn.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                md= new MembersData(resultSet.getInt("id")
                        ,resultSet.getString("memberId")
                        ,resultSet.getString("name")
                        ,resultSet.getString("address")
                        ,resultSet.getString("phoneNumber")
                        ,resultSet.getString("gender")
                        ,resultSet.getString("schedule")
                        ,resultSet.getString("status")
                        ,resultSet.getDate("endDate"),
                        resultSet.getDate("startDate"),
                        resultSet.getFloat("price")
                        );
                listdata.add(md);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return listdata;

    }
    private ObservableList<MembersData> paymentListData;
    public void paymentShowData(){
        paymentListData= paymenDataList();
        pay_col_cusid.setCellValueFactory(new PropertyValueFactory<>("memberid"));
        pay_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        pay_col_startdate.setCellValueFactory(new PropertyValueFactory<>("startdate"));
        pay_col_enddate.setCellValueFactory(new PropertyValueFactory<>("enddate"));
        pay_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        paytableview.setItems(paymentListData);

    }
    public void paymentMember(){
        String sql= "select memberId from member where status ='Unpaid'";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        try {
            ObservableList lisdata= FXCollections.observableArrayList();
            preparedStatement= conn.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                lisdata.add(resultSet.getString("memberId"));
            }
            paycustomerid.setItems(lisdata);
            paymentName();

        }catch (Exception e){

        }
    }
    public void paymentName(){
        String sql= "Select name from member where memberId ='"+paycustomerid.getSelectionModel().getSelectedItem()+"'";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        try {
            ObservableList lisdata= FXCollections.observableArrayList();
            preparedStatement= conn.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                lisdata.add(resultSet.getString("name"));
            }
            payname.setItems(lisdata);
            paymentDisplayTotal();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private float totalPay;
    public void displayTotal(){
        String sql= "select price from member where name ='"+payname.getSelectionModel().getSelectedItem()+"' and memberId ='"+paycustomerid.getSelectionModel().getSelectedItem()+"'";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();

        try {
            preparedStatement= conn.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();
          if(resultSet.next()){
              totalPay=resultSet.getFloat("price");
          }
            paytotal.setText("$"+ String.valueOf(totalPay));

        }catch (Exception e){
            e.printStackTrace();

        }
    }
    private float amount;
    private float change;

    public  void paymentAmount(){
        displayTotal();
        Alert alert;
        if(payamount.getText().isEmpty()||totalPay==0){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
            payamount.setText("");
        }else {
            amount= Float.parseFloat(payamount.getText());
            if (amount>=totalPay){
                change=(amount-totalPay);
                paychange.setText("$"+String.valueOf(change));
            }else {
                payamount.setText("");
                amount= 0;
                change= 0;
            }
        }

    }

    public void paymentDisplayTotal(){
        displayTotal();
    }
    public void paymentPayBtn(){
        String sql= "Update member set status = 'Paid' where memberId='"+paycustomerid.getSelectionModel().getSelectedItem()+"'";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        Alert alert;
        try {
            if (totalPay==0||change==0||payamount.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Some thing wrong!!!");
                alert.showAndWait();

            }else {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successfully");
                alert.setHeaderText(null);
                alert.setContentText("Congratulate");
                alert.showAndWait();
                paymentShowData();
                paymentClear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void paymentClear(){
        paycustomerid.getSelectionModel().clearSelection();
        payname.getSelectionModel().clearSelection();
        paytotal.setText("$0.0");
        payamount.setText("");
        paychange.setText("$0.0");
        amount=0;
        change=0;
        totalPay=0;


    }
    public void dashBoardNM() {
        String sql = "SELECT COUNT(id) AS memberCount FROM member WHERE status = 'Paid'";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        int num = 0;

        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                num = resultSet.getInt("memberCount");
            }
            dashbroad_NM.setText(String.valueOf(num));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dashBroadIC() {
        String sql = "SELECT SUM(price) AS totalIncome FROM member WHERE status = 'Paid'";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        float ic = 0;
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ic = resultSet.getFloat("totalIncome");
            }
            dashbroad_TI.setText(String.valueOf("$"+ic));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dashBroadTC() {
        String sql = "SELECT COUNT(id) AS coachCount FROM Coach WHERE status = 'Active'";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        int tc = 0;
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tc = resultSet.getInt("coachCount");
            }
            dashbroad_NC.setText(String.valueOf(tc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dashBroadChart() {
        dashbroad_datachar.getData().clear();
        String sql = "SELECT startDate, SUM(price) FROM member WHERE status = 'Paid' GROUP BY startDate ORDER BY CAST(startDate AS datetime) ASC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        XYChart.Series chart = new XYChart.Series();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getFloat(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dashbroad_datachar.getData().add(chart);
    }
    public void membersUpdate(){
        String sql = "UPDATE member SET name = ?, endDate = ?, address = ?, phoneNumber = ?, startDate = ?, status = ?, schedule = ?, gender = ? WHERE memberId = ?";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        Alert alert;

        try {
            if (member_ID.getText().isEmpty() || member_name.getText().isEmpty() || member_address.getText().isEmpty()
                    || member_phone.getText().isEmpty() || member_gender.getSelectionModel().getSelectedItem() == null
                    || member_schedule.getSelectionModel().getSelectedItem() == null
                    || member_status.getSelectionModel().getSelectedItem() == null
                    || member_startdate.getValue() == null || member_enddate.getValue() == null) {
                isemptyFields();
            } else {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, member_name.getText());
                preparedStatement.setString(2, String.valueOf(member_enddate.getValue()));
                preparedStatement.setString(3, member_address.getText());
                preparedStatement.setString(4, member_phone.getText());
                preparedStatement.setString(5, String.valueOf(member_startdate.getValue()));
                preparedStatement.setString(6, (String) member_status.getSelectionModel().getSelectedItem());
                preparedStatement.setString(7, (String) member_schedule.getSelectionModel().getSelectedItem());
                preparedStatement.setString(8, member_gender.getSelectionModel().getSelectedItem());
                preparedStatement.setString(9, member_ID.getText());

                preparedStatement.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update");
                alert.setHeaderText(null);
                alert.setContentText("Successfully!");
                alert.showAndWait();
                membersShowData();
                membersClear();
            }
        } catch (SQLException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while executing the SQL statement:\n" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An unexpected error occurred:\n" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    public void membersAddbtn(){


        String sql = "INSERT INTO member (memberId, name, address, phoneNumber, gender, schedule, status, startDate, endDate, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();

        try {
            if (member_ID.getText().isEmpty() || member_name.getText().isEmpty() || member_address.getText().isEmpty()
                    || member_phone.getText().isEmpty() || member_gender.getSelectionModel().getSelectedItem() == null
                    || member_schedule.getSelectionModel().getSelectedItem() == null
                    || member_status.getSelectionModel().getSelectedItem() == null || member_startdate.getValue() == null || member_enddate.getValue() == null) {
                isemptyFields();
            } else {
                String check = "SELECT memberId FROM member WHERE memberId = ?";
                preparedStatement = conn.prepareStatement(check);
                preparedStatement.setString(1, member_ID.getText());
                resultSet = preparedStatement.executeQuery();

                Alert alert;
                if (resultSet.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("Member ID is already taken!");
                    alert.showAndWait();
                } else {
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, member_ID.getText());
                    preparedStatement.setString(2, member_name.getText());
                    preparedStatement.setString(3, member_address.getText());
                    preparedStatement.setString(4, member_phone.getText());
                    preparedStatement.setString(5, (String) member_gender.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(6, (String) member_schedule.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(7, (String) member_status.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(8, String.valueOf(member_startdate.getValue()));
                    preparedStatement.setString(9, String.valueOf(member_enddate.getValue()));

                    int totalDay = (int) ChronoUnit.DAYS.between(member_startdate.getValue(), member_enddate.getValue());
                    float price = (float) totalDay * 50;
                    preparedStatement.setFloat(10, price);
                    preparedStatement.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    membersShowData();
                    membersClear();
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An unexpected error occurred:\n" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while executing the SQL statement:\n" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    public void membersClear(){
        member_ID.setText("");
        member_name.setText("");
        member_address.setText("");
        member_phone.setText("");
        member_gender.getSelectionModel().clearSelection();
        member_schedule.getSelectionModel().clearSelection();
        member_status.getSelectionModel().clearSelection();
        member_startdate.setValue(null);
        member_enddate.setValue(null);

    }

    public ObservableList<MembersData> membersDataList(){
        ObservableList<MembersData> listdata= FXCollections.observableArrayList();
        String sql="Select *from member";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        MembersData mb;
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mb = new MembersData(resultSet.getInt("id"), resultSet.getString("memberId"),
                        resultSet.getString("name"), resultSet.getString("address"),
                        resultSet.getString("phoneNumber"), resultSet.getString("gender"),
                        resultSet.getString("schedule"),
                        resultSet.getString("status"), resultSet.getDate("endDate"),
                        resultSet.getDate("startDate"), resultSet.getFloat("price"));
                listdata.add(mb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listdata;

    }
    private ObservableList<MembersData> membersListData;
    public void membersShowData(){
        membersListData= membersDataList();
        member_tableview_id.setCellValueFactory(new PropertyValueFactory<>("memberid"));
        member_tableview_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        member_tableview_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        member_tableview_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        member_tableview_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        member_tableview_schedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        member_tableview_startdate.setCellValueFactory(new PropertyValueFactory<>("startdate"));
        member_tableview_enddate.setCellValueFactory(new PropertyValueFactory<>("enddate"));
        member_tableview_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        member_tableview.setItems(membersListData);

    }
    public void memberDeletebtn(){
        Alert alert;
        String sql= "Delete from member Where coachId= '"+member_ID.getText()+"'";
        try {
            if (member_ID.getText().isEmpty() || member_name.getText().isEmpty() || member_address.getText().isEmpty()
                    || member_phone.getText().isEmpty() || member_gender.getSelectionModel().getSelectedItem() == null
                    || member_schedule.getSelectionModel().getSelectedItem() == null
                    || member_status.getSelectionModel().getSelectedItem() == null
                    || member_startdate.getValue() == null || member_enddate.getValue() == null) {
                isemptyFields();

            } else {
                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Delete!");
                alert.showAndWait();
//                update tableview
                membersShowData();
//                    clear
                membersClear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void coachesDeletebtn(){
        Alert alert;
        String sql= "Delete from Coach Where coachId= '"+coaches_id.getText()+"'";
        try {
            if (coaches_id.getText().isEmpty() || coaches_name.getText().isEmpty() || coaches_address.getText().isEmpty() || coaches_phone.getText().isEmpty() ||
                    coaches_status.getSelectionModel().getSelectedItem() == null ||
                    coaches_gender.getSelectionModel().getSelectedItem() == null) {
                isemptyFields();

            } else {
                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Delete!");
                alert.showAndWait();
//                update tableview
                coachShowData();
//                    clear
                coachesClearbtn();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void coachesUpdatebtn() {
        Alert alert;
        String sql = "Update Coach Set name= '" + coaches_name.getText() + "',address ='" + coaches_address.getText() + "', phonenumber ='"
                + coaches_phone.getText() + "',gender='" + coaches_gender.getSelectionModel().getSelectedItem() + "',status='"
                + coaches_status.getSelectionModel().getSelectedItem() + "' where coachId='" + coaches_id.getText() + "'";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        try {
            if (coaches_id.getText().isEmpty() || coaches_name.getText().isEmpty() || coaches_address.getText().isEmpty() || coaches_phone.getText().isEmpty() ||
                    coaches_status.getSelectionModel().getSelectedItem() == null ||
                    coaches_gender.getSelectionModel().getSelectedItem() == null) {
                isemptyFields();

            } else {
                preparedStatement = conn.prepareStatement(sql);

                preparedStatement.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully !");
                alert.showAndWait();
//                update tableview
                coachShowData();
//                    clear
                coachesClearbtn();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ObservableList<DataCoach> coachlistdata;

    public void isemptyFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();

    }

    public void coachesAddbtn() {
        String sql = "Insert into Coach (coachId,name,address,phonenumber,gender,status) values(?,?,?,?,?,?)";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        try {

            Alert alert;
            if (coaches_id.getText().isEmpty() || coaches_name.getText().isEmpty() || coaches_address.getText().isEmpty() || coaches_phone.getText().isEmpty() ||
                    coaches_status.getSelectionModel().getSelectedItem() == null ||
                    coaches_gender.getSelectionModel().getSelectedItem() == null) {
                isemptyFields();

            } else {
                String checkdata = "Select coachId from Coach where coachId= '" + coaches_id.getText() + "'";
                statement = conn.createStatement();
                resultSet = statement.executeQuery(checkdata);
                if (resultSet.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Coach ID :" + coaches_id.getText() + " was already taken");
                    alert.showAndWait();

                } else {
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, coaches_id.getText());
                    preparedStatement.setString(2, coaches_name.getText());
                    preparedStatement.setString(3, coaches_address.getText());
                    preparedStatement.setString(4, coaches_phone.getText());
                    preparedStatement.setString(5, (String) coaches_gender.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(6, (String) coaches_status.getSelectionModel().getSelectedItem());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully !");
                    alert.showAndWait();
//                    insert data
                    preparedStatement.executeUpdate();
//                   update tableview
                    coachShowData();
//                    clear
                    coachesClearbtn();

                }

            }

        } catch (Exception e) {


            e.printStackTrace();
        }

    }

    public void coachesClearbtn() {
        coaches_id.setText("");
        coaches_name.setText("");
        coaches_address.setText("");
        coaches_phone.setText("");
        coaches_gender.getSelectionModel().clearSelection();
        coaches_status.getSelectionModel().clearSelection();
    }

    public void coachShowData() {
        coachlistdata = coachObservableList();
        coaches_col_id.setCellValueFactory(new PropertyValueFactory<>("coachid"));
        coaches_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        coaches_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        coaches_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        coaches_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        coaches_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        coaches_tableview.setItems(coachlistdata);


    }
    public  void  memberSelect(){
        MembersData DTMB= member_tableview.getSelectionModel().getSelectedItem();
        int num = member_tableview.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        member_ID.setText(DTMB.getMemberid());
        member_name.setText(DTMB.getName());
        member_address.setText(DTMB.getAddress());
        member_phone.setText(DTMB.getPhone());
        member_startdate.setValue(LocalDate.parse(String.valueOf(DTMB.getStartdate())));
        member_enddate.setValue(LocalDate.parse(String.valueOf(DTMB.getEnddate())));




    }

    public void coachesSelect() {
        DataCoach cd = coaches_tableview.getSelectionModel().getSelectedItem();
        int num = coaches_tableview.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        coaches_id.setText(cd.getCoachid());
        coaches_name.setText(cd.getName());
        coaches_address.setText(cd.getAddress());
        coaches_phone.setText(String.valueOf(cd.getPhone()));



    }

    public ObservableList<DataCoach> coachObservableList() {
        ObservableList<DataCoach> listData = FXCollections.observableArrayList();
        String sql = "Select *from Coach";
        INDEXJDBC connection = new INDEXJDBC();
        conn = connection.getConnection();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            DataCoach DTC;
            while (resultSet.next()) {
                DTC = new DataCoach(resultSet.getInt("id"),
                        resultSet.getString("coachId"), resultSet.getString("name"),
                        resultSet.getString("address"), resultSet.getString("gender"),
                        resultSet.getInt("phonenumber"), resultSet.getString("status"));
                listData.add(DTC);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private String gender[] = {"Male", "Female", "Others"};

    private  void memberGenderList(){
        List<String> genderlist= new ArrayList<>();
        for (String data: gender){
            genderlist.add(data);
        }
        ObservableList listitem = FXCollections.observableArrayList(genderlist);
        member_gender.setItems(listitem);
    }
    private String [] scheduleList ={"9AM -11AM","11AM - 1PM","1PM -3PM", "3PM - 5PM","5PM - 7PM"};
    public void memberSchedule(){
        List<String> s1= new ArrayList<>();
        for(String data: scheduleList){
            s1.add(data);
        }
        ObservableList listitem = FXCollections.observableArrayList(s1);
        member_schedule.setItems(listitem);

    }
    private String memberStatus[]= {"Paid", "Unpaid"};
    public void membersStatus(){
        List<String> s1= new ArrayList<>();
        for(String data: memberStatus){
            s1.add(data);
        }
        ObservableList listitem = FXCollections.observableArrayList(s1);
        member_status.setItems(listitem);
    }

    public void coachGenderlist() {
        List<String> genderlist = new ArrayList<>();
        for (String itemgender : gender) {
            genderlist.add(itemgender);
        }
        ObservableList listitem = FXCollections.observableArrayList(genderlist);
        coaches_gender.setItems(listitem);

    }

    private String coachstatus[] = {"Active", "Inactive"};

    public void coachlistStatus() {
        List<String> Statuslist = new ArrayList<>();
        for (String itemStatus : coachstatus) {
            Statuslist.add(itemStatus);
        }
        ObservableList listitem = FXCollections.observableArrayList(Statuslist);
        coaches_status.setItems(listitem);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUserName();
        dashBroadIC();
        dashBoardNM();
        dashBroadTC();
        dashBroadChart();
        coachGenderlist();
        coachlistStatus();
        coachShowData();
        membersShowData();
        membersStatus();
        memberSchedule();
        memberGenderList();
        paymentMember();
        paymentName();
        paymentShowData();


    }

    private double x;
    private double y;

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> optional = alert.showAndWait();
            if (optional.get().equals(ButtonType.OK)) {
                logout_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("Indexlogincss.Css").toExternalForm());
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
    public  void displayUserName(){
        String user= DataUser.username;
        user= user.substring(0,1).toUpperCase()+user.substring(1);
        username.setText(user);

    }

    public void switchform(ActionEvent actionEvent) {
        if (actionEvent.getSource() == dashboard_btn) {
            dashbroad_form.setVisible(true);
            coaches_form.setVisible(false);
            PaymentForm.setVisible(false);
            member_form.setVisible(false);
            dashBroadIC();
            dashBoardNM();
            dashBroadTC();
            dashBroadChart();
        } else if (actionEvent.getSource() == coaches) {
            dashbroad_form.setVisible(false);
            coaches_form.setVisible(true);
            PaymentForm.setVisible(false);
            member_form.setVisible(false);
            coachShowData();
            coachGenderlist();
            coachlistStatus();
        } else if (actionEvent.getSource() == members) {

            dashbroad_form.setVisible(false);
            coaches_form.setVisible(false);
            PaymentForm.setVisible(false);
            member_form.setVisible(true);

            membersShowData();
            membersStatus();
            memberSchedule();
            memberGenderList();

        } else if (actionEvent.getSource() == payment) {
            dashbroad_form.setVisible(false);
            coaches_form.setVisible(false);
            PaymentForm.setVisible(true);
            member_form.setVisible(false);
            paymentShowData();
            paymentMember();
            paymentName();
        }


    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closedashbroad(ActionEvent event) {
        Stage stage = (Stage) close_dash.getScene().getWindow();
        stage.close();
    }
    public void sliderGroupChat (){
        try {
            Stage stage=  new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("GroupChat.fxml"));
            Scene scene = new Scene(root);
            root.setOnMousePressed((MouseEvent event) ->{
                x= event.getScreenX();
                y= event.getScreenY();
            });
            root.setOnMouseDragged((MouseEvent event) ->{
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() -y);
                stage.setOpacity(.8);

            });
            root.setOnMouseReleased((MouseEvent event) ->{
                stage.setOpacity(1);
            });
            stage.initStyle(StageStyle.UNDECORATED);
            scene.getStylesheets().add(getClass().getResource("ChatMessage.Css").toExternalForm());
            stage.setTitle("Gym Management Application");
            stage.setScene(scene);
            stage.show();



        }catch (Exception e){
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            e.getCause();
        }
    }

}
