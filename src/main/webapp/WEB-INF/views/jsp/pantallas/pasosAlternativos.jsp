
<div  class="container" id="divPrimer" > 
<br> <br>
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-6">
                <strong>Creacion Flujo Alternativo</strong>
            </div>

            <div class="col-6">
                <form id="logoutAltForm" action="sesiosionAct" method="POST">
                    <input type="hidden" name="w_modPantalla"id="w_modPantallaAlt" value="logout"> 
                    <div class="row">
                        <c:choose>
                            <c:when test="${botonesGuardarOpc}">
                                <div class="col-4"></div>
                                <div class="col-4" style="padding-left: 1px;padding-right: 1px;"><button type="button" class="btn btn-success" id="salirGuardarAlt">Guardar Pantalla</button></div>   
                                <div class="col-4" style="padding-left: 1px;padding-right: 1px;"><button type="button" class="btn btn-secondary" id="SalirSinGuardarAlt" >Salir Sin Guardar</button></div>   
                            </c:when>
                            <c:otherwise>
                                <div class="col-10"></div>
                                <div class="col-2" style="padding-left: 1px;padding-right: 1px;"><button type="button" class="btn btn-success" id="salirAlt">Salir</button></div> 
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form>   
            </div>
        </div>   
    </div>


    <div>
        <div class="col-12">
            <button type="button"   data-toggle="modal" data-target="#casoAlternativoModal" title="Patalla Flujo Alternativo" class="btn btn- btn-secondary login-btn btn-block" id="loginEnter"><img src="./resources/images/add_pantalla.png"></button>
        </div>
    </div>

    <br>
    <div class="row">

        <table  id="tablapantallaEventual" class="table table-striped">
            <thead>
                <tr style="text-align: center;">
                    <th scope="col">Transaccion</th>
                    <th scope="col">Numero de Pantalla</th>
                    <th scope="col">N°. de Elementos </th>
                    <th scope="col">Opciones</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${listPatallaOpcional}" var="pantOpc">
                <tr style="text-align: center;">
                    <td>${tranNombre}</td>
                    <td>${pantOpc.pantallaNumero}</td>
                    <td>${pantOpc.inputs.size()}</td>
                    <td>
                        <!--<a id="${pantOpc.pantallaNumero}" title="Editar" class="fa fa-pencil" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>-->
                        <a id="${pantOpc.pantallaNumero}" title="Borrar" class="fa fa-trash"  aria-hidden="true" style="color: #666666; cursor:pointer;"></a>
                    </td>
                </tr>
            </c:forEach>


            </tbody>
        </table>


    </div>

    <div>
        <form id="eliminaPantOpc" action="eliminarPantalla" method="POST">
            <input type="hidden" id="field_0" name="field_0">
        </form>
    </div>                


</div>
