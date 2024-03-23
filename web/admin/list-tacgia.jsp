<%-- 
    Document   : list-tacgia
    Created on : Mar 11, 2024, 7:31:33 PM
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Service.TacGiaService, java.util.*, Model.TacGia"%>
<%
    // Số lượng người dùng trên mỗi trang
    int tgsPerPage = 5;
    // Lấy danh sách tất cả người dùng từ TacGiaService
    ArrayList<TacGia> tgs = TacGiaService.findAllTacGia();
    // Tổng số trang
    int totalPages = (int) Math.ceil((double) tgs.size() / tgsPerPage);
    // Xác định trang hiện tại từ tham số trên URL (nếu không có sẽ là trang đầu tiên)
    int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
    // Xác định vị trí bắt đầu của danh sách người dùng trên trang hiện tại
    int startIndex = (currentPage - 1) * tgsPerPage;
    // Xác định vị trí kết thúc của danh sách người dùng trên trang hiện tại
    int endIndex = Math.min(startIndex + tgsPerPage, tgs.size());
    // Lấy danh sách người dùng cho trang hiện tại
    List<TacGia> currentPageTacGias = tgs.subList(startIndex, endIndex);
    // Xác định trang tiếp theo và trang trước
    int nextPage = (currentPage < totalPages) ? currentPage + 1 : totalPages;
    int prevPage = (currentPage > 1) ? currentPage - 1 : 1;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách tác giả</title>
    </head>
    <body>
        <%@include file="/layout/admin-header.jsp" %>
        <%@include file="/admin/add-tacgia.jsp" %>
        <h1>Danh Sách Tác giả - Page <%= currentPage %> of <%= totalPages %></h1>
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
                <th>Tác giả</th> 
            </tr>
            <%
                for (TacGia tg : currentPageTacGias) {
            %>
            <tr>
                <%int matg = tg.getMaTacGia(); %>
                <%boolean daxoa = TacGiaService.isDeletedTacGia(matg); %>

                <td ><%= matg%></td>
                <td><%= tg.getTenTacGia() %></td>
            </tr>
            <% } %>
        </table>

    </body>
</html>
