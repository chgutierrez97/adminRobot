
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
        
                  
            <div>
                <%@ include file="../pantallas/navbarleft.jsp"%> 
                <%@ include file="../pantallas/navbartop.jsp"%>  
              
                <!-- Begin Page Content -->
                <div class="container-fluid">  
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
                <c:if test="${paso==5}">
                    <%@ include file="../pantallas/edicionPantalla.jsp"%>  
                </c:if>
                    
                <c:if test="${paso==7}">
                    <%@ include file="../pantallas/fichauseradm.jsp"%>  
                </c:if>    
                <c:if test="${paso==8}">
                    <%@ include file="../pantallas/adm_personaList.jsp"%>  
                </c:if>   
                <c:if test="${paso==9}">          
                    
                    <%@ include file="../pantallas/adm_userNew01.jsp"%>  
                </c:if> 
                <c:if test="${paso==10}">          
                    
                    <%@ include file="../pantallas/adm_personaEdit.jsp"%>  
                </c:if> 
                <c:if test="${paso==11}">          
                    
                    <%@ include file="../pantallas/adm_userList.jsp"%>  
                </c:if> 
                <c:if test="${paso==12}">          
                    
                    <%@ include file="../pantallas/adm_userMant.jsp"%>  
                </c:if>                      
                <c:if test="${paso==14}">          
                    <%@ include file="../pantallas/adm_personaNew.jsp"%>  
                </c:if>                     
                    
        </div>
        <!-- /.container-fluid   
        ### $$ Este div se arma en el archivo navbarleft 
        ### $$ con el fin de que se integre la pantalla a mostrar segun la opcion de
        ### $$ menu accionada por el usuario-->

            <!-- </div>       -->

            <!--  <div class="login-footer">
            <!--     <p> footer footer footer</p> 
            <!--  </div> -->
            

            </div> 
        </div>
        </div>        
       <!-- Footer -->
       <footer class="sticky-footer bg-white"> 
          <!--<div class="container my-auto">  -->
           <div class="copyright text-center my-auto"> 
            <span>Copyright &copy; Your Website 2019</span> 
           </div>
         </div>
       </footer> 
      <!-- End of Footer -->                 
        <br>
                <%@ include file="../pantallas/modales.jsp"%> 



        <%@ include file="../common/jsPro.jsp"%>
    </body>

</html>
