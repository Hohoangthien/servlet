<%-- 
    Document   : list-user
    Created on : Feb 25, 2024, 5:08:56 PM
    Author     : admin
--%>

<%@page import="Service.UserService, Model.User, java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Số lượng người dùng trên mỗi trang
    int usersPerPage = 10;

    // Lấy danh sách tất cả người dùng từ UserService
    List<User> userList = UserService.getAllUser();

    // Tổng số trang
    int totalPages = (int) Math.ceil((double) userList.size() / usersPerPage);

    // Xác định trang hiện tại từ tham số trên URL (nếu không có sẽ là trang đầu tiên)
    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;

    // Xác định vị trí bắt đầu của danh sách người dùng trên trang hiện tại
    int startIndex = (currentPage - 1) * usersPerPage;

    // Xác định vị trí kết thúc của danh sách người dùng trên trang hiện tại
    int endIndex = Math.min(startIndex + usersPerPage, userList.size());

    // Lấy danh sách người dùng cho trang hiện tại
    List<User> currentPageUsers = userList.subList(startIndex, endIndex);

    // Xác định trang tiếp theo và trang trước
    int nextPage = (currentPage < totalPages) ? currentPage + 1 : totalPages;
    int prevPage = (currentPage > 1) ? currentPage - 1 : 1;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    </head>
    <body>
        <%@include file="/layout/admin-header.jsp" %>
        <h2>Danh sách User - Trang <%= currentPage %> trên <%= totalPages %></h2>
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Họ tên</th>
                <th>Password</th>
                <th>Email</th>
                <th>Role</th>
                <th>Sửa</th>
                <th>Xoá / Khôi phục</th>

            </tr>
            <%
                for (User user : currentPageUsers) {
            %>
            <tr>
                <%
                    String username = user.getUsername(); 
                    boolean daxoa = UserService.isDeletedUser(username);
                %>

                <td><%= user.getUsername() %></td>
                <td><%= user.getHoTen() %></td>
                <td><%= user.getPassword() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getRole() %></td>
                <td><a href="<%=request.getContextPath()%>/admin/editUser?username=<%= username%>">Edit</a></td>
                <td>
                    <% if(daxoa){%>
                    <a href="<%=request.getContextPath()%>/admin/deleteUser?username=<%= username %>">Delete</a>
                    <%}else{ %>
                    <a href="<%=request.getContextPath()%>/admin/unblockUser?username=<%= username %>">Unblock</a>
                    <%}%>
                </td>

            </tr>
            <%
                }
            %>
        </table>
        <div>
            <a href="<%=request.getRequestURI()%>?page=<%=prevPage%>">Previous</a>
            <% for (int i = 1; i <= totalPages; i++) { %>
            <a href="<%=request.getRequestURI()%>?page=<%=i%>"><%=i%></a>
            <% } %>
            <a href="<%=request.getRequestURI()%>?page=<%=nextPage%>">Next</a>
        </div>
    </body>
</html>
