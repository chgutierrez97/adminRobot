package com.accusy.robotpro.robotadmin.controller;

import com.accusy.robotpro.robotadmin.dto.AccionKeyboarDto;
import com.accusy.robotpro.robotadmin.dto.ConexionAsDto;
import com.accusy.robotpro.robotadmin.dto.DatosFormDto;
import com.accusy.robotpro.robotadmin.dto.InputDto;
import com.accusy.robotpro.robotadmin.dto.PantallaDto;
import com.accusy.robotpro.robotadmin.model.EnviarInformacion;
import com.accusy.robotpro.robotadmin.model.EnviarTransaccionForm;
import com.accusy.robotpro.robotadmin.model.InputIO;
import com.accusy.robotpro.robotadmin.model.PantallaIO;
import com.accusy.robotpro.robotadmin.model.TextoPantallaIO;
import com.accusy.robotpro.robotadmin.model.TransaccionIO;
import com.accusy.robotpro.robotadmin.model.TransaccionOI;
import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import org.tn5250j.Session5250;
import org.tn5250j.beans.ProtocolBean;
import org.tn5250j.framework.tn5250.Screen5250;
import org.tn5250j.framework.tn5250.ScreenField;
import org.tn5250j.framework.tn5250.ScreenFields;
import org.tn5250j.framework.tn5250.ScreenPlanes;

/**
 *
 * @author Christian Gutierrez
 */
//Declare this is a session scope bean.
//@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
@SessionScope
@Controller
public class AdminRobotController {

    @Autowired
    ServicesRobot service1;

    public List<ConexionAsDto> conexiones;
    public List<TransaccionIO> trans = new ArrayList<>();
    public List<TransaccionIO> transIni = new ArrayList<>();
    public static Session5250 sessions = null;
    public Screen5250 screen;
    public String pantalla;
    public List<PantallaDto> listPatalla = new ArrayList<>();
    public List<PantallaDto> listPatallaOpcional = new ArrayList<>();
    public List<PantallaDto> listPatallaAuxiliar = new ArrayList<>();
    public List<AccionKeyboarDto> listAcciones = new ArrayList<>();
    public boolean conectado;
    public ConexionAsDto coneco;
    private String scrip;
    private TransaccionIO tranSave;
    
        @RequestMapping(value = "/textopantallaByIdTrans", method = RequestMethod.GET)
        @ResponseBody
        public  List<PantallaDto> textopantallaByIdTrans(@RequestParam Integer idTransaccion) {
        PantallaIO PantallaIOResponse = new PantallaIO();
        
        List<PantallaDto> pantallas = service1.getPantallaByIdTransaccion(idTransaccion);

        return pantallas;
    }
    
    @RequestMapping(value = "/guardarTransaccion", method = RequestMethod.POST)
    public ModelAndView guardarTransaccion(EnviarTransaccionForm transaccionForm, HttpSession session) {

        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");

        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        model.addObject("accionesLista", cargaAcciones());
        model.addObject("botonesGuardar", false);
        TransaccionOI transaccionIO = new TransaccionOI();
        transaccionIO.setAplicativoExternocol(transaccionForm.getInputNombreAplic());
        transaccionIO.setDescripcion(transaccionForm.getInputDescripcionT());
        transaccionIO.setFechaCarga(new Date());
        transaccionIO.setNombre(transaccionForm.getInputNombreT());
        transaccionIO.setTipo("" + transaccionForm.getSelectTipoTrans());
        transaccionIO.setTipoAplicativo(transaccionForm.getSelectTipoAplic());
        transaccionIO.setUsuario(user);
        transaccionIO.setTransaccionIni(transaccionForm.getSelectTransInit());
        tranSave = service1.guardarTransaccion(transaccionIO);

        if (transaccionForm.getSelectModoCrea() == 1) {

            listPatalla.clear();
            listPatallaOpcional.clear();
            listPatallaAuxiliar.clear();
            listPatallaAuxiliar.addAll(service1.getPantallaByIdTransaccion(transaccionForm.getSelectTransInit()));
            actualiza(listPatallaAuxiliar);
            model.addObject("listPantalla", listPatalla);
            if (listPatalla.size() > 2) {
                model.addObject("botonesGuardar", true);
            } else {
                model.addObject("botonesGuardar", false);
            }

        } else {
            listPatalla.clear();
            listPatallaOpcional.clear();
            listPatallaAuxiliar.clear();

            PantallaDto pant = new PantallaDto();
            List<InputDto> inputs = new ArrayList<>();

            InputDto server = new InputDto();
            server.setLabel("Nombre del servidor ");
            server.setId("field_0");
            server.setName("field_0");
            server.setType("text");
            server.setRequired(true);
            server.setValue("");
            inputs.add(server);
            InputDto usuario = new InputDto();
            usuario.setLabel("Usuario ");
            usuario.setId("field_1");
            usuario.setName("field_1");
            usuario.setType("text");
            usuario.setRequired(true);
            usuario.setValue("");
            inputs.add(usuario);
            InputDto clave = new InputDto();
            clave.setLabel("Clave de Acceso");
            clave.setId("field_2");
            clave.setName("field_2");
            clave.setType("text");
            clave.setRequired(true);
            clave.setValue("");
            inputs.add(clave);

            InputDto numPantalla = new InputDto();

            numPantalla.setId("w_numPantalla");
            numPantalla.setName("w_numPantalla");
            numPantalla.setType("hidden");
            numPantalla.setValue("" + (listPatalla.size() + 1));
            inputs.add(numPantalla);

            InputDto modPantalla = new InputDto();
            modPantalla.setId("w_modPantalla");
            modPantalla.setName("w_modPantalla");
            modPantalla.setType("hidden");
            modPantalla.setValue("conec");
            inputs.add(modPantalla);

            pant.setInputs(inputs);

            pant.setPantallaNumero(listPatalla.size() + 1);
            //pant.setId(22L);
            pant.setActive(true);
            pant.setAction("sesiosionAct");
            pant.setActiveKey(false);
            this.listPatalla.add(pant);
            model.addObject("listPantalla", listPatalla);
            model.addObject("paso", 1);
        }

        session.setAttribute("listPatalla", listPatalla);

        model.addObject("paso", 1);
        model.addObject("trans", trans);
        return model;
    }

