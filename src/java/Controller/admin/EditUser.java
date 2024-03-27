/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.User;
import Service.UserService;

/**
 *
 * @author admin
 */
@WebServlet("/admin/editUser")
public class EditUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin username từ tham số trên URL
        String username = request.getParameter("username");
        

        // Lấy thông tin người dùng từ cơ sở dữ liệu
        User user = null;
        try {
            user = UserService.getUserByUsername(username);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Đặt thông tin người dùng vào request attribute để hiển thị trong JSP
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/edituser.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form chỉnh sửa
        String username = request.getParameter("username");
        String newPassword = request.getParameter("password");
        String newHoTen = request.getParameter("hoten");
        String newEmail = request.getParameter("email");
        String newRole = request.getParameter("role");

        try {
            if (UserService.updateUser(username, newPassword, newHoTen, newEmail, newRole)) {
                request.setAttribute("next", "/admin/list-user.jsp");
                request.setAttribute("message", "ChangePassSuccess");
            } else {
                request.setAttribute("message", "ChangePassFail");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/popup.jsp"); // thông báo thành công
            dispatcher.forward(request, response);
//
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
