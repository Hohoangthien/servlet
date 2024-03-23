/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.User;
import Service.UserService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
//@WebServlet("/register")
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Xác thực đăng kí
    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Thông tin đăng kí tk
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String hoTen = request.getParameter("hoTen");
        User user = new User(username, password, hoTen, email, "user");

        if (UserService.getUserByUsername(username) != null) { // Nếu tài khoản đã tồn tại
            request.setAttribute("message", "existUser");
            request.getRequestDispatcher("/popup.jsp").forward(request, response);
            System.out.println("Tai khoan da ton tai");
        } else {
            if (ValidateController.isValidUsername(username) && ValidateController.isValidPassword(password)) {
                if (UserService.register(user) == 1) {
                    System.out.println("Controller.Register.authenticate(): Them thanh cong " + username);
                    request.setAttribute("message", "success");
                    request.setAttribute("next", "/index.jsp");
                }
            } else {
                if (!ValidateController.isValidPassword(password)) {
                    request.setAttribute("message", "Badusn");
                }
                if (!ValidateController.isValidUsername(username)) {
                    request.setAttribute("message", "Badpwd");
                }
            }
            request.getRequestDispatcher("/popup.jsp").forward(request, response);
        }
    }
}
