<%-- 
    Document   : edituser
    Created on : Feb 7, 2024, 7:14:10 PM
    Author     : admin
--%>

<%@page import="Model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Edit User</title>
    </head>
    <body>
        <%@include file="../layout/admin-header.jsp" %> 

        <h2>Edit User Information</h2>

        <%
            // Lấy thông tin người dùng từ request attribute
            User user = (User) request.getAttribute("user");
        %>

        <form action="<%=request.getContextPath()%>/admin/editUser" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= user.getUsername() %>" readonly>
            <br>
            <label for="hoten">Họ tên:</label>
            <input type="text" id="hoten" name="hoten" value="<%= user.getHoTen() %>">
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="<%=user.getPassword()%>" >
            <br>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= user.getEmail() %>" >
            <br>
            <%-- Chọn "user" hoặc "admin" --%>
            <label for="role">Đổi vai trò từ <%= user.getRole() %> thành:</label>
            <select id="role" name="role">
                <option value="user">user</option>
                <option value="admin">admin</option>
            </select>

            <br>
            <input type="submit" value="Update">
        </form>

        <button onclick="history.back()">Go Back</button>

    </body>
</html>

