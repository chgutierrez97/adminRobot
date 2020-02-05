
<!-- content-wrapper -->
<div class= "content-wrapper" id="divPrimer">
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong>Creacion de Usuarios</strong>
            </div>
            <div class="col-1 align-content-md-end">
                <a id="btnHome" title="Salir/Home" href="home" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true"><i class="fas fa-sign-out-alt"></i></a>
            </div>
        </div>   
    </div>
        <div role="alert">
                <!-- Page Content --
                
                <!-- ini div container  -->
                <div class="container-fluid">
                
                    <div class="row">
                        <div class="col-md-8">
                            <div class="well well-sm" style="margin-left: 5%;width: 115%;">
                            <!-- ### $$-->
                                    
                                <!-- %%%  -->
                                <form action="saveUsuario_adm" modelAttribute="Usuario"  method="POST" class="form-horizontal" onsubmit="return validarFormulario();">
                                    <c:if test="${message != null}">
                                        <div class="alert alert-danger">
                                            <p>Ya existe la Usuario con el nombre ingresado en en el sistema, Verifique!</p>
                                        </div>
                                    </c:if>
                                    <div class="alert alert-danger" id="alert-login" style="display: none"></div>
                                    
                                    
                                    <div class="row">
                                        <div class="col">  
                                            <input type="hidden" class="form-control" value="${Usuario.id}" id="id" name="id">
                                            <label>Usuario</label>
                                            <input type="text" class="form-control" value="${Usuario.usuario}" required="required" id="usuario" name="usuario" placeholder="Usuario" />
                                        </div>
                                        <div class="col">  
                                            <label>Clave</label>
                                            <input type="text" class="form-control" required="required" value="${Usuario.clave}" id="clave" name="clave" placeholder="Clave">
                                        </div>
                                     </div>
                                    <br>
                                    <div class="alert alert-danger" id="alert-login" style="display: none"></div>
                                    <div class="row">
                                        <div class="col">
                                            <label for="selectRol">Rol</label>
                                            <select id="rol" name="rol" class="form-control "  required >
                                                <option value="">Seleccione</option>
                                                <option value="1">Operador</option>
                                                <option value="2">Administrador</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <label for="rol">Status</label>
                                            <select id="rol" name="rol" class="form-control "  required >
                                                <option value="">Seleccione</option>
                                                <option value="1">Activo</option>
                                                <option value="2">Inactivo</option>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <br>
                                    <div class="alert alert-danger" id="alert-login" style="display: none"></div>
                                    <div class="row">
                                        <div class="col">
                                            <div class="form-group">
                                                <button type="submit" class="btn btn-primary btn-sm" id="registroDatos">Guardar</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <!-- %%%-->                                    
                            </div>
                            
                        </div>
                    </div>
              
                </div> 
                <!--fin div container  -->
              </div>
              <!-- /.container-fluid -->            
        </div>
        
    <!-- Final content-wrapper -->
    <!--  </div> -->
        <br>
   <!-- <form action="siguientePaso2" method="post" class="form-horizontal">   
       <input type="hidden" name="codeClient"id="codeClient" value="7900576">
       <div class="col-lg-11" style="text-align: right;"><button type="submit" class="btn btn-warning" style="text-align: right;">Siguiente >> </button></div>
    </form> -->
    
</div> 
<!-- final  content-wrapper -->
