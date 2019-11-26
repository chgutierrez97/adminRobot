
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
                text = text + " <div class='form-group' id='div_" + i + "'> <label for='field_" + i + "'>Campo " + (i + 1) + "</label><input type='text' class='form-control  form-control-sm' name='field_" + i + "' id='field_" + i + "'value=''></div>";
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
            var text = "#div_" + i;
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

        if (accion == "far fa-edit") {
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

    $("#trasaccionSimulador").change(function (event) {
        event.preventDefault();
        var accion = $("#trasaccionSimulador").val();
        if (accion > 0) {

            $.ajax({
                type: "GET",
                url: "/robotadmin/textopantallaByIdTrans?idTransaccion=" + accion,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data.length > 0) {
                        console.log("SUCCESS: ", data);
                        var interval = 10 * 1000;
                        var text2 = '';
                        var text3 = '';
                        for (var i = 0; i <= data.length - 1; i++) {
                            var scrip = data[i].scrips;
                            var array = scrip.split(",")[0].split(":")[1];
                            if(array !='opc'){
                                setTimeout(function (i) {


                                text2 = text2.replace('active', '');
                                text3 = text3.replace('active', '');

                                text2 += ' <a class="nav-item nav-link active" id="nav-home-tab' + i + '" data-toggle="tab" href="#nav-home-' + i + '" role="tab" aria-controls="nav-home" aria-selected="true">Paso ' + (1 + i) + '</a>'
                                text3 += '<div class="tab-pane fade show active" id="nav-home-' + i + '" role="tabpanel" aria-labelledby="nav-home-tab"><div class="container"><div class="row"><div class="col" style="background-color: #152a14;color:#00b347;"><p>Pantalla del AS400</p>'

                                var texto = '';
                                for (var j = 0; j <= data[i].textoPantalla.length - 1; j++) {
                                    texto += "<h6>" + data[i].textoPantalla[j] + "</h6> "
                                }
                                text3 += '' + texto + '</div></div></div></div>';

                                $("#nav-tab-simu").html(text2);
                                $("#nav-tabContent-simu").html(text3);
                            }, interval * i, i);
                                
                            }

                        }

                        $("#alert-simulador").hide();
                    } else {
                        $("#alert-simulador").show();
                        $("#alert-simulador").html("<strong>Error en la Opcion seleccionada </strong> <br>");

                    }

                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    display(e);
                },
                done: function (e) {
                    console.log("DONE");
                    enableSearchButton(true);
                }
            });
            $("#alert-simulador").hide();
        } else {
            $("#alert-simulador").show();
            $("#alert-simulador").html("seleccione una opcion del menu");
        }
    });

});

    