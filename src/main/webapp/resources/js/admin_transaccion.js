
$(document).ready(function () {

//    $("#numImputs").keyup(function () {
//        this.value = (this.value + '').replace(/[^1-9]/g, '');
//    });
    $("#nunInt").keyup(function () {
        this.value = (this.value + '').replace(/[^1-9]/g, '');
    });
    $("#inputNombreT").keyup(function () {
        this.value = (this.value + '').replace(/[^0-9 a-z A-Z]/g, '');
        this.value = $.trim(this.value);
    });
    $("#w_idPantalla").keyup(function () {
        this.value = (this.value + '').replace(/[^0-9 a-z A-Z]/g, '');
        this.value = $.trim(this.value);
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


    $("#tipo_ciclo").change(function () {
        var accion = $("#tipo_ciclo").val();
        if (accion == 1) {
            $('#divRepe').show();
            $('#repeticiones').attr('required', 'required');
        } else {
            $('#divRepe').hide();
            $('#repeticiones').removeAttr('required');
        }
    });
    
    


    $("#w_ciclos").change(function () {
        var accion = $("#w_ciclos").val();
        switch (accion) {

            case "f":

                $('#w_nunInt').attr('required', 'required');
                $('#w_nunInt').removeAttr('disabled');
               
                break;

            case "w":
                $('#w_nunInt').removeAttr('required');
                $('#w_nunInt').attr('disabled', 'true');
               
                break;

            case "n":
                $('#w_nunInt').removeAttr('required');
                $('#w_nunInt').attr('disabled', 'true');
                
                break;

            default:
                $('#w_nunInt').removeAttr('required');
                $('#w_nunInt').attr('disabled', 'true');
                break;
        }
    });






    $('#checkCiclos').on('change', function () {
        if (!$(this).prop('checked')) {
            $('#divCiclo').hide();
        } else {
            $('#divCiclo').show();
        }

    });
    $('#checkExpresiones').on('change', function () {
        if (!$(this).prop('checked')) {
            $('#divExpresion').hide();
        } else {
            $('#divExpresion').show();
        }

    });


    $("#selectTipoTrans").change(function () {
        var accion = $("#selectTipoTrans").val();
        if (accion == 1) {
            $("#selectModoCrea option[value=1]").attr('disabled', 'disabled');
            $("#selectModoCrea option[value=2]").attr("selected", true);
            $('#selectTransInit').prop('selectedIndex', 0);
            $('#selectModoCrea').prop('selectedIndex', 0);
            $('#divTransaccionInit').hide();
            $('#selectTransInit').removeAttr('required');

        } else {
            $("#selectModoCrea option[value=1]").attr('disabled', false);

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
        text = text + "";
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
    
    
     $("body").on("click", "#tableExpresiones  a", function (event) {
        event.preventDefault();
        idsele = $(this).attr("id");
        accion = $(this).attr("class")

       if (accion == "far fa-edit") {
            var fila = idsele.split(",");
             $("#id").val(fila[0]);
             $("#codError").val(fila[1]);
             $("#mensajeError").val(fila[2]);  
             $("#modalCrearExpresion").modal("show");
       }else if(idsele=="btnAddExpresion"){
           
           $("#modalCrearExpresion").modal("show");
           
       }else if(accion=="far fa-trash-alt"){
           $.ajax({
                type: "GET",
                url: "/robotadmin/delExpresionById?id=" + idsele,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data) {                       
                        $("#idPantalla").val(idsele);
                        $("#alert-expresion-error").hide();
                        $(location).attr('href',"expresiones");
                    } else {
                        $("#alert-expresion-error").show();
                        $("#alert-expresion-error").html("<strong>Error en la Opcion seleccionada </strong> <br>");
                    }
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                done: function (e) {
                    console.log("DONE");
                    enableSearchButton(true);
                }
            });
           
       }
      
     
       
     });
    
    
    
    
    

    $("body").on("click", "#tablaEdicionPantalla  a", function (event) {
        event.preventDefault();
        idsele = $(this).attr("id");
        accion = $(this).attr("class")

        if (accion == "far fa-edit") {


            $.ajax({
                type: "GET",
                url: "/robotadmin/pantallaPorId?idPantalla=" + idsele,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data.inputs.length > 0) {
                        var text2 = '';
                        for (var i = 0; i < data.inputs.length; i++) {
                            if (data.inputs[i].type != 'hidden') {
                                text2 += '<div class="form-group"><label for="formGroupExampleInput">' + data.inputs[i].label + '</label><input type="' + data.inputs[i].type + '" class="form-control" id="' + data.inputs[i].id + '" name="' + data.inputs[i].name + '" value="' + data.inputs[i].value + '"></div>';
                            }
                        }
                        if (data.waccionar == 'Alternativa') {
                            var w_ciclo = data.scrips.split(',')[4].split(':')[1];

                            text2 += '<div class="form-group"><label for="w_ciclo">Seleccion el ciclo </label><select id="w_ciclo" name="w_ciclo" class="form-control custom-select-sm" required>'
                            if (w_ciclo == "n") {
                                text2 += '<option value="n" selected>Seleccione</option>';
                            } else {
                                text2 += '<option value="n">Seleccione</option>';
                            }
                            if (w_ciclo == "f") {
                                text2 += '<option value="f" selected>Desde</option>';
                            } else {
                                text2 += '<option value="f">Desde</option>';
                            }
                            if (w_ciclo == "w") {
                                text2 += '<option value="w" selected>Mientras</option>';
                            } else {
                                text2 += '<option value="w">Mientras</option>';
                            }

                            text2 += '</select></div>';




                        }
                        text2 += '<input type="hidden" id="idPantalla" name="idPantalla" value="' + idsele + '">';
                        $("#formInput").html(text2);
                        $("#idPantalla").val(idsele);

                        $("#alert-simulador").hide();
                    } else {
                        $("#alert-simulador").show();
                        $("#alert-simulador").html("<strong>Error en la Opcion seleccionada </strong> <br>");

                    }

                },
                error: function (e) {
                    console.log("ERROR: ", e);

                },
                done: function (e) {
                    console.log("DONE");
                    enableSearchButton(true);
                }
            });
            $('#modalEditarPantalla').modal("show");
        } else {
            console.log("Accion no es correspondida ");
        }

    })


    $("#btnUpdatePantalla").click(function () {
        $("#formUpdatePantalla").submit();
        $("#modalEditarPantalla").hide();
    })

    $("#transaccionSalir").click(function () {


    })



    $("#enviarOpcional").click(function () {
        $("#formAdd").submit();
    });

    $("body").on("click", "#tableTransacciones  a", function (event) {
        event.preventDefault();
        idsele = $(this).attr("id");
        accion = $(this).attr("class")
        console.log("idsele = " + idsele);
        console.log("accion = " + accion);
        if (accion == "far fa-edit") {
            $("#field_0").val(idsele);
            $("#editTransaccion").submit();

        } else if (accion == 'far fa-trash-alt') {
            $("#modalEliminarTransaccion    ").modal('show');
            $("#idTransDelet").val(idsele);
            //location.reload();
        } else if (accion == 'fas fa-download') {
            event.preventDefault();
            var accion = $("#trasaccionSimulador").val();
            $.ajax({
                type: "GET",
                url: "/robotadmin/exportarTransaccionAjax?idTransaccion=" + idsele,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data.flag) {
                        console.log("SUCCESS: ", data);
                        $("#alert-export").hide();
                        $("#alert-export-ok").show();
                        $("#alert-export-ok").html("Se exporto la data con exito. Nombre del Archivo : <strong> " + data.descripcion + "</strong> <br>");
                    } else {
                        $("#alert-export-ok").hide();
                        $("#alert-export-error").show();
                        $("#alert-export-error").html("<strong>Error en la Opcion de exportacion del scrips </strong> <br>");
                    }
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                },
                done: function (e) {
                    console.log("DONE");
                    enableSearchButton(true);
                }
            });
        } else if (idsele == 'btnAddTrans') {
            $("#accionSelectorform").submit();
        } else {
            alert("si");
        }

    })

    $("#modal-btn-si").on("click", function () {

        idTrans = $("#idTransDelet").val();

        $.ajax({
            type: "GET",
            url: "/robotadmin/eliminarTransaccion?idTransaccion=" + idTrans,
            dataType: 'json',
            timeout: 100000,
            success: function (data) {
                if (data.flag) {
                    console.log("SUCCESS: ", data);
                    $("#alert-eliminar-error").hide();

                    location.reload();
                } else {

                    $("#alert-eliminar-error").show();
                    $("#alert-eliminar-error").html("<strong>Error en la Opcion de Eliminacion de la transaccion </strong> <br>");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
                enableSearchButton(true);
            }
        });

        $("#modalEliminarTransaccion").modal('hide');

    });


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
                            if (array != 'opc') {
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


    $("#trasaccionSimuladorOnLine").change(function (event) {
        event.preventDefault();
        var accion = $("#trasaccionSimuladorOnLine").val();
        if (accion > 0) {

            $.ajax({
                type: "GET",
                url: "/robotadmin/textopantallaByIdTrans2?idTransaccion=" + accion,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data.length > 0) {
                        console.log("SUCCESS: ", data);
                        var interval = 10 * 1000;
                        var text2 = '';
                        var text3 = '';
                        for (var i = 0; i <= data.length - 1; i++) {
//                            var scrip = data[i].scrips;
//                            var array = scrip.split(",")[0].split(":")[1];
//                            if (array != 'opc') {
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

//                            }

                        }

                        $("#alert-simulador").hide();
                    } else {
                        $("#alert-simulador").show();
                        $("#alert-simulador").html("<strong>Error en la Opcion seleccionada </strong> <br>");

                    }

                },
                error: function (e) {
                    console.log("ERROR: ", e);

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

    $("#trasaccionSimulador2").change(function (event) {
        event.preventDefault();
        var accion = $("#trasaccionSimuladorOnLine").val();
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
                            if (array != 'opc') {
                                setTimeout(function (i) {

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

    