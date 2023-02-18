<%-- 
    Document   : cartDetail
    Created on : Feb 15, 2023, 11:09:11 AM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
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


    <!-- Cart -->

    <section id="cart-container"  class="pb-5" style="padding-top: 140px;">
        <div class="container">
            <div class="title text-center pb-4">
                <h2 class="position-relative d-inline-block">Shopping Cart</h2>
            </div>
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.items}"/>
                <c:if test="${not empty items}">
                    <table>
                        <thead class="h6 text-center">
                            <tr>
                                <td>No.</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Product</td>
                                <!--<td style="border-left: 1px solid #fff;">Size</td>-->
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Quantity</td>
                                <td>Subtotal</td>                        
                            </tr>
                        </thead>

                        <tbody class="text-center">                            
                            <c:set var="totalPriceAllProduct" value="0"/>                            
                            <c:forEach var="item" items="${items}" varStatus="counter" >
                                <c:set var="dto" value="${item.key}"/>
                                <c:set var="quantity" value="${item.value}"/>
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        <div class="d-flex justify-content-center justify-content-md-start align-items-center flex-wrap">
                                            <c:url var="clothingDetailLink" value="DispatchController">
                                                <c:param name="btAction" value="ClothingDetail"/>
                                                <c:param name="productId" value="${dto.id}"/>
                                            </c:url>
                                            <a href="${clothingDetailLink}">
                                                <img class="img-fluid" src="./${dto.imgPath}" alt="">
                                            </a>
                                            <div class="text-md-start">
                                                <a href="${clothingDetailLink}" class="text-decoration-none">
                                                    <p class="text-capitalize text-capitalize text-md-start my-1">${dto.name}</p>
                                                    <small class="text-muted">Price:
                                                        <span class="fw-bold">$${dto.price}</span>
                                                    </small>
                                                </a>
                                                
                                                <c:url var="linkDeleteProductCart" value="DispatchController">
                                                    <c:param name="btAction" value="CartRemoveItem"/>
                                                    <c:param name="idProduct" value="${dto.id}"/>
                                                </c:url>
                                                <a href="${linkDeleteProductCart}" class="btn btn-search" title="Delete clothing">
                                                    <i class="fas fa-trash"></i>
                                                </a>
                                            </div>                                
                                        </div>                            
                                    </td>

                                    <!--<td>
                                            <select name="" id="">
                                                <option>Size</option>
                                                <option>S</option>
                                                <option>M</option>
                                                <option>XL</option>
                                                <option>XXL</option>
                                            </select>
                                        </td>-->

                                    <td>
                                        <c:url var="linkUpdateProductCart" value="DispatchController">
                                            <c:param name="btAction" value="CartUpdateItem"/>                                            
                                        </c:url>
                                        <form action="DispatchController">
                                            <input name="newQuantity" value="${quantity}" type="number" min="1">
                                            <input type="hidden" name="idProduct" value="${dto.id}" />
                                            <button type="submit" name="btAction" value="CartUpdateItem" class="btn btn-search fs-5" title="Update quantity">
                                                <i class="fa-sharp fa-solid fa-circle-up"></i>
                                            </button>                                            
                                        </form>                                                                                        
                                        
                                    </td>
                                    <td>
                                        <c:set var="totalPriceEachProduct" value="${dto.price * quantity}"/>
                                        $${totalPriceEachProduct}

                                    </td>                                    
                                    <c:set var="totalPriceAllProduct" value="${totalPriceAllProduct + totalPriceEachProduct}"/>
                                    
                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>
                    <div class="total-price mt-4 d-flex justify-content-end">
                        <table>
                            <!--<tr>                        
                                    <td>
                                        Subtotal:
                                    </td>
                                    <td>
                                        <span class="text-muted fw-bold">$45.30</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Ship:
                                    </td>
                                    <td>
                                        <span class="text-muted fw-bold">$3</span>
                                    </td>
                                </tr>-->
                            <tr>
                                <td>
                                    Total:
                                </td>
                                <td>
                                    <span class="fw-bold">$${totalPriceAllProduct}</span>
                                </td>
                            </tr>
                        </table>                          
                    </div>

                    <div class="d-flex justify-content-end">
                        <form action="DispatchController">
                            <button type="submit" name="btAction" value="CheckOut" class="btn mt-2">CHECK OUT</button>
                        </form>
                        
                    </div>
                </c:if>
                
            </c:if>
            <c:if test="${empty cart or sessionScope.CART_SIZE eq 0}">
                <div class="d-flex justify-content-center align-items-center">
                    <h3 class="text-primary" style="margin: 180px 0">
                        No have product in your cart !!!
                    </h3>     
                </div>
            </c:if>

        </div>

    </section>    

    

    <!-- Footer -->
    <%@include file="footer.jsp" %>
    <!-- End of Footer -->


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