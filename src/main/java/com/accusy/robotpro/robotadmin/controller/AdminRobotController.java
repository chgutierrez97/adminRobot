package com.accusy.robotpro.robotadmin.controller;

import com.accusy.robotpro.robotadmin.dto.AccionKeyboarDto;
import com.accusy.robotpro.robotadmin.dto.ConexionAsDto;
import com.accusy.robotpro.robotadmin.dto.DatosFormDto;
import com.accusy.robotpro.robotadmin.dto.Export;
import com.accusy.robotpro.robotadmin.dto.InputDto;
import com.accusy.robotpro.robotadmin.dto.PantallaDto;
import com.accusy.robotpro.robotadmin.dto.ResponseAjaxDto;
import com.accusy.robotpro.robotadmin.model.EnviarInformacion;
import com.accusy.robotpro.robotadmin.model.EnviarTransaccionForm;
import com.accusy.robotpro.robotadmin.model.ExpresionesRegularesIO;
import com.accusy.robotpro.robotadmin.model.InputIO;
import com.accusy.robotpro.robotadmin.model.PantallaIO;
import com.accusy.robotpro.robotadmin.model.TextoPantallaIO;
import com.accusy.robotpro.robotadmin.model.TransaccionExport;
import com.accusy.robotpro.robotadmin.model.TransaccionIO;
import com.accusy.robotpro.robotadmin.model.TransaccionOI;
import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
import com.accusy.robotpro.robotadmin.utils.ExcepcionBaseMsn;
import com.accusy.robotpro.robotadmin.utils.UtilRobot;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

@SessionScope
@Controller
@PropertySource("classpath:application.properties")
public class AdminRobotController {

    @Autowired
    ServicesRobot service1;

    @Autowired
    UtilRobot util;

    public List<ConexionAsDto> conexiones;
    public List<TransaccionIO> trans = new ArrayList<>();
    public List<TransaccionIO> transIni = new ArrayList<>();
    public List<TransaccionIO> transAll = new ArrayList<>();
    public List<ExpresionesRegularesIO> expresiones = new ArrayList<>();

    public static Session5250 sessions = null;
    public Screen5250 screen;
    public String pantalla;
    public List<PantallaDto> listPatalla = new ArrayList<>();
    public List<PantallaDto> listPatallaOpcional = new ArrayList<>();
    public List<PantallaDto> listPatallaAuxiliar = new ArrayList<>();
    public List<PantallaDto> listPatallaSiluladora = new ArrayList<>();
    public List<String> listTextoPatallaSiluladora = new ArrayList<>();
    public List<AccionKeyboarDto> listAcciones = new ArrayList<>();
    public List<ExpresionesRegularesIO> listExpresionesAs = new ArrayList<>();
    public boolean conectado;
    public ConexionAsDto coneco;
    private String scrip;
    private TransaccionIO tranSave;

    SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy-hhmmss");
    @Value("${export.trans.file.paht.global}")
    private String PahtFile;
    @Value("${number.cicle.for}")
    private Integer numFor;
    @Value("${number.cicle.while}")
    private Integer numWhile;

    @RequestMapping(value = "/textopantallaByIdTrans", method = RequestMethod.GET)
    @ResponseBody
    public List<PantallaDto> textopantallaByIdTrans(@RequestParam Integer idTransaccion) {
        List<PantallaDto> pantallas = service1.getdPantallaByIdTrasaccionEmulacion(idTransaccion);
        pantallas.stream().filter(c -> c.getScrips().contains("otc")).forEach(c -> System.out.println(c));
        return pantallas;
    }

    @RequestMapping(value = "/textopantallaByIdTrans2", method = RequestMethod.GET)
    @ResponseBody
    public List<PantallaDto> textopantallaByIdTrans2(@RequestParam Integer idTransaccion) {
        PantallaIO PantallaIOResponse = new PantallaIO();
        List<PantallaDto> pantallas = service1.getdPantallaByIdTrasaccionEmulacion(idTransaccion);
        return simuladorAs(pantallas);
    }

