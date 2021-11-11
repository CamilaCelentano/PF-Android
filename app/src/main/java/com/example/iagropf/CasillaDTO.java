package com.example.iagropf;

import com.google.gson.annotations.Expose;

public class CasillaDTO {

    @Expose(serialize = true)
    private Long idCasilla;

    public Long getIdCasilla() {
        return idCasilla;
    }

    public void setIdCasilla(Long idCasilla) {
        this.idCasilla = idCasilla;
    }

    public CasillaDTO(Long idCasilla) {
        this.idCasilla = idCasilla;
    }
}
