<%-- 
    Document   : clothingDetail
    Created on : Feb 13, 2023, 1:53:21 AM
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
    <title>Chinn Clothing</title>
    <!-- Fontawesome cdn -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Bootstrap css -->
    <link rel="stylesheet" href="./bootstrap-5.0.2-dist/css/bootstrap.min.css">

    <!-- Custom css -->
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
    <!-- Navbar -->
    <%@include file="header.jsp" %>    
    <!-- End of navbar -->
    
    <!-- Product details -->
    <section class="container product my-5 pt-5">
        <c:set var="dto" value="${requestScope.CLOTHING_DETAIL}"/>
        <c:if test="${not empty dto}">
            <div class="row mt-5">
                <div class="col-lg-5 col-md-12 col-12">                
                    <img class="img-fluid w-100 h-75 pb-1" id="main-img-product" src="./${dto.imgPath}" alt="">
                    <%--
                    <div class="d-flex justify-content-between">
                        <div class="small-img-col">
                            <img width="100%" class="small-img w-100" src="./${dto.imgPath}" alt="">
                        </div>
                        <div class="small-img-col">
                            <img width="100%" class="small-img w-100" src="./${dto.imgPath}" alt="">
                        </div>
                        <div class="small-img-col">
                            <img width="100%" class="small-img w-100" src="./${dto.imgPath}" alt="">
                        </div>
                        <div class="small-img-col">
                            <img width="100%" class="small-img w-100" src="./${dto.imgPath}" alt="">
                        </div>
                    </div>
                    --%>
                </div>

                <div class="col-lg-6 col-md-12 col-12">
                    <h4 class="mt-md-4">Home /</h4>
                    <h3 class="pb-4">${dto.name}</h3>
                    <h2>$${dto.price}</h2>

                    <!--Form for add to cart-->
                    <form action="DispatchController">
                        <!--<select class="my-3" name="">
                                <option>Select Size</option>
                                <option>S</option>
                                <option>M</option>
                                <option>L</option>                    
                                <option>XL</option>                    
                            </select>                  -->
                        <input type="hidden" name="clothingID" value="${dto.id}" />
                        <input type="number" name="quantity" value="1" min="1">
                        <button type="submit" name="btAction" value="AddToCart" class="btn">Add To Cart</button>                
                    </form>
                    <!-- End Form for add to cart-->
                    <%--
                    <c:url var="linkAddToCart" value="DispatchController">
                        <c:param name="btAction" value="AddToCart"/>
                        <c:param name="clothingID" value="${dto.id}"/>
                    </c:url>
                    --%>

                    <h4 class="mt-4 mb-2">Details</h4>
                    <span>
                        ${dto.description}
                    </span>
                </div>
            </div>
        </c:if>
        <c:if test="${empty dto}">
            <div class="d-flex justify-content-center align-items-center">
                <h3 class="text-primary" style="margin: 250px 0">
                    No have this product with id ${param.productId}!!!
                </h3>     
            </div>
            
        </c:if>
    </section>    
    <!-- End product details -->

    <!-- Footer -->
    <%@include file="footer.jsp" %>
    <!--End of footer-->

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