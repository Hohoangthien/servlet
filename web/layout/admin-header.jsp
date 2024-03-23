<%-- 
    Document   : admin-header
    Created on : Mar 11, 2024, 7:21:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    </head>
    <body>
        <header class="bg-body-tertiary">
            <div class="p-3" style="background-color: rgba(0, 0, 0, 0.05);">
                J2EE WEB PAGE
            </div>
            <!-- Copyright -->
            <a href="../logout"> Log out</a> <br>
            <a href="<%=request.getContextPath()%>/admin/home">Admin main page</a>
            
        </header>   

    </body>
</html>
