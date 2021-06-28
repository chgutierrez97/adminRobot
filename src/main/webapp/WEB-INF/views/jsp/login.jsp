<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login page</title>
        <link href="<c:url value='/resources/css/boost2/bootstrap.css' />"  rel="stylesheet"></link>
        <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    </head>

    <body>
        <div id="mainWrapper" style="background-image: url('./resources/images/fondo.jpg'); width: 100%; height: 100%; ">
            <div class="login-container">
                <div class="login-card">
                    
                    <div class="login-form">
                        <a class="sidebar-brand d-flex align-items-center justify-content-center"rel="home"  title="">
                             <div class="sidebar-brand-text mx-3" >  <h2>AST-Probot IC</h2> <span> v 1.0.2</span></div>
                        </a>
                        <c:url var="loginUrl" value="/login" />
                        <form action="${loginUrl}" method="post" class="form-horizontal">
                           
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="username"><i class="fa fa-user" title="Usuario de Sistema "></i></label>
                                <input type="text" class="form-control" id="username" name="ssoId" placeholder="Usuario" required>
                            </div>
                            <div class="input-group input-sm">
                                <label class="input-group-addon" for="password"><i class="fa fa-lock" title="Clave de sistema"></i></label> 
                                <input type="password" class="form-control" id="password" name="password" placeholder="Clave" required>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

                            <div class="form-actions">
                                <input type="submit"
                                       class="btn btn-block btn-primary btn-default" value="Ingresar">
                            </div>
                            <br>
                             <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                   <p><strong>Error :</strong> Usuario o Clave es incorrecto Favor verifique</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>Sessión de Usuario fue cerrada correctamente...</p>
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>