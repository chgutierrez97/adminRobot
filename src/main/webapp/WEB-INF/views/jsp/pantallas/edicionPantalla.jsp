
<div  class="container" id="divPrimer" > 
    <br> <br>
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-6">
                <strong> Edicion de Pantallas</strong>
            </div>
        </div>   
    </div>
    <div>
        <div class="col-12">
            <h6> <strong>Transaccion : </strong>${tranSave.nombre}</h6>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-12">
            <table  id="tablaEdicionPantalla" class="table table-striped">
                <thead>
                    <tr style="text-align: center;">

                        <th scope="col">Pantalla</th>
                        <th scope="col">tipo de pantalla</th>
                        <th scope="col">N°. de Elementos </th>
                        <th scope="col">Opciones</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${listPantallaEdit}" var="pantEdit">
                    <tr style="text-align: center;">
                        <td>Nro. ${pantEdit.pantallaNumero}</td>
                        <td>${pantEdit.waccionar}</td>
                        <td>${pantEdit.inputs.size()}</td>
                        <td>
                            <a id="${pantEdit.id}" title="Editar" class="far fa-edit" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>
                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>

        </div>
    </div>


    <!-- modal -->
    <div class="modal" id="modalEditarPantalla">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title" id="headerModal">Editar Pantalla </h4>
                </div>
                <form id="formUpdatePantalla" action="editarPantalla" method="POST">    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <form id="formUpdatePantalla" action="actualizarPantalla" method="POST">

                                    


                                </form>
                            </div>
                        </div>    
                    </div>
                 </form>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-dark" id="btnUpdatePantalla">Actualizar</button>
                    </div>

            </div>
        </div>
    </div>                    


</div>