    public void actualiza(List<PantallaDto> listaActual) {
        String[] dataForm = new String[70];
        String scrits = "";
        for (PantallaDto pantallaDto : listaActual) {

            scrits = pantallaDto.getScrips();
            dataForm = pantallaDto.getScrips().split(",");
            pantallaDto.setId(null);

            if (scrits.contains("conec")) {
                pantallaDto.setPantallaNumero(listPatalla.size() + 1);
                String host = dataForm[2];
                host = host.split(":")[1];
                String usuario = dataForm[3];
                usuario = usuario.split(":")[1];
                String clave = dataForm[4];
                clave = clave.split(":")[1];
                try {
                    screen = connect(host, usuario, clave);
                    ScreenFields sf = screen.getScreenFields();
                    Thread.sleep(2000L);
                    ScreenField userField = sf.getField(0);
                    userField.setString(usuario);
                    ScreenField passField = sf.getField(1);
                    passField.setString(clave);
                    screen.sendKeys("[enter]");
                    Thread.sleep(2000L);
                    exploreScreenFields(screen);
                    printScreen(screen);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (scrits.contains("oper")) {
                pantallaDto.setPantallaNumero(listPatalla.size() + 1);
                if (dataForm[3].split(":")[1].equals("[enter]")) {
                    ScreenFields sf = screen.getScreenFields();
                    try {
                        Thread.sleep(3000L);
                        for (int i = 4; i < dataForm.length; i++) {
                            String datos = dataForm[i];
                            String[] datoAux = datos.split(":");
                            String indice = datoAux[0].split("_")[1];
                            String valor = datoAux[1];

                            if (indice.equals("0")) {
                                ScreenField field_0 = sf.getField(0);
                                field_0.setString(valor);
                            }
                            if (indice.equals("1")) {
                                ScreenField field_1 = sf.getField(1);
                                field_1.setString(valor);
                            }
                            if (indice.equals("2")) {
                                ScreenField field_2 = sf.getField(2);
                                field_2.setString(valor);
                            }
                            if (indice.equals("3")) {
                                ScreenField field_3 = sf.getField(3);
                                field_3.setString(valor);
                            }

                            if (indice.equals("4")) {
                                ScreenField field_4 = sf.getField(4);
                                field_4.setString(valor);
                            }

                            if (indice.equals("5")) {
                                ScreenField field_5 = sf.getField(5);
                                field_5.setString(valor);
                            }

                            if (indice.equals("6")) {
                                ScreenField field_6 = sf.getField(6);
                                field_6.setString(valor);
                            }

                            if (indice.equals("7")) {
                                ScreenField field_7 = sf.getField(7);
                                field_7.setString(valor);
                            }

                            if (indice.equals("8")) {
                                ScreenField field_8 = sf.getField(8);
                                field_8.setString(valor);
                            }

                            if (indice.equals("9")) {
                                ScreenField field_9 = sf.getField(9);
                                field_9.setString(valor);
                            }

                            if (indice.equals("10")) {
                                ScreenField field_10 = sf.getField(10);
                                field_10.setString(valor);
                            }

                            if (indice.equals("11")) {
                                ScreenField field_11 = sf.getField(11);
                                field_11.setString(valor);
                            }

                            if (indice.equals("12")) {
                                ScreenField field_12 = sf.getField(12);
                                field_12.setString(valor);
                            }

                            if (indice.equals("13")) {
                                ScreenField field_13 = sf.getField(13);
                                field_13.setString(valor);
                            }

                            if (indice.equals("14")) {
                                ScreenField field_14 = sf.getField(14);
                                field_14.setString(valor);
                            }

                            if (indice.equals("15")) {
                                ScreenField field_15 = sf.getField(15);
                                field_15.setString(valor);
                            }

                            if (indice.equals("16")) {
                                ScreenField field_16 = sf.getField(16);
                                field_16.setString(valor);
                            }

                            if (indice.equals("17")) {
                                ScreenField field_17 = sf.getField(17);
                                field_17.setString(valor);
                            }

                            if (indice.equals("18")) {
                                ScreenField field_18 = sf.getField(18);
                                field_18.setString(valor);
                            }

                            if (indice.equals("19")) {
                                ScreenField field_19 = sf.getField(19);
                                field_19.setString(valor);
                            }

                            if (indice.equals("20")) {
                                ScreenField field_20 = sf.getField(20);
                                field_20.setString(valor);
                            }
                            if (indice.equals("21")) {
                                ScreenField field_21 = sf.getField(21);
                                field_21.setString(valor);
                            }

                            if (indice.equals("22")) {
                                ScreenField field_22 = sf.getField(22);
                                field_22.setString(valor);
                            }

                            if (indice.equals("23")) {
                                ScreenField field_23 = sf.getField(23);
                                field_23.setString(valor);
                            }

                            if (indice.equals("24")) {
                                ScreenField field_24 = sf.getField(24);
                                field_24.setString(valor);
                            }

                            if (indice.equals("25")) {
                                ScreenField field_25 = sf.getField(25);
                                field_25.setString(valor);
                            }

                            if (indice.equals("26")) {
                                ScreenField field_26 = sf.getField(26);
                                field_26.setString(valor);
                            }

                            if (indice.equals("27")) {
                                ScreenField field_27 = sf.getField(27);
                                field_27.setString(valor);
                            }

                            if (indice.equals("28")) {
                                ScreenField field_28 = sf.getField(28);
                                field_28.setString(valor);
                            }

                            if (indice.equals("29")) {
                                ScreenField field_29 = sf.getField(29);
                                field_29.setString(valor);
                            }

                            if (indice.equals("30")) {
                                ScreenField field_30 = sf.getField(30);
                                field_30.setString(valor);
                            }

                            if (indice.equals("31")) {
                                ScreenField field_31 = sf.getField(31);
                                field_31.setString(valor);
                            }

                            if (indice.equals("32")) {
                                ScreenField field_32 = sf.getField(32);
                                field_32.setString(valor);
                            }

                            if (indice.equals("33")) {
                                ScreenField field_33 = sf.getField(33);
                                field_33.setString(valor);
                            }

                            if (indice.equals("34")) {
                                ScreenField field_34 = sf.getField(34);
                                field_34.setString(valor);
                            }

                            if (indice.equals("35")) {
                                ScreenField field_35 = sf.getField(35);
                                field_35.setString(valor);
                            }

                            if (indice.equals("36")) {
                                ScreenField field_36 = sf.getField(36);
                                field_36.setString(valor);
                            }

                            if (indice.equals("37")) {
                                ScreenField field_37 = sf.getField(37);
                                field_37.setString(valor);
                            }

                            if (indice.equals("38")) {
                                ScreenField field_38 = sf.getField(38);
                                field_38.setString(valor);
                            }

                            if (indice.equals("39")) {
                                ScreenField field_39 = sf.getField(39);
                                field_39.setString(valor);
                            }

                            if (indice.equals("40")) {
                                ScreenField field_40 = sf.getField(40);
                                field_40.setString(valor);
                            }

                            if (indice.equals("41")) {
                                ScreenField field_41 = sf.getField(41);
                                field_41.setString(valor);
                            }

                            if (indice.equals("42")) {
                                ScreenField field_42 = sf.getField(42);
                                field_42.setString(valor);
                            }

                            if (indice.equals("43")) {
                                ScreenField field_43 = sf.getField(43);
                                field_43.setString(valor);
                            }

                            if (indice.equals("44")) {
                                ScreenField field_44 = sf.getField(44);
                                field_44.setString(valor);
                            }

                            if (indice.equals("45")) {
                                ScreenField field_45 = sf.getField(45);
                                field_45.setString(valor);
                            }

                            if (indice.equals("46")) {
                                ScreenField field_46 = sf.getField(46);
                                field_46.setString(valor);
                            }

                            if (indice.equals("47")) {
                                ScreenField field_47 = sf.getField(47);
                                field_47.setString(valor);
                            }

                            if (indice.equals("48")) {
                                ScreenField field_48 = sf.getField(48);
                                field_48.setString(valor);
                            }

                            if (indice.equals("49")) {
                                ScreenField field_49 = sf.getField(49);
                                field_49.setString(valor);
                            }
                        }
                        screen.sendKeys("[enter]");
                        Thread.sleep(3000L);
                        exploreScreenFields(screen);
                        printScreen(screen);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(AdminRobotController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            //listPatalla.add(pantallaDto);

        }
        actualizaList(dataForm, scrits);
        PantallaDto pant = new PantallaDto();
        List<InputDto> inps = exploreScreenFieldsInputs(screen);
        pant.setInputs(inps);
        pant.setListAcciones(cargaAcciones());
        List<String> texts = printScreen(screen);
        pant.setTextoPantalla(texts);
        pant.setPantallaNumero(listPatalla.size() + 1);

        pant.setActiveKey(true);
        pant.setAction("sesiosionAct");
        this.listPatalla.add(pant);
        marcarUltima();

    }

    @RequestMapping(value = "/accionSelector", method = RequestMethod.POST)
    public ModelAndView accionSelector(EnviarInformacion accion, HttpSession session) {

        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");

        if (accion.getAccionSelector() != null) {
            if (transIni == null || transIni.size() == 0 || service1.getTransacionByTipoUsuario(1, 1).size() > transIni.size()) {
                transIni.clear();
                transIni = service1.getTransacionByTipoUsuario(1, 1);
            }
            if (accion.getAccionSelector() == 1) {
                model.addObject("actividad", 1);
                model.addObject("paso", 2);
            } else if (accion.getAccionSelector() == 2) {
                if (trans == null || trans.size() == 0 || service1.getTransacionByTipoUsuario(0, 1).size() > trans.size()) {
                    trans.clear();
                    trans = service1.getTransacionByTipoUsuario(0, 1);
                }
                model.addObject("actividad", 2);
                model.addObject("paso", 2);
                model.addObject("date", new Date());
            } else if (accion.getAccionSelector() == 0) {
                model.addObject("actividad", 0);
            }
        } else {
            if (accion.getAccionCreacion() == 1) {

                int var = accion.getTransaccionInit();
                listPatalla.clear();
                listPatallaOpcional.clear();
                listPatalla.addAll(service1.getPantallaByIdTransaccion(var));
                model.addObject("listPantalla", listPatalla);
            } else if (accion.getAccionCreacion() == 2) {

                listPatalla.clear();
                listPatallaOpcional.clear();

                PantallaDto pant = new PantallaDto();
                List<InputDto> inputs = new ArrayList<>();

                InputDto server = new InputDto();
                server.setLabel("Nombre del servidor ");
                server.setId("field_0");
                server.setName("field_0");
                server.setType("text");
                server.setRequired(true);
                server.setValue("");
                inputs.add(server);
                InputDto usuario = new InputDto();
                usuario.setLabel("Usuario ");
                usuario.setId("field_1");
                usuario.setName("field_1");
                usuario.setType("text");
                usuario.setRequired(true);
                usuario.setValue("");
                inputs.add(usuario);
                InputDto clave = new InputDto();
                clave.setLabel("Clave de Acceso");
                clave.setId("field_2");
                clave.setName("field_2");
                clave.setType("text");
                clave.setRequired(true);
                clave.setValue("");
                inputs.add(clave);

                InputDto numPantalla = new InputDto();

                numPantalla.setId("w_numPantalla");
                numPantalla.setName("w_numPantalla_0");
                numPantalla.setType("hidden");
                numPantalla.setValue("" + (listPatalla.size() + 1));
                inputs.add(numPantalla);

                InputDto modPantalla = new InputDto();
                modPantalla.setId("w_modPantalla");
                modPantalla.setName("w_modPantalla");
                modPantalla.setType("hidden");
                modPantalla.setValue("conec");
                inputs.add(modPantalla);

                pant.setInputs(inputs);

                pant.setPantallaNumero(listPatalla.size() + 1);
                // pant.setId(22L);
                pant.setActive(true);
                pant.setAction("sesiosionAct");
                pant.setActiveKey(false);
                this.listPatalla.add(pant);
                model.addObject("listPantalla", listPatalla);
                model.addObject("transacciones", trans);
                model.addObject("paso", 1);
                model.addObject("botonesGuardar", false);

            }
            if (accion.getTransaccionEdit() != null) {

            }
        }
        model.addObject("trans", trans);
        model.addObject("transIni", transIni);
        return model;
    }

    @RequestMapping(value = "/emulador", method = RequestMethod.GET)
    public ModelAndView emulador(HttpSession session) {

        listPatalla.clear();
        listPatallaOpcional.clear();
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        PantallaDto pant = new PantallaDto();
        List<InputDto> inputs = new ArrayList<>();

        InputDto server = new InputDto();
        server.setLabel("Nombre del servidor ");
        server.setId("field_0");
        server.setName("field_0");
        server.setType("text");
        server.setRequired(true);
        server.setValue("");
        inputs.add(server);
        InputDto usuario = new InputDto();
        usuario.setLabel("Usuario ");
        usuario.setId("field_1");
        usuario.setName("field_1");
        usuario.setType("text");
        usuario.setRequired(true);
        usuario.setValue("");
        inputs.add(usuario);
        InputDto clave = new InputDto();
        clave.setLabel("Clave de Acceso");
        clave.setId("field_2");
        clave.setName("field_2");
        clave.setType("text");
        clave.setRequired(true);
        clave.setValue("");
        inputs.add(clave);

        InputDto numPantalla = new InputDto();

        numPantalla.setId("w_numPantalla");
        numPantalla.setName("w_numPantalla_0");
        numPantalla.setType("hidden");
        numPantalla.setValue("" + (listPatalla.size() + 1));
        inputs.add(numPantalla);

        InputDto modPantalla = new InputDto();
        modPantalla.setId("w_modPantalla");
        modPantalla.setName("w_modPantalla");
        modPantalla.setType("hidden");
        modPantalla.setValue("conec");
        inputs.add(modPantalla);

        pant.setInputs(inputs);

        pant.setPantallaNumero(listPatalla.size() + 1);
        //pant.setId(22L);
        pant.setActive(true);
        pant.setAction("sesiosionAct");
        pant.setActiveKey(false);

        this.listPatalla.add(pant);
        model.addObject("listPantalla", listPatalla);
        model.addObject("transacciones", trans);
        model.addObject("accionesLista", cargaAcciones());
        model.addObject("paso", 1);
        model.addObject("botonesGuardar", false);

        return model;
    }

    // model.addObject("botonesGuardar", false);

    /*-----------------------------------------------------------------------------*/
    @RequestMapping(value = "/sesiosionAct", method = RequestMethod.POST)
    public ModelAndView sesiosionAct(DatosFormDto datosFormulario, HttpSession session) throws InterruptedException {
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");

        model.addObject("accionesLista", cargaAcciones());
        String[] dataForm = datosFormulario.toStringFilter().split(",");
        String dataFormScrips = datosFormulario.toStringFilter();

        if (datosFormulario.getW_modPantalla().equals("exitAlt")) {
            listPatalla.clear();
            model.addObject("paso", 2);
        } else if (datosFormulario.getW_modPantalla().equals("saveLogoutAlt")) {

            if (listPatallaOpcional.size() > 0) {
                if (guardarListaPantalla(2)) {
                    model.addObject("paso", 2);
                    listPatalla.clear();
                    listPatallaOpcional.clear();
                } else {
                    model.addObject("paso", 3);
                    //activar error correspondiente en pantalla 
                }
            }
        } else if (datosFormulario.getW_modPantalla().equals("logoutAlt")) {

            listPatalla.clear();
            listPatallaOpcional.clear();
            model.addObject("paso", 2);
        } else if (datosFormulario.getW_modPantalla().equals("exit")) {
            listPatalla.clear();
            model.addObject("paso", 2);
        } else if (datosFormulario.getW_modPantalla().equals("saveLogout")) {

            if (listPatalla.size() > 2) {
                if (guardarListaPantalla(1)) {
                    model.addObject("paso", 3);
                    sessions.disconnect();
                } else {
                    model.addObject("paso", 2);
                }
            }
        } else if (datosFormulario.getW_modPantalla().equals("logout")) {
            sessions.disconnect();
            listPatalla.clear();
            model.addObject("paso", 2);
        } else {
            if (dataForm.length <= 3) {
                model.addObject("errorForm", "error debe ingresar datos complemetarios en la pantalla Paso - " + datosFormulario.getW_numPantalla());
                model.addObject("errorFlag", true);
                model.addObject("lilistPatallastPantalla", listPatalla);
            } else {
                if (datosFormulario.getW_modPantalla().equals("conec")) {
                    screen = connect(datosFormulario.getField_0(), datosFormulario.getField_1(), datosFormulario.getField_2());
                    
                    printScreen(screen);
                 
                    actualizaList(dataForm, dataFormScrips);
                    if (conectado) {

                        ScreenFields sf = screen.getScreenFields();
                        Thread.sleep(3000L);
                        ScreenField userField = sf.getField(0);
                        userField.setString(datosFormulario.getField_1());
                        ScreenField passField = sf.getField(1);
                        passField.setString(datosFormulario.getField_2());
                        screen.sendKeys("[enter]");
                        Thread.sleep(3000L);
                        exploreScreenFields(screen);
                        printScreen(screen);

                        scrip += " " + "" + datosFormulario.toStringFilter();
                        PantallaDto pant = new PantallaDto();

                        List<InputDto> inps = exploreScreenFieldsInputs(screen);
                        pant.setInputs(inps);
                        pant.setListAcciones(cargaAcciones());
                        List<String> texts = printScreen(screen);
                        pant.setTextoPantalla(texts);
                        pant.setPantallaNumero(listPatalla.size() + 1);
                        // pant.setId(22L);
                        pant.setActiveKey(true);
                        pant.setAction("sesiosionAct");
                        this.listPatalla.add(pant);
                        marcarUltima();
                        model.addObject("listPantalla", listPatalla);
                        model.addObject("errorFlag", false);
                        sf = screen.getScreenFields();
                    } else {
                        model.addObject("errorForm", "Verifique los datos ingresados no se puede conectar el emulador AS400");
                        model.addObject("errorFlag", true);
                        model.addObject("listPantalla", listPatalla);
                    }
                    if (listPatalla.size() > 2) {
                        model.addObject("botonesGuardar", true);
                    } else {
                        model.addObject("botonesGuardar", false);
                    }
                    model.addObject("paso", 1);
                } else if (datosFormulario.getW_modPantalla().equals("oper")) {
                    if (dataForm[3].split(":")[1].equals("[enter]")) {
                        ScreenFields sf = screen.getScreenFields();
                        Thread.sleep(3000L);
                        try {
                            for (int i = 4; i < dataForm.length; i++) {
                                String datos = dataForm[i];
                                String[] datoAux = datos.split(":");
                                String indice = datoAux[0].split("_")[1];
                                String valor = datoAux[1];

                                if (indice.equals("0")) {
                                    ScreenField field_0 = sf.getField(0);
                                    field_0.setString(valor);
                                }
                                if (indice.equals("1")) {
                                    ScreenField field_1 = sf.getField(1);
                                    field_1.setString(valor);
                                }
                                if (indice.equals("2")) {
                                    ScreenField field_2 = sf.getField(2);
                                    field_2.setString(valor);
                                }
                                if (indice.equals("3")) {
                                    ScreenField field_3 = sf.getField(3);
                                    field_3.setString(valor);
                                }

                                if (indice.equals("4")) {
                                    ScreenField field_4 = sf.getField(4);
                                    field_4.setString(valor);
                                }

                                if (indice.equals("5")) {
                                    ScreenField field_5 = sf.getField(5);
                                    field_5.setString(valor);
                                }

                                if (indice.equals("6")) {
                                    ScreenField field_6 = sf.getField(6);
                                    field_6.setString(valor);
                                }

                                if (indice.equals("7")) {
                                    ScreenField field_7 = sf.getField(7);
                                    field_7.setString(valor);
                                }

                                if (indice.equals("8")) {
                                    ScreenField field_8 = sf.getField(8);
                                    field_8.setString(valor);
                                }

                                if (indice.equals("9")) {
                                    ScreenField field_9 = sf.getField(9);
                                    field_9.setString(valor);
                                }

                                if (indice.equals("10")) {
                                    ScreenField field_10 = sf.getField(10);
                                    field_10.setString(valor);
                                }

                                if (indice.equals("11")) {
                                    ScreenField field_11 = sf.getField(11);
                                    field_11.setString(valor);
                                }

                                if (indice.equals("12")) {
                                    ScreenField field_12 = sf.getField(12);
                                    field_12.setString(valor);
                                }

                                if (indice.equals("13")) {
                                    ScreenField field_13 = sf.getField(13);
                                    field_13.setString(valor);
                                }

                                if (indice.equals("14")) {
                                    ScreenField field_14 = sf.getField(14);
                                    field_14.setString(valor);
                                }

                                if (indice.equals("15")) {
                                    ScreenField field_15 = sf.getField(15);
                                    field_15.setString(valor);
                                }

                                if (indice.equals("16")) {
                                    ScreenField field_16 = sf.getField(16);
                                    field_16.setString(valor);
                                }

                                if (indice.equals("17")) {
                                    ScreenField field_17 = sf.getField(17);
                                    field_17.setString(valor);
                                }

                                if (indice.equals("18")) {
                                    ScreenField field_18 = sf.getField(18);
                                    field_18.setString(valor);
                                }

                                if (indice.equals("19")) {
                                    ScreenField field_19 = sf.getField(19);
                                    field_19.setString(valor);
                                }

                                if (indice.equals("20")) {
                                    ScreenField field_20 = sf.getField(20);
                                    field_20.setString(valor);
                                }
                                if (indice.equals("21")) {
                                    ScreenField field_21 = sf.getField(21);
                                    field_21.setString(valor);
                                }

                                if (indice.equals("22")) {
                                    ScreenField field_22 = sf.getField(22);
                                    field_22.setString(valor);
                                }

                                if (indice.equals("23")) {
                                    ScreenField field_23 = sf.getField(23);
                                    field_23.setString(valor);
                                }

                                if (indice.equals("24")) {
                                    ScreenField field_24 = sf.getField(24);
                                    field_24.setString(valor);
                                }

                                if (indice.equals("25")) {
                                    ScreenField field_25 = sf.getField(25);
                                    field_25.setString(valor);
                                }

                                if (indice.equals("26")) {
                                    ScreenField field_26 = sf.getField(26);
                                    field_26.setString(valor);
                                }

                                if (indice.equals("27")) {
                                    ScreenField field_27 = sf.getField(27);
                                    field_27.setString(valor);
                                }

                                if (indice.equals("28")) {
                                    ScreenField field_28 = sf.getField(28);
                                    field_28.setString(valor);
                                }

                                if (indice.equals("29")) {
                                    ScreenField field_29 = sf.getField(29);
                                    field_29.setString(valor);
                                }

                                if (indice.equals("30")) {
                                    ScreenField field_30 = sf.getField(30);
                                    field_30.setString(valor);
                                }

                                if (indice.equals("31")) {
                                    ScreenField field_31 = sf.getField(31);
                                    field_31.setString(valor);
                                }

                                if (indice.equals("32")) {
                                    ScreenField field_32 = sf.getField(32);
                                    field_32.setString(valor);
                                }

                                if (indice.equals("33")) {
                                    ScreenField field_33 = sf.getField(33);
                                    field_33.setString(valor);
                                }

                                if (indice.equals("34")) {
                                    ScreenField field_34 = sf.getField(34);
                                    field_34.setString(valor);
                                }

                                if (indice.equals("35")) {
                                    ScreenField field_35 = sf.getField(35);
                                    field_35.setString(valor);
                                }

                                if (indice.equals("36")) {
                                    ScreenField field_36 = sf.getField(36);
                                    field_36.setString(valor);
                                }

                                if (indice.equals("37")) {
                                    ScreenField field_37 = sf.getField(37);
                                    field_37.setString(valor);
                                }

                                if (indice.equals("38")) {
                                    ScreenField field_38 = sf.getField(38);
                                    field_38.setString(valor);
                                }

                                if (indice.equals("39")) {
                                    ScreenField field_39 = sf.getField(39);
                                    field_39.setString(valor);
                                }

                                if (indice.equals("40")) {
                                    ScreenField field_40 = sf.getField(40);
                                    field_40.setString(valor);
                                }

                                if (indice.equals("41")) {
                                    ScreenField field_41 = sf.getField(41);
                                    field_41.setString(valor);
                                }

                                if (indice.equals("42")) {
                                    ScreenField field_42 = sf.getField(42);
                                    field_42.setString(valor);
                                }

                                if (indice.equals("43")) {
                                    ScreenField field_43 = sf.getField(43);
                                    field_43.setString(valor);
                                }

                                if (indice.equals("44")) {
                                    ScreenField field_44 = sf.getField(44);
                                    field_44.setString(valor);
                                }

                                if (indice.equals("45")) {
                                    ScreenField field_45 = sf.getField(45);
                                    field_45.setString(valor);
                                }

                                if (indice.equals("46")) {
                                    ScreenField field_46 = sf.getField(46);
                                    field_46.setString(valor);
                                }

                                if (indice.equals("47")) {
                                    ScreenField field_47 = sf.getField(47);
                                    field_47.setString(valor);
                                }

                                if (indice.equals("48")) {
                                    ScreenField field_48 = sf.getField(48);
                                    field_48.setString(valor);
                                }

                                if (indice.equals("49")) {
                                    ScreenField field_49 = sf.getField(49);
                                    field_49.setString(valor);
                                }

                            }
                            screen.sendKeys("[enter]");
                            Thread.sleep(3000L);
                            exploreScreenFields(screen);
                            printScreen(screen);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Thread.sleep(3000L);

                        actualizaList(dataForm, dataFormScrips);

                        PantallaDto pant = new PantallaDto();
                        List<InputDto> inps = exploreScreenFieldsInputs(screen);
                        pant.setInputs(inps);
                        pant.setListAcciones(cargaAcciones());
                        List<String> texts = printScreen(screen);
                        pant.setTextoPantalla(texts);
                        pant.setPantallaNumero(listPatalla.size() + 1);
                        //pant.setId(22L);
                        pant.setActiveKey(true);
                        pant.setAction("sesiosionAct");
                        this.listPatalla.add(pant);
                        marcarUltima();
                        model.addObject("listPantalla", listPatalla);
                        model.addObject("errorFlag", false);
                        pant.setWaccionar(datosFormulario.getW_accionar());

                        if (listPatalla.size() > 1) {
                            model.addObject("botonesGuardar", true);
                        } else {
                            model.addObject("botonesGuardar", false);
                        }

                    } else {
                        screen.sendKeys(dataForm[3].split(":")[1]);
                        Thread.sleep(3000L);

                        printScreen1(screen);
                        PantallaDto pant = new PantallaDto();
                        List<InputDto> inps = exploreScreenFieldsInputs(screen);
                        pant.setInputs(inps);
                        pant.setListAcciones(cargaAcciones());
                        List<String> texts = printScreen(screen);
                        pant.setTextoPantalla(texts);
                        pant.setPantallaNumero(listPatalla.size() + 1);
                        //pant.setId(22L);
                        pant.setActiveKey(true);
                        pant.setAction("sesiosionAct");
                        pant.setWaccionar(datosFormulario.getW_accionar());
                        this.listPatalla.add(pant);
                        marcarUltima();
                        actualizaList(dataForm, dataFormScrips);
                        model.addObject("listPantalla", listPatalla);
                        model.addObject("errorFlag", false);
                    }

                    List<InputDto> inps = exploreScreenFieldsInputs(screen);
                    model.addObject("errorFlag", false);

                    model.addObject("listPantalla", listPatalla);
                    if (listPatalla.size() > 1) {
                        model.addObject("botonesGuardar", true);
                    } else {
                        model.addObject("botonesGuardar", false);
                    }
                    model.addObject("paso", 1);
                } else if (datosFormulario.getW_modPantalla().equals("opc")) {
                    //123
                    if (dataForm[3].split(":")[1].equals("[enter]")) {

                        PantallaDto pant = new PantallaDto();
                        pant.setAction("sesiosionAct");
                        pant.setActive(false);
                        pant.setActiveKey(false);
                        pant.setPantallaNumero(((listPatalla.size() - 1) + (listPatallaOpcional.size()) + 1));
                        pant.setScrips(datosFormulario.toStringFilter());
                        pant.setWaccionar(datosFormulario.getW_accionar());
                        pant.setWaccionar(dataForm[3].split(":")[1]);
                        List<InputDto> inps = generaFieldsInputs(dataForm, datosFormulario);
                        pant.setInputs(inps);
                        listPatallaOpcional.add(pant);
                    } else {

                    }
                    model.addObject("listPatallaOpcional", listPatallaOpcional);
                    model.addObject("tranNombre", tranSave.getNombre());

                    if (listPatallaOpcional.size() > 1) {
                        model.addObject("botonesGuardarOpc", true);
                    } else {
                        model.addObject("botonesGuardarOpc", false);
                    }
                    model.addObject("paso", 3);
                }
            }
        }

        return model;
    }

    @RequestMapping(value = "/eliminarPantalla", method = RequestMethod.POST)
    public ModelAndView eliminarPantalla(DatosFormDto datosFormulario, HttpSession session) throws InterruptedException {
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        Integer id = Integer.valueOf(datosFormulario.getField_0());
        boolean flag = false;
        PantallaDto deletePantalla = new PantallaDto();
        for (PantallaDto pantalla : listPatallaOpcional) {
            if (pantalla.getPantallaNumero() == id) {
                deletePantalla = pantalla;
                flag = true;
            }
        }
        listPatallaOpcional.remove(deletePantalla);

        model.addObject("accionesLista", cargaAcciones());
        model.addObject("statusDelete", flag);
        model.addObject("paso", 3);
        return model;
    }
    
    
    
     @RequestMapping(value = "/editTransaccion", method = RequestMethod.POST)
    public ModelAndView editTransaccion(DatosFormDto datosFormulario, HttpSession session) throws InterruptedException {
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        Integer id = Integer.valueOf(datosFormulario.getField_0());
        listPatallaAuxiliar.clear();
        boolean flag = false;
        
        TransaccionIO transaccion = service1.getTransacionById(id);
        listPatallaAuxiliar.addAll(service1.getPantallaByIdTransaccion(id));
        
        model.addObject("transaccion", transaccion);
        model.addObject("actividad", 3);
        model.addObject("pantallas",listPatallaAuxiliar );
        model.addObject("accionesLista", cargaAcciones());
        model.addObject("statusDelete", flag);
        model.addObject("paso", 2);
        return model;
    }

   
    public boolean guardarListaPantalla(Integer aux) {
        boolean flag = true;
        int contador = 1;
        int contador2 = 0;
        if (aux == 1) {
            try {
                for (PantallaDto pantallaDto : listPatalla) {
                    if (contador < listPatalla.size()) {
                        PantallaIO pantalla = new PantallaIO();
                        pantalla.setPantallaAccionTecla("");
                        pantalla.setPantallaAction(pantallaDto.getAction());
                        pantalla.setPantallaActive(pantallaDto.isActive());
                        pantalla.setPantallaActivekey(pantallaDto.isActiveKey());
                        pantalla.setPantallaNumero(pantallaDto.getPantallaNumero());
                        pantalla.setPantallaScrips(pantallaDto.getScrips());
                        pantalla.setIdTransaccion(tranSave.getId());
                        List<InputIO> inputIOList = new ArrayList<>();
                        for (InputDto input : pantallaDto.getInputs()) {
                            InputIO inp = new InputIO();
                            inp.setInputLabel(input.getLabel());
                            inp.setInputName(input.getName());
                            inp.setInputId(input.getId());
                            inp.setInputRequired(input.getRequired());
                            inp.setInputType(input.getType());
                            inp.setInputValue(input.getValue());
                            inputIOList.add(inp);
                        }
                        pantalla.setInputCollection(inputIOList);
                        List<TextoPantallaIO> textosList = new ArrayList<>();
                        if (pantallaDto.getTextoPantalla() != null) {
                            for (String textoLinea : pantallaDto.getTextoPantalla()) {
                                textosList.add(new TextoPantallaIO(textoLinea));
                            }
                        }
                        pantalla.setTextoPantallaCollection(textosList);
                        pantalla.setIdTransaccion(tranSave.getId());

                        PantallaIO pantallaSave = service1.guardarPantalla(pantalla);
                        if (pantallaSave.getId() != null) {
                            System.out.println("guardo");

                        } else {
                            System.out.println(" no guardo");
                        }
                    }
                    contador++;
                }

            } catch (Exception e) {
                flag = false;
                e.printStackTrace();
            }
        } else {
            try {
                for (PantallaDto pantallaDto : listPatallaOpcional) {
                    if (contador2 < listPatallaOpcional.size()) {
                        PantallaIO pantalla = new PantallaIO();
                        pantalla.setPantallaAccionTecla("");
                        pantalla.setPantallaAction(pantallaDto.getAction());
                        pantalla.setPantallaActive(pantallaDto.isActive());
                        pantalla.setPantallaActivekey(pantallaDto.isActiveKey());
                        pantalla.setPantallaNumero(pantallaDto.getPantallaNumero());
                        pantalla.setPantallaScrips(pantallaDto.getScrips());
                        pantalla.setIdTransaccion(tranSave.getId());
                        List<InputIO> inputIOList = new ArrayList<>();
                        for (InputDto input : pantallaDto.getInputs()) {
                            InputIO inp = new InputIO();
                            inp.setInputLabel(input.getLabel());
                            inp.setInputName(input.getName());
                            inp.setInputId(input.getId());
                            inp.setInputRequired(input.getRequired());
                            inp.setInputType(input.getType());
                            inp.setInputValue(input.getValue());
                            inputIOList.add(inp);
                        }
                        pantalla.setInputCollection(inputIOList);
                        List<TextoPantallaIO> textosList = new ArrayList<>();
                        if (pantallaDto.getTextoPantalla() != null) {
                            for (String textoLinea : pantallaDto.getTextoPantalla()) {
                                textosList.add(new TextoPantallaIO(textoLinea));
                            }
                        }
                        pantalla.setTextoPantallaCollection(textosList);
                        pantalla.setIdTransaccion(tranSave.getId());

                        PantallaIO pantallaSave = service1.guardarPantalla(pantalla);
                        if (pantallaSave.getId() != null) {
                            System.out.println("guardo");

                        } else {
                            System.out.println(" no guardo");
                        }
                    }
                    contador++;
                }

            } catch (Exception e) {
                flag = false;
                e.printStackTrace();
            }
        }

        return flag;
    }

    @RequestMapping(value = "/conectar", method = RequestMethod.POST)
    public ModelAndView conectar(String conex, HttpSession session) {
        //listPatalla.clear();

        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        ConexionAsDto conn = new ConexionAsDto();

        screen = connect(conn.getServidor(), conn.getUsuario(), conn.getClave());
        if (conectado) {
            PantallaDto pant = new PantallaDto();

            List<InputDto> inps = exploreScreenFieldsInputs(screen);
            pant.setInputs(inps);
            pant.setListAcciones(cargaAcciones());
            pant.setActiveKey(true);
            //model.addObject("inputs", inps);
            List<String> texts = printScreen(screen);
            pant.setTextoPantalla(texts);
            //model.addObject("textos", texts);
            pant.setPantallaNumero(listPatalla.size() + 1);
            //pant.setId(22L);
            this.listPatalla.add(pant);
            marcarUltima();
            model.addObject("listPantalla", listPatalla);
        } else {

            model.addObject("listPantalla", listPatalla);
        }
        model.addObject("paso", 1);
        sessions.disconnect();
        return model;
    }

    private Screen5250 connect(String servidor, String usuario, String clave) {
        ProtocolBean pb = new ProtocolBean(usuario, clave);
        Screen5250 screen = null;
        try {
            pb.setHostName(servidor);

            sessions = pb.getSession();
            pb.connect();

            screen = sessions.getScreen();
            Thread.sleep(3000L);
            conectado = sessions.isConnected();
            System.err.println("Is connected? - " + sessions.isConnected());
            return screen;
        } catch (UnknownHostException ex) {
            Logger.getLogger(AdminRobotController.class.getName()).log(Level.SEVERE, null, ex);
            return screen;
        } catch (IllegalStateException ex) {
            Logger.getLogger(AdminRobotController.class.getName()).log(Level.SEVERE, null, ex);
            return screen;
        } catch (InterruptedException ex) {
            Logger.getLogger(AdminRobotController.class.getName()).log(Level.SEVERE, null, ex);
            return screen;
        } //To change body of generated methods, choose Tools | Templates.
    }

    private String getScreenAsString(Screen5250 screen) {
        char[] buffer = new char[1920];
        screen.GetScreen(buffer, 1920, ScreenPlanes.PLANE_TEXT);
        return new String(buffer);
    }

    public List<InputDto> exploreScreenFieldsInputs(Screen5250 screen) {
        List<InputDto> inputs = new ArrayList<>();

        InputDto input2 = new InputDto();
        input2.setId("w_idPantalla");
        input2.setName("w_idPantalla");
        input2.setType("text");
        input2.setValue("");
        input2.setRequired(true);
        input2.setLabel("Identificador de la pantalla");
        inputs.add(input2);

        ScreenFields sf = screen.getScreenFields();
        String s = getScreenAsString(screen);
        String text = "";
        for (int i = 0; i < sf.getFieldCount(); i++) {
            InputDto input = new InputDto();
            if (!sf.getField(i).isBypassField()) {
                int pos = sf.getField(i).startPos();
                int posIni = 0;
                if (pos > 40) {
                    posIni = pos - 40;
                }
                text = s.substring(posIni, pos);
                input.setId("field_" + i);
                input.setName("field_" + i);
                input.setType("text");
                input.setValue("");
                input.setRequired(false);
                String[] labelInput = text.split("\\.");
                input.setLabel(labelInput[0].trim());

                inputs.add(input);
            }

        }
        InputDto numPantalla = new InputDto();
        numPantalla.setId("w_numPantalla");
        numPantalla.setName("w_numPantalla");
        numPantalla.setType("hidden");
        numPantalla.setValue("" + (listPatalla.size() + 1));
        numPantalla.setRequired(false);
        inputs.add(numPantalla);

        InputDto modPantalla = new InputDto();
        modPantalla.setId("w_modPantalla");
        modPantalla.setName("w_modPantalla");
        modPantalla.setType("hidden");
        modPantalla.setValue("oper");
        modPantalla.setRequired(false);
        inputs.add(modPantalla);
        return inputs;
    }

    public List<InputDto> generaFieldsInputs(String[] dataForm, DatosFormDto formulario) {
        List<InputDto> inputs = new ArrayList<>();

        InputDto input2 = new InputDto();
        input2.setId("w_idPantalla");
        input2.setName("w_idPantalla");
        input2.setType("text");
        input2.setValue(formulario.getW_idPantalla());
        input2.setRequired(true);
        input2.setLabel("Identificador de la pantalla");
        inputs.add(input2);

        for (int i = 4; i < dataForm.length; i++) {
            InputDto inp = new InputDto();
            String datos = dataForm[i];
            String[] datoAux = datos.split(":");
            String indice = datoAux[0];
            String label = "Campo  " + datoAux[0].split("_")[1];
            String valor = datoAux[1];
            inp.setLabel(label);
            inp.setType("text");
            inp.setName(indice);
            inp.setId(indice);
            inp.setRequired(false);
            inp.setValue(valor);
            inputs.add(inp);
        }

        InputDto numPantalla = new InputDto();
        numPantalla.setId("w_numPantalla");
        numPantalla.setName("w_numPantalla");
        numPantalla.setType("hidden");
        numPantalla.setValue("" + ((listPatalla.size() - 1) + (listPatallaOpcional.size()) + 1));
        numPantalla.setRequired(false);
        inputs.add(numPantalla);

        InputDto modPantalla = new InputDto();
        modPantalla.setId("w_modPantalla");
        modPantalla.setName("w_modPantalla");
        modPantalla.setType("hidden");
        modPantalla.setValue("oper");
        modPantalla.setRequired(false);
        inputs.add(modPantalla);
        return inputs;

    }

    private List<String> printScreen(Screen5250 screen) {
        String showme = getScreenAsString(screen);
        String sb = "";
        List<String> pantalla = new ArrayList<>();

        for (int i = 0; i < showme.length(); i += 80) {
            String sb2 = "";
            sb2 += showme.substring(i, i + 80);

            //sb += " \n ";
            pantalla.add(sb2);
        }
        System.out.println(sb);
        return pantalla;
    }

    private void marcarUltima() {
        int iter = 1;
        for (PantallaDto pantalla : listPatalla) {
            if (iter == listPatalla.size()) {
                pantalla.setActive(true);
            } else {
                pantalla.setActive(false);
            }
            iter++;
        }
    }

    public List<AccionKeyboarDto> cargaAcciones() {
        listAcciones.add(new AccionKeyboarDto("	ENTER	", "[enter]"));
        listAcciones.add(new AccionKeyboarDto("	PF1	", "[pf1]"));
        listAcciones.add(new AccionKeyboarDto("	PF2	", "[pf2]"));
        listAcciones.add(new AccionKeyboarDto("	PF3	", "[pf3]"));
        listAcciones.add(new AccionKeyboarDto("	PF4	", "[pf4]"));
        listAcciones.add(new AccionKeyboarDto("	PF5	", "[pf5]"));
        listAcciones.add(new AccionKeyboarDto("	PF6	", "[pf6]"));
        listAcciones.add(new AccionKeyboarDto("	PF7	", "[pf7]"));
        listAcciones.add(new AccionKeyboarDto("	PF8	", "[pf8]"));
        listAcciones.add(new AccionKeyboarDto("	PF9	", "[pf9]"));
        listAcciones.add(new AccionKeyboarDto("	PF10	", "[pf10]"));
        listAcciones.add(new AccionKeyboarDto("	PF11	", "[pf11]"));
        listAcciones.add(new AccionKeyboarDto("	PF12	", "[pf12]"));
        listAcciones.add(new AccionKeyboarDto("	PF13	", "[pf13]"));
        listAcciones.add(new AccionKeyboarDto("	PF14	", "[pf14]"));
        listAcciones.add(new AccionKeyboarDto("	PF15	", "[pf15]"));
        listAcciones.add(new AccionKeyboarDto("	PF16	", "[pf16]"));
        listAcciones.add(new AccionKeyboarDto("	PF17	", "[pf17]"));
        listAcciones.add(new AccionKeyboarDto("	PF18	", "[pf18]"));
        listAcciones.add(new AccionKeyboarDto("	PF19	", "[pf19]"));
        listAcciones.add(new AccionKeyboarDto("	PF20	", "[pf20]"));
        listAcciones.add(new AccionKeyboarDto("	PF21	", "[pf21]"));
        listAcciones.add(new AccionKeyboarDto("	PF22	", "[pf22]"));
        listAcciones.add(new AccionKeyboarDto("	PF23	", "[pf23]"));
        listAcciones.add(new AccionKeyboarDto("	PF24	", "[pf24]"));
        listAcciones.add(new AccionKeyboarDto("	PREVIOUS_WORD	", "[prevword]"));
        listAcciones.add(new AccionKeyboarDto("	PRINT	", "[print]"));
        listAcciones.add(new AccionKeyboarDto("	RESET	", "[reset]"));
        listAcciones.add(new AccionKeyboarDto("	RIGHT	", "[right]"));
        listAcciones.add(new AccionKeyboarDto("	SYSREQ	", "[sysreq]	"));
        listAcciones.add(new AccionKeyboarDto("	TAB	", "[tab]"));
        listAcciones.add(new AccionKeyboarDto("	UP	", "[up]"));
        listAcciones.add(new AccionKeyboarDto("	BACK_TAB", "[backtab]"));
        listAcciones.add(new AccionKeyboarDto("	BEGIN_OF_FIELD	", "[bof]"));
        listAcciones.add(new AccionKeyboarDto("	CLEAR	", "[clear]"));
        listAcciones.add(new AccionKeyboarDto("	DELETE	", "[delete]"));
        listAcciones.add(new AccionKeyboarDto("	DOWN	", "[down]"));
        listAcciones.add(new AccionKeyboarDto("	COPY	", "[copy]"));
        listAcciones.add(new AccionKeyboarDto("	END_OF_FIELD", "[eof]"));
        listAcciones.add(new AccionKeyboarDto("	ERASE_EOF", "[eraseeof]"));
        listAcciones.add(new AccionKeyboarDto("	ERASE_FIELD", "[erasefld]"));
        listAcciones.add(new AccionKeyboarDto("	FIELD_EXIT", "[fldext]"));
        listAcciones.add(new AccionKeyboarDto("	FIELD_MINUS", "[field-]"));
        listAcciones.add(new AccionKeyboarDto("	FIELD_PLUS	", "[field+]"));
        listAcciones.add(new AccionKeyboarDto("	HELP	", "[help]"));
        listAcciones.add(new AccionKeyboarDto("	HOME	", "[home]"));
        listAcciones.add(new AccionKeyboarDto("	INSERT	", "[insert]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD_COMMA", "[keypad,]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD_MINUS", "[keypad-]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD_PERIOD", "[keypad.]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD0	", "[keypad0]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD1	", "[keypad1]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD2	", "[keypad2]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD3	", "[keypad3]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD4	", "[keypad4]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD5	", "[keypad5]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD6	", "[keypad6]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD7	", "[keypad7]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD8	", "[keypad8]"));
        listAcciones.add(new AccionKeyboarDto("	KEYPAD9	", "[keypad9]"));
        listAcciones.add(new AccionKeyboarDto("	LEFT	", "[left]	"));
        listAcciones.add(new AccionKeyboarDto("	NEXT_WORD", "[nextword]"));
        listAcciones.add(new AccionKeyboarDto("	PA1	", "[pa1]"));
        listAcciones.add(new AccionKeyboarDto("	PA2	", "[pa2]"));
        listAcciones.add(new AccionKeyboarDto("	PA3	", "[pa3]"));
        listAcciones.add(new AccionKeyboarDto("	PASTE	", "[paste]"));
        listAcciones.add(new AccionKeyboarDto("	PAGE_DOWN", "[pgdown]"));
        listAcciones.add(new AccionKeyboarDto("	PAGE_UP	", "[pgup]"));
        return listAcciones;

    }

    public void actualizaList(String[] scrips, String scripDb) {
        String pantallaNum = scrips[0].split(":")[1].equals("conec") ? "1" : scrips[1].split(":")[1];

        for (PantallaDto pantallaDto : listPatalla) {

            if (pantallaNum.equals("" + pantallaDto.getPantallaNumero())) {

                pantallaDto.setScrips(scripDb);

                for (InputDto input : pantallaDto.getInputs()) {
                    for (int i = 0; i < scrips.length; i++) {
                        String datos = scrips[i];
                        String[] datoAux = datos.split(":");
                        String indice = datoAux[0];
                        String valor = datoAux[1];

                        if (input.getName().equals(indice)) {
                            if (indice.equals("w_modPantalla")) {
                                input.setValue(valor);;
                            }
                            if (indice.equals("w_numPantalla")) {
                                input.setValue(valor);;
                            }
                            if (indice.equals("w_idPantalla")) {
                                input.setValue(valor);;
                            }
                            if (indice.equals("w_accionar")) {
                                input.setValue(valor);;
                            }
                            if (indice.equals("field_0")) {
                                input.setValue(valor);;
                            }

                            if (indice.equals("field_1")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_2")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_3")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_4")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_5")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_6")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_7")) {
                                input.setValue(valor);;
                            }

                            if (indice.equals("field_8")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_9")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_10")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_11")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_12")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_13")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_14")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_15")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_16")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_17")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_18")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_19")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_20")) {
                                input.setValue(valor);
                            }
                            if (indice.equals("field_21")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_22")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_23")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_24")) {
                                input.setValue(valor);;
                            }

                            if (indice.equals("field_25")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_26")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_27")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_28")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_29")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_30")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_31")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_32")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_33")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_34")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_35")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_36")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_37")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_38")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_39")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_40")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_41")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_42")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_43")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_44")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_45")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_46")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("4field_7")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_48")) {
                                input.setValue(valor);
                            }

                            if (indice.equals("field_49")) {
                                input.setValue(valor);
                            }

                        }
                    }
                }
            }
        }

    }

    private void printScreen1(Screen5250 screen) {
        String showme = getScreenAsString(screen);
        String sb = "";

        for (int i = 0; i < showme.length(); i += 80) {
            sb += showme.substring(i, i + 80);
            sb += "\n";
        }
        System.out.println(sb);
    }

    private void exploreScreenFields(Screen5250 screen) {
        ScreenFields sf = screen.getScreenFields();
        String s = getScreenAsString(screen);

        String text = "";

        for (int i = 0; i < sf.getFieldCount(); i++) {
            if (!sf.getField(i).isBypassField()) {
                int pos = sf.getField(i).startPos();
                int posIni = 0;
                if (pos > 40) {
                    posIni = pos - 40;
                }
                text = s.substring(posIni, pos);
                System.out.println("field " + i + " -> id = " + sf.getField(i).getFieldId() + " str at left = " + text);
            }
        }

    }

}
