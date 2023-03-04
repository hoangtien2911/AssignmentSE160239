<%-- 
    Document   : headerAdmin
    Created on : Feb 26, 2023, 2:09:07 PM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chinn Clothing</title>
        <!-- Fontawesome cdn -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <!-- Bootstrap css -->
        <link rel="stylesheet" href="${url}/bootstrap-5.0.2-dist/css/bootstrap.min.css">

        <!-- Custom css -->
        <link rel="stylesheet" href="${url}/css/main.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-white py-3 fixed-top">
            <div class="container-fluid mx-lg-5">
                <a class="navbar-brand d-flex justify-content-between align-items-center order-lg-0" href="${url}/AdminController">                    
                    <span class="text-uppercase fw-lighter ms-2">
                        &nbsp;&nbsp;&nbsp;&nbsp;Chinn <br/> CLothings
                    </span>
                </a>            
                <div class="order-lg-2 nav-btns">
                    <ul class="list-unstyled m-0 d-flex">                                                
                        <c:set var="accountAdmin" value="${sessionScope.ACCOUNT_ADMIN}"/>                    
                        <!--when not logged in-->
                        <c:if test="${empty accountAdmin}">
                            <li>
                                <button type = "button" class = "btn position-relative mx-2">
                                    <a href="${url}/user/login.html" class="text-dark"><i class="fa-solid fa-user"></i></a>                                     
                                </button> 
                            </li> 
                        </c:if>
                        <!--when logged in-->
                        <c:if test="${not empty accountAdmin}">
                            <c:set var="username" value="${sessionScope.USERNAME}"/>
                            <li class="nav-user mx-2 d-flex justify-item-center position-relative">
                                <!--<img src="./images/user.jpg" alt="" class="user-img m-2">-->
                                <span class="user-name my-2">${username}</span>
                                <ul class="user-menu-admin list-unstyled">                                                                       
                                    <!--Logout-->
                                    <li class="user-menu-item ">
                                        <c:url var="linkLogout" value="DispatchController">
                                            <c:param name="btAction" value="Logout"/>
                                        </c:url>
                                        <a href="${url}/${linkLogout}" class="text-decoration-none text-dark">Logout</a>                                        
                                    </li>    
                                </ul>
                            </li>
                        </c:if>                                        

                    </ul>                                                                               
                </div>

                <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target ="#navMenu">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse order-lg-1" id="navMenu">
                    <ul class="navbar-nav mx-auto text-center">
                        <li class="nav-item px-2 py-2">
                            <a href="${url}/AdminController" class="nav-link text-uppercase text-dark">Manage Account</a>
                        </li>
                        <li class="nav-item px-2 py-2">
                            <a href="${url}/AdminController?btAction=ViewOrders" class="nav-link text-uppercase text-dark">View Order</a>
                        </li>
                        <li class="nav-item px-2 py-2">
                            <a href="${url}/AdminController?btAction=ViewClothes" class="nav-link text-uppercase text-dark">Manage Clothes</a>
                        </li>
                        <li class="nav-item px-2 py-2">
                            <a href="${url}/AdminController?btAction=ViewCategories" class="nav-link text-uppercase text-dark">Manage Categories</a>
                        </li>

                    </ul>
                </div>
            </div>        
        </nav>

        <!-- End of navbar -->  

        <!-- Jquery -->
        <script src="${url}/js/jquery-3.6.3.js"></script>
        <!-- Isotope -->
        <script src="${url}/js/isotope.pkgd.min.js"></script>
        <!-- Bootstrap js -->
        <script src="${url}/bootstrap-5.0.2-dist/js/bootstrap.min.js"></script>
        <!-- Custom js -->
        <script src="${url}/js/script.js"></script>

    </body>
</html>
