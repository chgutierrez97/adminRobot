
package com.accusy.robotpro.robotadmin.controller;


import com.accusy.robotpro.robotadmin.dto.ConexionAsDto;
import com.accusy.robotpro.robotadmin.dto.LoginDto;
import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
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
     @Autowired
    ServicesRobot service1;
    List<ConexionAsDto> conexiones;
    @RequestMapping(value = "/datosBasicos", method = RequestMethod.POST)
    public ModelAndView login(LoginDto login, HttpSession session) {
        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
        conexiones = new ArrayList<>();
          UsuarioIO user = service1.getUsuarioByLoginAndStatus(login.getUsuario(), 1);
          session.setAttribute("UsuarioSession", user);    
        model.addObject("paso", 2);
        return model;
    }
    
    
    private void addUserInSession(LoginDto login, HttpSession session){
        session.setAttribute("user", "christian Gutierrez");
        session.setAttribute("tarjeta", login.getUsuario());
        session.setAttribute("clave", login.getClave());
        session.setAttribute("role", "admin");
    
    }
    
}
