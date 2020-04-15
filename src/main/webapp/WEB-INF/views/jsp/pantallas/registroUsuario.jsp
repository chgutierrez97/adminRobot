<div class= "content-wrapper" id="divPrimer">
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong>Registro de Usuarios</strong>
            </div>
            <div class="col-1 align-content-md-end">
                <a id="btnHome" title="Salir/Home" href="home" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true"><i class="fas fa-sign-out-alt"></i></a>
            </div>
        </div>   
    </div>
    <c:if test="${vista == 1}">
        <div class="alert alert-danger" role="alert">
            ${mensajeError}
        </div>
    </c:if>

    <div role="alert">
        <!-- Page Content -->


        <!-- ini div container  -->
        <div class="container-fluid">

            <div class="row">
                <div class="col-md-10">
                    <div class="well well-sm" style="margin-left: 1%;width: 115%;">


                        <form action="registroUsuario" method="POST">
                            <div class="form-row">
                                <div class="form-group col-md-5">
                                    <input type="hidden" class="form-control" value="${RegistroUsuario.id}" id="id" name="id">
                                    <label for="nombre">Nombre</label>
                                    <input type="text" class="form-control" value="${RegistroUsuario.nombre}" required="required" id="nombre" name="nombre" placeholder="Nombre" />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="apellido">Apellido</label>
                                    <input type="text" class="form-control" required="required" value="${RegistroUsuario.apellido}" id="apellido" name="apellido" placeholder="Apellido">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="dni">Nro.Identificación</label>
                                    <input type="text" class="form-control" required="required" value="${RegistroUsuario.dni}" id="dni" name="dni" placeholder="N° Identificación">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <input type="hidden" class="form-control" value="${RegistroUsuario.idUsuario}" id="idUsuario" name="idUsuario">
                                    <label for="usuario">Usuario</label>
                                    <input type="text" class="form-control" value="${RegistroUsuario.usuario}" required="required" id="usuario" name="usuario" placeholder="Usuario">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="roles">Rol</label>
                                    <select id="roles" name="roles" class="form-control "  required >
                                        <option value="">Seleccione</option>Usuario
                                        <option value="1">Operador</option>
                                        <option value="2">Administrador</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="status">Status</label>
                                    <select id="status" name="status" class="form-control "  required >
                                        <option value="">Seleccione</option>
                                        <option value="1">Activo</option>
                                        <option value="2">Inactivo</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="clave">Clave </label>
                                    <input type="password" class="form-control" required="required" value="" id="clave" name="clave" placeholder="Clave">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="inputEmail4">Confirmación de Clave </label>
                                    <input type="password" class="form-control" required="required" value="${RegistroUsuario.clave2}" id="clave2" name="clave2" placeholder="Clave">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">Guardar</button>
                        </form>

                    </div>

                </div>
            </div>

        </div> 

    </div>

</div>

<br>

</div> 

