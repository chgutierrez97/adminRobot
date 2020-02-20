package com.accusy.robotpro.robotadmin.services;

import com.accusy.robotpro.robotadmin.dto.InputDto;
import com.accusy.robotpro.robotadmin.dto.PantallaDto;
import com.accusy.robotpro.robotadmin.dto.Persona;
import com.accusy.robotpro.robotadmin.dto.Roles;
import com.accusy.robotpro.robotadmin.dto.Status;
import com.accusy.robotpro.robotadmin.dto.Usuario;
import com.accusy.robotpro.robotadmin.model.ExpresionesRegularesIO;
import com.accusy.robotpro.robotadmin.model.InputIO;
import com.accusy.robotpro.robotadmin.model.ListaMacroIO;
import com.accusy.robotpro.robotadmin.model.PantallaIO;
import com.accusy.robotpro.robotadmin.model.PersonaIO;
import com.accusy.robotpro.robotadmin.model.TextoPantallaIO;
import com.accusy.robotpro.robotadmin.model.TransaccionIO;
import com.accusy.robotpro.robotadmin.model.TransaccionOI;
import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.utils.UtilRobot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource("classpath:application.properties")
public class ServicesRobot {

    @Value("${paht.url.service}")
    String urlpaht;

    public List<TransaccionIO> getTransaccionAll() {
        
        final String url = urlpaht+"findAllTransaccion";
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
        //System.out.println(result);
        return result.getTransaccionList();
    }

    public List<ExpresionesRegularesIO> getExpresionAll() {
//       ";
        final String url = urlpaht+"findAllExpresion";
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
        //System.out.println(result);
        return result.getExpresionesList();
    }

    public ExpresionesRegularesIO getExpresionById(Integer idExpresion) {
        
        final String url = urlpaht+"findExpresionById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", idExpresion);
        ExpresionesRegularesIO result = restTemplate.getForObject(builder.toUriString(), ExpresionesRegularesIO.class);

