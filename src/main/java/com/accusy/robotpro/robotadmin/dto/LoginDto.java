
package com.accusy.robotpro.robotadmin.dto;

/**
 *
 * @author Christian Gutierrez
 */
public class LoginDto {
    
    private  String usuario;
    private  String clave;

    public LoginDto() {
    }

    public LoginDto(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "LoginDto{" + "usuario=" + usuario + ", clave=" + clave + '}';
    }
}
