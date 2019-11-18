/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accusy.robotpro.robotadmin.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Controller
public class EnviarTransaccionForm {
    
    private String inputNombreT;
    private String inputDescripcionT;
    private String inputNombreAplic;
    private Integer selectTipoTrans;
    private Integer selectTipoAplic;
    private Integer selectModoCrea;
    private Integer selectTransInit;

    public String getInputNombreT() {
        return inputNombreT;
    }

    public void setInputNombreT(String inputNombreT) {
        this.inputNombreT = inputNombreT;
    }

    public String getInputDescripcionT() {
        return inputDescripcionT;
    }

    public void setInputDescripcionT(String inputDescripcionT) {
        this.inputDescripcionT = inputDescripcionT;
    }

    public String getInputNombreAplic() {
        return inputNombreAplic;
    }

    public void setInputNombreAplic(String inputNombreAplic) {
        this.inputNombreAplic = inputNombreAplic;
    }

    public Integer getSelectTipoTrans() {
        return selectTipoTrans;
    }

    public void setSelectTipoTrans(Integer selectTipoTrans) {
        this.selectTipoTrans = selectTipoTrans;
    }

    public Integer getSelectTipoAplic() {
        return selectTipoAplic;
    }

    public void setSelectTipoAplic(Integer selectTipoAplic) {
        this.selectTipoAplic = selectTipoAplic;
    }

    public Integer getSelectModoCrea() {
        return selectModoCrea;
    }

    public void setSelectModoCrea(Integer selectModoCrea) {
        this.selectModoCrea = selectModoCrea;
    }

    public Integer getSelectTransInit() {
        return selectTransInit;
    }

    public void setSelectTransInit(Integer selectTransInit) {
        this.selectTransInit = selectTransInit;
    }

    @Override
    public String toString() {
        return "EnviarTransaccionForm{" + "inputNombreT=" + inputNombreT + ", inputDescripcionT=" + inputDescripcionT + ", inputNombreAplic=" + inputNombreAplic + ", selectTipoTrans=" + selectTipoTrans + ", selectTipoAplic=" + selectTipoAplic + ", selectModoCrea=" + selectModoCrea + ", selectTransInit=" + selectTransInit + '}';
    }
 
}
