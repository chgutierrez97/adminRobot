/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accusy.robotpro.robotadmin.utils;

import com.accusy.robotpro.robotadmin.dto.Persona;
import com.accusy.robotpro.robotadmin.dto.Usuario;
import com.accusy.robotpro.robotadmin.model.PersonaIO;
import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author MGIAccusys
 */
public class UtilRobot {
   
       
    public boolean ifValidUserExist(Usuario usuario) {
        // Validar si existe el user ya en Base de datos 
        // Antes de Registrar en Base de Datos
        String usuarioFindUrl = "http://localhost:8080/api/findUsuarioByLogin";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(usuarioFindUrl)
                // Add query parameter
                .queryParam("login", usuario.getUsuario());
                //.queryParam("idStatus", "1");
        RestTemplate restTemplate = new RestTemplate();
        String yu = builder.toUriString();
        System.out.println(" builder  Yu - - - > "+yu+ "| en >> ifValidUserExist");
        Usuario result = restTemplate.getForObject(builder.toUriString(), Usuario.class);
        if (result.getUsuario()!= null){
            System.out.println("Ya existe el User Name en ifValidUserExist"+ result.getUsuario());
            return true;
        }else{
            return false;
        }
    }
    
 
    public boolean ifValidPersonExist(Persona persona) {
        // Validar si existe Persona segun ID o DNI ya en Base de datos
        // Antes de Registrar en Base de Datos
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
    } 
    
    
}
