<%-- 
    Document   : popupresult
    Created on : Jan 23, 2024, 7:58:13 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
        <!--<meta http-equiv="refresh" content="1; URL=your_previous_page.jsp">-->
        <title>Registration Result</title>
    </head>

    <body>      
        <%
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("success", "Đăng kí thành công!");
        messageMap.put("existUser", "User đã tồn tại!");
        messageMap.put("notExistUser", "User không tồn tại!");
        messageMap.put("wrong", "Sai username hoặc password!");
        messageMap.put("ChangePassSuccess", "Đổi dữ liệu tài khoản thành công!");
        messageMap.put("ChangePassFail", "Sai định dạng password!");
        messageMap.put("UploadFileSuccess", "Thêm thành công");
        messageMap.put("DeletedSuccess", "Xoá sách thành công");
        messageMap.put("RestoreSuccess", "Thêm lại thành công");
        messageMap.put("Fail", "Thất bại");
        messageMap.put("Badpwd", "Mật khẩu phải có: Từ 3 đến 15 ký tự, Ít nhất một chữ số, Ít nhất một ký tự không phải chữ số hoặc khoảng trắng.");
        messageMap.put("Badusn", "Có từ 3 - 5 kí tự, Bao gồm các kí tự thường a -> z và các chữ số 0 - 9");

        %>
        <script>
            var current = "<%= request.getContextPath() %>";
            var message = "<%= request.getAttribute("message") %>";
            var next = "<%= request.getAttribute("next") %>";
            <%if(request.getAttribute("message") != null){%>
            alert("<%= messageMap.get(request.getAttribute("message"))%>");
            <%if(request.getAttribute("next")!=null) {%>
            window.location.href = current + next;
            <%}else{ %>
            window.history.back();
            <%}%>
            <%}%>
        </script>
    </body>
</html>
