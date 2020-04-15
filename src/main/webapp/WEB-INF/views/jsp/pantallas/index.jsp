<div class="container-fluid" id="divPrimer" style="display: block;"> 

    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong>Transacion</strong>
            </div>
            ${sessionScope.username}
        </div>

    </div>
    <div>
        <form id="editTransaccion" action="editTransaccion" method="POST">
            <input type="hidden" id="field_0" name="field_0">
        </form>
    </div>
    <div>
        <form id="accionSelectorform" action="accionSelector" method="POST">
            <input type="hidden" id="accionSelector" name="accionSelector" value="1">
        </form>
    </div>

    <div class="alert alert-success" id="alert-export-ok" style="display: none"></div>
    <div class="alert alert-success" id="alert-delete-ok" style="display: none"></div>
    <div class="alert alert-danger" id="alert-export-error" style="display: none"></div>
    <div class="alert alert-danger" id="alert-delete-error" style="display: none"></div>
    <c:if test="${admin}">
        <div class="alert alert-danger" id="alert-export-error">
            <p><strong>Error :</strong> ${errorAcceso}</p>
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
                    <th scope="col"><a id="btnAddTrans" title="Crear Transaccion" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true"><i class="fas fa-plus"></i></a></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${trans}" var="transaccion">
                <tr  style="text-align: center;">
                    <td>${transaccion.nombre}</td>
                    <td>${transaccion.descripcion}</td>
                    <td>${transaccion.aplicativoExternocol}</td>
                    <td>${transaccion.tipo}</td>
                    <td>${transaccion.fechaCargaTexto}</td>
                    <td><a id="${transaccion.id}" title="Editar" class="far fa-edit" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>
                        <a id="${transaccion.id}" title="Exportar Json" class="fas fa-download" aria-hidden="true" style="color: #666666; cursor:pointer;"></a> 
                <sec:authorize access="hasRole('ADMIN')">
                    <a id="${transaccion.id}" title="Eliminar" class="far fa-trash-alt" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>
                </sec:authorize>

                </td>
                </tr>
            </c:forEach>


            </tbody>
        </table>

        <div>
            <form id="editTransaccion" action="editTransaccion" method="POST">
                <input type="hidden" id="field_0" name="field_0">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 

            </form>
        </div>
    </c:if>

</div>