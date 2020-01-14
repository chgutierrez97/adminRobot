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
                width: 340px;
             
             
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

        <div class="container">
            <div class="card card-login mx-auto mt-5" style="max-width: 18rem; max-height: 20px; border: none;" >
                <div class="login-form">
                    <form action="datosBasicos" modelAttribute="login"  method="post" onsubmit="return validarFormulario();">
                        <h2>AST-Probot IC</h2>   
                        <c:if test="${message != null}">
                            <div class="alert alert-danger">
                                <p>Usuario o Clave Incorrecto</p>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">
                                <p>You have been logged out successfully.</p>
                            </div>
                        </c:if>
                        <!-- <div class="alert alert-success">
                            <p>${message}</p>
                        </div> -->
                        <div class="alert alert-danger" id="alert-login" style="display: none">
                        </div>
                        <h6 style="text-align: center; font-weight: bold" >Login</h6>
                        
                            
                        <div class="form-group">
                                <label>Usuario</label>
                                <input type="text" class="form-control" required="required" id="usuario" name="usuario" placeholder="Usuario" >
                        </div>
                        <div class="form-group">
                            <label>Clave</label>
                            <input type="password" class="form-control" required="required" id="clave" name="clave" placeholder="Clave">
                        </div>
                        
                         <div class="form-group clearfix">
                                <button type="submit" class="btn btn-primary login-btn btn-block" id="loginEnter">Ingresar</button>
                         </div>       
                        
                        <!--
                        <div class="d-flex justify-content-center links">
                                ¿No estas Registrado? <a href="reguserdatos" class="ml-2">Registrar</a>
                        </div>
                        <div class="d-flex justify-content-center links">
                                <a href="#">¿Olvido su contraseña?</a>
                        </div> -->
                    </form>
                </div> 
            </div>
            
            
            
            
            
            
            <br>            
            <br>            
            <br>            
            <div  class="login-footer"  >
                <!--  -->
                <p></p>
            </div>
        </div>
                        
        <script>
            function mostrar(a) {
                var s = "#" + a;
                document.addEventListener("DOMContentLoaded", function (e) {

                    var miForm = document.getElementById();
                    miForm.onsubmit = function (e) {
                        e.preventDefault();
                        var formData = new FormData(this);
                        var jsonData = {};
                        for (var [k, v] of formData) {
                            jsonData[k] = v;
                        }
                        console.log(jsonData);
                    }

                });
            }

        </script>
        <%@ include file="common/jsPro.jsp"%>
        <script type="text/javascript" src="<c:url value="/resources/js/login.js" />"></script>
    </body>
</html>