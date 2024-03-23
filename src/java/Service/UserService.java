/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Controller.ValidateController;
import DatabaseConnect.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class UserService {

    public static User login(String username, String password)
            throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from user where username = ? and password = ?");
        stmt.setString(1, username);
        stmt.setString(2, password);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new User(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("hoTen"),
                        rs.getString("email"),
                        rs.getString("role"));
            } else {
                return null;
            }
        }
    }

    public static int register(User user) throws SQLException, ClassNotFoundException { // Dang ki
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn
                .prepareStatement("INSERT INTO user (username, password, hoten,  email, role) VALUES (?, ?, ?, ?, default )");

        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getHoTen());
        stmt.setString(4, user.getEmail());

        return stmt.executeUpdate();
    }

    public static List<User> getAllUser() throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from user");
        ResultSet rs = stmt.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            User u = new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("hoten"),
                    rs.getString("email"),
                    rs.getString("role"));
            list.add(u);
        }
        return list;
    }

    public static User getUserByUsername(String username) throws ClassNotFoundException, SQLException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from user where username = ?");
        stmt.setString(1, username);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new User(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("hoten"),
                        rs.getString("email"),
                        rs.getString("role"));
            } else {
                return null;
            }
        }
    }

    public static boolean updateUser(String username,
            String newPassword, String newhoTen, String newemail, String newrole)
            throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from user where username = ?");
        // Check xem user có tồn tại hay không
        stmt.setString(1, username);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next() && ValidateController.isValidPassword(newPassword)) { // nếu password đúng định dạng
                PreparedStatement stmt2 = conn
                        .prepareStatement("UPDATE user SET hoten = ?, email = ?, password = ?, role = ?  WHERE (username = ?)");
                stmt2.setString(1, newhoTen);
                stmt2.setString(2, newemail);
                stmt2.setString(3, newPassword);
                stmt2.setString(4, newrole);
                stmt2.setString(5, username);
                stmt2.executeUpdate();
                System.out.println("Service.UserService.updateUser(): Doi thong tin user thanh cong");
                return true;
            } else {
                throw new SQLException("User not found for username: " + username);
            }
        }
    }

    public static boolean deleteUser(String username)
            throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from user where username = ?");
        // Check xem user có tồn tại hay không
        stmt.setString(1, username);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) { // nếu password đúng định dạng
                PreparedStatement stmt2 = conn
                        .prepareStatement("UPDATE user SET role = ? WHERE (username = ?)");

                stmt2.setString(1, "delete");
                stmt2.setString(2, username);
                stmt2.executeUpdate();
                System.out.println("Service.UserService.updateUser(): Doi thong tin user thanh cong");
                return true;
            } else {
                throw new SQLException("User not found for username: " + username);
            }
        }
    }

    public static boolean unblockUser(String username) throws ClassNotFoundException, SQLException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from user where username = ?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            PreparedStatement stmt2 = conn.prepareStatement("UPDATE user SET role = 'user' WHERE (username = ?)");
            try {
                stmt2.setString(1, username);
                stmt2.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isDeletedUser(String username) throws ClassNotFoundException, SQLException {
        return getUserByUsername(username).getRole().equals("delete");
    }

    public static void main(String[] args) {
        try {
            
            System.out.println("Service.UserService.main()" + isDeletedUser("qalopywexa"));
            unblockUser("qalopywexa");
            System.out.println("Service.UserService.main()" + isDeletedUser("qalopywexa"));

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
