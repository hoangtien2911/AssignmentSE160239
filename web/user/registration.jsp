<%-- 
    Document   : registration
    Created on : Feb 12, 2023, 3:00:20 AM
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
        <title>Register</title>       
        <!-- Fontawesome cdn -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <!-- Bootstrap css -->
        <link rel="stylesheet" href="${url}/bootstrap-5.0.2-dist/css/bootstrap.min.css">

        <!-- Custom css -->
        <link rel="stylesheet" href="${url}/css/main.css">
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
                                            <a href="${url}/user/home.jsp" class="text-dark text-decoration-none"><span class="h3 text-uppercase fw-lighter">Chinn Clothings</span></a>
                                        </div>

                                        <form action="DispatchController" method="POST">      
                                            <c:set var="errors" value="${requestScope.INSERT_ERRORS}"/>
                                            <div class="d-flex flex-row align-items-center mb-2">
                                                <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input name="txtEmail" value="${param.txtEmail}" type="email" placeholder="Your Email" class="form-control" />                                                
                                                </div>
                                            </div>
                                            <c:if test="${not empty errors.emailMatchErr}">
                                                <div class="d-flex flex-row justify-content-start mb-2">                                                
                                                    <div class="error-text">
                                                        ${errors.emailMatchErr}                                                
                                                    </div>                                                    
                                                </div>    
                                            </c:if>
                                            <c:if test="${not empty errors.emailIsExisted}">
                                                <div class="d-flex flex-row justify-content-start mb-2">
                                                    <div class="error-text">
                                                        ${errors.emailIsExisted}                                                
                                                    </div>                                                    
                                                </div>    
                                            </c:if>    


                                            <div class="d-flex flex-row align-items-center mb-2">
                                                <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input name="txtPassword" value="" type="password" placeholder="Password" class="form-control" />                                                
                                                </div>
                                            </div>
                                            <c:if test="${not empty errors.passwordLengthErr}">
                                                <div class="d-flex flex-row justify-content-start mb-2">                                             
                                                    <div class="error-text">
                                                        ${errors.passwordLengthErr}                                                
                                                    </div>                                                    
                                                </div>    
                                            </c:if>       


                                            <div class="d-flex flex-row align-items-center mb-2">
                                                <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input name="txtConfirm" value="" type="password" placeholder="Confirm your password" class="form-control" />                                                
                                                </div>
                                            </div>
                                            <c:if test="${not empty errors.confirmNotMatch}">
                                                <div class="d-flex flex-row justify-content-start mb-2">                                                
                                                    <div class="error-text">
                                                        ${errors.confirmNotMatch}                                                
                                                    </div>                                                    
                                                </div>    
                                            </c:if>      


                                            <div class="d-flex flex-row align-items-center mb-2">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input name="txtFullname" value="${param.txtFullname}" type="text" placeholder="Your Name" class="form-control" />                                                
                                                </div>
                                            </div>
                                            <c:if test="${not empty errors.fullNameLengthErr}">
                                                <div class="d-flex flex-row justify-content-start mb-2">                                              
                                                    <div class="error-text">
                                                        ${errors.fullNameLengthErr}                                                
                                                    </div>                                                    
                                                </div>    
                                            </c:if> 

                                            <div class="d-flex flex-row align-items-center mb-2">
                                                <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div class="form-outline flex-fill mb-0">
                                                    <input name="txtPhone" value="${param.txtPhone}" type="text" placeholder="Your Phone" class="form-control" />                                                
                                                </div>
                                            </div>
                                            <c:if test="${not empty errors.phoneLengthErr}">
                                                <div class="d-flex flex-row justify-content-start mb-3">                                              
                                                    <div class="error-text">
                                                        ${errors.phoneLengthErr}                                                
                                                    </div>                                                    
                                                </div>    
                                            </c:if>                                                 

                                            <div class="form-check d-flex justify-content-center mb-2">
                                                <input class="form-check-input me-2" type="checkbox" name="chkAgree" value="ON"/>
                                                <label class="form-check-label">
                                                    I agree all statements in <a href="#!" class="text-muted">Terms of service</a>
                                                </label>
                                            </div>  
                                            <c:if test="${not empty errors.agreeTermErr}">
                                                <div class="d-flex flex-row justify-content-center mb-4">                                                
                                                    <div class="error-text" style="padding: 0">
                                                        ${errors.agreeTermErr}                                                
                                                    </div>                                                    
                                                </div>    
                                            </c:if>        


                                            <div class="d-flex justify-content-center mx-4 mb-2 mb-lg-3">                                            
                                                <button class="btn btn-dark btn-lg btn-block" type="submit" name="btAction" value="Register">Register</button>
                                            </div>                                       
                                        </form>

                                        <p class="pb-lg-2">Do have an account?</p>

                                        <div class=" my-2 text-center">                                            
                                            <a type="button" class="btn" href="${url}/user/login.html">Login here</a>                                            
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
        <script src="${url}/js/jquery-3.6.3.js"></script>
        <!-- Isotope -->
        <script src="${url}/js/isotope.pkgd.min.js"></script>
        <!-- Bootstrap js -->
        <script src="${url}/bootstrap-5.0.2-dist/js/bootstrap.min.js"></script>
        <!-- Custom js -->
        <script src="${url}/js/script.js"></script>
    </body>

</html>