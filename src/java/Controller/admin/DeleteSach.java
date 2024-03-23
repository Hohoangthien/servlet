/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin;

import Service.SachService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
@WebServlet("/admin/deleteSach")
public class DeleteSach extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            if (SachService.findSachByID(id) != null) {
                request.setAttribute("next", "/admin/list-sach.jsp");
                if (SachService.deleteSach(id)) {
                    request.setAttribute("message", "DeletedSuccess");
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
