package com.accusy.robotpro.robotadmin.controller;

import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
