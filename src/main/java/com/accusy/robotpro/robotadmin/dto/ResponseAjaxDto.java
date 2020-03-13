
package com.accusy.robotpro.robotadmin.dto;

import com.accusy.robotpro.robotadmin.model.ExpresionesRegularesIO;
import java.util.List;
/**
 *
 * @author Christian Gutierrez
 */
public class ResponseAjaxDto {
    
    private PantallaDto pantalla;
    private List<AccionKeyboarDto> accionTeclado;
    private List<ExpresionesRegularesIO> expresiones;

    public PantallaDto getPantalla() {
        return pantalla;
    }

    public void setPantalla(PantallaDto pantalla) {
        this.pantalla = pantalla;
    }

    public List<AccionKeyboarDto> getAccionTeclado() {
        return accionTeclado;
    }

    public void setAccionTeclado(List<AccionKeyboarDto> accionTeclado) {
        this.accionTeclado = accionTeclado;
    }

    public List<ExpresionesRegularesIO> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(List<ExpresionesRegularesIO> expresiones) {
        this.expresiones = expresiones;
    }

    @Override
    public String toString() {
        return "ResponseAjaxDto{" + "pantalla=" + pantalla + ", accionTeclado=" + accionTeclado + ", expresiones=" + expresiones + '}';
    }


    
}
