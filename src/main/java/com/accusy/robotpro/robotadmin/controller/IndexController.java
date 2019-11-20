package com.accusy.robotpro.robotadmin.controller;


import com.accusy.robotpro.robotadmin.model.PersonaIO;
import com.accusy.robotpro.robotadmin.model.RolesIO;
import com.accusy.robotpro.robotadmin.model.SecurityQuetionIO;
import com.accusy.robotpro.robotadmin.model.StatusIO;
import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
import com.accusy.robotpro.robotadmin.utils.UtilRobot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@Controller
@PropertySource("classpath:application.properties")
public class IndexController {

    @Autowired
    ServicesRobot ser;
    @Value("${spring.name.administrator.global}")
    private String administradorGobal;
    @Value("${spring.users.administrator.global}")
    private String administradorUserGobal;

//    @RequestMapping(value spring.datasource.diver= "/", method = RequestMethod.GET)
//    public String printWelcome(ModelMap model) {
//        String url;
//
//        String usuario = "";
//        usuario = System.getProperty("user.name");
//        UsuarioIO user = ser.getUsuarioByLogin(usuario);
//        if (user != null) {
//            url = "login";
//        } else {
//            url = "fichaUnicaDatos";
//        }
//
//        return url;
//    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login(HttpSession session) {
        boolean flag = false;
        ModelAndView model;
        String usuario = "";
        usuario = System.getProperty("user.name");

        if (administradorGobal.equals(usuario)) {
            flag = true;
            UsuarioIO user = ser.getUsuarioByLogin(administradorUserGobal);
            if (user != null) {
                model = new ModelAndView("main/fichaUnicaDatos");
                session.setAttribute("UsuarioAdmin", user);
            } else {
                model = new ModelAndView("login");
            }

        } else {
            UsuarioIO user = ser.getUsuarioByLogin(usuario);
            if (user != null) {
                session.setAttribute("UsuarioSession", user);
                model = new ModelAndView("main/fichaUnicaDatos");
            } else {
                model = new ModelAndView("login");
            }
        }
        model.addObject("paso", 2);
        model.addObject("admin", flag);

        return model;
    }
    
    
    @RequestMapping(value = "/reguserdatos", method = RequestMethod.GET)
    public String printRegDatosUsers(ModelMap model) {
       
        return "reguserdatos";
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
    public ModelAndView registroPersona(@ModelAttribute("persona") PersonaIO persona) throws ParseException {
        
        // Creacion de Personas - Datos Basicos.
        /* <<<<
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
        >>>>  */
        ModelAndView model = null;
        final String uri = "http://localhost:8080/api/savePersona";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06"); 
        PersonaIO per = new PersonaIO(null, persona.getNombre(), persona.getApellido(), persona.getDni(), myDay);
        System.out.println("usua a Enviar al restTemplate ##"+per);
        
        RestTemplate restTemplate = new RestTemplate();
        UtilRobot utils = new UtilRobot();
        
        if (!utils.ifValidPersonExist(per)){
            PersonaIO result = restTemplate.postForObject(uri, per, PersonaIO.class);
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
    public ModelAndView registroCredencials(@ModelAttribute("usuario") UsuarioIO usuario) throws ParseException {
      
        // Creacion de Usuarios (Credenciales)
        ModelAndView model = null;
        final String uri = "http://localhost:8080/api/saveUsuario";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06"); 
        // Debo Traerlo  de la sesion o del resultado del paso anterior Registro Datos Basicos
        PersonaIO persona = new PersonaIO(1,"paul","gonzalez", 1 ,myDay );
        RolesIO roles = new RolesIO(1,"nu"); 
        StatusIO status = new StatusIO(1,"OI");
        UsuarioIO usua = new UsuarioIO(null, usuario.getUsuario(),usuario.getClave(), myDay, persona, roles, status);
        System.out.println("usua a Enviar al restTemplate ##"+usua);
        
        RestTemplate restTemplate = new RestTemplate();
       
        UtilRobot utils = new UtilRobot();
        
        if (!utils.ifValidUserExist(usua)){
            UsuarioIO result = restTemplate.postForObject(uri, usua, UsuarioIO.class);
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
    public ModelAndView registroQuestions(@ModelAttribute("securityquetion") SecurityQuetionIO securityquetion) throws ParseException {
      
        ModelAndView model = null;
        final String uri = "http://localhost:8080/api/saveUsuario";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDay = sdf2.parse("2000-12-06"); 
        // Debo Traerlo  de la sesion o del resultado del paso anterior Registro Datos Basicos
        PersonaIO persona = new PersonaIO(1,"paul","gonzalez", 1 ,myDay );
        RolesIO roles = new RolesIO(1,"nu"); 
        StatusIO status = new StatusIO(1,"OI");
        
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

    
}
