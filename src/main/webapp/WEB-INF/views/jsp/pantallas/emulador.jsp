
<div class="container-fluid" id="divPrimer" style="display: block;"> 
    <br> <br>    
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-6">
                <strong>Sistema de emulacion   </strong>
            </div>
            <!--<div class="col-1">
                <button type="button"   data-toggle="modal" data-target="#casoAlternativoModal" title="Patalla Flujo Alternativo" class="btn btn- btn-secondary login-btn btn-block" id="loginEnter"><img src="./resources/images/add_pantalla.png"></button>
            </div>-->
            <div class="col-6">
                <form id="logoutForm" action="sesiosionAct" method="POST">
                    <input type="hidden" name="w_modPantalla"id="w_modPantalla" value="logout"> 
                    <div class="row">
                        <c:choose>
                            <c:when test="${botonesGuardar}">
                                <div class="col-4"></div>
                                <div class="col-4" style="padding-left: 1px;padding-right: 1px;"><button type="button" class="btn btn-success" id="salirGuardar">Guardar Pantalla</button></div>   
                                <div class="col-4" style="padding-left: 1px;padding-right: 1px;"><button type="button" class="btn btn-secondary" id="SalirSinGuardar" >Salir Sin Guardar</button></div>   
                            </c:when>
                            <c:otherwise>
                                <div class="col-10"></div>
                                <div class="col-2" style="padding-left: 1px;padding-right: 1px;"><button type="button" class="btn btn-success" id="salir">Salir</button></div> 
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form>   
            </div> 
        </div>
    </div>   



    <c:if test="${listPantalla!= null}">
        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <c:forEach items="${listPantalla}" var="panta">
                    <c:choose>
                        <c:when test="${panta.active}">
                            <a class="nav-item nav-link active" id="test${panta.pantallaNumero}-tab" data-toggle="tab" href="#test${panta.pantallaNumero}" role="tab" aria-controls="test${panta.pantallaNumero}" aria-selected="true">Paso - ${panta.pantallaNumero}</a>
                        </c:when>    
                        <c:otherwise> 
                            <a class="nav-item nav-link " id="test${panta.id}-tab" data-toggle="tab" href="#test${panta.pantallaNumero}" role="tab" aria-controls="test${panta.pantallaNumero}" aria-selected="true">Paso - ${panta.pantallaNumero}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <c:forEach items="${listPantalla}" var="food2">
                <c:choose>
                    <c:when test="${food2.active}">
                        <div class="tab-pane fade show active" id="test${food2.pantallaNumero}" role="tabpanel" aria-labelledby="test${food2.pantallaNumero}-tab">
                            <div class="row">
                                <div class="col">
                                    <form action="${food2.action}" method="POST" class="form-horizontal">
                                        <fieldset>
                                            <legend>Campos del formulario</legend>
                                            <c:forEach items="${food2.inputs}" var="input">
                                                <c:choose>
                                                    <c:when test="${input.type=='text' || input.type=='password'}">
                                                        <div class="form-group">
                                                            <label for="${input.id}">${input.label}</label>
                                                            <input type="${input.type}" class="form-control form-control-sm" name="${input.name}" id="${input.id}" value="${input.value}"
                                                                   <c:choose>
                                                                <c:when test="${input.required}">
                                                                    required="true"
                                                                </c:when>
                                                            </c:choose>
                                                            >
                                                        </div>
                                                    </c:when>    
                                                    <c:otherwise>
                                                        <input type="${input.type}" class="form-control form-control-sm" name="${input.name}" id="${input.id}" value="${input.value}"
                                                               <c:choose>
                                                            <c:when test="${input.required}">
                                                                required="true"
                                                            </c:when>
                                                        </c:choose>
                                                        >
                                                    </c:otherwise>
                                                </c:choose>

                                            </c:forEach>
                                            <div class="form-row align-items-center">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="" id="checkCiclos">
                                                    <label class="form-check-label" for="defaultCheck1">
                                                        Habilitar ciclos
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-row align-items-center" id="divCiclo" style="display: none" >
                                                <div class="col-sm-4  control-sm">
                                                    <select id="w_ciclos" name="w_ciclo" class="form-control form-control-sm">
                                                        <option value="0">Seleccione</option>
                                                        <option value="f">Desde</option>
                                                        <option value="w">Mientras</option>
                                                    </select>
                                                </div>
                                                <div class="col-auto my-1">
                                                    <div class="custom-control custom-checkbox mr-sm-2">
                                                        <input type="text" class="form-contro" id="w_nunInt"  name="w_nunInt" placeholder="Nro. de intentos" disabled="true" >
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="form-row align-items-center">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="" id="checkExpresiones">
                                                    <label class="form-check-label" for="defaultCheck1">
                                                        Habilitar Expresiones
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-row align-items-center" id="divExpresion" style="display: none" >
                                                <div class="col-sm-6  control-sm">
                                                    <select id="w_expresions" name="w_expresion" class="form-control form-control-sm"  >
                                                        <option value="0">Seleccione Expresión</option>
                                                        <c:forEach items="${expresiones}" var="expresion">
                                                            <option value="${expresion.id}">${expresion.codError}</opticonexon>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-sm-6  control-sm">
                                                    <select id="w_actExpre" name="w_actExpre" class="form-control form-control-sm" disabled="true" >
                                                        <option value="">Seleccione Acción</option>
                                                        <option value="r">Ejecutar Acción</option>
                                                        <option value="s">Ingresar Opción</option>
                                                        <option value="i">Imprimir Pantalla </option>
                                                        <option value="e">Imprimir Pantalla Error </option>  
                                                    </select>
                                                </div>
                                            </div>
                                            <c:choose>
                                                <c:when test="${food2.activeKey}">
                                                    <div class="form-group">
                                                        <label for="w_accionar">Acciones del Teclado</label>
                                                        <select id="w_accionar" name="w_accionar" class="form-control custom-select-sm" required>
                                                            <option value="">Seleccione</option>
                                                            <c:forEach items="${food2.listAcciones}" var="accion">
                                                                <option value="${accion.valor}">${accion.description}</opticonexon>
                                                            </c:forEach>
                                                        </select><input type="hidden" id="conex" name="conex"/>
                                                    </div> 
                                                </c:when>
                                            </c:choose>
                                            <textarea name="txt" style="visibility:hidden">         
                                            </textarea>
                                        </fieldset>
                                        <c:if test="${errorFlag==true}">          
                                            <div class="form-group clearfix">
                                                <button type="submit" class="btn btn-warning login-btn btn-block" id="loginEnter">Reintentar</button>
                                            </div> 
                                        </c:if>  
                                        <c:if test="${errorFlag==false}">          
                                            <div class="form-group clearfix">
                                                <button type="submit" class="btn btn-primary login-btn btn-block" id="loginEnter">Aceptar</button>
                                            </div> 
                                        </c:if>  


                                    </form>
                                </div>
                                <div class="col col-5" style="background-color: #152a14;color:#00b347;">
                                    <p>Pantalla del AS400</p>
                                    <c:forEach items="${food2.textoPantalla}" var="inter">
                                        <h6>${inter}</h6>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:when>    
                    <c:otherwise>
                        <div class="tab-pane fade " id="test${food2.pantallaNumero}" role="tabpanel" aria-labelledby="test${food2.pantallaNumero}-tab">
                            <div class="row">
                                <div class="col">
                                    <form action="${food2.action}" method="POST" class="form-horizontal">
                                        <fieldset>
                                            <legend>Campos del formulario</legend>
                                            <c:forEach items="${food2.inputs}" var="input">
                                                <div class="form-group">
                                                    <label for="${input.id}">${input.label}</label>
                                                    <input type="${input.type}" class="form-control form-control-sm" name="${input.name}" id="${input.id}"  value="${input.value}">
                                                </div>
                                            </c:forEach>
                                            <div class="form-group">
                                                <label for="w_accionar">Acciones del Teclado</label>
                                                <select id="w_accionar" name="w_accionar" class="form-control custom-select-sm" required>
                                                    <option value="">Seleccione</option>
                                                    <c:forEach items="${food2.listAcciones}" var="accion">
                                                        <option value="${accion.valor}">${accion.description}</opticonexon>
                                                    </c:forEach>
                                                </select><input type="hidden" id="conex" name="conex"/>
                                            </div> 

                                        </fieldset>

                                    </form>
                                </div>
                                <div class="col" style="background-color: #152a14;color:#00b347;">
                                    <p>Pantalla del AS400</p>
                                    <c:forEach items="${food2.textoPantalla}" var="inter">
                                        <h6>${inter}</h6>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
            <c:choose> 
                <c:when test="${errorFlag}">
                    <div class="alert alert-danger col-md-6" role="alert">
                        ${errorForm}
                    </div>   
                </c:when>   
            </c:choose>
        </div>
    </c:if>
</div>