    @RequestMapping(value = "/eliminarTransaccion", method = RequestMethod.GET)
    @ResponseBody
    public Export eliminarTransaccion(@RequestParam Integer idTransaccion) {
        Export export = new Export();
        try {
            export.setFlag(service1.delTransacionById(idTransaccion));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return export;
    }

    @RequestMapping(value = "/exportarTransaccionAjax", method = RequestMethod.GET)
    @ResponseBody
    public Export exportarTransaccionAjax(@RequestParam Integer idTransaccion) {
        Export export = new Export();
        try {
            export = exportarTransaccion(idTransaccion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return export;
    }

    @RequestMapping(value = "/validaNombreTrans", method = RequestMethod.GET)
    @ResponseBody
    public Boolean validaNombreTrans(@RequestParam String nombre) {

        nombre = nombre.trim();
        Boolean flag = true;//service1.validaPorNombre(nombre);
        return flag;
    }

    @RequestMapping(value = "/guardarTransaccion", method = RequestMethod.POST)
    public ModelAndView guardarTransaccion(EnviarTransaccionForm transaccionForm, HttpSession session) {
        
        Boolean flag1 = Boolean.TRUE;
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

            try {
                flag1 = actualiza(listPatallaAuxiliar);
                model.addObject("errorFlag", false);

                model.addObject("listPantalla", listPatalla);
                if (listPatalla.size() > 2) {
                    model.addObject("botonesGuardar", true);
                } else {
                    model.addObject("botonesGuardar", false);
                }
                model.addObject("paso", 1);
            } catch (ExcepcionBaseMsn e) {
                model.addObject("actividad", 1);
                model.addObject("paso", 2);
                model.addObject("errorForm", "Verifique los datos ingresados no se puede conectar el emulador AS400");
                model.addObject("errorFlag", true);
                model.addObject("transaccionForm", transaccionForm);
                service1.delTransacionById(tranSave.getId());
            } catch (InterruptedException ex) {
                model.addObject("actividad", 1);
                model.addObject("paso", 2);
                model.addObject("errorForm", "Verifique comunicaciones tiempo de espera agotado para la interaccion con el AS400");
                model.addObject("errorFlag", true);
                model.addObject("transaccionForm", transaccionForm);
                service1.delTransacionById(tranSave.getId());
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
            model.addObject("expresiones", service1.getExpresionAll());
            model.addObject("paso", 1);
            model.addObject("errorFlag", false);
        }

        session.setAttribute("listPatalla", listPatalla);

        model.addObject("trans", trans);
        if (flag1) {
            model.addObject("flagMsnError", false);
        } else {
            model.addObject("flagMsnError", true);
        }
        service1.sessionActivaById(user.getId(), Boolean.TRUE);
        return model;
    }

    @RequestMapping(value = "/actualizarTransaccion", method = RequestMethod.POST)
    public ModelAndView actualizarTransaccion(EnviarTransaccionForm transaccionForm, HttpSession session) {

        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        service1.sessionActivaById(user.getId(), Boolean.TRUE);

        TransaccionIO transaccionIO = service1.getTransacionById(transaccionForm.getIdTrans());
        TransaccionOI transaccionEdit = new TransaccionOI();
        transaccionEdit.setId(transaccionIO.getId());
        transaccionEdit.setNombre(transaccionForm.getInputNombreT());
        transaccionEdit.setDescripcion(transaccionForm.getInputDescripcionT());
        transaccionEdit.setAplicativoExternocol(transaccionForm.getInputNombreAplic());
        transaccionEdit.setTipo("" + transaccionForm.getSelectTipoTrans());
        transaccionEdit.setTipoAplicativo(transaccionIO.getTipoAplicativo());
        transaccionEdit.setUsuario(user);
        transaccionEdit.setFechaCarga(new Date());
        tranSave = service1.updateTransaccion(transaccionEdit);

        listPatalla.clear();
        listPatallaOpcional.clear();
        listPatallaAuxiliar.clear();
        listPatallaAuxiliar.addAll(service1.getPantallaByIdTransaccion(transaccionForm.getIdTrans()));

        int cont = 0;
        for (PantallaDto pantallaDto : listPatallaAuxiliar) {
            cont++;
            pantallaDto.setPantallaNumero(cont);
            String tipoPantalla = "";
            switch (pantallaDto.getScrips().split(",")[0].split(":")[1]) {
                case "conec":
                    tipoPantalla = "Conexion";
                    break;
                case "oper":
                    tipoPantalla = "Operacional";
                    break;
                case "opc":
                    tipoPantalla = "Alternativa";
                    break;
                default:

            }

            pantallaDto.setWaccionar(tipoPantalla);

        }

        if (listPatalla.size() > 1) {
            model.addObject("botonesGuardar", true);
        } else {
            model.addObject("botonesGuardar", false);
        }

        model.addObject("listPantallaEdit", listPatallaAuxiliar);
        model.addObject("paso", 5);
        model.addObject("tranSave", tranSave);

        return model;
    }

    @RequestMapping(value = "/pantallaPorId", method = RequestMethod.GET)
    @ResponseBody
    public PantallaDto pantallaPorId(@RequestParam Integer idPantalla) {
        PantallaDto patalla = new PantallaDto();
        for (PantallaDto pantallaDto : listPatallaAuxiliar) {
            if (pantallaDto.getId().toString().equals(idPantalla.toString())) {
                patalla = pantallaDto;
            }
        }
        return patalla;
    }
        
        
    @RequestMapping(value = "/pantallaPorIdAndExpre", method = RequestMethod.GET)
    @ResponseBody
    public ResponseAjaxDto pantallaPorIdAndExpre(@RequestParam Integer idPantalla) {
        PantallaDto patalla = new PantallaDto();
        ResponseAjaxDto response = new ResponseAjaxDto();
        for (PantallaDto pantallaDto : listPatallaAuxiliar) {
            if (pantallaDto.getId().toString().equals(idPantalla.toString())) {
               patalla = pantallaDto;
            }
        }
        List<ExpresionesRegularesIO> expresionesAS = service1.getExpresionAll();
        
        response.setExpresiones(expresionesAS);
        response.setPantalla(patalla);
        response.setAccionTeclado(cargaAcciones());
        return response;
    }

    @RequestMapping(value = "/editarPantalla", method = RequestMethod.POST)
    public ModelAndView editarPantalla(DatosFormDto datosFormulario, HttpSession session) throws InterruptedException {
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        String[] dataForm = new String[700];
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        Integer id = Integer.valueOf(datosFormulario.getIdPantalla());
        boolean flag = false;
        PantallaDto deletePantalla = new PantallaDto();
        System.out.println("" + datosFormulario.toStringFilter().split(","));
        dataForm = datosFormulario.toStringFilter().split(",");

        String scrips = "";

        for (PantallaDto pantallaDto : listPatallaAuxiliar) {
            if (pantallaDto.getId().toString().equals(datosFormulario.getIdPantalla().toString())) {
                scrips = pantallaDto.getScrips();
                System.out.println(scrips);
                for (int i = 0; i < dataForm.length; i++) {
                    String datos = dataForm[i];
                    String[] datoAux = datos.split(":");
                    String indice = datoAux[0];
                    String valor = datoAux[1];
                    System.out.println(" indice = " + indice + " valor =" + valor);
                    for (InputDto input : pantallaDto.getInputs()) {
                        if (input.getId().toString().equals(indice.toString())) {
                            if (!(valor.trim().equals(input.getValue().trim()))) {
                                input.setValue(valor);
                                InputIO inpUpdate = new InputIO();
                                inpUpdate.setId(input.getIdInp());
                                inpUpdate.setInputValue(input.getValue());
                                service1.updateInput(inpUpdate);
                            }
                        }
                    }
                    for (String string : pantallaDto.getScrips().split(",")) {
                        if (string.split(":")[0].equals(datos.split(":")[0])) {
                            if (!(string.split(":")[1].equals(datos.split(":")[1]))) {
                                scrips = scrips.replace(string, datos);
                            }

                        }
                    }
                }
                System.out.println(scrips);
            }
            pantallaDto.setScrips(scrips);
            if (service1.updateScripPantalla(scrips, Integer.valueOf(pantallaDto.getId().toString()))) {
                System.out.println("siii");
            }
        }
        model.addObject("listPantallaEdit", listPatallaAuxiliar);
        model.addObject("tranSave", tranSave);
        model.addObject("paso", 5);
        service1.sessionActivaById(user.getId(), Boolean.TRUE);
        return model;
    }

    public void toStrs(String scrip, String valorCambio) {

    }

    public Export exportarTransaccion(Integer idTransaccion) throws InterruptedException {
        
        Export exp = new Export();
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        boolean flag = true;
        TransaccionExport export = new TransaccionExport();
        TransaccionIO transaccionIO = service1.getTransacionById(idTransaccion);
        List<PantallaDto> pantallas = service1.getPantallaByIdTransaccion(idTransaccion);
        export.setTransaccion(transaccionIO);
        export.setListaPantalla(pantallas);
        Gson gson = new Gson();
        String JSON = gson.toJson(export);
        String nombreArchivo = "transaccion-" + transaccionIO.getNombre() + "-" + new Date().getTime() + ".json";
        try {
            FileWriter file = new FileWriter(PahtFile + nombreArchivo);
            file.write(JSON);
            file.flush();
            file.close();

        } catch (Exception ex) {
            flag = false;
            System.out.println("Error: " + ex.toString());
        } finally {
            System.out.print(JSON);
        }
        exp.setDescripcion(nombreArchivo);
        exp.setFlag(flag);
        return exp;
    }

    private Boolean procesado(List<PantallaDto> listaActual, int indice) throws ExcepcionBaseMsn {
        int longitud = listaActual.size();
        Boolean flag = false;

        if (longitud > (indice + 1)) {
            PantallaDto pantallaSiguiente = listaActual.get(indice + 1);

            if (pantallaSiguiente.getInputs().size() > 0) {
                if (!(pantallaSiguiente.getScrips().contains("opt"))) {
                    String texto = (pantallaSiguiente.getInputs().get(0).getValue()).trim();
                    PantallaDto pant = new PantallaDto();
                    if (util.comparadorDeCaracteres(getScreenAsString(screen), texto)) {
                        pant.setTextoPantalla(printScreen(screen));
                        listPatallaSiluladora.add(pant);
                        flag = true;
                    } else {
                        if (operacionesAlternativas(getScreenAsString(screen), listaActual, "conec")) {
                            pant.setTextoPantalla(printScreen(screen));
                            listPatallaSiluladora.add(pant);
                            throw new ExcepcionBaseMsn("Codigo:0010,Ejecucion de pantalla alternativa");
                        } else {
                            pant.setTextoPantalla(printScreen(screen));
                            listPatallaSiluladora.add(pant);
                            throw new ExcepcionBaseMsn("Codigo:0020,Pantalla no fue reconocidad en proceso programado por el administrador de procesos");
                        }
                    }
                }
            }
        } else {
            flag = true;
        }
        return flag;
    }

    public List<PantallaDto> simuladorAs(List<PantallaDto> listaActual) {
        listPatallaSiluladora.clear();
        String[] dataForm = new String[70];
        String scrits = "";
        int indice = 0;
        try {
            for (PantallaDto pantallaDto : listaActual) {
                PantallaDto panti = new PantallaDto();
                scrits = pantallaDto.getScrips();
                dataForm = pantallaDto.getScrips().split(",");
                pantallaDto.setId(null);
                String actExp = dataForm[5];
                actExp = actExp.split(":")[1];
                actExp = actExp.replace("*", "");
                if (scrits.contains("conec")) {
                    boolean flag2 = true;
                    pantallaDto.setPantallaNumero(listPatalla.size() + 1);
                    String host = dataForm[6];
                    host = host.split(":")[1];
                    host = host.replace("*", "");
                    String usuario = dataForm[7];
                    usuario = usuario.split(":")[1];
                    usuario = usuario.replace("*", "");
                    String clave = dataForm[8];
                    clave = clave.split(":")[1];
                    clave = clave.replace("*", "");
                    screen = connect(host, usuario, clave);
                    panti.setTextoPantalla(printScreen(screen));
                    listPatallaSiluladora.add(panti);
                    if (sessions.isConnected()) {
                        String idCiclo = dataForm[2].split(":")[1];
                        Integer numInt = Integer.valueOf(dataForm[3].split(":")[1]);
                        Integer expresionId = Integer.valueOf(dataForm[4].split(":")[1]);
                        if (!idCiclo.equals("0")) {
                            switch (idCiclo) {
                                // segmento de ciclo for de la conexion;
                                case "f":
                                    if (numInt > 0) {
                                        for (int i = 0; i < numInt; i++) {
                                            ScreenFields sf = screen.getScreenFields();
                                            Thread.sleep(3000L);
                                            ScreenField userField = sf.getField(0);
                                            userField.setString(usuario);
                                            ScreenField passField = sf.getField(1);
                                            passField.setString(clave);
                                            screen.sendKeys("[enter]");
                                            Thread.sleep(3000L);
                                            String pantalla = getScreenAsString(screen).trim();
                                            if (expresionId > 0) {
                                                Export expReq = ExpresionesAS4(getScreenAsString(screen).trim(), expresionId);
                                                if (expReq.getFlag()) {

                                                    int longitud = listaActual.size();
                                                    if (procesado(listaActual, indice)) {
                                                        break;
                                                    }
                                                    Thread.sleep(2000L);
                                                    exploreScreenFields(screen);
                                                } else {
                                                    Boolean a = true;
                                                    PantallaDto pant = new PantallaDto();
                                                    if (actExp == "i") {

                                                        pant.setTextoPantalla(printScreen(screen));
                                                        listPatallaSiluladora.add(pant);
                                                        throw new ExcepcionBaseMsn("Codigo:0010,Ejecucion de pantalla alternativa");

                                                    }

                                                    // manejar el accion programada para la expresion Mostrar pantalla o teclear [Enter] u otra tecla.                                                    
                                                }
                                            } else {
                                                int longitud = listaActual.size();

                                                if (procesado(listaActual, indice)) {
                                                    break;
                                                }
//                                                
                                                Thread.sleep(2000L);
                                                exploreScreenFields(screen);
                                            }

                                        }

                                    } else {

                                        //emitir una excceion no tiene cantidad de repeticiones 
                                        throw new ExcepcionBaseMsn("Codigo:0020,La expresion de ciclo for no posee numero de iteraciones.");
                                    }
                                    break;

                                case "w":
                                    // segmento de ciclo While de la conexion;   
                                    do {
                                        ScreenFields sf = screen.getScreenFields();
                                        Thread.sleep(3000L);
                                        ScreenField userField = sf.getField(0);
                                        userField.setString(usuario);
                                        ScreenField passField = sf.getField(1);
                                        passField.setString(clave);
                                        screen.sendKeys("[enter]");
                                        Thread.sleep(3000L);

                                        int longitud = listaActual.size();
                                        String pantalla = getScreenAsString(screen).trim();
                                        if (expresionId > 0) {
                                            Export expReq = ExpresionesAS4(getScreenAsString(screen).trim(), expresionId);
                                            if (expReq.getFlag()) {

                                                if (procesado(listaActual, indice)) {
                                                    flag2 = false;
                                                }
                                                Thread.sleep(2000L);
                                                exploreScreenFields(screen);
                                            } else {
                                                Boolean a = true;
                                                PantallaDto pant = new PantallaDto();
                                                if (actExp == "i") {
                                                    pant.setTextoPantalla(printScreen(screen));
                                                    listPatallaSiluladora.add(pant);
                                                    flag2 = false;
                                                }
                                            }
                                        } else {
                                            if (procesado(listaActual, indice)) {
                                                flag2 = false;
                                            }
                                            Thread.sleep(2000L);
                                            exploreScreenFields(screen);

                                        }

                                    } while (flag2);
                                    break;

                            }

                        } else {
                            ScreenFields sf = screen.getScreenFields();
                            Thread.sleep(3000L);
                            ScreenField userField = sf.getField(0);
                            userField.setString(usuario);
                            ScreenField passField = sf.getField(1);
                            passField.setString(clave);
                            screen.sendKeys("[enter]");
                            Thread.sleep(3000L);
                            String pantalla = getScreenAsString(screen).trim();
                            if (expresionId > 0) {
                                Export expReq = ExpresionesAS4(pantalla, expresionId);
                                if (expReq.getFlag()) {

                                    int longitud = listaActual.size();
                                    procesado(listaActual, indice);

                                    Thread.sleep(2000L);
                                    exploreScreenFields(screen);

                                } else {
                                    Boolean a = true;
                                    PantallaDto pant = new PantallaDto();
                                    if (actExp == "i") {
                                        pant.setTextoPantalla(printScreen(screen));
                                        listPatallaSiluladora.add(pant);
                                        throw new ExcepcionBaseMsn("Codigo:0020,error en panatalla no manejado");
                                    } else if (actExp == "r") {
                                        userField.setString(usuario);
                                        passField.setString(clave);
                                        screen.sendKeys("[enter]");
                                        pant.setTextoPantalla(printScreen(screen));
                                        listPatallaSiluladora.add(pant);
                                    }

                                }
                            } else {
                                int longitud = listaActual.size();

                                procesado(listaActual, indice);

                                Thread.sleep(2000L);
                                exploreScreenFields(screen);
                            }

                        }
                    } else {
                        throw new ExcepcionBaseMsn("Codigo:0002, Error Rota Conexion remota con el servidor AS400");
                    }
                    indice++;
                } else if (scrits.contains("oper")) {
                    boolean flag2 = true;
                    pantallaDto.setPantallaNumero(listPatalla.size() + 1);

                    if (sessions.isConnected()) {
                        String idCiclo = dataForm[4].split(":")[1];
                        Integer numInt = Integer.valueOf(dataForm[5].split(":")[1]);
                        Integer expresionId = Integer.valueOf(dataForm[6].split(":")[1]);
                        if (!idCiclo.equals("0")) {
                            switch (idCiclo) {
                                case "f":
                                    // segmento de ciclo for de la operaciones
                                    if (numInt > 0) {
                                        for (int j = 0; j < numInt; j++) {
                                            operaciones(dataForm);
                                            String pantallaTexto = getScreenAsString(screen).trim();
                                            if (expresionId > 0) {
                                                Export expReq = ExpresionesAS4(pantallaTexto, expresionId);
                                                if (expReq.getFlag()) {
                                                    int longitud = listaActual.size();
                                                    if (procesado(listaActual, indice)) {
                                                        break;
                                                    }
//                                                    
                                                } else {

                                                    // manejar el accion programada para la expresion Mostrar pantalla o teclear [Enter] u otra tecla.
                                                    Boolean a = true;
                                                    PantallaDto pant = new PantallaDto();
                                                    if (actExp == "i") {

                                                        pant.setTextoPantalla(printScreen(screen));
                                                        listPatallaSiluladora.add(pant);
                                                        throw new ExcepcionBaseMsn("Codigo:0010,Ejecucion de pantalla alternativa");

                                                    }

                                                }
                                            } else {

                                                if (procesado(listaActual, indice)) {
                                                    break;
                                                }
                                            }
                                        }
                                    } else {
                                        //emitir una excceion no tiene cantidad de repeticiones 
                                        throw new ExcepcionBaseMsn("Codigo:0020,La expresion de ciclo for no posee numero de iteraciones.");
                                    }
                                    break;
                                case "w":
                                    // segmento de ciclo while de la operaciones
                                    do {
                                        operaciones(dataForm);
                                        int longitud = listaActual.size();
                                        String pantalla = getScreenAsString(screen).trim();
                                        if (expresionId > 0) {
                                            Export expReq = ExpresionesAS4(getScreenAsString(screen).trim(), expresionId);
                                            if (expReq.getFlag()) {
                                                if (procesado(listaActual, indice)) {
                                                    flag2 = false;
                                                }

                                            } else {

                                                Boolean a = true;
                                                PantallaDto pant = new PantallaDto();
                                                if (actExp == "i") {

                                                    pant.setTextoPantalla(printScreen(screen));
                                                    listPatallaSiluladora.add(pant);
                                                    throw new ExcepcionBaseMsn("Codigo:0010,Ejecucion de pantalla alternativa");

                                                }

                                                // manejar el accion programada para la expresion Mostrar pantalla o teclear [Enter] u otra tecla.   
                                            }

                                        } else {

                                            if (procesado(listaActual, indice)) {
                                                flag2 = false;
                                            }

                                        }
                                    } while (flag2);

                                    break;
                            }
                        } else {

                            operaciones(dataForm);
                            int longitud = listaActual.size();
                            String pantalla = getScreenAsString(screen).trim();

                            if (expresionId > 0) {
                                Export expReq = ExpresionesAS4(getScreenAsString(screen).trim(), expresionId);
                                if (expReq.getFlag()) {
                                    if (procesado(listaActual, indice)) {
                                        flag2 = false;
                                    }

                                } else {

                                    Boolean a = true;
                                    PantallaDto pant = new PantallaDto();
                                    if (actExp == "i") {
                                        pant.setTextoPantalla(printScreen(screen));
                                        listPatallaSiluladora.add(pant);
                                        throw new ExcepcionBaseMsn("Codigo:0010,Ejecucion de pantalla alternativa");
                                    } else if (actExp == "r") {
                                        operaciones(dataForm);
                                        pant.setTextoPantalla(printScreen(screen));
                                        listPatallaSiluladora.add(pant);
                                    }
                                }
                            } else {
                                if (procesado(listaActual, indice)) {
                                    flag2 = false;
                                }
                            }
                        }

                    } else {
                        throw new ExcepcionBaseMsn("Codigo:0002, Error Rota Conexion remota con el servidor AS400");
                    }

                    indice++;

                }
                //listPatalla.add(pantallaDto);
            }
            PantallaDto pant = new PantallaDto();
            pant.setTextoPantalla(printScreen(screen));
            listPatallaSiluladora.add(pant);
            sessions.disconnect();

        } catch (ExcepcionBaseMsn ex) {
            sessions.disconnect();
            return listPatallaSiluladora;
        } catch (InterruptedException ex) {
            sessions.disconnect();
            Logger.getLogger(AdminRobotController.class.getName()).log(Level.SEVERE, null, ex);
            return listPatallaSiluladora;
        }
        return listPatallaSiluladora;

    }

    public Boolean operacionesAlternativas(String textoDePantalla, List<PantallaDto> listaActual, String operacion) {
        Boolean process = false;
        String[] dataForm2 = new String[70];
        for (PantallaDto pantallaDto1 : listaActual) {
            dataForm2 = pantallaDto1.getScrips().split(",");
            String num = (pantallaDto1.getScrips().split(",")[1]);
            String textComparador = (pantallaDto1.getScrips().split(",")[2].split(":")[1]);
            if (pantallaDto1.getScrips().contains("opc") && textoDePantalla.contains(textComparador)) {
                if (operacion != "conec") {
                    operaciones(dataForm2);
                }
                process = true;
            }
        }
        return process;
    }

    public void operaciones(String[] dataForm) {
        ScreenFields sf = screen.getScreenFields();
        try {
            Thread.sleep(3000L);
            for (int i = 7; i < dataForm.length; i++) {
                String datos = dataForm[i];
                String[] datoAux = datos.split(":");
                String indice = datoAux[0].split("_")[1];
                String valor = datoAux[1];
                valor = valor.replace("*", "");;
                System.out.println(" indice = " + indice + " valor =" + valor);

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

            if (dataForm[3].split(":")[1].equals("[enter]")) {
                screen.sendKeys("[enter]");
            } else {
                screen.sendKeys(dataForm[3].split(":")[1]);
            }

            Thread.sleep(3000L);


        } catch (InterruptedException ex) {
            Logger.getLogger(AdminRobotController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Boolean actualiza(List<PantallaDto> listaActual) throws ExcepcionBaseMsn, InterruptedException {
        Boolean flag = true;
        String[] dataForm = new String[700];
        String scrits = "";
        int pantallaNum = 0;
        for (PantallaDto pantallaDto : listaActual) {
            pantallaNum++;
            scrits = pantallaDto.getScrips();
            dataForm = pantallaDto.getScrips().split(",");
            pantallaDto.setId(null);
            if (scrits.contains("conec")) {
                pantallaDto.setPantallaNumero(listPatalla.size() + 1);
                String host = dataForm[4];
                host = host.split(":")[1];
                host = host.replace("*", "");
                String usuario = dataForm[5];
                usuario = usuario.replace("*", "");
                String clave = dataForm[6];
                clave = clave.replace("*", "");

                screen = connect(host, usuario, clave);

                if (sessions.isConnected()) {
                    printScreen2(screen);
                    ScreenFields sf = screen.getScreenFields();
                    Thread.sleep(2000L);
                    ScreenField userField = sf.getField(0);
                    userField.setString(usuario);
                    ScreenField passField = sf.getField(1);
                    passField.setString(clave);
                    screen.sendKeys("[enter]");
                    printScreen2(screen);
                    Thread.sleep(2000L);
                    exploreScreenFields(screen);
                } else {
                    flag = false;
                    throw new ExcepcionBaseMsn("Codigo:0010,error manejado modulo de conexion");
                }

            } else if (scrits.contains("oper")) {
                pantallaDto.setPantallaNumero(listPatalla.size() + 1);
                operaciones(dataForm);
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
        pant.setPantallaNumero(pantallaNum + 1);
        pant.setActiveKey(true);
        pant.setAction("sesiosionAct");
        this.listPatalla.add(pant);
        marcarUltima();
        return flag;
    }

    @RequestMapping(value = "/accionSelector", method = RequestMethod.POST)
    public ModelAndView accionSelector(EnviarInformacion accion, HttpSession session) {
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");

        if (accion.getAccionSelector() != null) {
            transIni.clear();
            if (transIni == null || transIni.size() == 0 || service1.getTransacionByTipoUsuario(1, 1).size() > transIni.size()) {

                transIni = service1.getTransacionByTipoUsuario(1, user.getId());
            }
            if (accion.getAccionSelector() == 1) {
                model.addObject("actividad", 1);
                model.addObject("paso", 2);
                model.addObject("errorFlag", false);
            } else if (accion.getAccionSelector() == 2) {
                trans.clear();
                if (trans == null || trans.size() == 0 || service1.getTransacionByTipoUsuario(0, user.getId()).size() > trans.size()) {

                    trans = service1.getTransacionByTipoUsuario(0, user.getId());

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

        model.addObject("expresiones", service1.getExpresionAll());
        model.addObject("trans", trans);
        model.addObject("transIni", transIni);
        service1.sessionActivaById(user.getId(), Boolean.TRUE);
        return model;
    }

    @RequestMapping(value = "/simuladorOffLine", method = RequestMethod.GET)
    public ModelAndView simulador(HttpSession session) {

        ModelAndView model;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        transAll.clear();
        if (user != null) {

            if (transAll == null || transAll.size() == 0 || service1.getTransaccionAll().size() > transAll.size()) {

                transAll = service1.getTransaccionAll();
            }
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("transaccionEdit", transAll);
            model.addObject("paso", 4);
            model.addObject("simulador", true);

        } else {
            model = new ModelAndView("login");
            model.addObject("paso", 0);

        }
        service1.sessionActivaById(user.getId(), Boolean.TRUE);
        return model;
    }

    @RequestMapping(value = "/simuladorOnLine", method = RequestMethod.GET)
    public ModelAndView simuladorOnLine(HttpSession session) {

        ModelAndView model;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        transAll.clear();
        if (user != null) {

            if (transAll == null || transAll.size() == 0 || service1.getTransaccionAll().size() > transAll.size()) {

                transAll = service1.getTransaccionAll();
            }

            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("transaccionEdit", transAll);
            model.addObject("paso", 4);
            model.addObject("simulador", false);

        } else {
            model = new ModelAndView("login");
            model.addObject("paso", 0);

        }
        service1.sessionActivaById(user.getId(), Boolean.TRUE);
        return model;
    }

    @RequestMapping(value = "/transacciones", method = RequestMethod.GET)
    public ModelAndView transacciones(HttpSession session) {
        ModelAndView model;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        if (user != null) {
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 2);
        } else {
            model = new ModelAndView("login");
            model.addObject("paso", 0);

        }
        service1.sessionActivaById(user.getId(), Boolean.TRUE);
        return model;
    }

    /*-----------------------------------------------------------------------------*/
    public Export ExpresionesAS4(String textoDePantalla, Integer idExpresion) {
        Export flag = new Export();
        Boolean process = true;
        if (idExpresion > 0) {
            ExpresionesRegularesIO ExpresionAs = service1.getExpresionById(idExpresion);
            if (util.comparadorDeCaracteres(textoDePantalla, ExpresionAs.getCodError())) {
                flag.setDescripcion(ExpresionAs.getMensajeError());
                process = false;
            }
        } else {
            List<ExpresionesRegularesIO> expresionesAS = service1.getExpresionAll();
            for (ExpresionesRegularesIO expresionRegular : expresionesAS) {
                if (util.comparadorDeCaracteres(textoDePantalla, expresionRegular.getCodError())) {
                    flag.setDescripcion(expresionRegular.getMensajeError());
                    process = false;
                }
            }
        }
        flag.setFlag(process);

        return flag;
    }

    @RequestMapping(value = "/delExpresionById", method = RequestMethod.GET)
    @ResponseBody
    public Boolean delExpresionById(@RequestParam Integer id) {
        Boolean flagExpresion = service1.delExpresionById(id);
        return flagExpresion;
    }

    /*-----------------------------------------------------------------------------*/
    @RequestMapping(value = "/expresiones", method = RequestMethod.GET)
    public ModelAndView expresiones(HttpSession session) {
        boolean flag = false;
        ModelAndView model;
        String usuario = "";
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        if (user != null) {
            expresiones = service1.getExpresionAll();
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("expresiones", expresiones);
            model.addObject("actividad", 2);
        } else {

            model = new ModelAndView("/login");
            model.addObject("message", "No posee sesion activa ");
        }

        model.addObject("paso", 15);
        model.addObject("admin", flag);
        service1.sessionActivaById(user.getId(), Boolean.TRUE);
        return model;
    }

    @RequestMapping(value = "/expresiones", method = RequestMethod.POST)
    public ModelAndView expresionesAdUp(ExpresionesRegularesIO expresionesRegulares, HttpSession session) throws InterruptedException {
        boolean flag = false;
        ModelAndView model;
        String usuario = "";

        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");

        if (user != null) {
            if (expresionesRegulares.getId() == null) {
                if (service1.guardarExpresion(expresionesRegulares).getId() != null) {
                    flag = true;
                }
            } else {
                ExpresionesRegularesIO expAuxiliar = service1.getExpresionById(expresionesRegulares.getId());

                if (expresionesRegulares.getCodError() != null) {
                    expAuxiliar.setCodError(expresionesRegulares.getCodError());
                }
                if (expresionesRegulares.getMensajeError() != null) {
                    expAuxiliar.setMensajeError(expresionesRegulares.getMensajeError());
                }
                if (service1.guardarExpresion(expAuxiliar).getId() != null) {
                    flag = true;
                }

            }
            expresiones = service1.getExpresionAll();
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("expresiones", expresiones);
            model.addObject("actividad", 2);

            if (flag) {
                model.addObject("message", "Expresión Creada o actualizada  Satisfactoria mente");
                model.addObject("messageFlag", flag);
            } else {
                model.addObject("message", "La expresion No pudo ser Creado o Actualizada");
                model.addObject("messageFlag", flag);
            }
        } else {

            model = new ModelAndView("/login");
            model.addObject("message", "No posee sesion activa ");
        }

        model.addObject("paso", 15);
        model.addObject("admin", flag);
        service1.sessionActivaById(user.getId(), Boolean.TRUE);
        return model;

    }

    /*-----------------------------------------------------------------------------*/
    @RequestMapping(value = "/sesiosionAct", method = RequestMethod.POST)
    public ModelAndView sesiosionAct(DatosFormDto datosFormulario, HttpSession session) throws InterruptedException {
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        if (datosFormulario.getW_numPantalla() != null) {
            if (datosFormulario.getW_numPantalla().split(",").length > 1) {
                datosFormulario.setW_numPantalla(datosFormulario.getW_numPantalla().split(",")[0]);
            }
        }
        model.addObject("accionesLista", cargaAcciones());
         model.addObject("expresiones", service1.getExpresionAll());
        
        String[] dataForm = datosFormulario.toStringFilter().split(",");
        String dataFormScrips = datosFormulario.toStringFilter();

        if (datosFormulario.getW_modPantalla().equals("exitAlt")) {
            listPatalla.clear();
            if (service1.delTransacionById(tranSave.getId())) {

                model.addObject("paso", 2);
                model.addObject("flagMsnError", false);
            } else {
                model.addObject("paso", 1);
                model.addObject("flagMsnError", true);
            }
        } else if (datosFormulario.getW_modPantalla().equals("saveLogoutAlt")) {
            if (listPatallaOpcional.size() > 0) {
                if (guardarListaPantalla(2)) {
                    if (exportarTransaccion(tranSave.getId()).getFlag()) {    
                        model.addObject("trans",service1.getTransacionByTipoUsuario(0, user.getId()));
                        model.addObject("paso", 0);
                        model.addObject("actividad", 2);
                        listPatalla.clear();
                        listPatallaOpcional.clear();
                    }

                } else {
                    model.addObject("paso", 3);
                    //activar error correspondiente en pantalla 
                }
            }
        } else if (datosFormulario.getW_modPantalla().equals("logoutAlt")) {

            listPatalla.clear();
            listPatallaOpcional.clear();
            if (service1.delTransacionById(tranSave.getId())) {
                model.addObject("paso", 2);
                model.addObject("flagMsnError", false);
            } else {
                model.addObject("paso", 1);
                model.addObject("flagMsnError", true);
            }
        } else if (datosFormulario.getW_modPantalla().equals("exit")) {
            if (service1.delTransacionById(tranSave.getId())) {
                listPatalla.clear();
                model.addObject("paso", 2);
                model.addObject("flagMsnError", false);
            } else {
                model.addObject("paso", 1);
                model.addObject("flagMsnError", true);
            }

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
                    String server = datosFormulario.getField_0();
                    server = server.replace("*", "");
                    String users = datosFormulario.getField_1();
                    users = users.replace("*", "");
                    String pass = datosFormulario.getField_2();
                    pass = pass.replace("*", "");

                    screen = connect(server, users, pass);
                    if (conectado) {

                        List<String> texts2 = printScreen(screen);
                        actualizaList(dataForm, dataFormScrips);
                        listPatalla.get(0).setTextoPantalla(texts2);
                        ScreenFields sf = screen.getScreenFields();
                        Thread.sleep(3000L);
                        ScreenField userField = sf.getField(0);
                        userField.setString(users);
                        ScreenField passField = sf.getField(1);
                        passField.setString(pass);
                        screen.sendKeys("[enter]");
                        Thread.sleep(3000L);

                        exploreScreenFields(screen);
                        printScreen(screen);

                        Integer idExpr = Integer.valueOf(datosFormulario.getW_expresion());
                        Export expReq = ExpresionesAS4(getScreenAsString(screen).trim(), idExpr);
                        if (expReq.getFlag()) {
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
                            model.addObject("errorForm", expReq.getDescripcion());
                            model.addObject("errorFlag", true);
                            model.addObject("listPantalla", listPatalla);
                        }

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
                    model.addObject("flagMsnError", false);
                    model.addObject("expresiones", service1.getExpresionAll());

                } else if (datosFormulario.getW_modPantalla().equals("oper")) {

                    operaciones(dataForm);
                    PantallaDto pant = new PantallaDto();
                    Integer idExpr = Integer.valueOf(datosFormulario.getW_expresion());
                    Export expReq = ExpresionesAS4(getScreenAsString(screen).trim(), idExpr);

                    if (expReq.getFlag()) {
                        Thread.sleep(3000L);
                        actualizaList(dataForm, dataFormScrips);
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
                        model.addObject("errorFlag", false);
                    } else {
                        model.addObject("errorForm", expReq.getDescripcion());
                        model.addObject("errorFlag", true);

                    }

                    model.addObject("listPantalla", listPatalla);
                    //model.addObject("errorFlag", false);
                    model.addObject("errorFlag", false);
                    pant.setWaccionar(datosFormulario.getW_accionar());

                    if (listPatalla.size() > 1) {
                        model.addObject("botonesGuardar", true);
                    } else {
                        model.addObject("botonesGuardar", false);
                    }
                    model.addObject("paso", 1);
                    //model.addObject("flagMsnError", false);

                } else if (datosFormulario.getW_modPantalla().equals("opc")) {

                    PantallaDto pant = new PantallaDto();
                    pant.setAction("sesiosionAct");
                    pant.setActive(false);
                    pant.setActiveKey(false);
                    pant.setPantallaNumero(Integer.valueOf(datosFormulario.getW_numPantalla()));
                    pant.setScrips(datosFormulario.toStringFilter());
                    pant.setWaccionar(datosFormulario.getW_accionar());
                    pant.setWaccionar(dataForm[3].split(":")[1]);
                    List<InputDto> inps = generaFieldsInputs(dataForm, datosFormulario);
                    pant.setInputs(inps);
                    listPatallaOpcional.add(pant);

                    model.addObject("listPatallaOpcional", listPatallaOpcional);
                    model.addObject("tranNombre", tranSave.getNombre());

                    if (listPatallaOpcional.size() > 0) {
                        model.addObject("botonesGuardarOpc", true);
                    } else {
                        model.addObject("botonesGuardarOpc", false);
                    }
                    model.addObject("paso", 3);
                    model.addObject("expresiones", service1.getExpresionAll());
                }
               model.addObject("expresiones", service1.getExpresionAll());

            }
        }
        service1.sessionActivaById(user.getId(), Boolean.TRUE);
        return model;
    }

    @RequestMapping(value = "/eliminarPantalla", method = RequestMethod.POST)
    public ModelAndView eliminarPantalla(DatosFormDto datosFormulario, HttpSession session) throws InterruptedException {
          UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
          service1.sessionActivaById(user.getId(), Boolean.TRUE);
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
    public ModelAndView editTransaccion(@ModelAttribute DatosFormDto datosFormulario, HttpSession session) throws InterruptedException {
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
          service1.sessionActivaById(user.getId(), Boolean.TRUE);
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        Integer id = Integer.valueOf(datosFormulario.getField_0());
        listPatallaAuxiliar.clear();
        boolean flag = false;

        TransaccionIO transaccion = service1.getTransacionById(id);
        listPatallaAuxiliar.addAll(service1.getPantallaByIdTransaccion(id));

        model.addObject("transaccion", transaccion);
        model.addObject("transaccion", transaccion);
        model.addObject("actividad", 3);
        model.addObject("pantallas", listPatallaAuxiliar);
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
          UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
          service1.sessionActivaById(user.getId(), Boolean.TRUE);
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        ConexionAsDto conn = new ConexionAsDto();

        screen = connect(conn.getServidor(), conn.getUsuario(), conn.getClave());
        if (conectado) {
            PantallaDto pant = new PantallaDto();

            List<InputDto> inps = exploreScreenFieldsInputs(screen);
            pant.setInputs(inps);
            pant.setListAcciones(cargaAcciones());
            pant.setActiveKey(true);        
            List<String> texts = printScreen(screen);
            pant.setTextoPantalla(texts);           
            pant.setPantallaNumero(listPatalla.size() + 1);
            this.listPatalla.add(pant);
            marcarUltima();
            model.addObject("listPantalla", listPatalla);
        } else {

            model.addObject("listPantalla", listPatalla);
        }
        model.addObject("paso", 1);
        model.addObject("flagMsnError", false);
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
            printScreen(screen);
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

        for (int i = 7; i < dataForm.length; i++) {
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

            sb += " \n ";
            pantalla.add(sb2);
        }
        System.out.println(sb);
        return pantalla;
    }

    private void printScreen2(Screen5250 screen) {
        String showme = getScreenAsString(screen);
        String sb = "";

        for (int i = 0; i < showme.length(); i += 80) {
            sb += showme.substring(i, i + 80);
            sb += "\n";
        }
        System.out.println(sb);
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
                                input.setValue(valor);
                            }
                            if (indice.equals("w_numPantalla")) {
                                input.setValue(valor);
                            }
                            if (indice.equals("w_idPantalla")) {
                                input.setValue(valor);
                            }
                            if (indice.equals("w_accionar")) {
                                input.setValue(valor);
                            }
                            if (indice.equals("w_ciclo")) {
                                input.setValue(valor);
                            }
                            if (indice.equals("field_0")) {
                                input.setValue(valor);
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
