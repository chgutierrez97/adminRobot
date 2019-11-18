
package com.accusy.robotpro.robotadmin.dto;

/**
 *
 * @author Christian Gutierrez
 */
public class DatosComplementariosDto {
    
    private Long codeClient;
    private Long cantidadSucursales;
    private  Long cantidadEmpleados;
    private  String facturacionMes;
    private  String motivoSolicitudP2;

    public Long getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(Long codeClient) {
        this.codeClient = codeClient;
    }

    public Long getCantidadSucursales() {
        return cantidadSucursales;
    }

    public void setCantidadSucursales(Long cantidadSucursales) {
        this.cantidadSucursales = cantidadSucursales;
    }

    public Long getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(Long cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public String getFacturacionMes() {
        return facturacionMes;
    }

    public void setFacturacionMes(String facturacionMes) {
        this.facturacionMes = facturacionMes;
    }


    public String getMotivoSolicitudP2() {
        return motivoSolicitudP2;
    }

    public void setMotivoSolicitudP2(String motivoSolicitudP2) {
        this.motivoSolicitudP2 = motivoSolicitudP2;
    }

    @Override
    public String toString() {
        return "DatosComplementariosDto{" + "cantidadSucursales=" + cantidadSucursales + ", cantidadEmpleados=" + cantidadEmpleados + ", facturacionMes=" + facturacionMes + ", motivoSolicitudP2=" + motivoSolicitudP2 + '}';
    }

   

}
