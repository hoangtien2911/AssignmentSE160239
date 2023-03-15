<%-- 
    Document   : orderDetail
    Created on : Feb 19, 2023, 12:28:59 PM
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
        <title>Order Detail</title>        
    </head>

    <body>
        <c:set var="sessionUser" value="${sessionScope.ACCOUNT_USER}"/>
        <c:if test="${not empty sessionUser}">
            <!-- Navbar -->
            <%@include file="../common/header.jsp" %>
            <!-- End of navbar -->

            <c:set var="orderInfor" value="${requestScope.ORDER_INFOR}"/>
            <c:set var="listDetailOrder" value="${requestScope.LIST_DETAIL_ORDER}"/>
            <c:set var="listClothesOrder" value="${requestScope.LIST_CLOTHES_OF_DETAIL}"/>
            <c:set var="sizeOfListClothes" value="${requestScope.SIZE_OF_LIST}"/>

            <c:set var="processing" value="Processing"/>
            <c:set var="completed" value="Completed"/>
            <c:set var="cancel" value="Cancel"/>
            <!-- Status of order -->           
            <div class="container px-1 px-md-4 py-5 mx-auto">
                <!-- <div class="title text-center">
                    <h2 class="position-relative d-inline-block">New Collection</h2>
                </div> -->
                <div class="order">
                    <div class="row">
                        <div class="d-flex justify-content-between px-3 top">
                            <div class="d-flex">
                                <h5 class="order-id">
                                    ORDER
                                    <span class="text-primary fw-bold">#${param.orderNumberOfList}</span>
                                </h5>
                            </div>
                            <div class="d-flex flex-column text-sm-end order-detail">
                                <p class="mb-0">Expected Arrival
                                    <span class="fw-bold">${orderInfor.shipDate}</span>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="d-flex justify-content-center">
                            <div class="col-12">
                                <ul id="progressbar" class="text-center">
                                    <c:if test="${orderInfor.status eq 1 or orderInfor.status eq 3}">
                                        <c:set var="statusProcessingCancel" value="active"/>
                                    </c:if>
                                    <c:if test="${orderInfor.status eq 2}">
                                        <c:set var="statusSuccess" value="active"/>
                                    </c:if>
                                    <li class="${statusProcessingCancel} ${statusSuccess} step0"></li>
                                    <li class="${statusSuccess} step0"></li>
                                    <li class="${statusSuccess} step0"></li>
                                    <li class="${statusSuccess} step0"></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row top">
                        <div class="d-flex justify-content-between p-0">
                            <div class="row">
                                <div class="d-flex icon-content p-0">
                                    <img src="./images/CheckList.png" alt="" class="icon">
                                    <div class="d-flex flex-column">                                        
                                        <c:choose>
                                            <c:when test="${orderInfor.status eq 1}">
                                                <p class="fw-bold">Order<br>${processing}</p>
                                                </c:when>
                                                <c:when test="${orderInfor.status eq 3}">
                                                <p class="fw-bold">Order<br>${cancel}</p>
                                                </c:when> 
                                                <c:otherwise>
                                                <p class="fw-bold">Order<br>${processing}</p>
                                                </c:otherwise>
                                            </c:choose>

                                    </div>
                                </div>
                            </div>
                            <div class="row ms-4">
                                <div class="d-flex icon-content p-0">
                                    <img src="./images/Delivery.png" alt="" class="icon">
                                    <div class="d-flex flex-column">
                                        <p class="fw-bold">Order<br>Shipped</p>
                                    </div>
                                </div>
                            </div>
                            <div class="row ms-4">
                                <div class="d-flex icon-content p-0">
                                    <img src="./images/Shipping.png" alt="" class="icon">
                                    <div class="d-flex flex-column">
                                        <p class="fw-bold">Order<br>En Route</p>
                                    </div>
                                </div>
                            </div>
                            <div class="row ps-5">
                                <div class="d-flex icon-content p-0">
                                    <img src="./images/Home.png" alt="" class="icon">
                                    <div class="d-flex flex-column">
                                        <p class="fw-bold">Order<br>Arrival</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!-- End of status order -->
            <!-- Information of order -->
            <section id="cart-container" class="pb-5">
                <div class="container">
                    <div class="title text-center pb-4 my-3">
                        <h2 class="position-relative d-inline-block">Detail Your Order</h2>
                    </div>

                    <table>
                        <thead class="h6 text-center">
                            <tr>
                                <td>Product</td>                        
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Total Price</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Order Date</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Delivery Date</td>
                                <td>Status</td>
                            </tr>
                        </thead>

                        <tbody class="text-center">                            
                            <tr>                        
                                <td>
                                    <c:set var="totalPrice" value="${0}"/>
                                    <c:forEach var="index" begin="0" end="${sizeOfListClothes - 1}" step="1">
                                        <div class="cart-infor d-flex justify-content-center justify-content-md-start align-items-center flex-wrap">
                                            <img class="img-fluid" src="${listClothesOrder.get(index).imgPath}" alt="">
                                            <div class="">
                                                <p class="text-capitalize text-md-start my-1">${listClothesOrder.get(index).name}</p>
                                                <small class="text-muted d-block text-md-start mb-1">Price of 1:
                                                    <span class="fw-bold">$${listClothesOrder.get(index).price}</span>
                                                </small>
                                                <small class="text-muted d-block text-md-start">Quantity:
                                                    <span class="fw-normal">${listDetailOrder.get(index).quantity}</span>
                                                </small>
                                                <c:set var="totalPrice" value="${totalPrice + listDetailOrder.get(index).quantity * listClothesOrder.get(index).price}"/>
                                            </div>
                                        </div>  
                                    </c:forEach>

                                </td>
                                <td class="text-muted">
                                    $
                                    <span class="fw-bold text-muted">
                                        ${totalPrice}
                                    </span>
                                </td>
                                <td>
                                    ${orderInfor.orderDate}                          
                                </td>
                                <td>
                                    ${orderInfor.shipDate} 
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${orderInfor.status eq 1}">
                                            <span class="processing-text">
                                                ${processing}
                                            </span>  
                                        </c:when>
                                        <c:when test="${orderInfor.status eq 2}">
                                            <span class="success-text">
                                                ${completed}
                                            </span>    
                                        </c:when>
                                        <c:when test="${orderInfor.status eq 3}">
                                            <span class="error-text">
                                                ${cancel}
                                            </span>  
                                        </c:when>
                                    </c:choose>

                                    <c:url var="linkChangeStatusOrder" value="DispatchController">                                                    
                                        <c:param name="btAction" value="ChangeStatusOrder"/>
                                        <c:param name="orderId" value="${orderInfor.orderId}"/>
                                        <c:if test="${orderInfor.status eq 1}">
                                            <c:param name="status" value = "${3}"/>
                                        </c:if>
                                        <c:if test="${orderInfor.status eq 3}">
                                            <c:param name="status" value = "${1}"/>
                                        </c:if>
                                        <c:param name="page" value="OrderDetail"/>                                        
                                    </c:url>
                                    <c:if test="${orderInfor.status eq 1}">
                                        <a href="${linkChangeStatusOrder}" class="btn btn-search fs-6 ms-2" title="Cancel">
                                            <i class="fa-solid fa-xmark"></i>
                                        </a>                                                                                 
                                    </c:if>
                                    <c:if test="${orderInfor.status eq 3}">                                                
                                        <a href="${linkChangeStatusOrder}" class="btn btn-search fs-6 ms-2" title="Order Again">
                                            <i class="fa-solid fa-rotate-left"></i>
                                        </a>                                                 
                                    </c:if>
                                </td>
                            </tr>


                        </tbody>
                    </table>

                </div>

            </section>
            <!-- Footer -->
            <%@include file="../common/footer.jsp" %>
            <!-- End of Footer -->            
        </c:if>

    </body>

</html>
