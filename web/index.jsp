<%-- 
    Document   : index
    Created on : Mar 17, 2024, 5:26:24 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    </head>
    <body class="container-sm">
        <ul class="nav nav-pills mb-3 nav-justified " id="pills-tab" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active " id="pills-login-tab" data-bs-toggle="pill" data-bs-target="#pills-login" type="button" role="tab" aria-controls="pills-login" aria-selected="true">Login</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="pills-register-tab" data-bs-toggle="pill" data-bs-target="#pills-register" type="button" role="tab" aria-controls="pills-register" aria-selected="false">Register</button>
            </li>
        </ul>
        <div class="tab-content" id="pills-tabContent">
            <div class="tab-pane fade show active text-center container" id="pills-login" role="tabpanel" aria-labelledby="pills-login-tab">
                <%@include file="/login.jsp" %>
                <%--<jsp:include page="/login"></jsp:include>--%>
            </div>
            <div class="tab-pane fade text-center" id="pills-register" role="tabpanel" aria-labelledby="pills-register-tab">
                <%@include file="/register.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
