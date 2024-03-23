<%-- 
    Document   : register
    Created on : Feb 25, 2024, 5:11:19 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTER</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-5 login-wrap text-center">
                    <form  method="POST" action="<%=request.getContextPath()%>/register" class="login-form">
                        <div class="form-group">
                            <input type="text" class="form-control rounded-left" placeholder="Họ và tên" name="hoTen"
                                   required>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control rounded-left" placeholder="Email" name="email" required>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control rounded-left" placeholder="Username" name="username"
                                   required>
                        </div>
                        <div class="form-group d-flex">
                            <input type="password" class="form-control rounded-left" placeholder="Password" name="password"
                                   required>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary rounded submit p-3 px-5 ">REGISTER</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
