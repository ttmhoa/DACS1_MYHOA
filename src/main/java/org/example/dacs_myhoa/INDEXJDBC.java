package org.example.dacs_myhoa;

import java.sql.Connection;
import java.sql.DriverManager;

public class INDEXJDBC {
    public Connection connection;


    public  Connection getConnection() {
        String  DB_URL = "jdbc:sqlserver://LAP-MYHOA\\MSSQLSERVER01;"
                + "databaseName=DACS_myhoa;encrypt=true;trustServerCertificate=true;";
        String USER_NAME = "sa";
        String PASSWORD = "sa";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (Exception ex) {
            System.out.println("Kết nối thất bại!");
            //noinspection CallToPrintStackTrace
            ex.printStackTrace();
        }
        return connection;
    }
}
