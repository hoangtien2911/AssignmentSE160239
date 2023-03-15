<%-- 
    Document   : 404
    Created on : Mar 12, 2023, 10:54:10 PM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>404</title>
        <!-- Bootstrap css -->
        <link rel="stylesheet" href="${url}/bootstrap-5.0.2-dist/css/bootstrap.min.css">

        <!-- Custom css -->
        <link rel="stylesheet" href="${url}/css/main.css">
    </head>

    <body style="background-color: #E9EDC9">
        <div class="d-flex align-items-center justify-content-center vh-100">
            <div class="text-center row">
                <div class=" col-md-6">
                    <img src="${url}/images/error404.png" alt=""
                         class="img-fluid ">
                </div>
                <div class=" col-md-6 mt-5">
                    <p class="fs-3 "> <span class="text-primary">Opps!</span> Page not found.</p>
                    <p class="lead">
                        The page you’re looking for doesn’t exist.
                    </p>
                    <a href="${url}/DispatchController" class="btn btn-primary">Go Home</a>
                </div>

            </div>
        </div>
    </body>

</html>
