/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DatabaseConnect.DatabaseManager;
import Model.TacGia;
import static Service.UserService.getUserByUsername;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class TacGiaService {

    public static TacGia addTacGia(String tentg) throws SQLException, ClassNotFoundException {
        if (findMaTacGiaByName(tentg) != null) {
            System.out.println("Tac gia da ton tai");
            return null;
        }
        DatabaseManager connectJDBC = new DatabaseManager();
        // do trong mysql đặt mã tác giả là auto increment nên sẽ để là default
        try (Connection conn = connectJDBC.connect()) {
            // do trong mysql đặt mã tác giả là auto increment nên sẽ để là default
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO tacgia VALUES (default, ?, default) ");
            stmt.setString(1, tentg);
            stmt.executeUpdate();
        }

        return findMaTacGiaByName(tentg);
    }

    public static TacGia findMaTacGiaByName(String tentg) throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        try (Connection conn = connectJDBC.connect()) {
            PreparedStatement stmt = conn.prepareStatement("select * from tacgia where ten_tg like ?");
            stmt.setString(1, tentg);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TacGia(rs.getInt("ma_tg"), rs.getString("ten_tg"));
            }
        }

        return null;
    }

    public static TacGia findTacGiaById(int matg) throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        try (Connection conn = connectJDBC.connect()) {
            PreparedStatement stmt = conn.prepareStatement("select * from tacgia where ma_tg like ?");
            stmt.setInt(1, matg);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TacGia(rs.getInt("ma_tg"), rs.getString("ten_tg"));
            }
        }
        return null;
    }

    public static ArrayList<TacGia> findAllTacGia() throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        ArrayList<TacGia> list;
        try (Connection conn = connectJDBC.connect()) {
            PreparedStatement stmt = conn.prepareStatement("select * from tacgia");
            list = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new TacGia(rs.getInt("ma_tg"), rs.getString("ten_tg")));
            }
        }
        return list;
    }
    
    public static boolean isDeletedTacGia(int matg) throws ClassNotFoundException, SQLException {
        DatabaseManager connectJDBC = new DatabaseManager();
        try (Connection conn = connectJDBC.connect()) {
            PreparedStatement stmt = conn.prepareStatement("select * from tacgia where ma_tg = ?");
            stmt.setInt(1, matg);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("is_deleted");
            }
        }
        return true;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(addTacGia("test tác giả số 2").getTenTacGia());
        
        
    }

}
