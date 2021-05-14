<div class="container-fluid" id="divPrimer" style="display: block;"> 
    <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");%>
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong>Cancelaciones </strong>
            </div>
            <div class="col-1 align-content-md-end">
                <a id="btnHome" title="Salir/Home" href="home" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true"><i class="fas fa-sign-out-alt"></i></a>
            </div>
        </div>
    </div>    
    <div class="alert alert-success" id="alert-cancelacion-ok" style="display: none"></div>
    <div class="alert alert-danger" id="alert-cancelacion-error" style="display: none"></div>
    <c:if test="${actividad == 2}">
        <table class="table"  id="tableCancelaciones">
            <thead class="thead-light">
                <tr  style="text-align: center;">
                    
                    <th scope="col">Proceso</th>
                    <th scope="col">Opcion</th>
                    <th scope="col">Estatus</th>
                    <th scope="col">Fecha</th>                
                    <th scope="col">Opciones (CDIR)</th>
                </tr>
            </thead>
            <tbody>

            <c:forEach items="${cancelaciones}" var="cancelacion">
                <tr  style="text-align: center;">
                   
                    <td>${cancelacion.proceso}</td>
                    <td>${cancelacion.opion}</td>
                    <td>${(cancelacion.flag>0)?"Completado":"En Espera"}</td>
                    <td>${cancelacion.fechaString}</td>             
                    <td><a id="${cancelacion.id}" title="C -> Cancelar" class="fas fa-thumbs-down" aria-hidden="true" style="color: #666666; cursor:pointer;"></a> /
                        <a id="${cancelacion.id}" title="D -> Saltar" class="fas fa-edit" aria-hidden="true" style="color: #666666; cursor:pointer;"></a> /
                        <a id="${cancelacion.id}" title="I -> Ignorar" class="fas fa-share-square" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>/
                        <a id="${cancelacion.id}" title="R -> Reintentar" class="fas fa-sync-alt" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>/
                        <a id="${cancelacion.alterna}"  title="${cancelacion.alterna}"  class="fas fa-comments" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>
                        
                        
                <sec:authorize access="hasRole('ADMIN')"> </sec:authorize>
                

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