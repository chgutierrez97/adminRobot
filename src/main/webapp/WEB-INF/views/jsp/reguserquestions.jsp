<%-- 
    Document   : regusercredenciales
    Created on : 17/09/2019, 03:41:19 PM
    Author     : MGIAccusys
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ include file="common/taglibs.jsp"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login page</title>
        <%@ include file="common/cssPro.jsp"%>
        <style type="text/css">
            h2 {
                font-size: 18px;
                margin: 0 0 15px;
            }
            .login-form {
                width: 600px;
                margin-left: -200px;
             
            }
            .login-form form {        
                background: #f7f7f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }    
            .form-control, .btn {
                height: 36px;
                border-radius: 2px;
            }
            .btn {        
                font-size: 15px;
                font-weight: bold;
            }
            .remember-me {
                margin: 8px 0 0 12px;
            }
        </style>

    </head>
    <body>  

        <div class=" container">

            <div class="card card-login mt-5  centered"style="max-width: 18rem; max-height: 20px; border: none;" accesskey="">
                                     
                        <div class="login-form">
                        <form action="registroPreguntas" method="post" class="form-horizontal" onsubmit="return validarFormulario();">
                            <h2>COM-LINK</h2>   
                            <div class="form-group">
                               <h6 style="text-align: center; font-weight: bold">Registro Usuario -  Preguntas de Seguridad</h6>
                               <label>Pregunta N° 1</label>
                               <input type="text" class="form-control" required="required" id="question01" name="question01" placeholder="Escriba una pregunta de seguridad" >
                               </br><input type="text" class="form-control" required="required" id="answer01" name="answer01" placeholder="Escriba respuesta a su pregunta de seguridad">
                            </div>
                            <div class="form-group">
                                <label>Pregunta N° 2</label>
                                <input type="text" class="form-control" required="required" id="question02" name="question02" placeholder="Escriba una pregunta de seguridad">
                                </br><input type="text" class="form-control" required="required" id="answer02" name="answer02" placeholder="Escriba respuesta a su pregunta de seguridad">
                            </div>
                            <div class="form-group">
                                <label>Pregunta N° 3</label>
                                <input type="text" class="form-control" required="required" id="question03" name="question03" placeholder="Escriba una pregunta de seguridad">
                                </br><input type="text" class="form-control" required="required" id="answer03" name="answer03" placeholder="Escriba respuesta a su pregunta de seguridad">
                            </div>                            
                            <div class="form-group clearfix">
                                <button type="submit" class="btn btn-primary login-btn btn-block" id="datosPersRegistrar">Registrar</button>
                            </div>
                        </form>
                    </div> 
            </div>
            <br>            
            <br>            
            <br>            

            <div class="login-footer">
                <p></p>
            </div>
        </div>

        <%@ include file="common/jsPro.jsp"%>
        <script type="text/javascript" src="<c:url value="/resources/js/login.js" />"></script>
    </body>
</html>