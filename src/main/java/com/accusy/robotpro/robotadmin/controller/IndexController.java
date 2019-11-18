package com.accusy.robotpro.robotadmin.controller;


import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    ServicesRobot ser;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
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
        ModelAndView model;
        String usuario = "";
        usuario = System.getProperty("user.name");
        UsuarioIO user = ser.getUsuarioByLogin(usuario);
        if (user != null) {
            model = new ModelAndView("login");
        } else {
            session.setAttribute("UsuarioSession", user);
            model = new ModelAndView("main/fichaUnicaDatos");
        }

        model.addObject("paso", 2);
        return model;
    }
}
