
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

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
        ModelAndView model = null;
        String errorMessge = null;
        UsuarioIO user = service1.getUserByLoginAndStatus(login.getUsuario(), 1);
        if (user.getUsuario() != null){
            if (login.getUsuario().equals(user.getUsuario()) && login.getClave().equals(user.getClave())) {
                System.out.println("Login correcto  ");
                model = new ModelAndView("main/fichaUnicaDatos");
                 session.setAttribute("UsuarioSession", user);                
            }else{
                errorMessge = "User o Password is incorrect !!";
                model = new ModelAndView("/login");
                model.addObject("message", "Dato incorrecto");
                System.out.println("/******** La clave no es correcta ********");
            } 
        }else{
            System.out.println("La clave no es correcta");
            //String errorMessge = "User o Password is incorrect !!";
            model = new ModelAndView("/login"); 
            model.addObject("message", "Dato incorrecto");    
            System.out.println("/******** La clave no es  | LoginController ********");
        }
        System.out.println("targeta : "+login.getUsuario());
        System.out.println("clave : "+login.getClave());
        model.addObject("clave", login.getClave());
        model.addObject("targeta", login.getUsuario());
        model.addObject("clave", login.getClave());
        model.addObject("targeta", login.getUsuario());
        /********Cargar datos usar servicios******/
        model.addObject("paso", 0);
        addUserInSession(login,session);
        System.out.println("Class Usuario segun Resul ");
        return model;

//////    ORGINAL CHRISTIAN
//////        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
//////        conexiones = new ArrayList<>();
//////          UsuarioIO user = service1.getUsuarioByLoginAndStatus(login.getUsuario(), 1);
//////          session.setAttribute("UsuarioSession", user);    
//////        model.addObject("paso", 2);
//////        return model;
//////    ORGINAL CHRISTIAN        
    }
    
    
    private void addUserInSession(LoginDto login, HttpSession session){
        session.setAttribute("user", "christian Gutierrez");
        session.setAttribute("tarjeta", login.getUsuario());
        session.setAttribute("clave", login.getClave());
        session.setAttribute("role", "admin");
    
    }
    
}
