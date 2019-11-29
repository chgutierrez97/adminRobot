
<!-- content-wrapper -->
<div class= "content-wrapper" id="divPrimer">
        <div class="alert alert-dark titulos-border-style " role="alert">
            <strong> Creación de Personas   </strong>
        </div>  
      
        <div role="alert">
                <!-- Page Content -->
                
                
                <!-- ini div container  -->
                <div class="container-fluid">
                
                    <div class="row">
                        <div class="col-md-8">
                            <div class="well well-sm" style="margin-left: 5%;width: 115%;">
                            <!-- ### $$-->
                                    
                                <!-- %%%  -->
                                <form action="registroamdPersona" modelAttribute="Persona"  method="POST" class="form-horizontal" onsubmit="return validarFormulario();">
                                    <c:if test="${message != null}">
                                        <div class="alert alert-danger">
                                            <p>Ya existe la Persona con el N° Identificación ingresado en el sistema, Verifique!</p>
                                        </div>
                                    </c:if>
                                    <div class="alert alert-danger" id="alert-login" style="display: none"></div>
                                    <div class="form-group">
                                       <!--</br> -->
                                       <label>Nombre</label>
                                       <input type="text" class="form-control"  required="required" id="nombre" name="Nombre" placeholder="Nombre" />
                                    </div>
                                    <div class="form-group">
                                        <label>Apellido</label>
                                        <input type="text" class="form-control" required="required"  id="personaapellido" name="Apellido" placeholder="Apellido">
                                    </div>
                                    <div class="form-group">
                                        <label>N° Identificación</label>
                                        <input type="text" class="form-control" required="required"    id="dni" name="dni" placeholder="N° Identificación">
                                        <!--<input type="date" class="form-control"    id="fechaCarga" name="fechaCarga"> -->
                                    </div>                            
                                    <div class="form-group clearfix">
                                        <button type="submit" class="btn btn-primary btn-sm" id="registroDatos">Guardar</button>
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
