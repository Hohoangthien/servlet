/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DatabaseConnect.DatabaseManager;
import Model.Sach;
import Model.TacGia;
import static Service.TacGiaService.findTacGiaById;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class SachService {

    public static Sach findSachByID(int ma_sach) throws ClassNotFoundException, SQLException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Sach sach;
        try (Connection conn = connectJDBC.connect()) {
            PreparedStatement stmt = conn
                    .prepareStatement("select * from sach join tacgia where sach.ma_tg = tacgia.ma_tg and ma_sach = ?");
            stmt.setInt(1, ma_sach);
            ResultSet rs = stmt.executeQuery();
            sach = new Sach();
            while (rs.next()) {
                sach.setMaSach(rs.getInt("ma_sach"));
                sach.setTenSach(rs.getString("ten_sach"));
                sach.setTg(findTacGiaById(rs.getInt("ma_tg")));
            }
        }
        return sach;
    }

    public static Sach editSach(int masach, String tenmoi, TacGia tg, int giatien) throws ClassNotFoundException, SQLException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt1 = conn.prepareStatement("select * from tacgia where ma_tg = ?");
        ResultSet rs = stmt1.executeQuery();
        if (rs.next()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE sach SET ten_sach = ?, ma_tg = ?, giatien = ? WHERE (ma_sach = ?)");
            stmt.setString(1, tenmoi);
            stmt.setInt(2, tg.getMaTacGia());
            stmt.setInt(3, masach);
            stmt.setInt(4, giatien);

            stmt.executeUpdate();
            return new Sach(masach, tenmoi, tg, giatien);
        } else {
            return null;
        }
    }

    public static List<Sach> getAllSach() throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from sach");
        ResultSet rs = stmt.executeQuery();
        List<Sach> sachs = new ArrayList<>();
        while (rs.next()) {
            sachs.add(
                    new Sach(
                            rs.getInt("ma_sach"),
                            rs.getString("ten_sach"),
                            findTacGiaById(rs.getInt("ma_tg")),
                            rs.getInt("giatien")));
        }
        return sachs;
    }
    public static List<Sach> getAllSachDeleted() throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from sach where (is_deleted = '1')");
        ResultSet rs = stmt.executeQuery();
        List<Sach> sachs = new ArrayList<>();
        while (rs.next()) {
            sachs.add(
                    new Sach(
                            rs.getInt("ma_sach"),
                            rs.getString("ten_sach"),
                            findTacGiaById(rs.getInt("ma_tg")),
                            rs.getInt("giatien")));
        }
        return sachs;
    }
     public static List<Sach> getAllSachNoDeleted() throws SQLException, ClassNotFoundException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from sach where (is_deleted = '0')");
        ResultSet rs = stmt.executeQuery();
        List<Sach> sachs = new ArrayList<>();
        while (rs.next()) {
            sachs.add(
                    new Sach(
                            rs.getInt("ma_sach"),
                            rs.getString("ten_sach"),
                            findTacGiaById(rs.getInt("ma_tg")),
                            rs.getInt("giatien")));
        }
        return sachs;
    }

    // AddSach bao gồm Tên sách , mã tác giả, đường dẫn tới ảnh đó
    public static Sach addSach(String tensach, int matg, int giatien) throws SQLException, ClassNotFoundException {
        TacGia tacgia = findTacGiaById(matg);
        // Neu tac gia tồn tại
        if (tacgia != null) {
            DatabaseManager connectJDBC = new DatabaseManager();
            int id;
            try (Connection conn = connectJDBC.connect()) {
                PreparedStatement stmt = conn
                        .prepareStatement("INSERT INTO sach (ten_sach, ma_tg, giatien) VALUES (?, ?, ?)",
                                Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, tensach);
                stmt.setInt(2, tacgia.getMaTacGia());
                stmt.setInt(3, giatien);

                stmt.executeUpdate();
                // Lấy ra ResultSet chứa giá trị auto increment ID
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                // Di chuyển đến bản ghi đầu tiên
                generatedKeys.next();
                // Lấy ra giá trị auto increment ID
                id = generatedKeys.getInt(1);
                System.out.println(
                        "Service.SachService.addSach(): Da them sach: ID: " + id + " Ten sach; " + tensach + "ảnh lưu tại: "
                );
                // Đóng kết nối
            }
            return new Sach(id, tensach, tacgia);
        } else {
            System.out.println("Service.SachService.addSach(): Tac gia ko tồn tại");
            return null;
        }
    }

    public static boolean deleteSach(int id) throws ClassNotFoundException, SQLException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from sach where ma_sach = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            PreparedStatement stmt2 = conn.prepareStatement("UPDATE sach SET is_deleted = '1' WHERE (ma_sach = ?)");
            try {
                stmt2.setInt(1, id);
                stmt2.executeUpdate();
                System.out.println("Service.SachService.deleteSach(): Xoa thanh cong sach " + id);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean restoreSach(int id) throws ClassNotFoundException, SQLException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from sach where ma_sach = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            PreparedStatement stmt2 = conn.prepareStatement("UPDATE sach SET is_deleted = '0' WHERE (ma_sach = ?)");
            try {
                stmt2.setInt(1, id);
                stmt2.executeUpdate();
                System.out.println("Service.SachService.deleteSach(): Xoa thanh cong sach " + id);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isDeletedSach(int id) throws ClassNotFoundException, SQLException {
        DatabaseManager connectJDBC = new DatabaseManager();
        Connection conn = connectJDBC.connect();
        PreparedStatement stmt = conn.prepareStatement("select * from sach where ma_sach = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getBoolean("is_deleted");
        }
        return false;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        addSach("Test", 9, 20000);
    }
}
