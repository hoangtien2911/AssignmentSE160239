<%-- 
    Document   : manageOrder
    Created on : Feb 27, 2023, 4:00:47 PM
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
        <!-- Bootstrap css -->        
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">        
    </head>
    <body>        
        <c:set var="accountAdmin" value="${sessionScope.ACCOUNT_ADMIN}"/>
        <c:if test="${not empty accountAdmin}">
            <%@include file="../common/headerAdmin.jsp" %>            
            <section id="cart-container" class="d-flex align-items-center mt-5 pt-5">
                <div class="container mt-md-5">
                    <div class="title text-center">
                        <h2 class="position-relative d-inline-block mb-5">View Order</h2>
                    </div>

                    <form action="AdminController">
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
                        <div class="search-select-box d-inline-block">
                            <select name="accId" data-live-search="true">
                                <option value="">Choose Account</option>                                
                                <c:forEach var="dto" items="${requestScope.LIST_ACCOUNT_USER}">
                                    <c:if test="${param.accId eq dto.accID}">
                                        <option value="${dto.accID}" selected="">${dto.accID}. ${dto.email}</option>
                                    </c:if>                                    
                                    <c:if test="${param.accId ne dto.accID}">
                                        <option value="${dto.accID}">${dto.accID}. ${dto.email}</option>
                                    </c:if>     
                                </c:forEach>                                                                                                
                            </select>
                        </div>
                        <div class="d-inline-block">
                            <button type="submit" name="btAction" value="ViewOrders" class="btn-second fs-6 ms-3">
                                Filter
                            </button>  
                        </div>

                    </form>                                                  
                </div>
            </section>
            <c:set var="listOrders" value="${requestScope.LIST_ORDERS}"/>
            <c:if test="${not empty listOrders}">                                                
                <!-- Information of order -->

                <section id="cart-container" class="d-flex align-items-start mb-5">
                    <div class="container">                                        
                        <table class="mt-5">
                            <thead class="h6 text-center">
                                <tr>
                                    <td>OrderID</td>                                
                                    <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Order Date</td>
                                    <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Delivery Date</td>
                                    <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Status</td>   
                                    <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">AccID</td>
                                    <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Update</td>
                                    <td>View Detail</td>
                                </tr>
                            </thead>

                            <tbody class="text-center">                            
                                <c:forEach var="dto" items="${listOrders}">
                                <form action="AdminController">
                                    <input type="hidden" name="lastFilterDateFrom" value="${param.DateFrom}" />
                                    <input type="hidden" name="lastFilterDateTo" value="${param.DateTo}" />
                                    <input type="hidden" name="lastFilterAccId" value="${param.accId}" /> 
                                    <input type="hidden" name="lastFilterStatus" value="${param.txtStatus}" />                                                                        
                                    <tr> 
                                        <td>
                                            ${dto.orderId}
                                            <input type="hidden" name="orderId" value="${dto.orderId}" />                                    
                                        </td>                                                                                                   
                                        <td>
                                            ${dto.orderDate}
                                        </td>                                                                
                                        <td>
                                            <c:if test="${dto.status eq 3}">
                                                ${dto.shipDate}
                                            </c:if>
                                            <c:if test="${dto.status ne 3}">
                                                <input type="date" name="newShipDate" value="${dto.shipDate}"  class="w-50" required=""/>
                                            </c:if>                                            
                                        </td>
                                        <td>
                                            <c:if test="${dto.status eq 3}">
                                                <span class="error-text p-0">
                                                    ${cancel}
                                                </span>
                                            </c:if>                                            
                                            <c:if test="${dto.status eq 1}">
                                                <select name="newStatus" class="form-select filter-select d-inline-block ms-2 processing-text">                                                    
                                                    <option value="Processing" selected="" class="processing-text">Processing</option>
                                                    <option value="Completed" class="success-text">Completed</option>                                                                                                               
                                                </select>
                                            </c:if>
                                            <c:if test="${dto.status eq 2}">
                                                <select name="newStatus" class="form-select filter-select d-inline-block ms-2 success-text">                                                    
                                                    <option value="Processing" class="processing-text">Processing</option>
                                                    <option value="Completed" selected="" class="success-text">Completed</option>                                                                                                               
                                                </select>
                                            </c:if>                                            
                                        </td>
                                        <td>
                                            ${dto.accId}
                                        </td>
                                        <td>
                                            <c:if test="${dto.status ne 3}">
                                                <button type="submit" name="btAction" value="UpdateOrder" class="btn btn-search" title="Update Order">
                                                    <i class="fa-sharp fa-solid fa-circle-up"></i>
                                                </button> 
                                            </c:if>                                                
                                        </td>
                                        <td>
                                            <c:url var="linkViewOrderDetail" value="AdminController">
                                                <c:param name="btAction" value="ViewOrderDetail"/>
                                                <c:param name="orderId" value="${dto.orderId}"/>                                                                                                     
                                            </c:url>
                                            <a href="${linkViewOrderDetail}" class="btn btn-search fs-6 ms-2" title="View Details">
                                                <i class="fa-solid fa-circle-info"></i>
                                            </a>                                           
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>                            

                            </tbody>
                        </table>            

                    </div>

                </section>  
            </c:if>
            <c:if test="${empty listOrders}">
                <div class="d-flex justify-content-center align-items-start vh-100 mt-5 pt-5">
                    <h3 class="text-primary mt-5">
                        Don't have any orders!!!
                    </h3>     
                </div>            
            </c:if>
        </c:if>        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
        <script>
            $(document).ready(function () {
                $('.search-select-box select').selectpicker();
            })
        </script>
    </body>
</html>