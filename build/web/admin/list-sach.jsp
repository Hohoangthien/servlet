<%-- 
    Document   : list-sach
    Created on : Mar 6, 2024, 7:27:36 PM
    Author     : admin
--%>

<%@page import="Service.SachService, Model.Sach, java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String searchtoken = request.getParameter("search");
    List<Sach> sachs = null;
    if(searchtoken == null){
        sachs = SachService.getAllSach();
    }
    else {
        sachs = SachService.Search(searchtoken);
    }
    // Số lượng người dùng trên mỗi trang
    int sachsPerPage = 5;

    // Tổng số trang
    int totalPages = (int) Math.ceil((double) sachs.size() / sachsPerPage);

    // Xác định trang hiện tại từ tham số trên URL (nếu không có sẽ là trang đầu tiên)
    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;

    // Xác định vị trí bắt đầu của danh sách người dùng trên trang hiện tại
    int startIndex = (currentPage - 1) * sachsPerPage;

    // Xác định vị trí kết thúc của danh sách người dùng trên trang hiện tại
    int endIndex = Math.min(startIndex + sachsPerPage, sachs.size());

    // Lấy danh sách người dùng cho trang hiện tại
    List<Sach> currentPageSachs = sachs.subList(startIndex, endIndex);

    // Xác định trang tiếp theo và trang trước
    int nextPage = (currentPage < totalPages) ? currentPage + 1 : totalPages;
    int prevPage = (currentPage > 1) ? currentPage - 1 : 1;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Page</title>
    </head>
    <body>
        <%@include file="/layout/admin-header.jsp" %>

        <form action="./list-sach.jsp">
            <label for="search">Tìm tên sách: </label>
            <input type="search" id="search" name="search">
            <input type="submit" value="Tìm">
        </form>
        <%@include file="/admin/add-sach.jsp" %>

        <h1>Danh Sách Sách - Page <%= currentPage %> of <%= totalPages %></h1>
        <div>
            <a href="<%=request.getRequestURI()%>?page=<%=prevPage%>">Previous</a>
            <% for (int i = 1; i <= totalPages; i++) { %>
            <a href="<%=request.getRequestURI()%>?page=<%=i%>"><%=i%></a>
            <% } %>
            <a href="<%=request.getRequestURI()%>?page=<%=nextPage%>">Next</a>
        </div>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Tên sách</th>
                <th>Tác giả</th> 
                <th>Ảnh</th>
                <th>Giá tiền</th>
                <th>Xoá</th>
                <th>Đã xoá</th>
            </tr>
            <%
                for (Sach sach : currentPageSachs) {
            %>
            <tr>
                <%int masach = sach.getMaSach(); %>
                <%boolean daxoa = SachService.isDeletedSach(masach); %>

                <td ><%= masach%></td>
                <td><%= sach.getTenSach() %></td>
                <td><%= sach.getTg().getTenTacGia() %></td>
                <td> <img src="<%=request.getContextPath()%>/img/sach/<%= masach%>.jpg" width="100px" height="100px" alt="/img/sach/<%= masach%>.jpg"/></td>
                <td><%= sach.getGiatien() %></td>
                <td> 
                    <a href="<%=request.getContextPath()%>/admin/deleteSach?id=<%=masach%>" <%if(daxoa) out.print("hidden");%> >Xoá</a>
                </td>
                <td> 
                    <a href="<%=request.getContextPath()%>/admin/restoreSach?id=<%=masach%>"  <%if(!daxoa) out.print("hidden");%>>Thêm lại</a> 
                </td>
            </tr>
            <% } %>
        </table>

    </body>
</html>
