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
        <div class="container-fluid">
                  
            <div>
                <%@ include file="../pantallas/navbartop.jsp"%>  
                <%@ include file="../pantallas/navbarleft.jsp"%> 
                        
                <c:if test="${paso==0}">

                    <%@ include file="../pantallas/index.jsp"%>  
                </c:if>
                <c:if test="${paso==1}">

                    <%@ include file="../pantallas/emulador.jsp"%>  
                </c:if>
                <c:if test="${paso==2}">
                    <%@ include file="../pantallas/transaccion.jsp"%>  
                </c:if>
                <c:if test="${paso==3}">
                    <%@ include file="../pantallas/pasosAlternativos.jsp"%>  
                </c:if>
                <c:if test="${paso==4}">
                    <%@ include file="../pantallas/simulador.jsp"%>  
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
