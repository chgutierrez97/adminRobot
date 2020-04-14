
package com.accusy.robotpro.robotadmin.dto;

import java.util.Date;


public class RegistroUsuario {
     
     private Integer id;
     private String nombre;
     private String apellido;
     private int dni;
     private Integer idUsuario;
     private String usuario;
     private String clave;
     private String clave2;
     private Integer roles;
     private Integer status;

    public RegistroUsuario() {
    }

    public RegistroUsuario(Integer id, String nombre, String apellido, int dni, Integer idUsuario, String usuario, String clave, String clave2, Integer roles, Integer status) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
        this.clave2 = clave2;
        this.roles = roles;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    public Integer getRoles() {
        return roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RegistroUsuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", idUsuario=" + idUsuario + ", usuario=" + usuario + ", clave=" + clave + ", clave2=" + clave2 + ", roles=" + roles + ", status=" + status + '}';
    }
    
}
