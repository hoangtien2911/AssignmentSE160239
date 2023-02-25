<%-- 
    Document   : login
    Created on : Feb 12, 2023, 2:09:26 AM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <!-- Fontawesome cdn -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Bootstrap css -->
    <link rel="stylesheet" href="./bootstrap-5.0.2-dist/css/bootstrap.min.css">

    <!-- Custom css -->
    <link rel="stylesheet" href="./css/main.css">
</head>

<body style="background-color: #1F8A70;">
    <section class="vh-100">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-xl-10">
                    <div class="card" style="border-radius: 1rem;">
                        <div class="row g-0">
                            <div class="col-md-6 col-lg-5 d-none d-md-flex align-items-center">
                                <img src="./images/background.png" alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem;" />
                            </div>
                            <div class="col-md-6 col-lg-7 d-flex align-items-center">
                                <div class="card-body p-4 p-lg-5 text-black">
                                    <div class="login-brand d-flex justify-content-center align-items-center mb-3 pb-1">
                                        <a href="index.html" class="text-dark text-decoration-none"><span class="h3 text-uppercase fw-lighter">Chinn Clothings</span></a>
                                    </div>   
                                    <form action="DispatchController" method="POST">                                                                                                                                                            
                                        <c:set var="error" value="${requestScope.ERROR_MSG}"/>
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input name="txtEmail" value="" type="text" placeholder="Email" class="form-control" />
                                            </div>
                                        </div>
                                        
                                        <div class="d-flex flex-row align-items-center mb-4">
                                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                            <div class="form-outline flex-fill mb-0">
                                                <input name="txtPassword" value="" type="password" placeholder="Password" class="form-control" />
                                            </div>
                                        </div>
                                        <c:if test="${not empty error}">
                                            <div class="d-flex flex-row justify-content-center mb-3">                                                
                                                <div class="error-text">
                                                    ${error}                                                
                                                </div>                                                    
                                            </div>                                                                                            
                                        </c:if>
                                        
                                        <div class="form-check d-flex justify-content-center mb-3">
                                            <input class="form-check-input me-2" type="checkbox" name="chkRemember" value="ON"/>
                                            <label class="form-check-label">
                                                Remember password.
                                            </label>
                                        </div>
                                        <div class="pt-1 mb-3 d-flex justify-content-center">
                                            <button class="btn btn-dark btn-lg btn-block" type="submit" name="btAction" value="Login">Login</button>                                            
                                        </div>
                                                                                
                                    </form>
                                    <a class="text-muted" href="#!">Forgot password?</a>
                                    <div class="text-center my-4">
                                        <a type="button" class="btn" href="registration.html">Register here</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    
    <!-- Jquery -->
    <script src="./js/jquery-3.6.3.js"></script>
    <!-- Isotope -->
    <script src="./js/isotope.pkgd.min.js"></script>
    <!-- Bootstrap js -->
    <script src="./bootstrap-5.0.2-dist/js/bootstrap.min.js"></script>
    <!-- Custom js -->
    <script src="./js/script.js"></script>
</body>

</html>