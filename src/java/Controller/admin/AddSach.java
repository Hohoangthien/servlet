/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin;

import Model.Sach;
import Service.SachService;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB

@WebServlet("/admin/addSach")
public class AddSach extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String tensach = request.getParameter("tensach");
            int matg = Integer.parseInt(request.getParameter("matg"));
            int giatien = Integer.parseInt(request.getParameter("giatien"));
            
            Part filePart = request.getPart("file");
            Sach sach = SachService.addSach(tensach, matg, giatien);
            if (sach != null) {
                String filename = sach.getMaSach() + ".jpg";
                String filePath = "D:\\J2EE\\qlthuvien2\\web\\img\\sach\\" + filename;
                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, Paths.get(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            request.setAttribute("next", "/admin/list-sach.jsp");
            request.setAttribute("message", "UploadFileSuccess");
            request.getRequestDispatcher("/popup.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AddSach.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
