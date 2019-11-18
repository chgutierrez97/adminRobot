
$(document).ready(function () {

//    $("#numTarjeta").keyup(function () {
//        this.value = (this.value + '').replace(/[^0-9]/g, '');
//    });
function mostrar2(a) {
 var x = $("#"+a+"").val();
 alert(x);
}

//    $("#btnLoginEnter").click(function (evt) {
//        evt.preventDefault();
//        
//        validarFormulario()
//
//
//    });



function canposFormulario(vat) {
        alert(vat);
    }
/******Proceso de validacion del formulario******/
    function validarFormulario() {
        if (!validarCamposFormulario("usuario")) {
            return false;
        } else if (!validarCamposFormulario("clave")) {
            return false;

        } else {
            return true;
        }
    }

    function  validarCamposFormulario(nombreCampo) {
        var flag = true;
        var mensagge = "";
        var alertaNumIden = "";



        if (nombreCampo == "numTarjeta") {

            if ($("#numTarjeta").val().length != 10) {
                alertaNumIden = alertaNumIden + " <strong>El campo N°. de Tarjeta debe tener 10 digitos .</strong> <br>";
                document.getElementById("myTextField").focus();
                toastr.info("Info Message", "Title");
                flag = false;
            } 

        }
        if (nombreCampo == "clave") {
          if ($("#clave").val().length != 10) {
                alertaNumIden = alertaNumIden + " <strong>El campo Clave debe tener 10 caracteres .</strong> <br>";
                flag = false;
            } 
        }


        if (flag) {
            $("#alert-login").hide();
        } else {
            $("#alert-login").show();
            $("#alert-login").html(alertaNumIden);
        }
        return flag;
    }

});

