<%-- 
    Document   : user-home
    Created on : Feb 29, 2024, 2:16:00 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Service.SachService, Model.Sach, java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Số lượng người dùng trên mỗi trang
    int sachsPerPage = 10;

    // Lấy danh sách tất cả người dùng từ SachService
    List<Sach> sachs = SachService.getAllSachNoDeleted();

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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>Home</title>
    </head>
    <body>
        <%@include file="/layout/header.jsp" %>
        <div>
            <a href="<%=request.getRequestURI()%>?page=<%=prevPage%>">Previous</a>
            <% for (int i = 1; i <= totalPages; i++) { %>
            <a href="<%=request.getRequestURI()%>?page=<%=i%>"><%=i%></a>
            <% } %>
            <a href="<%=request.getRequestURI()%>?page=<%=nextPage%>">Next</a>
        </div>

        <div class="container">
            <div class="row row-cols-2 row-cols-lg-5 g-2 g-lg-3">
                <%
                for (Sach sach : currentPageSachs) {
                int masach = sach.getMaSach(); 
                %>
                <div class="col">
                    <div class="p-3 border bg-light h-100" 
                         onclick="getLocation('${pageContext.request.contextPath}/user/sach?id=<%= masach%>')">
                        <img src="<%=request.getContextPath()%>/img/sach/<%= masach%>.jpg" 
                             width="100px" height="100px" alt="/img/sach/<%= masach%>.jpg" 
                             class="hover-shadow "/>
                        <h5><%= sach.getTenSach() %></h5>
                        <h6><%= sach.getTg().getTenTacGia() %></h6>
                        <p><%= sach.getGiatien() %>đ</p>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
            <script>
                function getLocation(index){
                    location.href = index;
                } 
                
            </script>
    </body>
</html>
