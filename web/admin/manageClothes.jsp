<%-- 
    Document   : manageClothes
    Created on : Feb 27, 2023, 3:12:59 PM
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
            <c:set var="listClothes" value="${requestScope.LIST_CLOTHES}"/>
            <!-- Manage Account -->
            <section id="manage-account" class="pb-5" style="padding-top: 140px;">
                <div id="cart-container" class="container">
                    <div class="title text-center pb-4">
                        <h2 class="position-relative d-inline-block">View All Clothes</h2>
                    </div>

                    <table>
                        <thead class="h6 text-center">
                            <tr>
                                <td>No.</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Name</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Price</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Img</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Description</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Status</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">CateID</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Update</td>
                                <td>Delete</td>
                            </tr>
                        </thead>
                        <c:set var="count" value="${0}"/>
                        <tbody class="text-center">
                            <c:forEach var="dto" items="${listClothes}" varStatus="counter">
                            <c:set var="count" value="${counter.count}"/>
                            <form action="AdminController" method="POST">
                                <tr>
                                    <td>
                                        ${dto.id}
                                    </td>
                                    <td>
                                        ${dto.name}
                                    </td>
                                    <td>
                                        ${dto.price}
                                    </td>
                                    <td>
                                        <img src="${dto.imgPath}" alt="" class="img-fluid">
                                    </td>
                                    <td>
                                        <textarea name="" id="" cols="30" rows="10">${dto.description}</textarea>                      
                                    </td>
                                    <td>
                                        <c:if test="${dto.status eq 0}">
                                            Inactive
                                        </c:if>
                                        <c:if test="${dto.status eq 1}">
                                            Active
                                        </c:if>
                                    </td>
                                    <td>
                                        ${dto.cateId}
                                    </td>
                                    <td>
                                        <button type="submit" name="btAction" class="btn btn-search" title="Update Cloth">
                                            <i class="fa-sharp fa-solid fa-circle-up"></i>
                                        </button> 
                                    </td>
                                    <td>
                                        <a href="" class="btn btn-search" title="Delete Cloth">
                                            <i class="fas fa-trash"></i>
                                        </a>
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
