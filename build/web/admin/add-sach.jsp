<%-- 
    Document   : add-sach
    Created on : Mar 10, 2024, 4:47:42 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Service.TacGiaService, Model.TacGia, java.util.*"%>

<%
    ArrayList<TacGia> tacgias = TacGiaService.findAllTacGia();
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Upload ảnh</title>
    </head>

    <body>
        <h1>Thêm sách mới</h1>
        <form action="/qlthuvien2/admin/addSach" method="post" enctype="multipart/form-data" charset="UTF-8">            
            <label for="tensach">Tên sách:</label> 
            <input type="text" name="tensach" id="tensach" placeholder="Nhập tên sách" required ><br>
            <label for="tentg">Tác giả:</label>
            <select id="matg" name="matg"  required>
                <% for (TacGia tg : tacgias) {%>
                <option value="<%=tg.getMaTacGia()%>"><%=tg.getTenTacGia()%></option>
                <% }%>
            </select>
            <br>
            <label for="giatien">Giá tiền: </label> 
            <input type="number" name="giatien" id="giatien" placeholder="Nhập giá tiền" required ><br>
            <label for="file">Nhập ảnh sách:</label>
            <br>
            <input type="file" name="file" required>
            <br>
            <input type="submit" value="Upload">
        </form>
    </body>
</html>
