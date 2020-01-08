
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
                            <label for="w_idPantalla">Identificador de la pantalla</label>
                            <input type="text" class="form-control form-control-sm" name="w_idPantalla" id="w_idPantalla" value="" required="true">
                        </div>
                        <div class="form-group">
                            <label for="w_idPantalla">Nro. de Pantalla Asociada</label>
                            <input type="text" class="form-control form-control-sm" name="w_numPantalla" id="w_numPantalla" value="" required="true">
                        </div>

                        <input type="hidden" class="form-control form-control-sm" name="w_modPantalla" id="w_modPantalla" value="opc">
                        <input type="hidden" class="form-control form-control-sm" name="w_numPantalla" id="w_numPantalla"  value="0">

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
