/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin;

import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
@WebServlet("/admin/unblockUser")
public class UnblockUser extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        try {
            if (UserService.getUserByUsername(username) != null) {
                request.setAttribute("next", "/admin/list-sach.jsp");
                if (UserService.unblockUser(username)) {
                    request.setAttribute("message", "RestoreSuccess");
                } else {
                    request.setAttribute("message", "Fail");
                }
            } else {
                request.setAttribute("message", "Fail");
            }
            request.getRequestDispatcher("/popup.jsp").forward(request, response);
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            request.getRequestDispatcher("/popup.jsp").forward(request, response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
