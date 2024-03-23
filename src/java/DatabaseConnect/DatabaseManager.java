/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DatabaseManager {
    private final String DB_URL = "jdbc:mysql://localhost:3306/dkhp?autoReconnect=true&useSSL=false";
    private final String DB_USN = "root";
    private final String DB_PSW = "1482003";

    public Connection connect() throws ClassNotFoundException {
        //Tạo đối tượng Connection
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            conn = DriverManager
                    .getConnection(DB_URL, DB_USN, DB_PSW);
            System.out.println("Kết nối thành công");
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại");
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        try {
            db.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
