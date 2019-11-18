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
                margin: 30px auto;
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

            <div class="login-header">
                <div class="page-header titulos-border-style ">
                    <!--<img src= "<c:url value="/resources/images/bg1.png"/>" class="img-fluid" alt="Responsive image">  -->   
                </div>
                <div class="login-wrap">
                    <div class="menu"><ul></ul></div> <div class="current-title">
                        <!--<img src= "<c:url value="/resources/images/empresas-current.jpg"/>" class="img-fluid" alt="Responsive image">    <!-- Imagen de empresas -->
                    </div>
                </div>     
            </div>
            <div class="row">
                <div class="col-lg-4">        
                    <div class="login-form">
                        <form action="datosBasicos" method="post" class="form-horizontal" onsubmit="return validarFormulario();">
                            <h2>Login de Usuario Empresa</h2>   
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
                            <div class="alert alert-danger" id="alert-login" style="display: none"></div>
                            <div class="form-group">
                                <label>N°. Tarjeta</label>
                                <input type="text" class="form-control" required="required" id="usuario" name="usuario" placeholder="N°. Tarjeta" >
                            </div>
                            <div class="form-group">
                                <label>Clave </label>
                                <input type="password" class="form-control" required="required" id="clave" name="clave" placeholder="Clave" >
                            </div>
                            <div class="form-group clearfix">
                                <button type="submit" class="btn btn-primary login-btn btn-block" id="loginEnter" onclick ="mostrar(this.id)">Ingresar</button>

                            </div>
                        </form>
                    </div>     
                </div>
                <div class="col-lg-8" alt="Responsive image">
                    <br>
                   <!--<img src= "<c:url value="/resources/images/cash_img.png"/>"  style="width: 70%" class="rounded mx-auto d-block" alt="Responsive image">      -->
                </div>
            </div>        

            <br>            
            <br>            
            <br>            

            <div class="login-footer">
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