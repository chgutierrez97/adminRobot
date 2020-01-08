
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

//////    ORGINAL CHRISTIAN
//////        ModelAndView model = new ModelAndView("main/fichaUnicaDatos");
//////        conexiones = new ArrayList<>();
//////          UsuarioIO user = service1.getUsuarioByLoginAndStatus(login.getUsuario(), 1);
//////          session.setAttribute("UsuarioSession", user);    
//////        model.addObject("paso", 2);
//////        return model;
//////    ORGINAL CHRISTIAN
        
        
        ModelAndView model = null;
        String errorMessge = null;
        String usuarioUrl = "http://localhost:8080/api/findUsuarioByLoginAndStatus";
        
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(usuarioUrl)
                // Add query parameter
                .queryParam("login", login.getUsuario())
                .queryParam("idStatus", "1");
         RestTemplate restTemplate = new RestTemplate();
        
        // String myBuilder = builder.toUriString();
        // System.out.println("builder > > > > >  xxxxxxxxxxxxxxxxxxxx  > "+myBuilder);
            UsuarioIO result = restTemplate.getForObject(builder.toUriString(), UsuarioIO.class);
            // System.out.println("Usuario segun restTemplate " + result.getUsuario());
            // System.out.println("Clave segun restTemplate " + result.getClave());
            System.out.println("Class Usuario segun Resul " + result.getClass()+"\\\\ Clave segun restTemplate " + result.getClave()+ "  \\\\Usuario segun restTemplate " + result.getUsuario());
            if (result.getUsuario() != null){
                if (login.getUsuario().equals(result.getUsuario()) && login.getClave().equals(result.getClave())) {
                    System.out.println("Login correcto  ");
                    model = new ModelAndView("main/fichaUnicaDatos");
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
                      

;
        
        System.out.println("targeta : "+login.getUsuario());
        System.out.println("clave : "+login.getClave());
        model.addObject("clave", login.getClave());
        model.addObject("targeta", login.getUsuario());
        model.addObject("clave", login.getClave());
        model.addObject("targeta", login.getUsuario());
        
        
        //20   session.setAttribute("conexiones", conexiones);
        
        /********Cargar datos usar servicios******/ 
        
       model.addObject("paso", 1);
       addUserInSession(login,session);
       session.setAttribute("UsuarioSession", result);
       System.out.println("Class Usuario segun Resul ");
       return model;
                
                
    }
    
    
    private void addUserInSession(LoginDto login, HttpSession session){
        session.setAttribute("user", "christian Gutierrez");
        session.setAttribute("tarjeta", login.getUsuario());
        session.setAttribute("clave", login.getClave());
        session.setAttribute("role", "admin");
    
    }
    
}
