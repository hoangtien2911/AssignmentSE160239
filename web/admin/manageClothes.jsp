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
            <c:set var="listCategories" value="${requestScope.LIST_CATEGORIES}"/>            
            <!-- Manage Account -->
            <section id="manage-account" class="pb-5" style="padding-top: 140px;">
                <div id="cart-container" class="container">
                    <div id="home" class="title text-center pb-4">
                        <h2 class="position-relative d-inline-block">View All Clothes</h2>
                    </div>
                    <div class="d-flex justify-content-center mb-4">
                        <button id="btn-create" onclick="showForm()" class="btn btn-search fs-6" title="Show form create">
                            <i class="fa-solid fa-circle-plus fs-3"></i>
                        </button>        
                    </div>
                    <table>
                        <thead class="h6 text-center">
                            <tr>
                                <td>ClothID</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Name</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Price</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Img</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Description</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">Status</td>
                                <td style="border-left: 1px solid #fff;border-right: 1px solid #fff;">CateID</td>  
                                <td>Update</td>
                            </tr>
                        </thead>
                        <c:set var="newId" value="${requestScope.LIST_CLOTHES_SIZE}"/>
                        <tbody class="text-center">
                        <form action="AdminController?btAction=UpdateCreateCloth" method="POST" enctype="multipart/form-data">
                            <tr id="form-create" style="display: none">
                                <input type="hidden" name="numberAction" value="1" />
                                <td>
                                    ${newId + 1}
                                </td>
                                <td>
                                    <input type="text" name="newName" value="" class="w-auto" title="Name" required=""/>    
                                </td>
                                <td>
                                    <input type="number" name="newPrice" value="" title="Price" required=""/>                                            
                                </td>
                                <td>                                                                        
                                    <div>
                                        <label>New: </label> 
                                        <input type="file" name="newPath" class="w-50 h-50" required=""/>                                            
                                    </div>
                                </td>
                                <td>                                        
                                    <textarea name="newDescription" cols="30" rows="10" required=""></textarea>                                                              
                                </td>
                                <td>
                                    <select name="newStatus" class="form-select filter-select d-inline-block ms-2">                                                    
                                        <option value="1" selected="">Active</option>
                                        <option value="0">Inactive</option>                                                                                                                                                                                                       
                                    </select>
                                </td>
                                <td>
                                    <select name="newCategory" class="form-select filter-select d-inline-block ms-2">                                                    
                                        <c:forEach var="dtoCate" items="${listCategories}" varStatus="counter">
                                            <c:choose>
                                                <c:when test="${counter.count eq 1}">
                                                    <option value="${dtoCate.cateId}" selected="">${dtoCate.cateName}</option>                    
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${dtoCate.cateId}">${dtoCate.cateName}</option>                    
                                                </c:otherwise>
                                            </c:choose>                                                
                                        </c:forEach>
                                    </select>                                        
                                </td>                                

                                <td>
                                    <button type="submit" name="btAction" value="CreateNewCategory" class="btn btn-search fs-5" title="Create new">
                                        <i class="fa-solid fa-circle-plus"></i>
                                    </button>
                                </td>
                            </tr>
                        </form>    
                        <c:forEach var="dtoCloth" items="${listClothes}">                                
                            <form action="AdminController?btAction=UpdateCreateCloth" method="POST" enctype="multipart/form-data">                                
                                <tr>
                                    <input type="hidden" name="numberAction" value="2" />
                                    <td>
                                        ${dtoCloth.id}
                                        <input type="hidden" name="id" value="${dtoCloth.id}" />                                        
                                    </td>
                                    <td>                                        
                                        <input type="text" name="newName" value="${dtoCloth.name}" class="w-auto border-0" />    
                                    </td>
                                    <td>
                                        <input type="number" name="newPrice" value="${dtoCloth.price}" class="border-0"/>                                            
                                    </td>
                                    <td>
                                        <img src="${dtoCloth.imgPath}" alt="" class="img-fluid">
                                        <input type="hidden" name="oldPath" value="${dtoCloth.imgPath}" />                                        
                                        <div>
                                            <label>Change: </label> 
                                            <input type="file" name="newPath" class="w-50 h-50"/>                                            
                                        </div>
                                    </td>
                                    <td>                                        
                                        <textarea name="newDescription" cols="30" rows="10" class="border-0" >${dtoCloth.description}</textarea>                                                              
                                    </td>
                                    <td>
                                        <c:if test="${dtoCloth.status eq 0}">
                                            <select name="newStatus" class="form-select filter-select d-inline-block ms-2">                                                    
                                                <option value="0" selected="">Inactive</option>    
                                                <option value="1">Active</option>                                                                                                                                                           
                                            </select>
                                        </c:if>
                                        <c:if test="${dtoCloth.status eq 1}">
                                            <select name="newStatus" class="form-select filter-select d-inline-block ms-2">                                                    
                                                <option value="1" selected="">Active</option>    
                                                <option value="0">Inactive</option>                                                                                                                                                           
                                            </select>
                                        </c:if>
                                    </td>
                                    <td>
                                        <select name="newCategory" class="form-select filter-select d-inline-block ms-2">                                                    
                                            <c:forEach var="dtoCate" items="${listCategories}">
                                                <c:choose>
                                                    <c:when test="${dtoCloth.cateId eq dtoCate.cateId}">
                                                        <option value="${dtoCate.cateId}" selected="">${dtoCate.cateName}</option>                    
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${dtoCate.cateId}">${dtoCate.cateName}</option>                    
                                                    </c:otherwise>
                                                </c:choose>                                                
                                            </c:forEach>
                                        </select>                                        
                                    </td>                                                                       
                                    <td>                                        
                                        <button type="submit" class="btn btn-search" title="Update Cloth">
                                            <i class="fa-sharp fa-solid fa-circle-up"></i>
                                        </button>                                         
                                    </td>                                    
                                </tr>
                            </form> 
                        </c:forEach>                                                                    

                        </tbody>
                    </table>  
                    <div class="d-flex justify-content-center mt-4">
                        <a href="#home" onclick="showForm()" class="btn btn-search fs-6" title="Show form create">
                            <i class="fa-solid fa-circle-plus fs-3"></i>
                        </a>        
                    </div>
                </div>

            </section>
        </c:if>
        <script>
            function showForm() {
                const form = document.getElementById('form-create');

                if (form.style.display === 'none') {
                    form.style.display = 'table-row';
                } else {
                    form.style.display = 'none';
                }
            }
        </script>    
    </body>    
</html>
