package com.accusy.robotpro.robotadmin.controller;

import com.accusy.robotpro.robotadmin.dto.ListaPersonaDTO;
import com.accusy.robotpro.robotadmin.dto.Persona;
import com.accusy.robotpro.robotadmin.dto.Roles;
import com.accusy.robotpro.robotadmin.dto.SecurityQuetion;
import com.accusy.robotpro.robotadmin.dto.Status;
import com.accusy.robotpro.robotadmin.dto.Usuario;
import com.accusy.robotpro.robotadmin.model.PersonaIO;
import com.accusy.robotpro.robotadmin.model.TransaccionIO;

import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
import com.accusy.robotpro.robotadmin.utils.UtilRobot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    public List<TransaccionIO> trans = new ArrayList<>();
    public List<TransaccionIO> trans2 = new ArrayList<>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login(HttpSession session) {
        boolean flag = false;
        ModelAndView model;
        String usuario = "";
        usuario = System.getProperty("user.name");

//        if (administradorGobal.equals(usuario)) {
//            flag = true;
//            UsuarioIO user = ser.getUsuarioByLogin(administradorUserGobal);
//            if (user.getId() != null) {
//                model = new ModelAndView("main/fichaUnicaDatos");
//                session.setAttribute("UsuarioSession", user);
//            } else {
//                model = new ModelAndView("login");
//            }
//        } else {
//            UsuarioIO user = ser.getUsuarioByLogin(usuario);
//            if (user.getId() != null) {
//                session.setAttribute("UsuarioSession", user);
//                model = new ModelAndView("main/fichaUnicaDatos");
//            } else {
//                model = new ModelAndView("login");
//            }
//        }
        model = new ModelAndView("login");
        model.addObject("paso", 0);
        model.addObject("admin", flag);
        return model;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        boolean flag = false;
        ModelAndView model;
        String usuario = "";

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }
        String userName = userDetails.getUsername();
        if (!userName.equals("")) {
            UsuarioIO user = ser.getUsuarioByLogin(userName);
            if (user != null) {
                session.setAttribute("UsuarioSession", user);
            }
        }
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        if (user != null) {
            trans = ser.getTransacionByTipoUsuario(0, user.getId());
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("trans", trans);
            model.addObject("actividad", 2);
        } else {

            model = new ModelAndView("/login");
            model.addObject("message", "No posee sesion activa ");
        }

        model.addObject("paso", 0);
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

    @RequestMapping(value = "/registrop", method = RequestMethod.POST)
    public ModelAndView savePersonaAuto(@ModelAttribute("persona") Persona persona, HttpSession session) throws ParseException {
        // Auto - Registro Persona o Datos Personales
        ModelAndView model = null;
        Persona per = ser.guardarPersona(persona);
        if (per != null) {
            session.setAttribute("pers", per);
            model = new ModelAndView("/regusercredencial");
        } else {
            System.out.println(" Ya existe la Persona con el ID ingresado en el sistema, Verifique !!   -  -  > ");
            model = new ModelAndView("/reguserdatos");
            model.addObject("message", "Ya existe una Persona registrada con el ID ingresado en el Sistema, verifique!!");
        }
        return model;
    }

    @RequestMapping(value = "/regcredencials", method = RequestMethod.POST)
    public ModelAndView saveUsuarioAuto(@ModelAttribute("usuario") Usuario usuario, HttpSession session) throws ParseException {
        // Auto - Creacion de Usuarios (Credenciales)
        ModelAndView model = null;
        Usuario usua = ser.guardarUsuarioAuto(usuario, session);
        if (usua != null) {
            System.out.println("  Exitoso Creacion de  Credenciales   -  -  > " + usua.getUsuario() + " | IndexController");
            model = new ModelAndView("/login");
        } else {
            System.out.println(" Ya existe el user o Login Ingrese otro %%% IndexController   -  -  > ");
            model = new ModelAndView("/regusercredencial");
            model.addObject("message", "El Usuario ya existe en el Sistema, verifique!!");
        }
        return model;
    }

    @RequestMapping(value = "/registroamdPersona", method = RequestMethod.POST)
    public ModelAndView savePersonaAdm(Persona persona, HttpSession session) throws ParseException {
        // Usado en Adm Persona ( Add / Update ) Personas en modulo Administrador o Auto Registro
        // Por verificar  si AUTO tambien lo implementa. 
        ModelAndView model = null;
        if (persona.getId() != null) {
            Persona persOld = ser.getPersonaByIds(persona.getId());
            Persona persUpdate = new Persona(persona.getId(), persona.getNombre(), persona.getApellido(), persona.getDni(), persOld.getFechaCarga());
            Persona per = ser.guardarPersona(persUpdate);
        } else {
            Persona per = ser.guardarPersona(persona);
        }
        List<PersonaIO> ListaPersona = ser.getPersonaList();
        model = new ModelAndView("main/fichaUnicaDatos");
        model.addObject("paso", 8);
        model.addObject("ListaPersona", ListaPersona);
        return model;
    }

    @RequestMapping(value = "/registroPersonaAdm", method = RequestMethod.POST)
    public ModelAndView savePersonaRegAdm(Persona persona, HttpSession session) throws ParseException {
        /* # por comprobar uso # - Registro en Adm Persona */
        ModelAndView model = null;
        Persona per = ser.guardarPersona(persona);
        if (per != null) {
            session.setAttribute("pers", per);
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 9);
        } else {
            model = new ModelAndView("login");
            model.addObject("paso", 14);
        }
        return model;
    }

    @RequestMapping(value = "/fichauseradm", method = RequestMethod.GET)
    public ModelAndView AdmUser(HttpSession session) {
        ModelAndView model;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        if (user != null) {
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 7);
        } else {
            model = new ModelAndView("login");
            model.addObject("paso", 0);
        }
        return model;
    }

    @RequestMapping(value = "/fichauseradmAdd", method = RequestMethod.GET)
    public ModelAndView viewUserAddAdm(HttpSession session) {
        ModelAndView model;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        if (user != null) {
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 7);
        } else {
            model = new ModelAndView("login");
            model.addObject("paso", 0);
        }
        return model;
    }

    @RequestMapping(value = "/adm_newpersonaList", method = RequestMethod.GET)
    public ModelAndView viewPersonaListAdm(HttpSession session) {
        // ADM - Lista las Personas 
        ModelAndView model;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        List<PersonaIO> ListaPersona = ser.getPersonaList();
        model = new ModelAndView("main/fichaUnicaDatos");
        model.addObject("paso", 8);
        model.addObject("ListaPersona", ListaPersona);
        return model;
    }

    @RequestMapping(value = "/adm_newpersonaAdd", method = RequestMethod.GET)
    public ModelAndView viewPersonaAdd(HttpSession session) {
        // ADM - View Agregar Persona Modulo 
        ModelAndView model;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        if (user != null) {
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 14);
        } else {
            model = new ModelAndView("login");
            model.addObject("paso", 0);
        }
        return model;
    }

    @RequestMapping(value = "/adm_newuser", method = RequestMethod.GET)
    public ModelAndView viewUsuarioNew(HttpSession session) {
        ModelAndView model;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        if (user != null) {
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("paso", 9);
        } else {
            model = new ModelAndView("login");
            model.addObject("paso", 0);
        }
        return model;
    }

    @RequestMapping(value = "/adm_personaForm", method = RequestMethod.GET)
    public ModelAndView viewPersonaEdit(HttpServletRequest request) {
        int personaId = Integer.parseInt(request.getParameter("id"));
        ModelAndView model;
        Persona result = ser.getPersonaByIds(personaId);
        model = new ModelAndView("main/fichaUnicaDatos");
        model.addObject("paso", 10);
        model.addObject("Persona", result);
        return model;
    }

    @RequestMapping(value = "/adm_userList", method = RequestMethod.GET)
    public ModelAndView viewUsuarioListAdm(HttpSession session) {
        //Metodo - Modulo Adm Personas - Lista las Personas registradas en Sis
        ModelAndView model;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        List<UsuarioIO> ListaUsuario = ser.getUsuarioList();
        model = new ModelAndView("main/fichaUnicaDatos");
        model.addObject("paso", 11);
        model.addObject("ListaUsuario", ListaUsuario);
        return model;
    }

    @RequestMapping(value = "/AdmregCredencials", method = RequestMethod.POST)
    public ModelAndView saveUsuario(@ModelAttribute("usuario") Usuario usuario) throws ParseException {
        // Creacion de Usuarios (Credenciales)
        ModelAndView model = null;
        return model;
    }

    @RequestMapping(value = "/viewUsuarioMant_Adm", method = RequestMethod.GET)
    public ModelAndView findUsuarioById(HttpServletRequest request) {
        // ADM  Usuario view Mantenimiento
        int usuarioId = Integer.parseInt(request.getParameter("id"));
        ModelAndView model;
        Usuario result = ser.getUsuarioById(usuarioId);
        model = new ModelAndView("main/fichaUnicaDatos");
        model.addObject("paso", 12);
        model.addObject("Usuario", result);
        return model;
    }

    @RequestMapping(value = "/saveUsuario_adm", method = RequestMethod.POST)
    public ModelAndView saveUsuarioAdm(Usuario usuario, HttpSession session) throws ParseException {
        // ADM - ( Add / Update ) Usuarios 
        ModelAndView model = null;
        if (usuario.getId() != null) {
            Usuario usuaOld = ser.getUsuarioById(usuario.getId());
            Usuario usuaUpdate = new Usuario(usuario.getId(), usuario.getUsuario(), usuario.getClave(), usuaOld.getFechaCarga(), usuaOld.getPersona(), usuaOld.getRoles(), usuaOld.getStatus());
            //Usuario usuaUpdate = new Usuario(usuario.getId(), usuario.getUsuario(), usuario.);
            Usuario usu = ser.guardarUsuario(usuaUpdate, session);
        } else {
            Usuario usu = ser.guardarUsuario(usuario, session);
        }
        // Solo busco All Usuarios y muestro
        List<UsuarioIO> ListaUsuario = ser.getUsuarioList();
        model = new ModelAndView("main/fichaUnicaDatos");
        model.addObject("paso", 11);
        model.addObject("ListaUsuario", ListaUsuario);
        return model;
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public ModelAndView accessDeniedPage(ModelAndView model, HttpSession session) {
        boolean flag = false;
        UsuarioIO user = (UsuarioIO) session.getAttribute("UsuarioSession");
        if (user != null) {
            trans = ser.getTransacionByTipoUsuario(0, user.getId());
            model = new ModelAndView("main/fichaUnicaDatos");
            model.addObject("trans", trans);
            model.addObject("actividad", 2);
            flag = true;
            
            model.addObject("errorAcceso","No tiene permisos para acceder a la página.");
        } else {

            model = new ModelAndView("/login");
            model.addObject("message", "No posee sesion activa ");
        }

        model.addObject("paso", 0);
        model.addObject("admin", flag);
        return model;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
