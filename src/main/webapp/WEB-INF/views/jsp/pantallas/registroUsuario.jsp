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
                                    <input type="hidden" class="form-control" value="${Persona.id}" id="id" name="id">
                                    <label for="nombre">Nombre</label>
                                    <input type="text" class="form-control" value="${Persona.nombre}" required="required" id="nombre" name="nombre" placeholder="Nombre" />
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="apellido">Apellido</label>
                                    <input type="text" class="form-control" required="required" value="${Persona.apellido}" id="apellido" name="apellido" placeholder="Apellido">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="dni">Nro.Identificaci�n</label>
                                    <input type="text" class="form-control" required="required" value="${Persona.dni}" id="dni" name="dni" placeholder="N� Identificaci�n">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <input type="hidden" class="form-control" value="${Usuario.id}" id="idUsuario" name="idUsuario">
                                    <label for="usuario">Usuario</label>
                                    <input type="text" class="form-control" value="" required="required" id="usuario" name="usuario" placeholder="Usuario">
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
                                    <input type="password" class="form-control" required="required" value="${Usuario.clave}" id="clave" name="clave" placeholder="Clave">
                                </div>

                                <div class="form-group col-md-6">
                                    <label for="inputEmail4">Confirmaci�n de Clave </label>
                                    <input type="password" class="form-control" required="required" value="" id="clave2" name="clave2" placeholder="Clave">
                                </div>

                            </div>



                            <button type="submit" class="btn btn-primary">Sign in</button>
                        </form>

                    </div>

                </div>
            </div>

        </div> 

    </div>

</div>

<br>

</div> 

