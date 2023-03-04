<%-- 
    Document   : navbar
    Created on : Feb 15, 2023, 12:26:28 AM
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
                <a class="navbar-brand d-flex justify-content-between align-items-center order-lg-0" href="${url}/DispatchController">
                    
                    <span class="text-uppercase fw-lighter ms-2">
                        &nbsp;&nbsp;&nbsp;&nbsp;Chinn <br/> CLothings
                    </span>
                </a>            
                <div class="order-lg-2 nav-btns">
                    <ul class="list-unstyled m-0 d-flex">
                        <li>
                            <button type = "button" class = "btn position-relative mx-2">                            
                                <a href="${url}/user/cartDetail.jsp" class="text-dark"><i class = "fa fa-shopping-cart"></i></a>
                                    <c:set var="cartSize" value="${sessionScope.CART_SIZE}"/>
                                    <c:if test="${cartSize gt 0}">
                                    <span class = "position-absolute top-0 start-100 translate-middle badge bg-primary">${cartSize}</span>
                                </c:if>                            
                            </button>
                        </li>
                        <li>
                            <button type = "button" class = "btn position-relative mx-2">
                                <i class = "fa fa-heart"></i>
                                <span class = "position-absolute top-0 start-100 translate-middle badge bg-primary">2</span>
                            </button>
                        </li>


                        <c:set var="accountUser" value="${sessionScope.ACCOUNT_USER}"/>                    
                        <!--when not logged in-->
                        <c:if test="${empty accountUser}">
                            <li>
                                <button type = "button" class = "btn position-relative mx-2">
                                    <a href="${url}/user/login.html" class="text-dark"><i class="fa-solid fa-user"></i></a>                                     
                                </button> 
                            </li> 
                        </c:if>
                        <!--when logged in-->
                        <c:if test="${not empty accountUser}">
                            <c:set var="username" value="${sessionScope.USERNAME}"/>
                            <li class="nav-user mx-2 d-flex justify-item-center position-relative">
                                <!--<img src="./images/user.jpg" alt="" class="user-img m-2">-->
                                <span class="user-name my-2">${username}</span>
                                <ul class="user-menu list-unstyled">
                                    <li class="user-menu-item">
                                        <a href="${url}/user/changeProfile.jsp" class="text-decoration-none text-dark">My Account</a>
                                    </li>
                                    <li class="user-menu-item">
                                        <c:url var="linkOrderHistory" value="DispatchController">
                                            <c:param name="btAction" value="OrderHistory"/>
                                        </c:url>
                                        <a href="${url}/${linkOrderHistory}" class="text-decoration-none text-dark">My Purchase</a>
                                    </li>

                                    <!--Logout-->
                                    <li class="user-menu-item item-seperate">
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
                            <a href="#header" class="nav-link text-uppercase text-dark">Home</a>
                        </li>
                        <li class="nav-item px-2 py-2">
                            <a href="#collection" class="nav-link text-uppercase text-dark">Collection</a>
                        </li>
                        <li class="nav-item px-2 py-2">
                            <a href="#special" class="nav-link text-uppercase text-dark">Specials</a>
                        </li>

                        <li class="nav-item px-2 py-2">
                            <a href="#about" class="nav-link text-uppercase text-dark">About us</a>
                        </li>
                        <li class="nav-item px-2 py-2">
                            <a href="#popular" class="nav-link text-uppercase text-dark">Popular</a>
                        </li>

                        <li class="nav-item px-2 border-0" style="padding-top: 6px;">

                            <!--form search-->                                                
                            <form action="DispatchController" method="GET" class="form-inline my-2 my-lg-0" id="search-box">
                                <input name="txtSearchValue" value="${param.txtSearchValue}" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" id="search-text" required>
                                <select name="txtSearchBy" class="form-select" id="search-select">
                                    <c:set var="searchBy" value="${param.txtSearchBy}"/>
                                    <c:set var="byName" value="byname"/>
                                    <c:set var="byCategory" value="bycategory"/>
                                    <c:if test="${searchBy eq byName}">
                                        <option value="byname" selected>Name</option>
                                        <option value="bycategory">Category</option>
                                    </c:if>                                
                                    <c:if test="${searchBy eq byCategory}">
                                        <option value="byname">Name</option>
                                        <option value="bycategory" selected>Category</option>
                                    </c:if>                                                                
                                    <c:if test="${searchBy ne byName and searchBy ne byCategory}">
                                        <option value="byname" selected>Name</option>
                                        <option value="bycategory">Category</option>
                                    </c:if>                                
                                </select>
                                <button name="btAction" value="Search" type="submit" class="btn btn-search" id="search-btn"><i class="fa fa-search"></i></button>
                            </form>
                            <!--end form search-->
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