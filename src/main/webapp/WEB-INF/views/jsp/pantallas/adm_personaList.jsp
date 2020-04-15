
<!-- content-wrapper -->
<div class= "content" id="divPrimer">
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
                        <div class="col-md-10">
                            
                            
                            <div class="well well-sm" style="margin-left: 5%;width: 115%;">
                               
                                    
                                                          
                            </div>
                            <div class="well well-sm" style="margin-left: 5%;width: 115%;">
                                <!--<a href="adm_newpersonaAdd"  class="btn btn-primary btn-sm"  role="button" aria-pressed="true"><i class="fa fa-plus"></i></a>-->
                                
                           
                                 
                                <table class="table" id="dataTable2" width="100%" cellspacing="0">
                                    <thead class="thead-light">
                                      <tr>
                                        <!--<th scope="col">#</th>-->
                                        <th scope="col">Id</th>
                                        <th scope="col">N° Identificación</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Apellido</th>
                                        <th scope="col"><a href="registroUsuario" title="Crear Usuario" class="btn btn-primary btn-sm"  role="button" aria-pressed="true"><i class="fa fa-plus"></i></a></th>
                                      </tr>
                                    </thead>
                                    <tbody >
                                   <c:forEach var="persona" items="${ListaPersona}" >
                                      <tr>
                                        <!-- <th scope="row">1</th> -->
                                        <td>${persona.id}</td>
                                        <td>${persona.dni}</td>
                                        <td>${persona.nombre}</td>
                                        <td>${persona.apellido}</td>
                                        <!-- <td>${persona.fechaCarga}</td> -->
                                        <td>

                                           <a href="adm_personaForm?id=${persona.id}"  class="far fa-edit"  role="button" aria-pressed="true"></a>
                                        </td>
                                      </tr>
                                     </c:forEach>  
                                        
                                     
                                        
                                        
                                    </tbody>
                                  </table>
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
