package com.accusy.robotpro.robotadmin.model;

import java.io.Serializable;
import java.util.Date;

public class TransaccionIO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String nombre;

    private String descripcion;

    private String aplicativoExternocol;

    private Date fechaCarga;

    private Integer tipoAplicativo;
     
    private String tipo;
    
    private Integer transaccionIni;

    public TransaccionIO() {
    }

    public TransaccionIO(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAplicativoExternocol() {
        return aplicativoExternocol;
    }

    public void setAplicativoExternocol(String aplicativoExternocol) {
        this.aplicativoExternocol = aplicativoExternocol;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Integer getTipoAplicativo() {
        return tipoAplicativo;
    }

    public void setTipoAplicativo(Integer tipoAplicativo) {
        this.tipoAplicativo = tipoAplicativo;
    }
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransaccionIO other = (TransaccionIO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

  @Override
    public String toString() {
        return "TransaccionIO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", aplicativoExternocol=" + aplicativoExternocol + ", fechaCarga=" + fechaCarga + ", tipoAplicativo=" + tipoAplicativo + ", tipo=" + tipo + '}';
    }

}

    
