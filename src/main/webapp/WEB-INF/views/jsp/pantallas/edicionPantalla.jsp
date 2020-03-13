
<div  class="container" id="divPrimer" > 

    <div class="alert alert-dark titulos-border-style " role="alert">
        <div class="row" role="alert">
            <div class="col-11">
                <strong> Edicion de Pantallas</strong>
            </div>
            <div class="col-1 align-content-md-end">
                <a id="btnHome" title="Salir/Home" href="home" class="btn btn-outline-primary btn-sm"  role="button" aria-pressed="true"><i class="fas fa-sign-out-alt"></i></a>
            </div>
        </div>   
    </div>
    <div>
        <div class="col-12">
            <h6> <strong>Transaccion : </strong>${tranSave.nombre}</h6>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-12">
            <table  id="tablaEdicionPantalla" class="table table-striped">
                <thead>
                    <tr style="text-align: center;">
                        <th scope="col">Pantalla</th>
                        <th scope="col">tipo de pantalla</th>
                        <th scope="col">N�. de Elementos </th>
                        <th scope="col">Opciones</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${listPantallaEdit}" var="pantEdit">
                    <tr style="text-align: center;">
                        <td>Nro. ${pantEdit.pantallaNumero}</td>
                        <td>${pantEdit.waccionar}</td>
                        <td>${pantEdit.inputs.size() - 2}</td>
                        <td>
                            <a id="${pantEdit.id}" title="Editar" class="far fa-edit" aria-hidden="true" style="color: #666666; cursor:pointer;"></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- modal -->
    <div class="modal" id="modalEditarPantalla">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title" id="headerModal">Editar Pantalla </h4>
                </div>
                <form id="formUpdatePantalla" action="editarPantalla" method="POST">    <!-- Modal body -->
                    <div class="modal-body">
                        <div id="formInput" >
                        </div>  
                        
                        
                        
                       <!-- <div class="form-group">
                            <label for="w_accionar">Acciones del Teclado</label>
                            <select id="w_accionar" name="w_accionar" class="form-control custom-select-sm" required="">
                                <option value="">Seleccione</option>
                                <option value="[enter]">ENTER
                                </option><option value="[pf1]">PF1
                                </option><option value="[pf2]">PF2
                                </option><option value="[pf3]">PF3
                                </option><option value="[pf4]">PF4
                                </option><option value="[pf5]">PF5
                                </option><option value="[pf6]">PF6
                                </option><option value="[pf7]">PF7
                                </option><option value="[pf8]">PF8
                                </option><option value="[pf9]">PF9
                                </option><option value="[pf10]">PF10
                                </option><option value="[pf11]">PF11
                                </option><option value="[pf12]">PF12
                                </option><option value="[pf13]">PF13
                                </option><option value="[pf14]">PF14
                                </option><option value="[pf15]">PF15
                                </option><option value="[pf16]">PF16
                                </option><option value="[pf17]">PF17
                                </option><option value="[pf18]">PF18
                                </option><option value="[pf19]">PF19
                                </option><option value="[pf20]">PF20
                                </option><option value="[pf21]">PF21
                                </option><option value="[pf22]">PF22
                                </option><option value="[pf23]">PF23
                                </option><option value="[pf24]">PF24
                                </option><option value="[prevword]">PREVIOUS_WORD
                                </option><option value="[print]">PRINT
                                </option><option value="[reset]">RESET
                                </option><option value="[right]">RIGHT
                                </option><option value="[sysreq]	">SYSREQ
                                </option><option value="[tab]">TAB
                                </option><option value="[up]">UP
                                </option><option value="[backtab]">BACK_TAB
                                </option><option value="[bof]">BEGIN_OF_FIELD
                                </option><option value="[clear]">CLEAR
                                </option><option value="[delete]">DELETE
                                </option><option value="[down]">DOWN
                                </option><option value="[copy]">COPY
                                </option><option value="[eof]">END_OF_FIELD
                                </option><option value="[eraseeof]">ERASE_EOF
                                </option><option value="[erasefld]">ERASE_FIELD
                                </option><option value="[fldext]">FIELD_EXIT
                                </option><option value="[field-]">FIELD_MINUS
                                </option><option value="[field+]">FIELD_PLUS
                                </option><option value="[help]">HELP
                                </option><option value="[home]">HOME
                                </option><option value="[insert]">INSERT
                                </option><option value="[keypad,]">KEYPAD_COMMA
                                </option><option value="[keypad-]">KEYPAD_MINUS
                                </option><option value="[keypad.]">KEYPAD_PERIOD
                                </option><option value="[keypad0]">KEYPAD0
                                </option><option value="[keypad1]">KEYPAD1
                                </option><option value="[keypad2]">KEYPAD2
                                </option><option value="[keypad3]">KEYPAD3
                                </option><option value="[keypad4]">KEYPAD4
                                </option><option value="[keypad5]">KEYPAD5
                                </option><option value="[keypad6]">KEYPAD6
                                </option><option value="[keypad7]">KEYPAD7
                                </option><option value="[keypad8]">KEYPAD8
                                </option><option value="[keypad9]">KEYPAD9
                                </option><option value="[left]	">LEFT
                                </option><option value="[nextword]">NEXT_WORD
                                </option><option value="[pa1]">PA1
                                </option><option value="[pa2]">PA2
                                </option><option value="[pa3]">PA3
                                </option><option value="[paste]">PASTE
                                </option><option value="[pgdown]">PAGE_DOWN
                                </option><option value="[pgup]">PAGE_UP
                                </option><option value="[enter]">ENTER
                                </option><option value="[pf1]">PF1
                                </option><option value="[pf2]">PF2
                                </option><option value="[pf3]">PF3
                                </option><option value="[pf4]">PF4
                                </option><option value="[pf5]">PF5
                                </option><option value="[pf6]">PF6
                                </option><option value="[pf7]">PF7
                                </option><option value="[pf8]">PF8
                                </option><option value="[pf9]">PF9
                                </option><option value="[pf10]">PF10
                                </option><option value="[pf11]">PF11
                                </option><option value="[pf12]">PF12
                                </option><option value="[pf13]">PF13
                                </option><option value="[pf14]">PF14
                                </option><option value="[pf15]">PF15
                                </option><option value="[pf16]">PF16
                                </option><option value="[pf17]">PF17
                                </option><option value="[pf18]">PF18
                                </option><option value="[pf19]">PF19
                                </option><option value="[pf20]">PF20
                                </option><option value="[pf21]">PF21
                                </option><option value="[pf22]">PF22
                                </option><option value="[pf23]">PF23
                                </option><option value="[pf24]">PF24
                                </option><option value="[prevword]">PREVIOUS_WORD
                                </option><option value="[print]">PRINT
                                </option><option value="[reset]">RESET
                                </option><option value="[right]">RIGHT
                                </option><option value="[sysreq]	">SYSREQ
                                </option><option value="[tab]">TAB
                                </option><option value="[up]">UP
                                </option><option value="[backtab]">BACK_TAB
                                </option><option value="[bof]">BEGIN_OF_FIELD
                                </option><option value="[clear]">CLEAR
                                </option><option value="[delete]">DELETE
                                </option><option value="[down]">DOWN
                                </option><option value="[copy]">COPY
                                </option><option value="[eof]">END_OF_FIELD
                                </option><option value="[eraseeof]">ERASE_EOF
                                </option><option value="[erasefld]">ERASE_FIELD
                                </option><option value="[fldext]">FIELD_EXIT
                                </option><option value="[field-]">FIELD_MINUS
                                </option><option value="[field+]">FIELD_PLUS
                                </option><option value="[help]">HELP
                                </option><option value="[home]">HOME
                                </option><option value="[insert]">INSERT
                                </option><option value="[keypad,]">KEYPAD_COMMA
                                </option><option value="[keypad-]">KEYPAD_MINUS
                                </option><option value="[keypad.]">KEYPAD_PERIOD
                                </option><option value="[keypad0]">KEYPAD0
                                </option><option value="[keypad1]">KEYPAD1
                                </option><option value="[keypad2]">KEYPAD2
                                </option><option value="[keypad3]">KEYPAD3
                                </option><option value="[keypad4]">KEYPAD4
                                </option><option value="[keypad5]">KEYPAD5
                                </option><option value="[keypad6]">KEYPAD6
                                </option><option value="[keypad7]">KEYPAD7
                                </option><option value="[keypad8]">KEYPAD8
                                </option><option value="[keypad9]">KEYPAD9
                                </option><option value="[left]	">LEFT
                                </option><option value="[nextword]">NEXT_WORD
                                </option><option value="[pa1]">PA1
                                </option><option value="[pa2]">PA2
                                </option><option value="[pa3]">PA3
                                </option><option value="[paste]">PASTE
                                </option><option value="[pgdown]">PAGE_DOWN
                                </option><option value="[pgup]">PAGE_UP
                                </option><option value="[enter]">ENTER
                                </option><option value="[pf1]">PF1
                                </option><option value="[pf2]">PF2
                                </option><option value="[pf3]">PF3
                                </option><option value="[pf4]">PF4
                                </option><option value="[pf5]">PF5
                                </option><option value="[pf6]">PF6
                                </option><option value="[pf7]">PF7
                                </option><option value="[pf8]">PF8
                                </option><option value="[pf9]">PF9
                                </option><option value="[pf10]">PF10
                                </option><option value="[pf11]">PF11
                                </option><option value="[pf12]">PF12
                                </option><option value="[pf13]">PF13
                                </option><option value="[pf14]">PF14
                                </option><option value="[pf15]">PF15
                                </option><option value="[pf16]">PF16
                                </option><option value="[pf17]">PF17
                                </option><option value="[pf18]">PF18
                                </option><option value="[pf19]">PF19
                                </option><option value="[pf20]">PF20
                                </option><option value="[pf21]">PF21
                                </option><option value="[pf22]">PF22
                                </option><option value="[pf23]">PF23
                                </option><option value="[pf24]">PF24
                                </option><option value="[prevword]">PREVIOUS_WORD
                                </option><option value="[print]">PRINT
                                </option><option value="[reset]">RESET
                                </option><option value="[right]">RIGHT
                                </option><option value="[sysreq]	">SYSREQ
                                </option><option value="[tab]">TAB
                                </option><option value="[up]">UP
                                </option><option value="[backtab]">BACK_TAB
                                </option><option value="[bof]">BEGIN_OF_FIELD
                                </option><option value="[clear]">CLEAR
                                </option><option value="[delete]">DELETE
                                </option><option value="[down]">DOWN
                                </option><option value="[copy]">COPY
                                </option><option value="[eof]">END_OF_FIELD
                                </option><option value="[eraseeof]">ERASE_EOF
                                </option><option value="[erasefld]">ERASE_FIELD
                                </option><option value="[fldext]">FIELD_EXIT
                                </option><option value="[field-]">FIELD_MINUS
                                </option><option value="[field+]">FIELD_PLUS
                                </option><option value="[help]">HELP
                                </option><option value="[home]">HOME
                                </option><option value="[insert]">INSERT
                                </option><option value="[keypad,]">KEYPAD_COMMA
                                </option><option value="[keypad-]">KEYPAD_MINUS
                                </option><option value="[keypad.]">KEYPAD_PERIOD
                                </option><option value="[keypad0]">KEYPAD0
                                </option><option value="[keypad1]">KEYPAD1
                                </option><option value="[keypad2]">KEYPAD2
                                </option><option value="[keypad3]">KEYPAD3
                                </option><option value="[keypad4]">KEYPAD4
                                </option><option value="[keypad5]">KEYPAD5
                                </option><option value="[keypad6]">KEYPAD6
                                </option><option value="[keypad7]">KEYPAD7
                                </option><option value="[keypad8]">KEYPAD8
                                </option><option value="[keypad9]">KEYPAD9
                                </option><option value="[left]	">LEFT
                                </option><option value="[nextword]">NEXT_WORD
                                </option><option value="[pa1]">PA1
                                </option><option value="[pa2]">PA2
                                </option><option value="[pa3]">PA3
                                </option><option value="[paste]">PASTE
                                </option><option value="[pgdown]">PAGE_DOWN
                                </option><option value="[pgup]">PAGE_UP
                                </option><option value="[enter]">ENTER
                                </option><option value="[pf1]">PF1
                                </option><option value="[pf2]">PF2
                                </option><option value="[pf3]">PF3
                                </option><option value="[pf4]">PF4
                                </option><option value="[pf5]">PF5
                                </option><option value="[pf6]">PF6
                                </option><option value="[pf7]">PF7
                                </option><option value="[pf8]">PF8
                                </option><option value="[pf9]">PF9
                                </option><option value="[pf10]">PF10
                                </option><option value="[pf11]">PF11
                                </option><option value="[pf12]">PF12
                                </option><option value="[pf13]">PF13
                                </option><option value="[pf14]">PF14
                                </option><option value="[pf15]">PF15
                                </option><option value="[pf16]">PF16
                                </option><option value="[pf17]">PF17
                                </option><option value="[pf18]">PF18
                                </option><option value="[pf19]">PF19
                                </option><option value="[pf20]">PF20
                                </option><option value="[pf21]">PF21
                                </option><option value="[pf22]">PF22
                                </option><option value="[pf23]">PF23
                                </option><option value="[pf24]">PF24
                                </option><option value="[prevword]">PREVIOUS_WORD
                                </option><option value="[print]">PRINT
                                </option><option value="[reset]">RESET
                                </option><option value="[right]">RIGHT
                                </option><option value="[sysreq]	">SYSREQ
                                </option><option value="[tab]">TAB
                                </option><option value="[up]">UP
                                </option><option value="[backtab]">BACK_TAB
                                </option><option value="[bof]">BEGIN_OF_FIELD
                                </option><option value="[clear]">CLEAR
                                </option><option value="[delete]">DELETE
                                </option><option value="[down]">DOWN
                                </option><option value="[copy]">COPY
                                </option><option value="[eof]">END_OF_FIELD
                                </option><option value="[eraseeof]">ERASE_EOF
                                </option><option value="[erasefld]">ERASE_FIELD
                                </option><option value="[fldext]">FIELD_EXIT
                                </option><option value="[field-]">FIELD_MINUS
                                </option><option value="[field+]">FIELD_PLUS
                                </option><option value="[help]">HELP
                                </option><option value="[home]">HOME
                                </option><option value="[insert]">INSERT
                                </option><option value="[keypad,]">KEYPAD_COMMA
                                </option><option value="[keypad-]">KEYPAD_MINUS
                                </option><option value="[keypad.]">KEYPAD_PERIOD
                                </option><option value="[keypad0]">KEYPAD0
                                </option><option value="[keypad1]">KEYPAD1
                                </option><option value="[keypad2]">KEYPAD2
                                </option><option value="[keypad3]">KEYPAD3
                                </option><option value="[keypad4]">KEYPAD4
                                </option><option value="[keypad5]">KEYPAD5
                                </option><option value="[keypad6]">KEYPAD6
                                </option><option value="[keypad7]">KEYPAD7
                                </option><option value="[keypad8]">KEYPAD8
                                </option><option value="[keypad9]">KEYPAD9
                                </option><option value="[left]	">LEFT
                                </option><option value="[nextword]">NEXT_WORD
                                </option><option value="[pa1]">PA1
                                </option><option value="[pa2]">PA2
                                </option><option value="[pa3]">PA3
                                </option><option value="[paste]">PASTE
                                </option><option value="[pgdown]">PAGE_DOWN
                                </option><option value="[pgup]">PAGE_UP
                                </option><option value="[enter]">ENTER
                                </option><option value="[pf1]">PF1
                                </option><option value="[pf2]">PF2
                                </option><option value="[pf3]">PF3
                                </option><option value="[pf4]">PF4
                                </option><option value="[pf5]">PF5
                                </option><option value="[pf6]">PF6
                                </option><option value="[pf7]">PF7
                                </option><option value="[pf8]">PF8
                                </option><option value="[pf9]">PF9
                                </option><option value="[pf10]">PF10
                                </option><option value="[pf11]">PF11
                                </option><option value="[pf12]">PF12
                                </option><option value="[pf13]">PF13
                                </option><option value="[pf14]">PF14
                                </option><option value="[pf15]">PF15
                                </option><option value="[pf16]">PF16
                                </option><option value="[pf17]">PF17
                                </option><option value="[pf18]">PF18
                                </option><option value="[pf19]">PF19
                                </option><option value="[pf20]">PF20
                                </option><option value="[pf21]">PF21
                                </option><option value="[pf22]">PF22
                                </option><option value="[pf23]">PF23
                                </option><option value="[pf24]">PF24
                                </option><option value="[prevword]">PREVIOUS_WORD
                                </option><option value="[print]">PRINT
                                </option><option value="[reset]">RESET
                                </option><option value="[right]">RIGHT
                                </option><option value="[sysreq]	">SYSREQ
                                </option><option value="[tab]">TAB
                                </option><option value="[up]">UP
                                </option><option value="[backtab]">BACK_TAB
                                </option><option value="[bof]">BEGIN_OF_FIELD
                                </option><option value="[clear]">CLEAR
                                </option><option value="[delete]">DELETE
                                </option><option value="[down]">DOWN
                                </option><option value="[copy]">COPY
                                </option><option value="[eof]">END_OF_FIELD
                                </option><option value="[eraseeof]">ERASE_EOF
                                </option><option value="[erasefld]">ERASE_FIELD
                                </option><option value="[fldext]">FIELD_EXIT
                                </option><option value="[field-]">FIELD_MINUS
                                </option><option value="[field+]">FIELD_PLUS
                                </option><option value="[help]">HELP
                                </option><option value="[home]">HOME
                                </option><option value="[insert]">INSERT
                                </option><option value="[keypad,]">KEYPAD_COMMA
                                </option><option value="[keypad-]">KEYPAD_MINUS
                                </option><option value="[keypad.]">KEYPAD_PERIOD
                                </option><option value="[keypad0]">KEYPAD0
                                </option><option value="[keypad1]">KEYPAD1
                                </option><option value="[keypad2]">KEYPAD2
                                </option><option value="[keypad3]">KEYPAD3
                                </option><option value="[keypad4]">KEYPAD4
                                </option><option value="[keypad5]">KEYPAD5
                                </option><option value="[keypad6]">KEYPAD6
                                </option><option value="[keypad7]">KEYPAD7
                                </option><option value="[keypad8]">KEYPAD8
                                </option><option value="[keypad9]">KEYPAD9
                                </option><option value="[left]	">LEFT
                                </option><option value="[nextword]">NEXT_WORD
                                </option><option value="[pa1]">PA1
                                </option><option value="[pa2]">PA2
                                </option><option value="[pa3]">PA3
                                </option><option value="[paste]">PASTE
                                </option><option value="[pgdown]">PAGE_DOWN
                                </option><option value="[pgup]">PAGE_UP
                                </option><option value="[enter]">ENTER
                                </option><option value="[pf1]">PF1
                                </option><option value="[pf2]">PF2
                                </option><option value="[pf3]">PF3
                                </option><option value="[pf4]">PF4
                                </option><option value="[pf5]">PF5
                                </option><option value="[pf6]">PF6
                                </option><option value="[pf7]">PF7
                                </option><option value="[pf8]">PF8
                                </option><option value="[pf9]">PF9
                                </option><option value="[pf10]">PF10
                                </option><option value="[pf11]">PF11
                                </option><option value="[pf12]">PF12
                                </option><option value="[pf13]">PF13
                                </option><option value="[pf14]">PF14
                                </option><option value="[pf15]">PF15
                                </option><option value="[pf16]">PF16
                                </option><option value="[pf17]">PF17
                                </option><option value="[pf18]">PF18
                                </option><option value="[pf19]">PF19
                                </option><option value="[pf20]">PF20
                                </option><option value="[pf21]">PF21
                                </option><option value="[pf22]">PF22
                                </option><option value="[pf23]">PF23
                                </option><option value="[pf24]">PF24
                                </option><option value="[prevword]">PREVIOUS_WORD
                                </option><option value="[print]">PRINT
                                </option><option value="[reset]">RESET
                                </option><option value="[right]">RIGHT
                                </option><option value="[sysreq]	">SYSREQ
                                </option><option value="[tab]">TAB
                                </option><option value="[up]">UP
                                </option><option value="[backtab]">BACK_TAB
                                </option><option value="[bof]">BEGIN_OF_FIELD
                                </option><option value="[clear]">CLEAR
                                </option><option value="[delete]">DELETE
                                </option><option value="[down]">DOWN
                                </option><option value="[copy]">COPY
                                </option><option value="[eof]">END_OF_FIELD
                                </option><option value="[eraseeof]">ERASE_EOF
                                </option><option value="[erasefld]">ERASE_FIELD
                                </option><option value="[fldext]">FIELD_EXIT
                                </option><option value="[field-]">FIELD_MINUS
                                </option><option value="[field+]">FIELD_PLUS
                                </option><option value="[help]">HELP
                                </option><option value="[home]">HOME
                                </option><option value="[insert]">INSERT
                                </option><option value="[keypad,]">KEYPAD_COMMA
                                </option><option value="[keypad-]">KEYPAD_MINUS
                                </option><option value="[keypad.]">KEYPAD_PERIOD
                                </option><option value="[keypad0]">KEYPAD0
                                </option><option value="[keypad1]">KEYPAD1
                                </option><option value="[keypad2]">KEYPAD2
                                </option><option value="[keypad3]">KEYPAD3
                                </option><option value="[keypad4]">KEYPAD4
                                </option><option value="[keypad5]">KEYPAD5
                                </option><option value="[keypad6]">KEYPAD6
                                </option><option value="[keypad7]">KEYPAD7
                                </option><option value="[keypad8]">KEYPAD8
                                </option><option value="[keypad9]">KEYPAD9
                                </option><option value="[left]	">LEFT
                                </option><option value="[nextword]">NEXT_WORD
                                </option><option value="[pa1]">PA1
                                </option><option value="[pa2]">PA2
                                </option><option value="[pa3]">PA3
                                </option><option value="[paste]">PASTE
                                </option><option value="[pgdown]">PAGE_DOWN
                                </option><option value="[pgup]">PAGE_UP
                                </option><option value="[enter]">ENTER
                                </option><option value="[pf1]">PF1
                                </option><option value="[pf2]">PF2
                                </option><option value="[pf3]">PF3
                                </option><option value="[pf4]">PF4
                                </option><option value="[pf5]">PF5
                                </option><option value="[pf6]">PF6
                                </option><option value="[pf7]">PF7
                                </option><option value="[pf8]">PF8
                                </option><option value="[pf9]">PF9
                                </option><option value="[pf10]">PF10
                                </option><option value="[pf11]">PF11
                                </option><option value="[pf12]">PF12
                                </option><option value="[pf13]">PF13
                                </option><option value="[pf14]">PF14
                                </option><option value="[pf15]">PF15
                                </option><option value="[pf16]">PF16
                                </option><option value="[pf17]">PF17
                                </option><option value="[pf18]">PF18
                                </option><option value="[pf19]">PF19
                                </option><option value="[pf20]">PF20
                                </option><option value="[pf21]">PF21
                                </option><option value="[pf22]">PF22
                                </option><option value="[pf23]">PF23
                                </option><option value="[pf24]">PF24
                                </option><option value="[prevword]">PREVIOUS_WORD
                                </option><option value="[print]">PRINT
                                </option><option value="[reset]">RESET
                                </option><option value="[right]">RIGHT
                                </option><option value="[sysreq]	">SYSREQ
                                </option><option value="[tab]">TAB
                                </option><option value="[up]">UP
                                </option><option value="[backtab]">BACK_TAB
                                </option><option value="[bof]">BEGIN_OF_FIELD
                                </option><option value="[clear]">CLEAR
                                </option><option value="[delete]">DELETE
                                </option><option value="[down]">DOWN
                                </option><option value="[copy]">COPY
                                </option><option value="[eof]">END_OF_FIELD
                                </option><option value="[eraseeof]">ERASE_EOF
                                </option><option value="[erasefld]">ERASE_FIELD
                                </option><option value="[fldext]">FIELD_EXIT
                                </option><option value="[field-]">FIELD_MINUS
                                </option><option value="[field+]">FIELD_PLUS
                                </option><option value="[help]">HELP
                                </option><option value="[home]">HOME
                                </option><option value="[insert]">INSERT
                                </option><option value="[keypad,]">KEYPAD_COMMA
                                </option><option value="[keypad-]">KEYPAD_MINUS
                                </option><option value="[keypad.]">KEYPAD_PERIOD
                                </option><option value="[keypad0]">KEYPAD0
                                </option><option value="[keypad1]">KEYPAD1
                                </option><option value="[keypad2]">KEYPAD2
                                </option><option value="[keypad3]">KEYPAD3
                                </option><option value="[keypad4]">KEYPAD4
                                </option><option value="[keypad5]">KEYPAD5
                                </option><option value="[keypad6]">KEYPAD6
                                </option><option value="[keypad7]">KEYPAD7
                                </option><option value="[keypad8]">KEYPAD8
                                </option><option value="[keypad9]">KEYPAD9
                                </option><option value="[left]	">LEFT
                                </option><option value="[nextword]">NEXT_WORD
                                </option><option value="[pa1]">PA1
                                </option><option value="[pa2]">PA2
                                </option><option value="[pa3]">PA3
                                </option><option value="[paste]">PASTE
                                </option><option value="[pgdown]">PAGE_DOWN
                                </option><option value="[pgup]">PAGE_UP
                                </option><option value="[enter]">ENTER
                                </option><option value="[pf1]">PF1
                                </option><option value="[pf2]">PF2
                                </option><option value="[pf3]">PF3
                                </option><option value="[pf4]">PF4
                                </option><option value="[pf5]">PF5
                                </option><option value="[pf6]">PF6
                                </option><option value="[pf7]">PF7
                                </option><option value="[pf8]">PF8
                                </option><option value="[pf9]">PF9
                                </option><option value="[pf10]">PF10
                                </option><option value="[pf11]">PF11
                                </option><option value="[pf12]">PF12
                                </option><option value="[pf13]">PF13
                                </option><option value="[pf14]">PF14
                                </option><option value="[pf15]">PF15
                                </option><option value="[pf16]">PF16
                                </option><option value="[pf17]">PF17
                                </option><option value="[pf18]">PF18
                                </option><option value="[pf19]">PF19
                                </option><option value="[pf20]">PF20
                                </option><option value="[pf21]">PF21
                                </option><option value="[pf22]">PF22
                                </option><option value="[pf23]">PF23
                                </option><option value="[pf24]">PF24
                                </option><option value="[prevword]">PREVIOUS_WORD
                                </option><option value="[print]">PRINT
                                </option><option value="[reset]">RESET
                                </option><option value="[right]">RIGHT
                                </option><option value="[sysreq]	">SYSREQ
                                </option><option value="[tab]">TAB
                                </option><option value="[up]">UP
                                </option><option value="[backtab]">BACK_TAB
                                </option><option value="[bof]">BEGIN_OF_FIELD
                                </option><option value="[clear]">CLEAR
                                </option><option value="[delete]">DELETE
                                </option><option value="[down]">DOWN
                                </option><option value="[copy]">COPY
                                </option><option value="[eof]">END_OF_FIELD
                                </option><option value="[eraseeof]">ERASE_EOF
                                </option><option value="[erasefld]">ERASE_FIELD
                                </option><option value="[fldext]">FIELD_EXIT
                                </option><option value="[field-]">FIELD_MINUS
                                </option><option value="[field+]">FIELD_PLUS
                                </option><option value="[help]">HELP
                                </option><option value="[home]">HOME
                                </option><option value="[insert]">INSERT
                                </option><option value="[keypad,]">KEYPAD_COMMA
                                </option><option value="[keypad-]">KEYPAD_MINUS
                                </option><option value="[keypad.]">KEYPAD_PERIOD
                                </option><option value="[keypad0]">KEYPAD0
                                </option><option value="[keypad1]">KEYPAD1
                                </option><option value="[keypad2]">KEYPAD2
                                </option><option value="[keypad3]">KEYPAD3
                                </option><option value="[keypad4]">KEYPAD4
                                </option><option value="[keypad5]">KEYPAD5
                                </option><option value="[keypad6]">KEYPAD6
                                </option><option value="[keypad7]">KEYPAD7
                                </option><option value="[keypad8]">KEYPAD8
                                </option><option value="[keypad9]">KEYPAD9
                                </option><option value="[left]	">LEFT
                                </option><option value="[nextword]">NEXT_WORD
                                </option><option value="[pa1]">PA1
                                </option><option value="[pa2]">PA2
                                </option><option value="[pa3]">PA3
                                </option><option value="[paste]">PASTE
                                </option><option value="[pgdown]">PAGE_DOWN
                                </option><option value="[pgup]">PAGE_UP
                                </option>
                            </select>
                        </div-->

                        <div class="form-group">
                            <button type="submit" id="btnUpdatePantalla" class="btn btn-dark btn-sm btn-block">Actualizar</button
                        </div>
                    </div>
                </form>
                <!-- Modal footer -->
                <div class="modal-footer">

                </div>

            </div>
        </div>
    </div>                    
  </div>
</div>
