package com.accusy.robotpro.robotadmin.controller;


import com.accusy.robotpro.robotadmin.dto.ListaPersonaDTO;
import com.accusy.robotpro.robotadmin.dto.Persona;
import com.accusy.robotpro.robotadmin.dto.Roles;
import com.accusy.robotpro.robotadmin.dto.SecurityQuetion;
import com.accusy.robotpro.robotadmin.dto.Status;
import com.accusy.robotpro.robotadmin.dto.Usuario;
import com.accusy.robotpro.robotadmin.model.PersonaIO;

import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
import com.accusy.robotpro.robotadmin.utils.UtilRobot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@PropertySource("classpath:application.properties")
public class IndexController {

    @Autowired
    ServicesRobot ser;
    @Value("${spring.name.administrator.global}")
    private String administradorGobal;
    @Value("${spring.users.administrator.global}")
    private String administradorUserGobal;
    


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login(HttpSession session) {
        boolean flag = false;
        ModelAndView model;
        String usuario = "";
        usuario = System.getProperty("user.name");

        if (administradorGobal.equals(usuario)) {
            flag = true;
            UsuarioIO user = ser.getUsuarioByLogin(administradorUserGobal);
            if (user.getId() != null) {
                model = new ModelAndView("main/fichaUnicaDatos");
                session.setAttribute("UsuarioSession", user);
            } else {
                model = new ModelAndView("login");
            }

        } else {
            UsuarioIO user = ser.getUsuarioByLogin(usuario);
            if (user.getId() != null) {
                session.setAttribute("UsuarioSession", user);
                model = new ModelAndView("main/fichaUnicaDatos");
            } else {
                model = new ModelAndView("login");
            }
        }
        model.addObject("paso", 0);
        model.addObject("admin", flag);
        return model;
    }
    
    @RequestMapping(value = "/reguserdatos", method = RequestMethod.GET)
    public String printRegDatosUsers(ModelMap model) {
        return "reguserdatos";
    }
    
