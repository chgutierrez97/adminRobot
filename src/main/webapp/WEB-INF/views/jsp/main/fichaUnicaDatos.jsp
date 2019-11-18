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
        <div class=" container">
            <div class="login-header">
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
                        <p></p>    <!-- Imagen de empresas -->
                    </div>
                </div>     
            </div>
            <div></div>        
            <div>
                <c:if test="${paso==1}">
                    <%@ include file="../pantallas/emulador.jsp"%>  
                </c:if>
                <c:if test="${paso==2}">
                    <%@ include file="../pantallas/transaccion.jsp"%>  
                </c:if>
                <c:if test="${paso==3}">
                    <%@ include file="../pantallas/pasosAlternativos.jsp"%>  
                </c:if>


            </div>      

            <div class="login-footer">
                <p></p>
            </div>
        </div>
        <br>
                <%@ include file="../pantallas/modales.jsp"%> 


 



        <%@ include file="../common/jsPro.jsp"%>



    </body>

</html>
