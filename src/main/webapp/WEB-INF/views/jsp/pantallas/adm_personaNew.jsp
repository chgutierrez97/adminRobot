
<!-- content-wrapper -->
<div class= "content-wrapper" id="divPrimer">
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong>Creación de Personas</strong>
            </div>
             <div class="col-1 align-content-md-end">
                <a id="btnHome" title="Salir/Home" href="home" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true"><i class="fas fa-sign-out-alt"></i></a>
            </div>
        </div>   
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
                                <form action="registroPersonaAdm" modelAttribute="persona"  method="POST" class="form-horizontal" >
                                    <c:if test="${message != null}">
                                        <div class="alert alert-danger">
                                            <p>Ya existe la Persona con el N° Identificación ingresado en el sistema, Verifique!</p>
                                        </div>
                                    </c:if>
                                    <div class="alert alert-danger" id="alert-login" style="display: none"></div>
                                    <div class="form-group">
                                       <!--</br> -->
                                       <input type="hidden" class="form-control" value="${Persona.id}" id="id" name="id">
                                       <label>Nombre</label>
                                       <input type="text" class="form-control" value="${Persona.nombre}" required="required" id="nombre" name="nombre" placeholder="Nombre" />
                                    </div>
                                    <div class="form-group">
                                        <label>Apellido</label>
                                        <input type="text" class="form-control" required="required" value="${Persona.apellido}" id="apellido" name="apellido" placeholder="Apellido">
                                    </div>
                                    <div class="form-group">
                                        <label>N° Identificación</label>
                                        <input type="text" class="form-control" required="required" value="${Persona.dni}" id="dni" name="dni" placeholder="N° Identificación">
                                    </div>                            
                                    <div class="form-group clearfix">
                                        <button type="submit" class="btn btn-primary btn-sm" id="registroDatos">Siguiente</button>
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
