/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accusy.robotpro.robotadmin.dto;

import java.sql.Date;

public class ConexionAsDto {

	private Long id;
	private Long idUsuario;
	private String conexionName;
	private String servidor;
	private String usuario;
	private String clave;
        private Date fechaCreacion;
      

    public ConexionAsDto() {
    }

    public ConexionAsDto(Long id, Long idUsuario, String conexionName, String servidor, String usuario, String clave, Date fechaCreacion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.conexionName = conexionName;
        this.servidor = servidor;
        this.usuario = usuario;
        this.clave = clave;
        this.fechaCreacion = fechaCreacion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getConexionName() {
        return conexionName;
    }

    public void setConexionName(String conexionName) {
        this.conexionName = conexionName;
    }

    @Override
    public String toString() {
        return "ConexionAs{" + "id=" + id + ", idUsuario=" + idUsuario + ", conexionName=" + conexionName + ", servidor=" + servidor + ", usuario=" + usuario + ", clave=" + clave + ", fechaCreacion=" + fechaCreacion + '}';
    }
}
