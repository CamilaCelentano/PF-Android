package com.example.iagropf;

import com.google.gson.annotations.Expose;

public class RespuestaCasillaDTO {

    @Expose(serialize = true)
    private CasillaDTO casilla;

    @Expose(serialize = true)
    private String respuesta;


    public CasillaDTO getCasilla() {
        return casilla;
    }

    public void setCasilla(CasillaDTO idCasilla) {
        this.casilla = idCasilla;
    }

    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public RespuestaCasillaDTO(CasillaDTO casilla, String respuesta) {
        this.casilla = casilla;
        this.respuesta = respuesta;
    }
}
