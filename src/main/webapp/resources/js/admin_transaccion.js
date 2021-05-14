$(document).ready(function () {
    $('#tableUsuarios, #tableTransacciones,#dataTable2, #tableExpresiones,#tableCancelaciones').DataTable({

        "order": [[1, "desc"]],
        "oLanguage": {
            "oPaginate": {
                "sPrevious": "Anterior",
                "sNext": "Siguiente",
                "sLast": "&Uacute;ltima",
                "sFirst": "Primera"
            },

            "sLengthMenu": 'Mostrar <select>' +
                    '<option value="5">5</option>' +
                    '<option value="10">10</option>' +
                    '<option value="20">20</option>' +
                    '<option value="30">30</option>' +
                    '<option value="40">40</option>' +
                    '<option value="50">50</option>' +
                    '</select> registros',

            "sInfo": "Mostrando del _START_ a _END_ (Total: _TOTAL_ resultados)",

            "sInfoFiltered": " - filtrados de _MAX_ registros",

            "sInfoEmpty": "No hay resultados de b&uacute;squeda",

            "sZeroRecords": "No hay registros a mostrar",

            "sProcessing": "Espere, por favor...",

            "sSearch": "Buscar:"

        }
    });



//    $("#numImputs").keyup(function () {
//        this.value = (this.value + '').replace(/[^1-9]/g, '');
//    });
    $("#nunInt").keyup(function () {
        this.value = (this.value + '').replace(/[^0-9]/g, '');
    });
    $("#w_nunInt").keyup(function () {
        this.value = (this.value + '').replace(/[^0-9]/g, '');
    });
    $("#inputNombreT").keyup(function () {
        this.value = (this.value + '').replace(/[^0-9 a-z A-Z]/g, '');
        this.value = $.trim(this.value);
    });
//    $("#w_idPantalla").keyup(function () {
//        this.value = (this.value + '').replace(/[^0-9 a-z A-Z]/g);
//        this.value = $.trim(this.value);
//    });

    $("#w_idPantalla").change()

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

    $("#w_idPantalla").change(function () {
        var accion = $("#w_idPantalla").val();
        str = accion;

        for (var i = 0; i < str.length; i++) {
            //Sustituye "á é í ó ú"
            if (str.charAt(i) == "á")
                str = str.replace(/á/, "a");
            if (str.charAt(i) == "é")
                str = str.replace(/é/, "e");
            if (str.charAt(i) == "í")
                str = str.replace(/í/, "i");
            if (str.charAt(i) == "ó")
                str = str.replace(/ó/, "o");
            if (str.charAt(i) == "ú")
                str = str.replace(/ú/, "u");
        }

        $("#w_idPantalla").val(str.trim());


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
    $("#w_expresions").change(function () {
        var accion = $("#w_expresions").val();


        if (accion == "0") {
            $('#w_actExpre').attr('disabled', true);
            $('#w_actExpre').removeAttr('required');
        } else {
            $('#w_actExpre').removeAttr('disabled');
            $('#w_actExpre').attr('required', 'required');
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
        $.blockUI({message: '<h4> Cargando...</h4>'});

    })
    $("#salirGuardar").click(function () {
        $("#w_modPantalla").val("saveLogout")
        console.log("paso por salirGuardar");
        $("#logoutForm").submit();
        $.blockUI({message: '<h4> Cargando...</h4>'});

    })

    $("#SalirSinGuardar").click(function () {
        $("#w_modPantalla").val("logout")
        console.log("paso por salirSinGuardar");
        $("#logoutForm").submit();
        $.blockUI({message: '<h4> Cargando...</h4>'});
    })

    /*------------------------------------------*/

    $("#salirAlt").click(function () {
        $("#w_modPantallaAlt").val("exitAlt")
        $("#logoutAltForm").submit();
        $.blockUI({message: '<h4> Cargando...</h4>'});
    })

    $("#salirGuardarAlt").click(function () {
        $("#w_modPantallaAlt").val("saveLogoutAlt")
        $("#logoutAltForm").submit();
        $.blockUI({message: '<h4> Cargando...</h4>'});
    })

    $("#SalirSinGuardarAlt").click(function () {
        $("#w_modPantallaAlt").val("logoutAlt")
        $("#logoutAltForm").submit();
        $.blockUI({message: '<h4> Cargando...</h4>'});
    })

    $("#SalirSinGuardarAlt2").click(function () {
        $("#w_modPantallaAlt").val("nexModAlt")
        $("#logoutAltForm").submit();
        $.blockUI({message: '<h4> Cargando...</h4>'});
    })

    /*------------------------------------------*/

    $("#btnGenerarForm").click(function () {
        var inputCantidad = $("#numImputs").val();
        var text = "";
        text = text + "";
        if (inputCantidad != null && inputCantidad >= 0 && inputCantidad < 50) {
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
            acc = fila[3];

            $.ajax({
                type: "GET",
                url: "/robotadmin/findAccionesAll?idPantalla=1",
                dataType: 'json',
                timeout: 100000,
                success: function (data) {

                    if (data.accionTeclado.length > 0) {
                        // $("#wAccionar").prepend("<option value='Seleccione' selected='selected' >Seleccione</option>");
                        data.accionTeclado.forEach(function (persona, index) {
                            $("#wAccionar").prepend("<option value='" + persona.valor + "'>" + persona.description + "</option>");
                        });

                        $("#wAccionar > option[value='" + acc + "']").attr("selected", true);


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
            $("#modalCrearExpresion").modal("show");
        } else if (idsele == "btnAddExpresion") {
            $.ajax({
                type: "GET",
                url: "/robotadmin/findAccionesAll?idPantalla=1",
                dataType: 'json',
                timeout: 100000,
                success: function (data) {

                    if (data.accionTeclado.length > 0) {
                        // $("#wAccionar").prepend("<option value='Seleccione' selected='selected' >Seleccione</option>");
                        data.accionTeclado.forEach(function (persona, index) {
                            $("#wAccionar").prepend("<option value='" + persona.valor + "'>" + persona.description + "</option>");
                        });


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

            $("#modalCrearExpresion").modal("show");

        } else if (accion == "far fa-trash-alt") {
            $.ajax({
                type: "GET",
                url: "/robotadmin/delExpresionById?id=" + idsele,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data) {
                        $("#idPantalla").val(idsele);
                        $("#alert-expresion-error").hide();
                        $(location).attr('href', "expresiones");
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

    $("body").on("click", "#tableCancelaciones  a", function (event) {
        event.preventDefault();
        idsele = $(this).attr("id");
        accion = $(this).attr("class")

        if (accion == "fas fa-thumbs-down") {
            console.log("Cancelar");
            updateCancelacion(idsele,"1");
            
        } else if (accion == "fas fa-edit") {
            console.log("Saltar");
            updateCancelacion(idsele,"D");


        } else if (accion == "fas fa-share-square") {
            console.log("Ignorar");
            updateCancelacion(idsele,"I");


        } else if (accion == "fas fa-sync-alt") {
            console.log("Reintentar");
            updateCancelacion(idsele,"R");

        }else if (accion == "fas fa-comments") {
            console.log("Reintentar");
            //window.alert(idsele);
            
                   $("#alert-cancelacion-ok").show();
                        $("#alert-cancelacion-ok").html("<strong>"+idsele+"</strong> <br>");
            

        }
    });


    function updateCancelacion(id,valor) {
         $.ajax({
                type: "GET",
                url: "/robotadmin/updateCancelacion?id=" + id+"&valor="+valor,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data) {
                        $("#idPantalla").val(idsele);
                        $("#alert-cancelacion-error").hide();//alert-cancelacion-ok
                         $("#alert-cancelacion-ok").show();
                        $("#alert-cancelacion-ok").html("<strong>Operacion Finalizada con Exito... </strong> <br>");
                        $(location).attr('href', "cancelaciones");
                    } else {
                        $("#alert-cancelacion-ok").hide()
                        $("#alert-cancelacion-error").show();
                        $("#alert-cancelacion-error").html("<strong>Error no se pudo realizar la Opcion Seleccionada </strong> <br>");
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
        return text;
    };
    

    $("body").on("click", "#tablaEdicionPantalla  a", function (event) {
        event.preventDefault();
        idsele = $(this).attr("id");
        accion = $(this).attr("class")

        if (accion == "far fa-edit") {


            $.ajax({
                type: "GET",
                url: "/robotadmin/pantallaPorIdAndExpre?idPantalla=" + idsele,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data.pantalla.inputs.length > 0) {
                        var text2 = '';
                        for (var i = 0; i < data.pantalla.inputs.length; i++) {
                            if (data.pantalla.inputs[i].type != 'hidden') {
                                text2 += '<div class="form-group"><label for="formGroupExampleInput">' + data.pantalla.inputs[i].label + '</label><input type="' + data.pantalla.inputs[i].type + '" class="form-control" id="' + data.pantalla.inputs[i].id + '" name="' + data.pantalla.inputs[i].name + '" value="' + data.pantalla.inputs[i].value + '"></div>';
                            }
                        }
//                        
                        var w_modPantalla = data.pantalla.scrips.split(',')[0].split(':')[1];
                        var w_ciclo = "";
                        var w_expresion = "";
                        var w_actExpre = "";
                        var w_nunInt = "";
                        if (w_modPantalla == 'conec') {
                            w_ciclo = data.pantalla.scrips.split(',')[2].split(':')[1];
                            w_nunInt = data.pantalla.scrips.split(',')[3].split(':')[1];
                            w_expresion = data.pantalla.scrips.split(',')[4].split(':')[1];
                            w_actExpre = data.pantalla.scrips.split(',')[5].split(':')[1];

                        } else if (w_modPantalla == 'oper') {
                            w_accionar = data.pantalla.scrips.split(',')[3].split(':')[1]
                            w_ciclo = data.pantalla.scrips.split(',')[4].split(':')[1];
                            w_nunInt = data.pantalla.scrips.split(',')[5].split(':')[1];
                            w_expresion = data.pantalla.scrips.split(',')[6].split(':')[1];
                            w_actExpre = data.pantalla.scrips.split(',')[7].split(':')[1];
                            ;


                        } else if (w_modPantalla == 'opc') {
                            w_accionar = data.pantalla.scrips.split(',')[3].split(':')[1];
                            w_ciclo = data.pantalla.scrips.split(',')[4].split(':')[1];
                            w_nunInt = data.pantalla.scrips.split(',')[5].split(':')[1];
                            w_expresion = data.pantalla.scrips.split(',')[6].split(':')[1];
                            w_actExpre = data.pantalla.scrips.split(',')[7].split(':')[1];



                        }


                        text2 += '<div class="form-group"><label for="w_ciclo">Seleccion el ciclo </label><select id="w_ciclo" name="w_ciclo" class="form-control custom-select-sm" required>'
                        if (w_ciclo == "n") {
                            text2 += '<option value="n" selected>Seleccione</option>';
                        } else {
                            text2 += '<option value="n">Seleccione</option>';
                        }
                        data.pantalla.scrips.split(',')[4].split(':')[1];
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

                        if (w_ciclo == "f") {
                            text2 += '<div class="form-group"><label for="formGroupExampleInput">Nro. de  Iteraciones</label><input type="text" class="form-control" id="w_nunInt" name="w_nunInt" value="' + w_nunInt + '"></div>';
                        } else {
                            text2 += '<div class="form-group"><label for="formGroupExampleInput">Nro. de  Iteraciones</label><input type="text" class="form-control" id="w_nunInt" name="w_nunInt" value="0" placeholder="Nro. de intentos" ></div>';
                        }



                        text2 += '<div class="form-group"><label for="w_ciclo">Seleccion de Expresión </label><select id="w_expresions" name="w_expresions" class="form-control custom-select-sm" required>'
                        for (var item in data.expresiones) {
                            if (item.id == w_expresion) {
                                text2 += '<option value="' + data.expresiones[item].id + '" selected>' + data.expresiones[item].codError + '</option>';
                            } else {
                                text2 += '<option value="' + data.expresiones[item].id + '">' + data.expresiones[item].codError + '</option>';
                            }
                        }
                        text2 += '</select></div>';


                        text2 += '<div class="form-group"><label for="w_actExpre">Seleccione Acción </label><select id="w_actExpre" name="w_actExpre" class="form-control custom-select-sm" required>'
                        if (w_actExpre == "") {
                            text2 += '<option value="" selected>Seleccione</option>';
                        } else {
                            text2 += '<option value="">Seleccione</option>';
                        }
                        if (w_actExpre == "r") {
                            text2 += '<option value="r" selected>Ejecutar Acción</option>';
                        } else {
                            text2 += '<option value="r">Ejecutar Acción</option>';
                        }
                        if (w_actExpre == "s") {
                            text2 += '<option value="s" selected>Ingresar Opción</option>';
                        } else {
                            text2 += '<option value="s">Ingresar Opción</option>';
                        }
                        if (w_actExpre == "i") {
                            text2 += '<option value="i" selected>Imprimir pantalla</option>';
                        } else {
                            text2 += '<option value="i">Imprimir pantalla</option>';
                        }
                        if (w_actExpre == "e") {
                            text2 += '<option value="e" selected>Imprimir Pantalla Error</option>';
                        } else {
                            text2 += '<option value="e">Imprimir Pantalla Error</option>';
                        }



                        text2 += '</select></div>';

                        if (w_modPantalla != 'conec') {
                            text2 += '<div class="form-group"><label for="w_accionar">Acciones del Teclado </label><select id="w_accionar" name="w_accionar" class="form-control custom-select-sm" required>'
                            for (var itemAccion in data.accionTeclado) {
                                if (data.accionTeclado[itemAccion].valor == w_accionar) {
                                    text2 += '<option value="' + data.accionTeclado[itemAccion].valor + '" selected>' + data.accionTeclado[itemAccion].description + '</option>';
                                } else {
                                    text2 += '<option value="' + data.accionTeclado[itemAccion].valor + '" >' + data.accionTeclado[itemAccion].description + '</option>';
                                }
                            }
                            text2 += '</select></div>'
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
        $.blockUI({message: '<h4> Cargando...</h4>'});
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
            $.ajax({
                type: "GET",
                url: "/robotadmin/findByTransaccionIniIdAjax?idTransaccion=" + idsele,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    if (data.flag) {
                        $("#modalEliminarTransaccion").modal('show');
                        $("#idTransDelet").val(idsele);
                        $("#alert-delete-error").hide();
//                        $("#alert-delete-ok").show();
//                        $("#alert-delete-ok").html("Se Elimina la transaccion corectamente <br>");  
                    } else {
                        $("#alert-delete-ok").hide();
                        $("#alert-delete-error").show();
                        $("#alert-delete-error").html("<strong>Error La Transacción No puedes ser eliminada en vista de posee transacciones despendientes  </strong> <br>");
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
        $("#nav-tab-simu").empty();
        $("#nav-tabContent-simu").empty();
        var accion = $("#trasaccionSimulador").val();
        if (accion > 0) {
            $.blockUI({message: '<h4> Cargando...</h4>'});
            $.ajax({
                type: "GET",
                url: "/robotadmin/textopantallaByIdTrans?idTransaccion=" + accion,
                dataType: 'json',
                timeout: 300000,
                success: function (data) {
                    $.unblockUI();
                    if (data.length > 0) {
                        console.log("SUCCESS: ", data);
                        var interval = 4 * 1000;
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
            $("#alert-simulador").html("Seleccione una Opción del Menú");
        }
    });


    $("#trasaccionSimuladorOnLine").change(function (event) {
        event.preventDefault();
        var accion = $("#trasaccionSimuladorOnLine").val();
        $("#nav-tab-simu").empty();
        $("#nav-tabContent-simu").empty()
        if (accion > 0) {
            $.blockUI({message: '<h4> Cargando...</h4>'});
            $.ajax({

                type: "GET",
                url: "/robotadmin/textopantallaByIdTrans2?idTransaccion=" + accion,
                dataType: 'json',
                timeout: 500000,
                success: function (data) {
                    $.unblockUI();
                    if (data.length > 0) {
                        console.log("SUCCESS: ", data);
                        var interval = 4 * 1000;
                        var text2 = '';
                        var text3 = '';
                        for (var i = 0; i <= data.length - 1; i++) {
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
                                //$.unblockUI();
                            }, interval * i, i);



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
            //$.unblockUI();
        } else {
            $("#alert-simulador").show();
            $("#alert-simulador").html("seleccione una opcion del menu");
            // $.unblockUI();
        }
    });
    $("#trasaccionSimulador2").change(function (event) {
        event.preventDefault();
        var accion = $("#trasaccionSimuladorOnLine").val();
        if (accion > 0) {
            $.blockUI({message: '<h4> Cargando...</h4>'});
            $.ajax({
                type: "GET",
                url: "/robotadmin/textopantallaByIdTrans?idTransaccion=" + accion,
                dataType: 'json',
                timeout: 100000,
                success: function (data) {
                    $.unblockUI();
                    if (data.length > 0) {
                        console.log("SUCCESS: ", data);
                        var interval = 4 * 1000;
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