        return result;
    }

    public ExpresionesRegularesIO guardarExpresion(ExpresionesRegularesIO trans) {
        
        final String url = urlpaht+"saveExpresion";
        RestTemplate restTemplate = new RestTemplate();
        ExpresionesRegularesIO result = restTemplate.postForObject(url, trans, ExpresionesRegularesIO.class);
        return result;
    }

    public Boolean delExpresionById(Integer id) {

        final String url = urlpaht+"deleteExpresionById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", id);
        Boolean result = restTemplate.getForObject(builder.toUriString(), Boolean.class);
        //System.out.println(result);
        return result;
    }

    public List<TransaccionIO> getTransacionByTipoUsuario(Integer idTipo, Integer idUsuario) {
        
        final String url = urlpaht+"findTransacionByTipoUsuario";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("idUsuario", idUsuario)
                .queryParam("idTipo", idTipo);
        ListaMacroIO result = restTemplate.getForObject(builder.toUriString(), ListaMacroIO.class);
        //System.out.println(result);
        return result.getTransaccionList();
    }

    public TransaccionIO getTransacionById(Integer idTransaccion) {
        
        final String url = urlpaht+"findTransaccionById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", idTransaccion);
        TransaccionIO result = restTemplate.getForObject(builder.toUriString(), TransaccionIO.class);
        //System.out.println(result);
        return result;
    }

    public PersonaIO getPersonaById(Integer idPersona) {
        
        final String url = urlpaht+"findPersonaById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", idPersona);
        PersonaIO result = restTemplate.getForObject(builder.toUriString(), PersonaIO.class);
        //System.out.println(result);
        return result;
    }

    public Persona getPersonaByIds(Integer idPersona) {
        
        final String url = urlpaht+"findPersonaById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", idPersona);
        Persona result = restTemplate.getForObject(builder.toUriString(), Persona.class);
        return result;
    }

    public List<TransaccionIO> getTransacionByTipo(Integer idTipo) {
        
        final String url = urlpaht+"findTransacionByTipo";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("idTipo", idTipo);
        ListaMacroIO result = restTemplate.getForObject(builder.toUriString(), ListaMacroIO.class);
        //System.out.println(result);
        return result.getTransaccionList();
    }

    public Boolean delTransacionById(Integer id) {

        final String url = urlpaht+"deleteTransaccionById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", id);
        Boolean result = restTemplate.getForObject(builder.toUriString(), Boolean.class);
        //System.out.println(result);
        return result;
    }

    public Boolean updateScripPantalla(String scrips, Integer pantallaId) {
        
        final String url = urlpaht+"updateScripPantalla";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("scrips", scrips)
                .queryParam("pantallaId", pantallaId);
        Boolean result = restTemplate.getForObject(builder.toUriString(), Boolean.class);
        //System.out.println(result);
        return result;
    }

    public List<PantallaDto> getPantallaByIdTransaccion(Integer idTransaccion) {
        List<PantallaDto> listPatalla = new ArrayList<>();
        
        final String url = urlpaht+"findPantallaByIdTransaccion";
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("idTransaccion", idTransaccion);
        ListaMacroIO result = restTemplate.getForObject(builder.toUriString(), ListaMacroIO.class);
        for (PantallaIO pantallaIO : result.getPantallasList()) {
            PantallaDto pant = new PantallaDto();
            pant.setAction(pantallaIO.getPantallaAction());
            pant.setId(Long.valueOf("" + pantallaIO.getId()));

            pant.setActive(pantallaIO.getPantallaActive());
            pant.setActiveKey(pantallaIO.getPantallaActivekey());

            pant.setIdTransaccion(idTransaccion);

            pant.setScrips(pantallaIO.getPantallaScrips());
            List<InputDto> inputList = new ArrayList<>();
            for (InputIO input : pantallaIO.getInputCollection()) {
                InputDto inputDto = new InputDto();
                inputDto.setIdInp(input.getId());
                inputDto.setId(input.getInputId());
                inputDto.setLabel(input.getInputLabel());
                inputDto.setName(input.getInputName());
                inputDto.setRequired(input.getInputRequired());
                inputDto.setType(input.getInputType());
                inputDto.setValue(input.getInputValue());

                inputList.add(inputDto);
            }
            pant.setInputs(inputList);
            List<String> textoPantallaList = new ArrayList<>();
            for (TextoPantallaIO textoPantallaIO : pantallaIO.getTextoPantallaCollection()) {
                textoPantallaList.add(textoPantallaIO.getTexto());
            }
            pant.setTextoPantalla(textoPantallaList);

            listPatalla.add(pant);
        }
        return listPatalla;

    }

    public List<PantallaDto> getdPantallaByIdTrasaccionEmulacion(Integer idTransaccion) {
        List<PantallaDto> listPatalla = new ArrayList<>();
        
        final String url = urlpaht+"findPantallaByIdTrasaccionEmulacion";
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("idTransaccion", idTransaccion);
        ListaMacroIO result = restTemplate.getForObject(builder.toUriString(), ListaMacroIO.class);
        for (PantallaIO pantallaIO : result.getPantallasList()) {
            PantallaDto pant = new PantallaDto();
            pant.setAction(pantallaIO.getPantallaAction());
            pant.setId(Long.valueOf("" + pantallaIO.getId()));
            pant.setActive(pantallaIO.getPantallaActive());
            pant.setActiveKey(pantallaIO.getPantallaActivekey());
            pant.setIdTransaccion(idTransaccion);
            pant.setScrips(pantallaIO.getPantallaScrips());
            List<InputDto> inputList = new ArrayList<>();
            for (InputIO input : pantallaIO.getInputCollection()) {

                InputDto inputDto = new InputDto();
                inputDto.setIdInp(input.getId());
                inputDto.setId(input.getInputId());
                inputDto.setLabel(input.getInputLabel());
                inputDto.setName(input.getInputName());
                inputDto.setRequired(input.getInputRequired());
                inputDto.setType(input.getInputType());
                inputDto.setValue(input.getInputValue());
                inputList.add(inputDto);
            }
            pant.setInputs(inputList);
            List<String> textoPantallaList = new ArrayList<>();
            for (TextoPantallaIO textoPantallaIO : pantallaIO.getTextoPantallaCollection()) {
                textoPantallaList.add(textoPantallaIO.getTexto());
            }
            pant.setTextoPantalla(textoPantallaList);
            listPatalla.add(pant);
        }
        return listPatalla;

    }

    public UsuarioIO getUsuarioByLoginAndStatus(String login, Integer idStatus) {
        
        final String url = urlpaht+"findUsuarioByLoginAndStatus";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("login", login)
                .queryParam("idStatus", idStatus);
        UsuarioIO result = restTemplate.getForObject(builder.toUriString(), UsuarioIO.class);
        //System.out.println(result.toString());
        return result;
    }

    public UsuarioIO getUserByLoginAndStatus(String login, Integer idStatus) {

        String usuarioUrl = urlpaht+"findUsuarioByLoginAndStatus";

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(usuarioUrl)
                // Add query parameter
                .queryParam("login", login)
                .queryParam("idStatus", idStatus);
        RestTemplate restTemplate = new RestTemplate();
        UsuarioIO result = restTemplate.getForObject(builder.toUriString(), UsuarioIO.class);
        return result;
    }

    public UsuarioIO getUsuarioByLogin(String login) {
        
        final String url = urlpaht+"findUsuarioByLogin";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("login", login);
        UsuarioIO result = restTemplate.getForObject(builder.toUriString(), UsuarioIO.class);
        //System.out.println(result.toString());
        return result;
    }

    public TransaccionIO guardarTransaccion(TransaccionOI trans) {
        
        final String url = urlpaht+"saveTransaccion";
        RestTemplate restTemplate = new RestTemplate();
        TransaccionIO result = restTemplate.postForObject(url, trans, TransaccionIO.class);
        return result;
    }

    public TransaccionIO updateTransaccion(TransaccionOI trans) {
        
        final String url = urlpaht+"updateTransaccion";
        RestTemplate restTemplate = new RestTemplate();
        TransaccionIO result = restTemplate.postForObject(url, trans, TransaccionIO.class);
        return result;
    }

    public PantallaIO guardarPantalla(PantallaIO pantalla) {
        
        final String url = urlpaht+"savePantalla";
        RestTemplate restTemplate = new RestTemplate();
        PantallaIO result = restTemplate.postForObject(url, pantalla, PantallaIO.class);
        return result;
    }

    public InputIO updateInput(InputIO input) {
        
        final String url = urlpaht+"updateInput";
        RestTemplate restTemplate = new RestTemplate();
        InputIO result = restTemplate.postForObject(url, input, InputIO.class);
        return result;
    }

    public Persona guardarPersona(Persona persona) throws ParseException {
        // Adm Persona Guardar.
        // Persona per = null;
        final String uri = urlpaht+"savePersona";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06");
        RestTemplate restTemplate = new RestTemplate();
        if (persona.getId() == null) {
            //   Canal de Creacion, id viene en null  (Add)
            /*   Colocar validacion verificacion de Persona que exista segun cedula o DNI. */
            Persona perI = new Persona(null, persona.getNombre(), persona.getApellido(), persona.getDni(), new Date());
            Persona result = restTemplate.postForObject(uri, perI, Persona.class);
            //System.out.println(result);
            return result;
        } else {
            // Canal de actualizacion, id viene con valor (Update)
            // Persona perI = new Persona(persona.getId(), persona.getNombre(), persona.getApellido(), persona.getDni(), myDay);
            Persona result = restTemplate.postForObject(uri, persona, Persona.class);
            //System.out.println(result);
            return result;
        }
    }

    public Persona guardarPersonaAuto(Persona persona) throws ParseException {
        Persona per = null;
        final String uri = urlpaht+"savePersona";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06");
        RestTemplate restTemplate = new RestTemplate();
        UtilRobot utils = new UtilRobot();

        if (!utils.ifValidPersonExist(persona)) {
            Persona perI = new Persona(null, persona.getNombre(), persona.getApellido(), persona.getDni(), myDay);
            Persona result = restTemplate.postForObject(uri, perI, Persona.class);
            //System.out.println(result);
            return result;
        } else {
            // Canal La persona existe
            return per;
        }

    }

    public List<PersonaIO> getPersonaList() {
        final String url = urlpaht+"findAllPersona";
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
        //System.out.println("com.accusy.robotpro.robotadmin.services.ServicesRobot.getPersonaList() >>>>>>> " + result);
        return result.getPersonaList();
    }

    public Usuario guardarUsuario(Usuario usuario, HttpSession session) throws ParseException {
        // ADM 
        Usuario usu = null;
        final String uri = urlpaht+"saveUsuario";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06");
        UtilRobot utils = new UtilRobot();
        if (usuario.getId() == null) {
            // Canal de Creacion, id viene en null (Add)
            /**
             * Colocar validacion verificacion de Usuario que exista segun el
             * campo usuario.
             */
            Persona perIngresa = (Persona) session.getAttribute("pers");
            Roles roles = new Roles(1, "nu");
            Status status = new Status(1, "OI");
            Usuario usua = new Usuario(null, usuario.getUsuario(), usuario.getClave(), new Date(), perIngresa, roles, status);
            RestTemplate restTemplate = new RestTemplate();
            Usuario result = restTemplate.postForObject(uri, usua, Usuario.class);
            session.removeAttribute("pers");
            return result;
        } else {

            RestTemplate restTemplate = new RestTemplate();
            Usuario result = restTemplate.postForObject(uri, usuario, Usuario.class);
            session.removeAttribute("pers");
            return result;
        }

    }

    public Usuario guardarUsuarioAuto(Usuario usuario, HttpSession session) throws ParseException {
        Usuario usu = null;
        final String uri = urlpaht+"saveUsuario";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06");
        Roles roles = new Roles(1, "nu");
        Status status = new Status(1, "OI");
        Persona perIngresa = (Persona) session.getAttribute("pers");
        Usuario usua = new Usuario(null, usuario.getUsuario(), usuario.getClave(), myDay, perIngresa, roles, status);
        //System.out.println("usua a Enviar al restTemplate ##"+usua);
        RestTemplate restTemplate = new RestTemplate();
        UtilRobot utils = new UtilRobot();
        if (!utils.ifValidUserExist(usua)) {
            Usuario result = restTemplate.postForObject(uri, usua, Usuario.class);
            //System.out.println(result);
            //System.out.println("  Exitoso Creacion de  Credenciales   -  -  > "+ result.getUsuario()+" | IndexController");  
            session.removeAttribute("pers");
            return result;
        } else {
            // El user ya existe en el sistema
            session.removeAttribute("pers");
            return usu;
        }
    }

    public List<UsuarioIO> getUsuarioList() {
        final String url = urlpaht+"findAllUsuario";
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
        //System.out.println("com.accusy.robotpro.robotadmin.services.ServicesRobot.getUsuarioList()" + result);
        return result.getUsuarioList();
    }

    public Usuario getUsuarioById(Integer idUsuario) {
        final String url = urlpaht+"findUsuarioById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", idUsuario);
        Usuario result = restTemplate.getForObject(builder.toUriString(), Usuario.class);
        return result;
    }

}
