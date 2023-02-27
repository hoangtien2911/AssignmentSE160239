<%-- 
    Document   : manageCategory
    Created on : Feb 26, 2023, 8:24:30 PM
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
        <title>Manage Category</title>
    </head>
    <body>
        <c:set var="accountAdmin" value="${sessionScope.ACCOUNT_ADMIN}"/>
        <c:if test="${not empty accountAdmin}">
            <%@include file="../common/headerAdmin.jsp" %>
            <c:set var="listCategories" value="${requestScope.LIST_CATEGORIES}"/>
            <!-- Manage Account -->
            <section id="manage-account" class="pb-5" style="padding-top: 140px;">
                <div id="cart-container" class="container">
                    <div class="title text-center pb-4">
                        <h2 class="position-relative d-inline-block">Manage Account</h2>
                    </div>

                    <table>
                        <thead class="h6 text-center">
                            <tr>
                                <td>CateID</td>
                                <td style="border-left: 1px solid #fff;">CateName</td>                                
                                <td>Manage</td>
                            </tr>
                        </thead>
                        <c:set var="count" value="${0}"/>
                        <tbody class="text-center">
                            <c:forEach var="dto" items="${listCategories}" varStatus="counter">
                            <c:set var="count" value="${counter.count}"/>
                            <form action="AdminController" method="POST">
                                <tr>
                                    <td>
                                        ${dto.cateId}
                                        <input type="hidden" name="cateId" value="${dto.cateId}"/>
                                    </td>
                                    <td>                                        
                                        <input type="text" name="txtCateName" value="${dto.cateName}" class="w-50" required=""/>
                                    </td>

                                    <td>                                                                                                                       
                                        <button type="submit" name="btAction" value="UpdateCategory" class="btn btn-search fs-5" title="Update">
                                            <i class="fa-sharp fa-solid fa-circle-up"></i>
                                        </button>                                                                             
                                    </td>
                                </tr>
                            </form> 
                        </c:forEach>                        
                        <form action="AdminController" method="POST">
                            <tr>
                                <td>
                                    ${count + 1}
                                </td>
                                <td>
                                    <input type="text" name="txtCateName" value="" class="w-50" placeholder="New category" required=""/>
                                </td>
                                <td>
                                    <button type="submit" name="btAction" value="CreateNewCategory" class="btn btn-search fs-5" title="Create new Category">
                                        <i class="fa-solid fa-circle-plus"></i>
                                    </button>   
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