<div class="container-fluid" id="divPrimer" style="display: block;"> 

    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong>Administrador de Expresiones Regulares</strong>
            </div>
            <div class="col-1 align-content-md-end">
                <a id="btnHome" title="Salir/Home" href="home" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true"><i class="fas fa-sign-out-alt"></i></a>
            </div>
        </div>
    </div>
    <!--<div>
        <form id="editTransaccion" action="editTransaccion" method="POST">
            <input type="hidden" id="field_0" name="field_0">
        </form>
    </div>
    <div>
        <form id="accionSelectorform" action="accionSelector" method="POST">
            <input type="hidden" id="accionSelector" name="accionSelector" value="1">
        </form>
    </div>-->
    
    <div class="alert alert-success" id="alert-expresion-ok" style="display: none"></div>
    <div class="alert alert-danger" id="alert-expresion-error" style="display: none"></div>
    <c:if test="${actividad == 2}">
        <table class="table"  id="tableExpresiones">
            <thead class="thead-light">
                <tr  style="text-align: center;">
                    <th scope="col">Id</th>
                    <th scope="col">Expresión</th>
                    <th scope="col">Descripción</th>
                    <th scope="col">Acción</th>
                 
                    <th scope="col"><a id="btnAddExpresion" title="Crear Expresion" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true" ><i class="fas fa-plus"></i></a></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${expresiones}" var="expresion">
                <tr  style="text-align: center;">
                    <td>${expresion.id}</td>
                    <td>${expresion.codError}</td>
                    <td>${expresion.mensajeError}</td>
                    <td>${expresion.wAccionar}</td>
                    
                    <td><a id="${expresion.id},${expresion.codError},${expresion.mensajeError},${expresion.wAccionar}" title="Editar" class="far fa-edit" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>
                <sec:authorize access="hasRole('ADMIN')">
                    <a id="${expresion.id}" title="Eliminar" class="far fa-trash-alt" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>
                </sec:authorize>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div>
            <form id="editTransaccion" action="editTransaccion" method="POST">
                <input type="hidden" id="field_0" name="field_0">
            </form>
        </div>
    </c:if>

</div>