    @RequestMapping(value = "/transacciones", method = RequestMethod.GET)
    public ModelAndView transacciones(HttpSession session) {
        ModelAndView model ;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
         if(user!=null){
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 2);
         }else{
            model = new ModelAndView("login");
            model.addObject("paso", 0);
         
         }
        return model;
    }  
    
    
    @RequestMapping(value = "/simulador", method = RequestMethod.GET)
    public ModelAndView simulador(HttpSession session) {
        ModelAndView model ;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
         if(user!=null){
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 4);
         }else{
              model = new ModelAndView("login");
            model.addObject("paso", 0);
         }
         return model;
    }  

    @RequestMapping(value = "/regusercredencial", method = RequestMethod.GET)
    public String printRegCredenciales(ModelMap model) {
       
        return "regusercredencial";
    }      
    
    @RequestMapping(value = "/reguserquestions", method = RequestMethod.GET)
    public String printRegQuestions(ModelMap model) {
         
        return "reguserquestions";
    }     
    
    
    
    
    // @RequestMapping(value = "/datosBasicos", method = RequestMethod.POST)
    @RequestMapping(value = "/registrop", method = RequestMethod.POST)
    public ModelAndView registroPersona(@ModelAttribute("persona") Persona persona) throws ParseException {
        
        // Personas - Datos Basicos.
        /* < < < < 
        final String uri = "http://localhost:8080/api/savePersona";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06");
        Persona per = new Persona(null, persona.getNombre(), persona.getApellido(), persona.getDni(), myDay);
        System.out.println("Crear Persona   -  001 -  > per" +per);
        RestTemplate restTemplate = new RestTemplate();
        Persona result = restTemplate.postForObject(uri, per, Persona.class);
        System.out.println("Fue registrada Data basicos  de Persona  #"+ result);
        System.out.println("Crear Persona   -  -  >");
       
        return "regusercredencial";
        > > > >   */
        ModelAndView model = null;
        final String uri = "http://localhost:8080/api/savePersona";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06"); 
        Persona per = new Persona(null, persona.getNombre(), persona.getApellido(), persona.getDni(), myDay);
        System.out.println("usua a Enviar al restTemplate ##"+per);
        
        RestTemplate restTemplate = new RestTemplate();
        UtilRobot utils = new UtilRobot();
        
        if (!utils.ifValidPersonExist(per)){
            Persona result = restTemplate.postForObject(uri, per, Persona.class);
            System.out.println(result);
            System.out.println("  Exitoso Creacion de  Persona   -  -  > "+ result.getNombre()+" | IndexController");  
            model = new ModelAndView("/regusercredencial");
        }else{
            System.out.println(" Ya existe la Persona con el ID ingresado en el sistema, Verifique !!   -  -  > ");
            model = new ModelAndView("/reguserdatos");
            model.addObject("message", "Ya existe una Persona registrada con el ID ingresado en el Sistema, verifique!!");
        }
        return model;        
    } 
    
       
    
    @RequestMapping(value = "/regcredencials", method = RequestMethod.POST)
    public ModelAndView registroCredencials(@ModelAttribute("usuario") Usuario usuario) throws ParseException {
      
        // Creacion de Usuarios (Credenciales)
        ModelAndView model = null;
        final String uri = "http://localhost:8080/api/saveUsuario";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06"); 
        // Debo Traerlo  de la sesion o del resultado del paso anterior Registro Datos Basicos
        Persona persona = new Persona(1,"paul","gonzalez", 1 ,myDay );
        Roles roles = new Roles(1,"nu"); 
        Status status = new Status(1,"OI");
        Usuario usua = new Usuario(null, usuario.getUsuario(),usuario.getClave(), myDay, persona, roles, status);
        System.out.println("usua a Enviar al restTemplate ##"+usua);
        
        RestTemplate restTemplate = new RestTemplate();
       
        UtilRobot utils = new UtilRobot();
        
        if (!utils.ifValidUserExist(usua)){
            Usuario result = restTemplate.postForObject(uri, usua, Usuario.class);
            System.out.println(result);
            System.out.println("  Exitoso Creacion de  Credenciales   -  -  > "+ result.getUsuario()+" | IndexController");  
            model = new ModelAndView("/reguserquestions");
            
        }else{
            System.out.println(" Ya existe el user o Login Ingrese otro %%% IndexController   -  -  > ");
            model = new ModelAndView("/regusercredencial");
            model.addObject("message", "El Usuario ya existe en el Sistema, verifique!!");
            
        }
    
        return model;
        
    } 

    @RequestMapping(value = "/regquestions", method = RequestMethod.POST)
    public ModelAndView registroQuestions(@ModelAttribute("securityquetion") SecurityQuetion securityquetion) throws ParseException {
      
        ModelAndView model = null;
        final String uri = "http://localhost:8080/api/saveUsuario";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06"); 
        // Debo Traerlo  de la sesion o del resultado del paso anterior Registro Datos Basicos
        Persona persona = new Persona(1,"paul","gonzalez", 1 ,myDay );
        Roles roles = new Roles(1,"nu"); 
        Status status = new Status(1,"OI");
        
 /*       En contruccion JG
//        Usuario usua = new Usuario(null, usuario.getUsuario(),usuario.getClave(), myDay, persona, roles, status);
        System.out.println("usua a Enviar al restTemplate ##"+usua);
        
        RestTemplate restTemplate = new RestTemplate();
       
        UtilRobot utils = new UtilRobot();
        
        if (!utils.ifValidUserExist(usua)){
            Usuario result = restTemplate.postForObject(uri, usua, Usuario.class);
            System.out.println(result);
            System.out.println("  Exitoso Creacion de  Credenciales   -  -  > "+ result.getUsuario()+" | IndexController");  
            model = new ModelAndView("/reguserquestions");
        }else{
            System.out.println(" Ya existe el user o Login Ingrese otro %%% IndexController   -  -  > ");
            model = new ModelAndView("/regusercredencial");
            model.addObject("message", "El Usuario ya existe en el Sistema, verifique!!");
        }
    */
        return model;
    } 
    
        
    @RequestMapping(value = "/registroamdPersona", method = RequestMethod.POST)
    public ModelAndView savePersona(Persona persona, HttpSession session) throws ParseException {
        //  ( Add / Update )Personas en modulo Administrador o Auto Registro
        ModelAndView model = null;
        
        final String uri = "http://localhost:8080/api/savePersona";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06"); 
        RestTemplate restTemplate = new RestTemplate();
        UtilRobot utils = new UtilRobot();
        if (persona.getId()== null){
            // Canal de Creacion, id viene en null
            /**
             * Colocar validacion verificacion de Persona que exista segun cedula o codigo.
             */
             
            Persona per = new Persona(null, persona.getNombre(), persona.getApellido(), persona.getDni(), myDay);
            Persona result = restTemplate.postForObject(uri, per, Persona.class);
            System.out.println(result);
//            System.out.println("  Exitoso Creacion de  Persona   -  -  > "+ result.getNombre()+" | IndexController");  
           //model = new ModelAndView("main/fichaUnicaDatos");
           //model.addObject("paso", 8);
            AdmPersonaList(session);
        }else {
        // Canal de actualizacion, id viene con valor
////            if (!utils.ifValidPersonExist(per)){
                Persona per = new Persona(persona.getId(), persona.getNombre(), persona.getApellido(), persona.getDni(), myDay);
                System.out.println("com.accusy.robotpro.robotadmin.controller.IndexController.savePersona()  >>>" + per  );
                Persona result = restTemplate.postForObject(uri, per, Persona.class);
                System.out.println(result);
               
//                System.out.println("  Exitoso Creacion de  Persona   -  -  > "+ result.getNombre()+" | IndexController");  
               //model = new ModelAndView("main/fichaUnicaDatos");
               //model.addObject("paso", 8);
               
////            }else{
////                System.out.println(" Ya existe la Persona con el ID ingresado en el sistema, Verifique !!   -  -  > ");
////                model = new ModelAndView("/adm_newpersona");
////                model = new ModelAndView("main/fichaUnicaDatos");
////                model.addObject("paso", 10);
////                model.addObject(persona);
////                model.addObject("message", "Ya existe una Persona registrada con el ID ingresado en el Sistema, verifique!!");
////            }
        }
        AdmPersonaList(session);
        return model;
        
          
        /*   // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            Recomendacion 001 
            hacer de este metodo sea implementado tanto para el autoregistro como para el 
            el Modulo de Administracion,  como se logra eso, validando que el id del user venga vacio,
            si viene vacio es por primera vez captado en el formulario de registro.
            Si trae id quiere decir que viene para una actualizacion.
        */   // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    } 
    
     @RequestMapping(value = "/updatePersona", method = RequestMethod.POST)
        public ModelAndView updatePersona(@ModelAttribute("persona") Persona persona) throws ParseException {
        PersonaIO per = ser.getPersonaById(persona.getId());
        return null;
    }
    
    
    @RequestMapping(value = "/fichauseradm", method = RequestMethod.GET)
    public ModelAndView AdmUser(HttpSession session) {
        ModelAndView model ;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        if(user!=null){
            model = new ModelAndView("main/fichaUnicaDatos");
               model.addObject("paso", 7);
        }else{
            model = new ModelAndView("login");
            model.addObject("paso", 0);
        }
        return model;
    }      
    
        
 @RequestMapping(value = "/fichauseradmAdd", method = RequestMethod.GET)
    public ModelAndView AdmUserAdd(HttpSession session) {
        ModelAndView model ;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
         if(user!=null){
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 7);
         }else{
              model = new ModelAndView("login");
            model.addObject("paso", 0);
         }
             return model;
    }   
   
 @RequestMapping(value = "/adm_newpersonaList", method = RequestMethod.GET)
    public ModelAndView AdmPersonaList(HttpSession session) {
       //Metodo - Modulo Adm Personas - Lista las Personas registradas en Sis
        ModelAndView model ;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        //if(user!=null){
            final String uri = "http://localhost:8080/api/findAllPersona";
            RestTemplate restTemplate = new RestTemplate();
            ListaPersonaDTO result = restTemplate.getForObject(uri, ListaPersonaDTO.class);
            List<Persona> ListaPersona =  result.getPersonaList();
            System.out.println(" EN  adm_newpersona   LA LISTA ES : "+ result);     
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 8);
            model.addObject("ListaPersona", ListaPersona);
