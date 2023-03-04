<%-- 
    Document   : manageOrderDetail
    Created on : Feb 27, 2023, 9:30:40 PM
    Author     : Hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Manage Clothes</title>
    </head>
    <body>        
        <c:set var="accountAdmin" value="${sessionScope.ACCOUNT_ADMIN}"/>
        <c:if test="${not empty accountAdmin}">
            <%@include file="../common/headerAdmin.jsp" %>
            <c:set var="orderInfor" value="${requestScope.ORDER_INFOR}"/>
            <c:set var="listDetailOrder" value="${requestScope.LIST_DETAIL_ORDER}"/>
            <c:set var="listClothesOrder" value="${requestScope.LIST_CLOTHES_OF_DETAIL}"/>
            <c:set var="sizeOfListClothes" value="${requestScope.SIZE_OF_LIST}"/>

            <c:set var="processing" value="Processing"/>
            <c:set var="completed" value="Completed"/>
            <c:set var="cancel" value="Cancel"/>

            <section id="cart-container" class="d-flex align-items-center mt-5 pt-5">
                <div class="container mt-md-5">
                    <div class="title text-center">
                        <h2 class="position-relative d-inline-block mb-5">Order Detail</h2>
                    </div>                                                                      
                </div>
            </section>                        
            <!-- Information of order -->

            <section id="cart-container" class="d-flex align-items-start mb-5">
                <div class="container">                                        
                    <table class="mt-5">
                        <thead class="h6 text-center">
                            <tr>
                                <td>OrderID</td>                                
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Product</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Order Date</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Delivery Date</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Status</td>   
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">AccID</td>                                    
                                <td>Update</td>
                            </tr>
                        </thead>

                        <tbody class="text-center">                                                        
                        <form action="AdminController">
                            <input type="hidden" name="lastFilterDateFrom" value="${param.DateFrom}" />
                            <input type="hidden" name="lastFilterDateTo" value="${param.DateTo}" />
                            <input type="hidden" name="lastFilterAccId" value="${param.accId}" /> 
                            <input type="hidden" name="lastFilterStatus" value="${param.txtStatus}" />                                    
                            <tr> 
                                <td>
                                    ${orderInfor.orderId}
                                    <input type="hidden" name="orderId" value="${orderInfor.orderId}" />                                    
                                </td>                                         
                                <td>
                                    <c:forEach var="index" begin="0" end="${sizeOfListClothes - 1}" step="1">
                                        <div class="cart-infor d-flex justify-content-center justify-content-md-start align-items-center flex-wrap">
                                            <img class="img-fluid" src="${listClothesOrder.get(index).imgPath}" alt="">
                                            <div class="">
                                                <p class="text-capitalize text-md-start my-1">${listClothesOrder.get(index).name}</p>
                                                <small class="text-muted d-block text-md-start mb-1">Price of 1:
                                                    <span class="fw-bold">$${listClothesOrder.get(index).price}</span>
                                                </small>
                                                <small class="text-muted d-block text-md-start">Quantity:
                                                    <span class="fw-bold">${listDetailOrder.get(index).quantity}</span>
                                                </small>
                                            </div>
                                        </div>  
                                    </c:forEach>
                                </td>
                                <td>
                                    ${orderInfor.orderDate}
                                </td>                                                                
                                <td>
                                    <c:if test="${orderInfor.status eq 3}">
                                        ${orderInfor.shipDate}
                                    </c:if>
                                    <c:if test="${orderInfor.status ne 3}">
                                        <input type="date" name="newShipDate" value="${orderInfor.shipDate}"  class="w-75"/>
                                    </c:if>                                            
                                </td>
                                <td>
                                    <c:if test="${orderInfor.status eq 3}">
                                        ${cancel}
                                    </c:if>

                                    <c:if test="${orderInfor.status eq 1}">
                                        <select name="newStatus" class="form-select filter-select d-inline-block ms-2">                                                    
                                            <option value="Processing" selected="">Processing</option>
                                            <option value="Completed">Completed</option>                                                                                                               
                                        </select>
                                    </c:if>
                                    <c:if test="${orderInfor.status eq 2}">
                                        <select name="newStatus" class="form-select filter-select d-inline-block ms-2">                                                    
                                            <option value="Processing">Processing</option>
                                            <option value="Completed" selected="">Completed</option>                                                                                                               
                                        </select>
                                    </c:if>                                    
                                </td>
                                <td>
                                    ${orderInfor.accId}
                                </td>
                                <td>
                                    <c:if test="${orderInfor.status ne 3}">
                                        <button type="submit" name="btAction" value="UpdateOrder" class="btn btn-search" title="Update Order">
                                            <i class="fa-sharp fa-solid fa-circle-up"></i>
                                        </button> 
                                    </c:if>                                                
                                </td>                                        
                            </tr>
                        </form>                                                

                        </tbody>
                    </table>            

                </div>

            </section>              
        </c:if>

    </body>
</html>