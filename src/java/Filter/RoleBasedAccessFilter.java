/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.User;

/**
 *
 * @author admin
 */
public class RoleBasedAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = ((HttpServletRequest) request).getSession();
        User user = (User) session.getAttribute("user");

        String requestedPage = ((HttpServletRequest) request).getServletPath();
        //user bằng null thì chuyển về login
        if (user == null) {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/index.jsp");
            return;
        } else {
            // Nếu là user mà truy cập vào trang admin thì sẽ chuyển hướng về user home
            if (requestedPage.startsWith("/admin") && !user.getRole().equals("admin")) {
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/user/home");
                return;
            } else if (requestedPage.startsWith("/user") && !user.getRole().equals("user")) {
                ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/admin/home");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        // Initialization code here
    }

    @Override
    public void destroy() {

        // Destroy code here
    }

}