//         }else{
//              model = new ModelAndView("login");
//              model.addObject("paso", 0);
//         }
        return model;
    }
    
   
 @RequestMapping(value = "/adm_newpersonaAdd", method = RequestMethod.GET)
    public ModelAndView AdmPersonaAdd(HttpSession session) {
        //agregar Persona Modulo Admin
        ModelAndView model ;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
         if(user!=null){
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 10);
         }else{
              model = new ModelAndView("login");
              model.addObject("paso", 0);
         }
        return model;
    }    
    

               
 @RequestMapping(value = "/adm_newuser", method = RequestMethod.GET)
    public ModelAndView AdmUsuarioNew(HttpSession session) {
        ModelAndView model ;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
         if(user!=null){
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 9);
         }else{
            model = new ModelAndView("login");
            model.addObject("paso", 0);
         }
            return model;
    }

    
    @RequestMapping(value = "/adm_personaForm", method = RequestMethod.GET)
    public ModelAndView AdmPersonaEdit(HttpServletRequest request) {
        //
        
        int personaId = Integer.parseInt(request.getParameter("id"));
        ModelAndView model;
        
////        Persona persona = personaDAO.get(personaId);
////        ModelAndView model = new ModelAndView("personaForm");
////        model.addObject("persona", persona);
 
        
        String personaFindUrl = "http://localhost:8080/api/findPersonaById";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(personaFindUrl)
                // Add query parameter
                .queryParam("id", personaId);
               // .queryParam("idStatus", "1");
        RestTemplate restTemplate = new RestTemplate();
        String yu = builder.toUriString();
        System.out.println(" builder  Yu - - - > "+yu + "|UtilRobot - ifValidPersonExist");
        Persona result = restTemplate.getForObject(builder.toUriString(), Persona.class);
       
        //  dar formato a la fecha de base de datos
        //  set 
        
        
        model = new ModelAndView("main/fichaUnicaDatos");
        model.addObject("paso", 10);
        model.addObject("Persona", result);
        
        return model;
    /*
        
        String personaFindUrl = "http://localhost:8080/api/findPersonaByDNI";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(personaFindUrl)
                // Add query parameter
                .queryParam("dni", persona.getDni());
               // .queryParam("idStatus", "1");
        RestTemplate restTemplate = new RestTemplate();
        String yu = builder.toUriString();
        System.out.println(" builder  Yu - - - > "+yu + "|UtilRobot - ifValidPersonExist");
        Persona result = restTemplate.getForObject(builder.toUriString(), Persona.class);
        if (result.getDni() == 0){
             return false;
        }else{
            
            System.out.println("Ya existe la persona con el DNI ingresado en  % % %  ifValidPersonExist"+ result.getDni());
            return true;
        }        
        
        
        
        */
    
    }
    
    
    
}
