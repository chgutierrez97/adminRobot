package com.accusy.robotpro.robotadmin.services;

import com.accusy.robotpro.robotadmin.dto.CancelacionesDto;
import com.accusy.robotpro.robotadmin.dto.InputDto;
import com.accusy.robotpro.robotadmin.dto.PantallaDto;
import com.accusy.robotpro.robotadmin.dto.Persona;
import com.accusy.robotpro.robotadmin.dto.Roles;
import com.accusy.robotpro.robotadmin.dto.Status;
import com.accusy.robotpro.robotadmin.dto.Usuario;
import com.accusy.robotpro.robotadmin.dto.UsuarioDTO;
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

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@PropertySource("classpath:application.properties")
public class ServicesRobot {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        final String url = urlpaht+"findAllExpresion";
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
        //System.out.println(result);
        return result.getExpresionesList();
    }
    
     public List<CancelacionesDto> getCancelacionesAll() {
        final String url = urlpaht+"findAllCancelacion";
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
        return result.getCancelacionesList();
    }
     
       public Boolean  actualizarCancelacionById(Integer id , String valor, Integer flag ){
        final String url = urlpaht+"findAllCancelacion";
        final String url2 = urlpaht+"saveCancelacion";
        CancelacionesDto cancelacion = new CancelacionesDto();
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
           for (CancelacionesDto cancelacionesDto : result.getCancelacionesList()) {
               if(cancelacionesDto.getId().toString().equals(id.toString())){
                    cancelacion = cancelacionesDto;
                    break;
               }
           }
           if((!valor.equals(""))&& valor!=null){
             cancelacion.setOpion(valor);
           }
           if(flag!=null){
            cancelacion.setFlag(flag);
           }
        CancelacionesDto cancelacionUpdate = restTemplate.postForObject(url2, cancelacion, CancelacionesDto.class);       
        return (cancelacionUpdate.getOpion().equals(cancelacion.getOpion()))?true:false;
    }
       
       public Boolean  crearCancelacion(CancelacionesDto cancelacion ){
       
        final String url = urlpaht+"saveCancelacion";     
        RestTemplate restTemplate = new RestTemplate();
        CancelacionesDto cancelacionUpdate = restTemplate.postForObject(url, cancelacion, CancelacionesDto.class);
       
        return (cancelacionUpdate.getOpion().equals(cancelacion.getOpion()))?true:false;
    }
       
       public ExpresionesRegularesIO getCancelacionByName(String  nameExpresion) {
        
        final String url = urlpaht+"findCancelacionByName";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("name", nameExpresion);
        ExpresionesRegularesIO result = restTemplate.getForObject(builder.toUriString(), ExpresionesRegularesIO.class);

        return result;
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
    
        public Boolean delCancelacionById(Integer id) {

        final String url = urlpaht+"deleteCancelacionById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", id);
        Boolean result = restTemplate.getForObject(builder.toUriString(), Boolean.class);
        //System.out.println(result);
        return result;
    }
    
    public List<TransaccionIO> findByTransaccionIniId(Integer idTransacion) {
        
        final String url = urlpaht+"findByTransaccionIniId";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("idTransaccion", idTransacion);
        ListaMacroIO result = restTemplate.getForObject(builder.toUriString(), ListaMacroIO.class);

        return result.getTransaccionList();
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
    
    public List<TransaccionIO> getTransacionByNoTipo(Integer idTipo) {  
        final String url = urlpaht+"findByNotTipo";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("idTipo", idTipo);
        ListaMacroIO result = restTemplate.getForObject(builder.toUriString(), ListaMacroIO.class);
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
    
      public PantallaIO guardarPantalla(PantallaIO pantalla) {
        
        final String url = urlpaht+"savePantalla";
        RestTemplate restTemplate = new RestTemplate();
        PantallaIO result = restTemplate.postForObject(url, pantalla, PantallaIO.class);
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
        List<PantallaDto> listPatalla1 = new ArrayList<>();
        
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
        
        for (PantallaDto pantallaDto : listPatalla) {
              String scrits = pantallaDto.getScrips();
              
                if (scrits.contains("conec")) {
                    listPatalla1.add(pantallaDto);
                }
                if (scrits.contains("oper")) {
                    listPatalla1.add(pantallaDto);
                }   
                  
        }
        
            for (PantallaDto pantallaDto : listPatalla) {
             String scrits = pantallaDto.getScrips();
                if (scrits.contains("opc")) {
                    listPatalla1.add(pantallaDto);
                }
        }
        return listPatalla1;
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
    
        public Boolean sessionActivaById(Integer id, Boolean sessionActive) {

        final String url = urlpaht+"marcarSessionUsersById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", id)
                .queryParam("sessionActive", sessionActive);
        Boolean result = restTemplate.getForObject(builder.toUriString(), Boolean.class);
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
            
            persona.setFechaCarga(getPersonaById(persona.getId()).getFechaCarga()); 
            // Canal de actualizacion, id viene con valor (Update)
            // Persona perI = new Persona(persona.getId(), persona.getNombre(), persona.getApellido(), persona.getDni(), myDay);
            Persona result = restTemplate.postForObject(uri, persona, Persona.class);
            //System.out.println(result);
            return result;
        }
    }

       public Boolean deletePersonaById(Integer id) {

        final String url = urlpaht+"deletePersonaById";
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", id);
        Boolean result = restTemplate.getForObject(builder.toUriString(), Boolean.class);
        //System.out.println(result);
        return result;
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
            return result;
        } else {
            return per;
        }

    }

    public List<PersonaIO> getPersonaList() {
        final String url = urlpaht+"findAllPersona";
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
        return result.getPersonaList();
    }
    
    
    public Usuario guardarUsuario(UsuarioDTO usuario, HttpSession session) throws ParseException {
        // ADM 
        Usuario usu = null;
        final String uri = urlpaht+"saveUsuario";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06");
        UtilRobot utils = new UtilRobot();
        if (usuario.getId() == null) {
            Persona perIngresa = (Persona) session.getAttribute("pers");
            Roles roles = new Roles(usuario.getRoles(), "nu");
            Status status = new Status(usuario.getStatus(), "OI");
            Usuario usua = new Usuario(null, usuario.getUsuario(), usuario.getClave(), new Date(), perIngresa, roles, status,Boolean.FALSE,new Date().getTime());
            usua.setClave(passwordEncoder.encode(usua.getClave()));           
            RestTemplate restTemplate = new RestTemplate();
            Usuario result = restTemplate.postForObject(uri, usua, Usuario.class);
            session.removeAttribute("pers");
            return result;
        } else {
            Usuario usuaOld = getUsuarioById(usuario.getId());
          if(usuario.getRoles()!=null){
              Roles roles = new Roles(usuario.getRoles(), "nu");
              usuaOld.setRoles(roles);
          }
          if(usuario.getStatus()!=null){
              Status status = new Status(usuario.getStatus(), "OI");
              usuaOld.setStatus(status);
          }
          if(usuario.getClave()!=null && usuario.getClave()!=""){
             if(!usuaOld.getClave().equals(usuario.getClave())){
                usuaOld.setClave(passwordEncoder.encode(usuario.getClave()));
             }
           
          }
            RestTemplate restTemplate = new RestTemplate();
            Usuario result = restTemplate.postForObject(uri, usuaOld, Usuario.class);
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
        Usuario usua = new Usuario(null, usuario.getUsuario(), usuario.getClave(), myDay, perIngresa, roles, status,Boolean.FALSE,new Date().getTime());
        RestTemplate restTemplate = new RestTemplate();
        UtilRobot utils = new UtilRobot();
        if (!utils.ifValidUserExist(usua)) {
            Usuario result = restTemplate.postForObject(uri, usua, Usuario.class);            
            session.removeAttribute("pers");
            return result;
        } else {
            session.removeAttribute("pers");
            return usu;
        }
    }

    public List<UsuarioIO> getUsuarioList() {
        final String url = urlpaht+"findAllUsuario";
        RestTemplate restTemplate = new RestTemplate();
        ListaMacroIO result = restTemplate.getForObject(url, ListaMacroIO.class);
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
  /**
     * Permite Autenticar usuario y contraseña ante un servidor LDAP
     *
     * @param usuario (String) Usuario ingresado para loggearse
     * @param password (String) Password ingresado para loggearse
     * @return true si el login se realizo exitosamen, false si hubo fallo
     * @author Argenis R.
     */
    public boolean autenticacionLDAP(String usuario, String password) {
        Hashtable auth = new Hashtable(11);
        String base = "ou=Venezuela,dc=accusysargbsas,dc=local";
        String dn = "uid=" + usuario + "," + base;
        String ldapURL = "ldap://172.28.194.24:389";

        auth.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        auth.put(Context.PROVIDER_URL, ldapURL);
        auth.put(Context.SECURITY_AUTHENTICATION, "simple");
        auth.put(Context.SECURITY_PRINCIPAL, dn);
        auth.put(Context.SECURITY_CREDENTIALS, password);

        try {
            DirContext authContext = new InitialDirContext(auth);
            System.out.println("LA AUTENTICACION SE REALIZAO CORRECTAMENTE ANTE EL LDAP!");
            return true;
        } catch (AuthenticationException authEx) {
            authEx.printStackTrace();
            System.out.println("NO SE ENCONTRO ESTOS DATOS!");
            return false;

        } catch (NamingException namEx) {
            System.out.println("SUCEDIO ALGO!");
            namEx.printStackTrace();
            return false;
        }
    }  
    

}
