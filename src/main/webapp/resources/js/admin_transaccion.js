
$(document).ready(function () {
    $("#numImputs").keyup(function () {
        this.value = (this.value + '').replace(/[^1-9]/g, '');
    });


    $("#selectModoCrea").change(function () {
        var accion = $("#selectModoCrea").val();
        if (accion == 1) {
            $('#divTransaccionInit').show();
            $('#selectTransInit').attr('required', 'required');
        } else {
            $('#divTransaccionInit').hide();
            $('#selectTransInit').removeAttr('required');
        }
    });
    
    /*------------------------------------------*/
    $("#salir").click(function () {
        $("#w_modPantalla").val("exit")
        console.log("paso por salir");
        $("#logoutForm").submit();

    })
    $("#salirGuardar").click(function () {
        $("#w_modPantalla").val("saveLogout")
        console.log("paso por salirGuardar");
        $("#logoutForm").submit();

    })

    $("#SalirSinGuardar").click(function () {
        $("#w_modPantalla").val("logout")
        console.log("paso por salirSinGuardar");
        $("#logoutForm").submit();
    })
    
    /*------------------------------------------*/
    
    $("#salirAlt").click(function () {
        $("#w_modPantallaAlt").val("exitAlt")
        $("#logoutAltForm").submit();
    })
    
    $("#salirGuardarAlt").click(function () {
        $("#w_modPantallaAlt").val("saveLogoutAlt")   
        $("#logoutAltForm").submit();
    })

    $("#SalirSinGuardarAlt").click(function () {
        $("#w_modPantallaAlt").val("logoutAlt")      
        $("#logoutAltForm").submit();
    })

    /*------------------------------------------*/
    
    $("#btnGenerarForm").click(function () {
        var inputCantidad = $("#numImputs").val();
        var text = "";
        if (inputCantidad != null && inputCantidad > 0 && inputCantidad < 50) {
            $('#numGene').hide();
            for (var i = 0; i < inputCantidad; i++) {
                text = text + " <div class='form-group' id='div_"+i+"'> <label for='field_" + i + "'>Campo " + (i + 1) + "</label><input type='text' class='form-control  form-control-sm' name='field_" + i + "' id='field_" + i + "'value=''></div>";
            }
            $("#formAdd").append(text);
            $("#inpGene").show();
        } else {
            $('#numGene').show();
            $('#inpGene').hide();
        }
    });


    $("#cierraMOdal").click(function () {
        var inputCantidad = $("#numImputs").val();
        for (var i = 0; i < inputCantidad; i++) {
            var text="#div_"+i;
            $(text).remove();
        }
        $('#numGene').show();
        $('#inpGene').hide();
        $('#casoAlternativoModal').modal('hide')
        $("#numImputs").val("")
         


    });
    
     $("#enviarOpcional").click(function () {
         $("#formAdd").submit();      
     });
    
    $("body").on("click", "#tableTransacciones  a", function (event) {
        event.preventDefault();
        idsele = $(this).attr("id");
        accion = $(this).attr("class")

        if (accion == "fa fa-pencil") {
             $("#field_0").val(idsele);
            $("#editTransaccion").submit();
            
        } else {
           alert("si");
        }

    })
  
    
    $("body").on("click", "#tablapantallaEventual  a", function (event) {
        event.preventDefault();
        idsele = $(this).attr("id");
        accion = $(this).attr("class")

        if (accion == "fa fa-pencil") {
            alert("si");
        

        } else {
            $("#field_0").val(idsele);
            $("#eliminaPantOpc").submit();
        }

    })

});

    