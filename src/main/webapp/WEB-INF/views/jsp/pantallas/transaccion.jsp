
 
<div class="container" id="divPrimer" style="display: block;"> 
    <br> <br>    <br>    <br>
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong>Transacion</strong>
            </div>

        </div>
    </div>
    <c:if test="${actividad == null || actividad == 0 }">
        <div>
            <form id="accionSelector" action="accionSelector" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="tipoTrans">Tipo de Operacion</label>
                        <select name="accionSelector" id="accionSelector" class="form-control" >
                            <option selected value="0">Seleccione</option>
                            <option value="1">Crear Nueva Trasaccion</option>
                            <option value="2">Editar Trasaccion</option>
                        </select>
                    </div>
                    <div class="form-group col-md-12">
                        <button type="submit" class="btn btn-primary login-btn btn-block" id="loginEnter">Enviar</button>
                    </div>

                </div>
            </form>
        </div>
    </c:if>


    <c:if test="${actividad == 1}">
        <div>
            <form action="guardarTransaccion" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputNombreT">Nombre de Transaccion</label>
                        <input type="text" class="form-control" name="inputNombreT" id="inputNombreT" required="true" >
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputDescripcionT">Descripcion </label>
                        <input type="text" class="form-control" name="inputDescripcionT" id="inputDescripcionT" required="true">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="inputNombreAplic">Nombre del Aplicativo</label>
                        <input type="text" class="form-control" name="inputNombreAplic" id="inputNombreAplic" required="true">
                    </div>

                    <div class="col-sm-4">
                        <label for="selectTipoTrans">Tipo Transaccion</label>
                        <select id="selectTipoTrans" name="selectTipoTrans" class="form-control" required >
                            <option value="">Seleccione</option>
                            <option value="1">De Inicio</option>
                            <option value="2">Ordinaria </option>>
                        </select>
                    </div>  

                </div>
                <div class="form-row">
                    <div class="col-sm-6">
                        <label for="selectTipoAplic">Tipo de Aplicativo Externo</label>
                        <select id="selectTipoAplic" name="selectTipoAplic" class="form-control" required >
                            <option value="">Seleccione</option>
                            <option value="1">AS400</option>
                        </select>
                    </div> 
                    <div class="col-sm-6">
                        <label for="selectModoCrea">Modo de Creacion</label>
                        <select id="selectModoCrea" name="selectModoCrea" class="form-control" required >
                            <option value="">Seleccione</option>
                            <option value="1">Modo Complementaria</option>
                            <option value="2">Modo Edicion Inicial</option>
                        </select>
                    </div> 


                    <div id="divTransaccionInit" class="col-sm-6" style="display: none">
                        <label for="selectTransInit">Transaccion Inicial </label>
                        <select id="selectTransInit" name="selectTransInit" class="form-control">
                            <option value="">Seleccione</option>
                            <c:forEach items="${transIni}" var="opt">
                                <option value="${opt.id}">${opt.nombre}</option>
                            </c:forEach>
                        </select>
                    </div> 
                </div>
                <br>

                <div class="form-group col-md-12">
                    <button type="submit" class="btn btn-primary login-btn btn-block" id="loginEnter">Enviar</button>
                    <button type="button" class="btn btn-danger login-btn btn-block" id="loginEnter">Cancelar</button>
                </div>


            </form>

        </div>
    </c:if>



    <c:if test="${actividad == 2}">
        <table class="table"  id="tableTransacciones">
            <thead class="thead-light">
                <tr  style="text-align: center;">
                    <th scope="col">Nombre de Transaccion</th>
                    <th scope="col">Descripcion</th>
                    <th scope="col">Aplicativo Externo</th>
                    <th scope="col">Tipo de Transaccion</th>
                    <th scope="col">Fecha de Creacion</th>
                    
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${trans}" var="transaccion">
                <tr  style="text-align: center;">
                    <td>${transaccion.nombre}</td>
                    <td>${transaccion.descripcion}</td>
                    <td>${transaccion.aplicativoExternocol}</td>
                    <td>${transaccion.tipo='1'?"Inicial":"Ordinaria"}</td>
                    <td>${transaccion.fechaCarga}</td>
                    
                    <td><a id="${transaccion.id}" title="Editar" class="fa fa-pencil" aria-hidden="true" style="color: #666666; cursor:pointer;"></a></td>
                </tr>
            </c:forEach>
actividad

            </tbody>
        </table>
       
    <div>
        <form id="editTransaccion" action="editTransaccion" method="POST">
            <input type="hidden" id="field_0" name="field_0">
        </form>
    </div>
                


    </c:if>
    <c:if test="${actividad == 3}">
          <div>
            <form action="guardarTransaccion" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputNombreT">Nombre de Transaccion</label>
                        <input type="text" class="form-control" name="inputNombreT" id="inputNombreT" required="true" value="${transaccion.nombre}" >
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputDescripcionT">Descripcion </label>
                        <input type="text" class="form-control" name="inputDescripcionT" id="inputDescripcionT" required="true" value="${transaccion.descripcion}">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="inputNombreAplic">Nombre del Aplicativo</label>
                        <input type="text" class="form-control" name="inputNombreAplic" id="inputNombreAplic" required="true" value="${transaccion.aplicativoExternocol}">
                    </div>

                    <div class="col-sm-4">
                        <label for="selectTipoTrans">Tipo Transaccion</label>
                        <select id="selectTipoTrans" name="selectTipoTrans" class="form-control" required >
                            <option value="">Seleccione</option>
                            <option value="1">De Inicio</option>
                            <option value="2">Ordinaria </option>>
                        </select>
                    </div>  

                </div>
                <div class="form-row">
                    <div class="col-sm-6">
                        <label for="selectTipoAplic">Tipo de Aplicativo Externo</label>
                        <select id="selectTipoAplic" name="selectTipoAplic" class="form-control" required >
                            <option value="">Seleccione</option>
                            <option value="1">AS400</option>
                        </select>
                    </div> 
                    <div class="col-sm-6">
                        <label for="selectModoCrea">Indicar a partir de que pantalla </label>
                        <select id="selectModoCrea" name="selectModoCrea" class="form-control" required >
                            <option value="">Seleccione</option>
                            <option value="1">Modo Complementaria</option>
                            <option value="2">Modo Edicion Inicial</option>
                        </select>
                    </div>   
                </div>
                <br>

                <div class="form-group col-md-12">
                    <button type="submit" class="btn btn-primary login-btn btn-block" id="loginEnter">Actualizar</button>
                    <button type="button" class="btn btn-danger login-btn btn-block" id="loginEnter">Cancelar</button>
                </div>
            </form>
        </div>
    </c:if>            
</div>
