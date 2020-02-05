<div class="container-fluid" id="divPrimer" style="display: block;"> 
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong>Simulador de Transacciones  <c:if test="${simulador}">OffLine</c:if> <c:if test="${!simulador}"> OnLine </c:if>
                </strong>
            </div>
            <div class="col-1 align-content-md-end">
                <a id="btnHome" title="Salir/Home" href="home" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true"><i class="fas fa-sign-out-alt"></i></a>
            </div>
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
    <c:if test="${simulador}">
        <div>
            <select id="trasaccionSimulador" class="form-control form-control-sm">
                <option selected value="0">Choose...</option>
                <c:forEach items="${transaccionEdit}" var="opt">
                    <option value="${opt.id}">${opt.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <br>           
    </c:if>

    <c:if test="${!simulador}">
        <div>
            <select id="trasaccionSimuladorOnLine" class="form-control form-control-sm">
                <option selected value="0">Choose...</option>
                <c:forEach items="${transaccionEdit}" var="opt">
                    <option value="${opt.id}">${opt.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <br>
    </c:if>
                
    <div>
        <nav><div class="nav nav-tabs" id="nav-tab-simu" role="tablist"> </div></nav>
        <div class="tab-content" id="nav-tabContent-simu"></div>
    </div>
</div>   
