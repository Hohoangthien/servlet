<%-- 
    Document   : admin-home
    Created on : Feb 25, 2024, 4:52:27 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN HOME</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    </head>
    <body>
        <%@include file="../layout/admin-header.jsp" %>
        <ul class="nav justify-content-center">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/admin/listUser">Danh sách người dùng</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/admin/list-sach.jsp">Danh sách sách</a> 
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/admin/listTacGia">Danh sách tác giả</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/admin/listMuon">Duyệt lượt mượn sách</a>
            </li>
        </ul>


    </body>
</html>
