<%-- 
    Document   : index
    Created on : Feb 12, 2023, 5:33:42 PM
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
    </head>
    <body>    
        <!-- Navbar -->
        <%@include file="../common/header.jsp" %>    
        <!-- End of navbar -->


        <!-- Header -->
        <header id="header" class="vh-100 carousel slide" data-bs-ride="carousel" style="padding-top: 104px;">
            <div class="container h-100 d-flex align-items-center carousel-inner">
                <div class="text-center carousel-item active">
                    <h2 class="text-capitalize text-white">Best Collection</h2>
                    <h1 class="text-uppercase py-2 fw-bold text-white">new arrivals</h1>
                    <a href="#collection" class="btn mt-3 text-uppercase">Shop now</a>
                </div>

                <div class="text-center carousel-item">
                    <h2 class="text-capitalize text-white">Best price & offer</h2>
                    <h1 class="text-uppercase py-2 fw-bold text-white">new season</h1>
                    <a href="#collection" class="btn mt-3 text-uppercase">Buy now</a>
                </div>            
            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#header" data-bs-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </button>

            <button class="carousel-control-next" type="button" data-bs-target="#header" data-bs-slide="next">
                <span class="carousel-control-next-icon"></span>
            </button>
        </header>
        <!-- End of header -->

        <!-- Collection -->
        <section id="collection" class="py-5">
            <div class="container">
                <div class="title text-center">
                    <h2 class="position-relative d-inline-block">New Collection</h2>
                </div>

                <div class="row g-0">
                    <div class="d-flex flex-wrap justify-content-center mt-5 filter-button-group">
                        <button type="button" class="btn m-2 text-dark active-filter-btn" data-filter = "*">All</button>
                        <button type="button" class="btn m-2 text-dark" data-filter = ".1">New Blouse</button>
                        <button type="button" class="btn m-2 text-dark" data-filter = ".2">New Shirt</button>
                        <button type="button" class="btn m-2 text-dark" data-filter = ".3">New Dress</button>
                    </div>

                    <div class="collection-list mt-4 row gx-0 gy-3">

                        <c:set var="listClothes" value="${requestScope.LIST_CLOTHES}"/>                    
                        <c:if test="${not empty listClothes}">
                            <c:forEach var="dto" items="${listClothes}">
                                <div class="col-md-6 col-lg-4 col-xl-3 p-2 ${dto.cateId}">
                                    <div class="collection-img position-relative overflow-hidden">                                    
                                        <img src="./${dto.imgPath}" alt=""  class="w-100">
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

                    </div>
                </div>
                <div class="d-flex justify-content-center mt-3">
                    <c:set var="numberPage" value="${requestScope.NUMBER_PAGE}"/>
                    <c:set var="numberPageShow" value="${3}"/>
                    <c:if test="${numberPage le 3}">
                        <c:set var="numberPageShow" value="${numberPage}"/>
                    </c:if>
                    <c:forEach begin="${1}" end="${numberPageShow}" varStatus="counter">
                        <c:url var="changePage" value="DispatchController">
                            <c:param name="pageNumber" value="${counter.index}"/>
                        </c:url>
                        <div class="text-center me-3">                            
                            <button class="btn btn-primary mt-3 fs-6" style="border-radius: 0; width: 40px; height: 40px; padding: 0;">
                                <a href="${changePage}" class="py-2 px-3">${counter.index}</a>                                
                            </button>
                        </div>
                    </c:forEach>
                    <c:if test="${numberPage gt 3}">
                        <c:forEach begin="${4}" end="${numberPage}" varStatus="counter" step="1">
                            <c:url var="changePage" value="DispatchController">
                                <c:param name="pageNumber" value="${counter.index}"/>
                            </c:url>
                            <div class="text-center me-3 more-page" id="" style="display: none;">                                                        
                                <button class="btn btn-primary mt-3 fs-6" style="border-radius: 0; width: 40px; height: 40px; padding: 0;">
                                    <a href="${changePage}" class="py-2 px-3">${counter.index}</a>
                                </button>
                            </div>
                        </c:forEach>
                    </c:if>
                    <div class="text-center">                            
                        <button href="#" class="btn btn-primary mt-3 fs-6" style="border-radius: 0; width: 40px; height: 40px; padding: 0;" onclick="showMorePage()">
                            <i class="fa-solid fa-arrow-right"></i>
                        </button>
                    </div>
                </div>
            </div>
        </section>
        <!-- End of collection -->

        <!-- Special products -->
        <section id="special" class="py-5">
            <div class="container">
                <div class="title text-center pb-5">
                    <h2 class="position-relative d-inline-block">Special Selection</h2>
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


        <!-- Offer -->
        <section id="offers" class="py-5">
            <div class="container">
                <div class="row d-flex align-items-center justify-content-center text-center justify-content-lg-start text-lg-start">
                    <div class="offer-content">
                        <span class="text-white">Discount Up To 20%</span>
                        <h2 class="mt-2 mb-4 text-white">Grand Sale Offers!</h2>
                        <a href="#collection" class="btn">Buy Now</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- End of Offers -->    

        <!-- About us -->
        <section id="about" class="py-5">
            <div class="container">
                <div class="row gy-lg-5 align-items-center">
                    <div class="col-lg-6 order-lg-1 text-center text-lg-start">
                        <div class="title pt-3 pb-5">
                            <h2 class="position-relative d-inline-block ms-4">
                                About us
                            </h2>                        
                        </div>
                        <p class="lead text-muted">
                            🍂 CHINN CLOTHINGS 🍂 71 Nguyễn Đức Cảnh - 094. 162. 46. 46.
                        </p>
                        <p> 
                            Refresh your daily rotation with our women’s clothing range.
                            With the freshest styles available all in one place, you can expect everyday basics,
                            like women's tops and skirts, as well as must-have knitwear and cozy loungewear for downtime days.
                            Plans to go out? Our women's dresses line up mini, midi and maxi styles that were made for summer 
                            evenings, while our stylish jeans and pants offer something to flatter every silhouette.
                        </p>
                    </div>

                    <div class="col-lg-6 order-lg-0">
                        <img src="./images/about_us.jpg" alt="" class="img-fluid">
                    </div>
                </div>
            </div>
        </section>
        <!-- End of about us -->

        <!-- Footer -->
        <%@include file="../common/footer.jsp" %>    
        <!-- End of footer -->
        <script>
            function showMorePage() {
                const more = document.getElementsByClassName('more-page');
                for (let i = 0; i < more.length; i++) {
                    if (more[i].style.display === 'none') {
                        more[i].style.display = 'block';
                    } else {
                        more[i].style.display = 'none';
                    }
                }
            }
        </script>
        <c:set var="msg" value="${requestScope.MSG}"/>
        <c:if test="${not empty msg}">
            <script>
                alert("${msg}");
            </script>
        </c:if>    
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