
$(document).ready(function () {
   
    $("#selectConexion").change(function() {
        
        $("#conex").val($("#selectConexion").val())
	$("#formConex").submit();
    });   
    
    }); 
    
    
    
    
    
    
    
    
//    $("#cantidadSucursales").keyup(function () {
//        this.value = (this.value + '').replace(/[^0-9]/g, '');
//    });
//    $("#cantidadEmpleados").keyup(function () {
//        this.value = (this.value + '').replace(/[^0-9]/g, '');
//    });
//
//    $("#facturacionMes").mask('000.000.000.000.000,00', {reverse: true});



    /******Proceso de validacion del formulario******/
//    $("#btnNexPant2").click(function (evt) {
//        evt.preventDefault();
//        validarFormularioPantalla2()
//    });


//    function validarFormularioPantalla2() {
//        if (!validarCamposFormulario("motivoSolicitudP2")) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    function  validarCamposFormulario(nombreCampo) {
//        var flag = true;
//        var mensagge = "";
//        var alertaNumIden = "";
//
//
//
//        if (nombreCampo == "motivoSolicitudP2") {
//            if ($("#motivoSolicitudP2").val()== "0") {
//                alertaNumIden = alertaNumIden + " <strong>Debe seleccionar un elemento de la lista Motivo Solicitud.</strong> <br>";
//                document.getElementById("motivoSolicitudP2").focus();
//                
//                flag = false;
//            }
//        }
//
//
//
//        if (flag) {
//            
//            $("#alert-pantalla2").hide();
//            $("#formPag2").submit();
//        } else {
//            $("#alert-pantalla2").show();
//            $("#alert-pantalla2").html(alertaNumIden);
//        }
//        return flag;
//    }
