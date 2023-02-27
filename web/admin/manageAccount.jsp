<%-- 
    Document   : manageAccount
    Created on : Feb 26, 2023, 2:32:26 PM
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
        <title>Manage Account</title>
    </head>
    <body>
        <c:set var="accountAdmin" value="${sessionScope.ACCOUNT_ADMIN}"/>
        <c:if test="${not empty accountAdmin}">
            <%@include file="../common/headerAdmin.jsp" %>
            <c:set var="listAccountUser" value="${requestScope.LIST_ACCOUNT_USER}"/>
            <!-- Manage Account -->
            <section id="manage-account" class="pb-5" style="padding-top: 140px;">
                <div id="cart-container" class="container">
                    <div class="title text-center pb-4">
                        <h2 class="position-relative d-inline-block">Manage Account</h2>
                    </div>

                    <table>
                        <thead class="h6 text-center">
                            <tr>
                                <td>AccID</td>
                                <td style="border-left: 1px solid #fff;">Email</td>                                
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Fullname</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Phone</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Status</td>
                                <td>Action</td>
                            </tr>
                        </thead>

                        <tbody class="text-center">
                            <c:forEach var="dto" items="${listAccountUser}">
                                <tr>
                                    <td>
                                        ${dto.accID}
                                    </td>
                                    <td>
                                        ${dto.email}                                        
                                    </td>                                    
                                    <td>
                                        ${dto.fullname}
                                    </td>
                                    <td>
                                        ${dto.phone}
                                    </td>
                                    <td>
                                        <c:if test="${dto.status eq 1}">
                                            Active
                                        </c:if>
                                        <c:if test="${dto.status eq 0}">
                                            Inactive
                                        </c:if>
                                    </td>
                                    <td>                                        
                                        <c:if test="${dto.status eq 1}">                                            
                                            <form action="AdminController" method="POST" class="d-inline-block">
                                                <input type="hidden" name="email" value="${dto.email}" />
                                                <input type="hidden" name="changeStatusTo" value="0" />
                                                <button type="submit" name="btAction" value="ChangeAccountStatus" class="btn btn-search fs-6 ms-2" title="Block">
                                                    <i class="fa-solid fa-ban"></i>
                                                </button>
                                            </form>                                            
                                        </c:if>
                                        <c:if test="${dto.status eq 0}">                                            
                                            <form action="AdminController" method="POST" class="d-inline-block">
                                                <input type="hidden" name="email" value="${dto.email}" />
                                                <input type="hidden" name="changeStatusTo" value="1" />
                                                <button type="submit" name="btAction" value="ChangeAccountStatus" class="btn btn-search fs-6 ms-2" title="UnBlock">
                                                    <i class="fa-solid fa-unlock"></i>
                                                </button>
                                            </form>
                                        </c:if>

                                    </td>
                                </tr>  
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </section>
        </c:if>

    </body>
</html>
