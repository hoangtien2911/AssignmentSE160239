<%-- 
    Document   : changeProfile
    Created on : Feb 20, 2023, 12:08:01 AM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Chinn Clothing</title>
        <!-- Fontawesome cdn -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
              integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />

        <!-- Bootstrap css -->
        <link rel="stylesheet" href="./bootstrap-5.0.2-dist/css/bootstrap.min.css" />

        <!-- Custom css -->
        <link rel="stylesheet" href="./css/main.css" />
    </head>

    <body>
        <c:set var="account" value="${sessionScope.ACCOUNT_USER}"/>        
        <!-- Navbar -->
        <%@include file="header.jsp" %>
        <!-- End of navbar -->
        <c:set var="errors" value="${requestScope.INSERT_ERRORS}"/>
        <div class="container mb-5">
            <div class="row d-flex justify-content-center pt-3">
                <div class="col-md-10 mt-5 pt-5">
                    <div class="row shadow bg-body rounded mt-5">
                        <div class="col-sm-4 bg-info rounded-start">
                            <div class="card-body text-center text-white">
                                <i class="fas fa-user-tie fa-7x mt-5"></i>
                                <h5 class="fw-bold mt-4">${account.fullname}</h5>                            
                                <i class="far fa-edit fa-2x mb-4"></i>
                            </div>
                        </div>
                        <div class="col-sm-8 bg-white rounded-end">
                            <h3 class="mt-3 text-center">Information</h3>
                            <hr class="bg mt-0">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="fw-bold">Email:</div>
                                    <h6 class="text-muted">${account.email}</h6>                                
                                </div>                            
                            </div>

                            <form action="DispatchController" method="POST">
                                <div class="row mb-3">
                                    <div class="col-sm-6">
                                        <div class="fw-bold">Fullname:</div>
                                        <c:if test="${empty errors}">
                                            <input name="txtNewFullName" type="text" class="text-muted" value="${account.fullname}">
                                        </c:if>                             
                                        <c:if test="${not empty errors}">
                                            <input name="txtNewFullName" type="text" class="text-muted" value="${param.txtNewFullName}">
                                        </c:if> 
                                        <c:if test="${not empty errors.fullNameLengthErr}">                                        
                                            <div class="error-text p-0">
                                                ${errors.fullNameLengthErr}                                                
                                            </div>                                                                                            
                                        </c:if>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="fw-bold">Phone:</div>
                                        <c:if test="${empty errors}">
                                            <input name="txtNewPhone" type="text" class="text-muted" value="${account.phone}">
                                        </c:if>  
                                        <c:if test="${not empty errors}">
                                            <input name="txtNewPhone" type="text" class="text-muted" value="${param.txtNewPhone}">
                                        </c:if>                               
                                        <c:if test="${not empty errors.phoneLengthErr}">                                        
                                            <div class="error-text p-0">
                                                ${errors.phoneLengthErr}                                                
                                            </div>                                                                                            
                                        </c:if>
                                    </div>

                                </div>
                                <div class="row mb-3">
                                    <div class="col-sm-6">
                                        <div class="fw-bold">Old password:</div>
                                        <input name="txtOldPassword" type="password" class="text-muted" value="">                                  
                                        <c:if test="${not empty errors.passwordOldNotMatch}">
                                            <div class="error-text p-0">
                                                ${errors.passwordOldNotMatch}                                                
                                            </div>                                                    
                                        </c:if>
                                    </div>                                    
                                </div>

                                <div class="row mb-3">
                                    <div class="col-sm-6">
                                        <div class="fw-bold">New password:</div>
                                        <input name="txtNewPassword" type="password" class="text-muted" value="">                                         
                                        <c:if test="${not empty errors.passwordLengthErr}">                                                                                  
                                            <div class="error-text p-0">
                                                ${errors.passwordLengthErr}                                                
                                            </div>                                                                                            
                                        </c:if>
                                    </div>                 

                                    <div class="col-sm-6">
                                        <div class="fw-bold">Confirm new password:</div>
                                        <input name="txtConfirmPassword" type="password" class="text-muted" value="">                                  
                                        <c:if test="${not empty errors.confirmNotMatch}">
                                            <div class="error-text p-0">
                                                ${errors.confirmNotMatch}                                                
                                            </div>                                                                                             
                                        </c:if>  
                                    </div>

                                </div>                        

                                <div class="d-flex justify-content-start my-4">
                                    <button name="btAction" value="ChangeInformation" type="submit" class="btn-second fs-6 w-auto">Change information</button>
                                </div>  
                            </form>

                        </div>                    
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <%@include file="footer.jsp" %>

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
