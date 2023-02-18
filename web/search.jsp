<%-- 
    Document   : search
    Created on : Feb 12, 2023, 5:36:07 PM
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

    <!-- Collection -->
    <section id="collection" class="py-5">
        <div class="container py-5">
            <div class="title text-center py-5">
                <h2 class="position-relative d-inline-block">Search Result</h2>
            </div>

            <div class="row g-0">                
                <c:set var="listSearchValue" value="${requestScope.LIST_SEARCH_RESULT}"/>
                <div class="collection-list mt-4 row gx-0 gy-3">                                        
                    <c:if test="${not empty listSearchValue}">
                        <c:forEach var="dto" items="${listSearchValue}">
                            <div class="col-md-6 col-lg-4 col-xl-3 p-2 ${dto.cateId}">
                                <div class="collection-img position-relative overflow-hidden">
                                    <a href="product.html"><img src="./${dto.imgPath}" alt=""  class="w-100"></a>
                                    <span class="position-absolute bg-primary text-white d-flex align-items-center justify-content-center">sale</span>
                                </div>
                                <div class="text-center">
                                    <div class="rating mt-3">
                                        <span class="text-primary"><i class="fas fa-star"></i></span>
                                        <span class="text-primary"><i class="fas fa-star"></i></span>
                                        <span class="text-primary"><i class="fas fa-star"></i></span>
                                        <span class="text-primary"><i class="fas fa-star"></i></span>
                                        <span class="text-primary"><i class="fas fa-star"></i></span>
                                    </div>
                                    <p class="text-capitalize my-1">${dto.name}</p>
                                    <span class="fw-bold d-block">$ ${dto.price}</span>
                                    <c:url var="clothingDetailLink" value="DispatchController">
                                        <c:param name="btAction" value="ClothingDetail"/>
                                        <c:param name="productId" value="${dto.id}"/>
                                    </c:url>
                                    <a href="${clothingDetailLink}" class="btn btn-primary mt-3">Buy Now</a>
                                </div>
                            </div>
                        </c:forEach>                           
                    </c:if>                                
                    
                    <c:if test="${empty listSearchValue}">
                        <span class="text-primary text-center h3 fw-bold ms-2">
                            No exact matches found !!!
                        </span>
                    </c:if>
                    
                </div>
            </div>
        </div>
    </section>
    <!-- End of collection -->

    <!-- Another products -->
    <section id="special" class="py-5">
        <div class="container">
            <div class="title text-center pb-5">
                <h2 class="position-relative d-inline-block">Another Selection</h2>
            </div>

            <div class="special-list row g-0">
                <c:set var="listSpecial" value="${requestScope.LIST_SPECIAL}"/>
                <c:if test="${not empty listSpecial}">
                    <c:forEach var="dto" items="${listSpecial}">
                        <div class="col-md-6 col-lg-4 col-xl-3 p-2">
                            <div class="special-img position-relative overflow-hidden">
                                <img src="${dto.imgPath}" alt="" class="w-100">
                                <span class="position-absolute d-flex align-items-center justify-content-center text-primary fs-4">
                                    <i class="fas fa-heart"></i>
                                </span>
                            </div>

                            <div class="text-center">
                                <p class="text-capitalize mt-3 mb-1">${dto.name}</p>
                                <span class="fw-bold d-block">$ ${dto.price}</span>
                                <c:url var="clothingDetailLink" value="DispatchController">
                                    <c:param name="btAction" value="ClothingDetail"/>
                                    <c:param name="productId" value="${dto.id}"/>
                                </c:url>
                                <a href="${clothingDetailLink}" class="btn btn-primary mt-3">Buy Now</a>
                            </div>
                        </div>  
                    </c:forEach>
                </c:if>                                             
            </div>
                           
        </div>
    </section>

    <!-- End special products -->  

    <!-- Footer -->
    <%@include file="footer.jsp" %>    
    <!-- End of footer -->

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