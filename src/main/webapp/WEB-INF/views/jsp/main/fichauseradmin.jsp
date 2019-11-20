<%-- 
    Document   : fichaUnicaDatos
    Created on : 10/07/2019, 01:13:14 AM
    Author     : Christian Gutierrez
--%>

<%@page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@ include file="../common/cssPro.jsp"%>
    </head>
    <body>
    <body>  

        <div class="container-fluid">

            <div >
                <!-- <div class="login-header">  LO ORIGINAL -->
                <div class="page-header titulos-border-style ">
                    <!--<img src= "<c:url value="/resources/images/bg1.png"/>" class="img-fluid" alt="Responsive image">  -->  
                </div>
                <div class="login-wrap">
                    <div class="menu"><ul></ul></div> <div class="current-title">
                        <!--<img src= "<c:url value="/resources/images/empresas-current.jpg"/>" class="img-fluid" alt="Responsive image">  -->  <!-- Imagen de empresas -->
                    </div>
                </div>     
                <div class="login-wrap">
                    
                    <div class="menu"><ul></ul></div> <div class="current-title">
                        
                      <c:if test="${sessionScope.role!= null}">
                            <div class="alert alert-danger">
                                <p>${targeta}</p>
                            </div>
                        </c:if> 
                        <p></p>     <!--   Imagen de empresas -->
                    </div>  
                </div>     
            </div>
            <div></div>        

            <div>
                    <c:if test="${paso==1}">
                        <%@ include file="../pantallas/navbartop.jsp"%>  
                        <%@ include file="../pantallas/navbarleft.jsp"%> 
                        <%@ include file="../pantallas/fichauseradm.jsp"%>  
                    </c:if>
                    
                  <nav>
                        <div class="nav nav-tabs" id="nav-tab" role="tablist">
                            <c:forEach items="${list}" var="food">
                                <c:choose>
                                <c:when test="${food2.id==1}">
                                   <a class="nav-item nav-link active" id="test${food.id}-tab" data-toggle="tab" href="#test${food.id}" role="tab" aria-controls="test${food.id}" aria-selected="true">Home</a>
                                </c:when>    
                                <c:otherwise>
                                    <a class="nav-item nav-link " id="test${food.id}-tab" data-toggle="tab" href="#test${food.id}" role="tab" aria-controls="test${food.id}" aria-selected="true">Home</a>
                                </c:otherwise>
                            </c:choose>
                                
                                
                            </c:forEach>
                        </div>
                    </nav>
                    <div class="tab-content" id="nav-tabContent">
                        <c:forEach items="${list}" var="food2">
                            <c:choose>
                                <c:when test="${food2.id==1}">
                                   <div class="tab-pane fade show active" id="test${food2.id}" role="tabpanel" aria-labelledby="test${food2.id}-tab">
                                       
                                       <c:forEach items="${food2.a}" var="inter">
                                           <h1>${inter.descripcion}</h1>
                                       </c:forEach>
                                   
                                   </div>
                                </c:when>    
                                <c:otherwise>
                                    <div class="tab-pane fade " id="test${food2.id}" role="tabpanel" aria-labelledby="test${food2.id}-tab">
                                    <c:forEach items="${food2.a}" var="inter">
                                           <h1>${inter.descripcion}</h1>
                                       </c:forEach>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            
                        </c:forEach>


                    </div> 
                    
                    
                    
               
            </div>      

            <div class="login-footer">
                <p></p>
            </div>
        </div>
                <br>

        <%@ include file="../common/jsPro.jsp"%>
        
        

    </body>

</html>
