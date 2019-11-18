
package com.accusy.robotpro.robotadmin.controller;

import com.accusy.robotpro.robotadmin.dto.PantallaDto;
import com.accusy.robotpro.robotadmin.services.ServicesRobot;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Controller
public class AdminTransaccionController {
    
    @Autowired
    ServicesRobot service1;
    public List<PantallaDto> listPatalla = new ArrayList<>();
        
    
}
