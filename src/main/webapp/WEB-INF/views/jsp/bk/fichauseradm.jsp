<!-- 
### Bk 2019 11 28previo a colocar stylos de paginas del emulador#
### #
### #
### #
-->




<!-- content-wrapper -->
<div class= "content-wrapper" id="divPrimer">
        <div class="alert alert-dark titulos-border-style " role="alert">
            <strong> Datos Basicos    </strong>
        </div>  
       -->
        <div role="alert">
                <!-- Page Content -->
                
                <!-- ini div container  -->
                <div class="container-fluid">
                
                    <div class="row">
                        <div class="col-md-8">
                            <div class="well well-sm" style="margin-left: 5%;width: 115%;">
                                <form class="form-horizontal"  id="frm_user" method="post">
                                    <fieldset>
                                        
                                        <!-- Breadcrumbs-->
                                        <ol class="breadcrumb"style="margin-top: 4%;">
                                          <li class="breadcrumb-item">
                                            <a href="#">Sección</a>
                                          </li>
                                          <li class="breadcrumb-item active">Usuarios - Mantenimiento</li>
                                        </ol>
                                        
 
                                        
                                        <!-- <legend class="text-center header">Usuarios</legend> -->
                                        
                                        <!--  001 Incluir input id oculto -->
                                        <!--  002 Incluir input fecha de creacion  Fecha sera automatica del sistema -->
                                        
                                        <div class="form-group">
                                            <span class="col-md-1 col-md-offset-2 text-center"></span>
                                            <div class="col-md-12">
                                                <input id="user_id" name="user_Id" type="hidden">
                                                <input id="user" name="user" type="text" placeholder="Usuario" class="form-control">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                          <div class="col-md-12">
                                          <select class="browser-default custom-select">
                                              <option selected>Rol</option>
                                              <option value="1">Administrador</option>
                                              <option value="2">Operador</option>
                                            </select>
                                          </div> 
                                        </div>
                                        
                                                                                
                                        
                                        <div class="form-group">
                                          <div class="col-md-12">
                                          <select class="browser-default custom-select">
                                              <option selected>Estatus</option>
                                              <option value="1">Activo</option>
                                              <option value="2">Inactivo</option>
                                              <option value="3">Inicial</option>
                                            </select>
                                          </div> 
                                        </div>

                                        
                                        <div class="form-group">
                                            <div class="col-md-16 text-center">

                                               <button type="submit" class="btn btn-primary btn-sm">Actualizar</button>
                                               <!-- <button type="submit" class="btn btn-primary ">Actualizar</button>
                                                <button type="submit" class="btn btn-primary ">Eliminar</button>
                                                <button type="submit" class="btn btn-primary ">Buscar</button>
                                               -->
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                            <div class="well well-sm" style="margin-left: 5%;width: 115%;">
                                <br>
                                <br>
                                 <button type="submit" class="btn btn-primary btn-sm ">Agregar</button>
                                 <br>
                           
                                <table class="table">
                                    <thead class="thead-dark">
                                      <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Usuario</th>
                                        <th scope="col">Rol</th>
                                        <th scope="col">Estatus</th>
                                        <th scope="col">Opción</th>
                                      </tr>
                                    </thead>
                                    <tbody >
                                      <tr>
                                        <th scope="row">1</th>
                                        <td>JA400</td>
                                        <td>Operador</td>
                                        <td>Activo</td>
                                        <td>
                                            <button type="submit" class="btn btn-primary btn-sm">Modificar</button>
                                            <button type="submit" class="btn btn-primary btn-sm">Eliminar</button>
                                        </td>
                                      </tr>
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
