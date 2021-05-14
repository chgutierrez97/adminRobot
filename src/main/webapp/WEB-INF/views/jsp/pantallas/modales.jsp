
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" >
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">

            <div class="modal-body">
                <div class="row align-items-center" >

                    <div class="col align-self-center">
                        <p>Elija la opcion de su preferencia </p>      
                    </div>

                </div>
            </div>
            <div class="modal-footer">


                <c:choose>
                    <c:when test="${botonesGuardar}">
                        <button type="button" class="btn btn-success" id="salirGuardar">Salir y Guardar</button>
                        <button type="button" class="btn btn-secondary" id="SalirSinGuardar" >Salir Sin Guardar</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-success" id="salir">Salir</button>
                    </c:otherwise>
                </c:choose>



                <button type="button" class="btn btn-danger" id="SalirDelModal" data-dismiss="modal">Cancelar</button>
            </div>
        </div>

    </div>
</div>

<!-- Modal  caso alternativo -->

<div class="modal fade" id="casoAlternativoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Pantalla caso Alternativo</h5>
            </div>
            <div class="modal-body" >
                <div id="numGene" style="display: block">
                    <form>
                        <div class="form-row">
                            <div class="col-sm-6">
                                <input type="text" class="form-control form-control-sm" id="numImputs">
                            </div>
                            <div class="col-sm-6">
                                <button type="button" id="btnGenerarForm" class="btn btn-primary">Generar</button>
                            </div>
                        </div>                         
                    </form> 
                </div>
                <div id="inpGene" style="display: none">
                    <form id="formAdd" action="sesiosionAct" method="POST">
                        <div class="form-group">
                            <label for="w_flagPantalla">Bandera de la pantalla</label>
                            <input type="text" class="form-control form-control-sm" id="w_flagPantalla" name="w_flagPantalla" value="" required="true">
                        </div>    
                        <div class="form-group">
                            <label for="w_idPantalla">Identificador de la pantalla</label>
                            <input type="text" class="form-control form-control-sm" name="w_idPantalla" id="w_idPantalla" value="" required="true">
                        </div>
                        <div class="form-group">
                            <label for="w_numPantalla">Nro. de Pantalla Asociada</label>
                            <input type="text" class="form-control form-control-sm" name="w_numPantalla" id="w_numPantalla" value="" required="true">
                        </div>

                        <input type="hidden" class="form-control form-control-sm" name="w_modPantalla" id="w_modPantalla" value="opc">

                        <div class="form-group" >
                            <label for="w_ciclo">Seleccion el ciclo </label>
                            <select id="w_ciclos" name="w_ciclo" class="form-control custom-select-sm" required>
                                <option value="n" selected="">Seleccione</option>
                                <option value="f">Desde</option>
                                <option value="w">Mientras</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="w_idPantalla">Nro. de intentos</label>
                            <input type="text" class="form-control form-control-sm" name="w_nunInt" id="w_nunInt" value=""  placeholder="Nro. de intentos" disabled="true" >
                        </div>

                        <div class="form-group">
                            <label for="w_expresions">Expresiones</label>
                            <select id="w_expresions" name="w_expresion" class="form-control form-control-sm"  >
                                <option value="0">Seleccione Expresión</option>
                                <c:forEach items="${expresiones}" var="expresion">
                                    <option value="${expresion.id}">${expresion.codError}</opticonexon>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="w_actExpre">Acciones</label>
                            <select id="w_actExpre" name="w_actExpre" class="form-control form-control-sm" disabled="true" >
                                <option value="">Seleccione Acción</option>
                                <option value="r">Repetir Acción</option>
                                <option value="i">Imprimir pantalla</option>

                            </select>
                        </div>


                        <div class="form-group">
                            <label for="w_accionar">Acciones del Teclado</label>
                            <select id="w_accionar" name="w_accionar" class="form-control custom-select-sm" required>
                                <option value="">Seleccione</option>
                                <c:forEach items="${accionesLista}" var="accion">
                                    <option value="${accion.valor}">${accion.description}</opticonexon>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="cierraMOdal">Cerrar</button>
                <button type="button" class="btn btn-primary" id="enviarOpcional">Enviar</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="modalEliminarTransaccion">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <input type="hidden" class="form-control form-control-sm"  id="idTransDelet" >

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Certificación</h4>
            </div>

            <!-- Modal body -->
            <div class="alert alert-danger" id="alert-eliminar-error" style="display: none"></div>
            <div class="modal-body">
                Desea eliminar el Registro?
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="modal-btn-si">Aceptar</button>
                <button type="button" class="btn btn-secondary"  id="modal-btn-no"  data-dismiss="modal">Cancelae</button>
            </div>

        </div>
    </div>
</div>



<div class="modal" id="modalCrearExpresion">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title" id="headerModal">Expresiones regulares</h4>
               
            </div>
            <form id="formExpresiones" action="expresiones" method="POST">    <!-- Modal body -->
                <div class="modal-body">
                    <input type="hidden" class="form-control form-control-sm" id="id" name="id" >
                    <div class="form-group">
                        <label for="exampleInputEmail1">Expresión</label>
                        <input type="text" class="form-control form-control-sm" id="codError" name="codError" aria-describedby="emailHelp" placeholder="Expresión Regular" required="true">
                    </div> 
                    <div class="form-group">
                        <label for="exampleInputPassword1">Mensaje de la Expresión</label>
                        <input type="text" class="form-control form-control-sm" id="mensajeError" name="mensajeError" placeholder="Msj, de Expresión" required="true">
                    </div>
                    <div class="form-group">
                        <label for="wAccionar">Acción de la Expresión</label>
                        <select id="wAccionar" name="wAccionar" class="form-control form-control-sm" required >
                        </select><input type="hidden" id="conex" name="conex"/>
                    </div>
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                    <div class="form-group">
                        <button type="submit" id="btnUpdatePantalla" class="btn btn-dark btn-sm btn-block">Actualizar</button
                    </div>
                </div>
        </div>
        </form>
    </div>
</div>
</div>  



