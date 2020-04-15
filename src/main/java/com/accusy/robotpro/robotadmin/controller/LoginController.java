package com.accusy.robotpro.robotadmin.controller;

import com.accusy.robotpro.robotadmin.dto.ConexionAsDto;
import com.accusy.robotpro.robotadmin.dto.LoginDto;
import com.accusy.robotpro.robotadmin.model.TransaccionIO;
import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
import com.accusy.robotpro.robotadmin.utils.Encriptar;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



/**
 *
 * @author Christian Gutierrez
 */
@Controller
public class LoginController {

    static final String TIPO_ENCRIPTACION_CLAVE = "SHA1";

    @Autowired
    ServicesRobot service1;
    @Autowired
    Encriptar encriptar;
    List<ConexionAsDto> conexiones;
    public List<TransaccionIO> trans = new ArrayList<>();

    @RequestMapping(value = "/datosBasicos", method = RequestMethod.POST)
    public ModelAndView login(LoginDto login, HttpSession session) {
        ModelAndView model = null;
        String errorMessge = null;
        String claveEncrip = encriptar.getHash(login.getClave(), TIPO_ENCRIPTACION_CLAVE);
        UsuarioIO user = service1.getUserByLoginAndStatus(login.getUsuario(), 1);
        if (user.getUsuario() != null) {

            //if (login.getUsuario().equals(user.getUsuario()) && login.getClave().equals(user.getClave())) {
            if (login.getUsuario().equals(user.getUsuario())) {

                model = new ModelAndView("main/fichaUnicaDatos");
                session.setAttribute("UsuarioSession", user);
                trans = service1.getTransacionByTipoUsuario(0, user.getId());
                model.addObject("trans", trans);
                model.addObject("actividad", 2);

            } else {
                errorMessge = "User o Password is incorrect !!";
                model = new ModelAndView("/login");
                model.addObject("message", "Dato incorrecto");

            }
        } else {
            System.out.println("La clave no es correcta");
            //String errorMessge = "User o Password is incorrect !!";
            model = new ModelAndView("/login");
            model.addObject("message", "Dato incorrecto");

        }

        model.addObject("clave", login.getClave());
        model.addObject("targeta", login.getUsuario());
        model.addObject("clave", login.getClave());
        model.addObject("targeta", login.getUsuario());
        /**
         * ******Cargar datos usar servicios*****
         */
        model.addObject("paso", 0);
        addUserInSession(login, session);

        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        ModelAndView model = null;
        model = new ModelAndView("/login");
        return "login";
    }

    private void addUserInSession(LoginDto login, HttpSession session) {
        session.setAttribute("user", "christian Gutierrez");
        session.setAttribute("tarjeta", login.getUsuario());
        session.setAttribute("clave", login.getClave());
        session.setAttribute("role", "admin");

    }

    

}
