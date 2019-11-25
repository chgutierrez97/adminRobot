<div class="container-fluid" id="divPrimer" style="display: block;"> 
    <br> <br>    
    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-6">
                <strong>Simulador de Transacciones  </strong>
            </div>
            <!--<div class="col-1">
                <button type="button"   data-toggle="modal" data-target="#casoAlternativoModal" title="Patalla Flujo Alternativo" class="btn btn- btn-secondary login-btn btn-block" id="loginEnter"><img src="./resources/images/add_pantalla.png"></button>
            </div>-->
            <div class="col-6">
                <form id="logoutForm" action="sesiosionAct" method="POST">
                    <input type="hidden" name="w_modPantalla"id="w_modPantalla" value="logout"> 
                    <div class="row">

                        <div class="col-10"></div>
                        <div class="col-2" style="padding-left: 1px;padding-right: 1px;"><button type="button" class="btn btn-success" id="salir">Salir</button></div> 

                    </div>
                </form>   
            </div> 
        </div>
    </div>
    <div class="alert alert-danger" id="alert-simulador" style="display: none"></div>
    <div>

        <select id="trasaccionSimulador" class="form-control form-control-sm">
            <option selected value="0">Choose...</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
        </select>

    </div>

    <br>

    <div>
        <div class="col" style="background-color: #152a14;color:#00b347;">
            <p>Pantalla del AS400</p>
            <div id="tabContent-simulador">
               
                
                
            </div>

            

        </div>
    </div>

<input type="button" id="botondelay" value="Acción con delay">
<input type="button" id="botonsindelay" value="Acción sin delay">

<div>
    

<p id="funciones">En este texto se harán los efectos con delay</p>
<p id="funciones2">En este texto se harán los efectos sin delay</p>

<button onclick="setTimeout('saludo()',3000);">Saludo a los 3 segundos</button>
    
</div>

</div>   

<script type="text/javascript">
function saludo(){
 alert("Han pasado 3 segundos");
}
</script>