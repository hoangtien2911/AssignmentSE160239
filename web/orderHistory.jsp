<%-- 
    Document   : orderHistory
    Created on : Feb 18, 2023, 1:17:55 AM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
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
        <c:set var="sessionUser" value="${sessionScope.ACCOUNT_USER}"/>
        <c:if test="${not empty sessionUser}">
            <!--Header-->
            <%@include file="header.jsp" %>
            <!--End of header-->


            <section id="cart-container" class="d-flex align-items-center mt-5 pt-5">
                <div class="container mt-md-5">
                    <div class="title text-center">
                        <h2 class="position-relative d-inline-block mb-5">Detail Your Order</h2>
                    </div>

                    <form action="DispatchController">
                        <span>From: </span>
                        <input type="date" name="DateFrom" value="${param.DateFrom}"/>
                        <span>To: </span>
                        <input type="date" name="DateTo" value="${param.DateTo}" />
                        <select name="txtStatus" class="form-select filter-select d-inline-block ms-2">                            
                            <c:set var="processing" value="Processing"/>
                            <c:set var="completed" value="Completed"/>
                            <c:set var="cancel" value="Cancel"/>
                            <c:choose>                                
                                <c:when test="${param.txtStatus eq processing}">
                                    <option value="All">All</option>
                                    <option value="Processing" selected>Processing</option>
                                    <option value="Completed">Completed</option>                                                           
                                    <option value="Cancel">Cancel</option>
                                </c:when>
                                <c:when test="${param.txtStatus eq completed}">
                                    <option value="All">All</option>
                                    <option value="Processing">Processing</option>
                                    <option value="Completed" selected>Completed</option>                                                           
                                    <option value="Cancel">Cancel</option>
                                </c:when>
                                <c:when test="${param.txtStatus eq cancel}">
                                    <option value="All">All</option>
                                    <option value="Processing">Processing</option>
                                    <option value="Completed">Completed</option>                                                           
                                    <option value="Cancel" selected>Cancel</option>
                                </c:when>
                                <c:otherwise>                                    
                                    <option value="All" selected>All</option>
                                    <option value="Processing">Processing</option>
                                    <option value="Completed">Completed</option>                                                           
                                    <option value="Cancel">Cancel</option>
                                </c:otherwise>
                            </c:choose>                                                        
                        </select>
                        <button type="submit" name="btAction" value="OrderHistory" class="btn-second fs-6 ms-2">
                            Filter
                        </button>                        
                    </form>                                                  
                </div>
            </section>
            <c:set var="listOrder" value="${requestScope.LIST_ORDER}"/>
            <c:if test="${not empty listOrder}">                                                
                <!-- Information of order -->

                <section id="cart-container" class="d-flex align-items-start vh-100">
                    <div class="container">                                        
                        <table class="mt-5">
                            <thead class="h6 text-center">
                                <tr>
                                    <td>No.</td>                                
                                    <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Order Date</td>
                                    <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Delivery Date</td>
                                    <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Status</td>                        
                                    <td>View Detail</td>
                                </tr>
                            </thead>

                            <tbody class="text-center">                            
                                <c:forEach var="dto" items="${listOrder}" varStatus="counter">
                                    <tr> 
                                        <td>${counter.count}</td>                                                                                                   
                                        <td>
                                            ${dto.orderDate}
                                        </td>
                                        <td>
                                            ${dto.shipDate}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${dto.status eq 1}">
                                                    ${processing}
                                                </c:when>
                                                <c:when test="${dto.status eq 2}">
                                                    ${completed}
                                                </c:when>
                                                <c:when test="${dto.status eq 3}">
                                                    ${cancel}
                                                </c:when>
                                            </c:choose>

                                            <c:if test="${dto.status eq 1}">
                                                <c:url var="linkCancelOrder" value="DispatchController">
                                                    <c:param name="btAction" value="ChangeStatusOrder"/>
                                                    <c:param name="orderId" value="${dto.orderId}"/>
                                                    <c:param name="status" value = "${3}"/>
                                                </c:url>
                                                <a href="${linkCancelOrder}" class="btn btn-search fs-6 ms-2" title="Cancel">
                                                    <i class="fa-solid fa-xmark"></i>
                                                </a>                                                                                 
                                            </c:if>
                                            <c:if test="${dto.status eq 3}">
                                                <c:url var="linkCancelOrder" value="DispatchController">
                                                    <c:param name="btAction" value="ChangeStatusOrder"/>
                                                    <c:param name="orderId" value="${dto.orderId}"/>
                                                    <c:param name="status" value = "${1}"/>
                                                </c:url>
                                                <a href="${linkCancelOrder}" class="btn btn-search fs-6 ms-2" title="Order Again">
                                                    <i class="fa-solid fa-rotate-left"></i>
                                                </a>                                                 
                                            </c:if>

                                        </td>
                                        <td>
                                            <c:url var="linkViewOrderDetail" value="DispatchController">
                                                <c:param name="btAction" value="ViewDetailOrder"/>
                                                <c:param name="orderId" value="${dto.orderId}"/>
                                                <c:param name="orderNumberOfList" value="${counter.count}"/>                                                
                                            </c:url>
                                            <a href="${linkViewOrderDetail}" class="btn btn-search fs-6 ms-2" title="View Details">
                                                <i class="fa-solid fa-circle-info"></i>
                                            </a>                                           
                                        </td>
                                    </tr>
                                </c:forEach>                            

                            </tbody>
                        </table>            

                    </div>

                </section>  
            </c:if>
            <c:if test="${empty listOrder}">
                <div class="d-flex justify-content-center align-items-start vh-100 mt-5 pt-5">
                    <h3 class="text-primary mt-5">
                        You don't have any orders!!!
                    </h3>     
                </div>            
            </c:if>


            <!--Footer-->
            <%@include file="footer.jsp" %>
            <!--End of Footer-->
            <!-- Jquery -->
            <script src="./js/jquery-3.6.3.js"></script>
            <!-- Isotope -->
            <script src="./js/isotope.pkgd.min.js"></script>
            <!-- Bootstrap js -->
            <script src="./bootstrap-5.0.2-dist/js/bootstrap.min.js"></script>
            <!-- Custom js -->
            <script src="./js/script.js"></script>
        </c:if>        
    </body>
</html>
