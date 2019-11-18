/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accusy.robotpro.robotadmin.model;

/**
 *
 * @author audra.zapata
 */
public class EnviarInformacion {
    
    private Integer accionSelector;
    private Integer accionCreacion;
    private Integer transaccionInit;
    private Integer transaccionEdit;
    //private Integer accionSelector;

    public Integer getAccionSelector() {
        return accionSelector;
    }

    public void setAccionSelector(Integer accionSelector) {
        this.accionSelector = accionSelector;
    }

    public Integer getAccionCreacion() {
        return accionCreacion;
    }

    public void setAccionCreacion(Integer accionCreacion) {
        this.accionCreacion = accionCreacion;
    }

    public Integer getTransaccionInit() {
        return transaccionInit;
    }

    public void setTransaccionInit(Integer transaccionInit) {
        this.transaccionInit = transaccionInit;
    }

    public Integer getTransaccionEdit() {
        return transaccionEdit;
    }

    public void setTransaccionEdit(Integer transaccionEdit) {
        this.transaccionEdit = transaccionEdit;
    }
    
    
}
