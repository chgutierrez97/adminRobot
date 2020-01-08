<div class="container-fluid" id="divPrimer" style="display: block;"> 
    <br> <br>    
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-6">
                <strong>Simulador de Transacciones  </strong>
            </div>
            <!--<div class="col-1">
                <button type="button"   data-toggle="modal" data-target="#casoAlternativoModal" title="Patalla Flujo Alternativo" class="btn btn- btn-secondary login-btn btn-block" id="loginEnter"><img src="./resources/images/add_pantalla.png"></button>
            </div> transaccionEdit-->
            <div class="col-6">
                <form id="logoutForm" action="sesiosionAct" method="POST">
                    <input type="hidden" name="w_modPantalla"id="w_modPantalla" value="logout"> 
                    <div class="row">

                        <div class="col-10"></div>
                      

                    </div>
                </form>   
            </div> 
        </div>
    </div>
    <div class="alert alert-danger" id="alert-simulador" style="display: none"></div>
    <div>

        <select id="trasaccionSimulador" class="form-control form-control-sm">
            <option selected value="0">Choose...</option>
            <c:forEach items="${transaccionEdit}" var="opt">
                <option value="${opt.id}">${opt.nombre}</option>
            </c:forEach>
        </select>
    </div>
    <br>

    <div>
        <nav><div class="nav nav-tabs" id="nav-tab-simu" role="tablist"> </div></nav>
        <div class="tab-content" id="nav-tabContent-simu"></div>
    </div>
</div>   
