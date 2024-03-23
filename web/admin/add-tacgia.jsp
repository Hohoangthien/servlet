<%-- 
    Document   : add-tacgia
    Created on : Mar 17, 2024, 2:35:19 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm tác giả</title>
    </head>
    <body>
        <h1>Thêm tác giả</h1>
        <form action="/qlthuvien2/admin/addTacGia" method="post" enctype="multipart/form-data" charset="UTF-8">            
            <label for="tensach">Tên tác giả:</label> 
            <input type="text" name="tentg" id="tentg" placeholder="Nhập tên tác giả" required ><br>
            <br>
            <input type="submit" value="Thêm tác giả">
        </form>
    </body>
</html>
