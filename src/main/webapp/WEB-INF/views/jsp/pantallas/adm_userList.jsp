
<!-- content-wrapper -->
<div class= "content-wrapper" id="divPrimer"> 
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong>Administración Usuarios</strong>
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

                        <table class="table" id="tableUsuarios">
                            <thead  class="thead-light">
                                <tr>
                                    <th scope="col">Nro. Documento</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Apellido</th>
                                    <th scope="col">Usuario</th>
                                    <th scope="col">Rol</th>
                                    <th scope="col">Status</th>
                                    <th scope="col"><a href="registroUsuario"  title="Crear Usuario" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true" ><i class="fas fa-plus"></i></a></th>                
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="usuario" items="${ListaUsuario}" >
                                <tr>
                                    <td>${usuario.persona.dni}</td>
                                    <td>${usuario.persona.nombre}</td>
                                    <td>${usuario.persona.apellido}</td>
                                    <td>${usuario.usuario}</td>
                                    <td>${usuario.roles.descripcion}</td>
                                    <td>${usuario.status.descripcion}</td>
                                    <td>
                                    <c:if test="${usuario.persona.dni!=0}">          
                                        <a  href="viewUsuarioMant_Adm?id=${usuario.id}" title="Editar" class="far fa-edit" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>
                                    </c:if> 
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
