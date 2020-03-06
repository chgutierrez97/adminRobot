/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accusy.robotpro.robotadmin.services;

import com.accusy.robotpro.robotadmin.model.UsuarioIO;
import com.accusy.robotpro.robotadmin.utils.UtilRobot;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
@PropertySource("classpath:application.properties")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilRobot util;

    @Autowired
    private ServicesRobot userService;

    @Value("${time.number.min.logout}")
    private Long numMin;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        UsuarioIO user = userService.getUserByLoginAndStatus(ssoId, 1);
        Long ninutos = util.canculoEntrFechas(user.getFechaLogueo(), new Date().getTime(), 2);        
        if (user == null || (user.getLogueado() != false && (ninutos < numMin))) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Usuario no encontrado");
        } 
        return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getClave(),
                user.getStatus().getDescripcion().equals("Activo"), true, true, true, getGrantedAuthorities(user));
    }

    
    private List<GrantedAuthority> getGrantedAuthorities(UsuarioIO user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        System.out.println("Perfil de Usuario : " + user.getRoles());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles().getDescripcion()));

        System.out.print("Autorizado :" + authorities);
        return authorities;
    }
}
