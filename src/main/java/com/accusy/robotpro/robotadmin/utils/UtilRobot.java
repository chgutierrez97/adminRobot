/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accusy.robotpro.robotadmin.utils;

import com.accusy.robotpro.robotadmin.dto.Persona;
import com.accusy.robotpro.robotadmin.dto.Usuario;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author MGIAccusys
 */
@Service
@PropertySource("classpath:application.properties")
public class UtilRobot {
    
    @Value("${url.service.ldap}")
    private String urlLdap;
    @Value("${dn.service.ldap}")
    private String dnLdap;
    
    

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
        System.out.println(" builder  Yu - - - > " + yu + "| en >> ifValidUserExist");
        Usuario result = restTemplate.getForObject(builder.toUriString(), Usuario.class);
        if (result.getUsuario() != null) {
            System.out.println("Ya existe el User Name en ifValidUserExist" + result.getUsuario());
            return true;
        } else {
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
        System.out.println(" builder  Yu - - - > " + yu + "|UtilRobot - ifValidPersonExist");
        Persona result = restTemplate.getForObject(builder.toUriString(), Persona.class);
        if (result.getDni() == 0) {
            return false;
        } else {
            System.out.println("Ya existe la persona con el DNI ingresado en  % % %  ifValidPersonExist" + result.getDni());
            return true;
        }
    }

    public boolean comparadorUsersLdap(String uid) {
        boolean flag = true;
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, urlLdap);
        DirContext context;
        try {
            context = new InitialDirContext(env);

            BasicAttributes attributes = new BasicAttributes("objectclass", "inetOrgPerson");
            attributes.put("uid", uid);

            NamingEnumeration<SearchResult> resultEnumeration = context.search(dnLdap, attributes, new String[]{"cn", "sn", "uid"});
            while (resultEnumeration.hasMore()) {
                SearchResult nextElement = resultEnumeration.nextElement();
                flag = false;
                System.out.println(nextElement.getNameInNamespace());
                System.out.println(nextElement.getAttributes());
            }       
        } catch (NamingException ex) {
             flag = true;
            Logger.getLogger(UtilRobot.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean comparadorDeCaracteres(String sTexto, String sTextoBuscado) {

        sTexto = sTexto.toLowerCase();
        sTextoBuscado = sTextoBuscado.toLowerCase();

        boolean flag = false;
        int contador = 0;
        if (sTexto.indexOf(sTextoBuscado) > -1) {
            flag = true;
        }
        if (sTexto.contains("" + sTextoBuscado)) {
            flag = true;
        }

        return flag;
    }

    public Long canculoEntrFechas(Long milis1, Long milis2, Integer Opciones) {
        long diff;

        diff = milis2 - milis1;

        switch (Opciones) {
            case 1:
                // calcular la diferencia en segundos

                long diffSegundos = Math.abs(diff / 1000);
                return diffSegundos;

            case 2:
                // calcular la diferencia en minutos

                long diffMinutos = Math.abs(diff / (60 * 1000));
                return diffMinutos;
            case 3:

                // calcular la diferencia en horas
                long diffHoras = (diff / (60 * 60 * 1000));
                return diffHoras;

            default:
                return 0L;
        }

    }
}
