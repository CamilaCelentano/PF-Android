package com.example.iagropf;

public class EstacionMuestreoDTO {

    private int idEstacionMuestreo;
    private String nombre;

    public int getIdEstacionMuestreo() {
        return idEstacionMuestreo;
    }
    public void setIdEstacionMuestreo(int idEstacionMuestreo) {
        this.idEstacionMuestreo = idEstacionMuestreo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstacionMuestreoDTO() {
    }

    public EstacionMuestreoDTO(String nombre) {
        super();
        this.nombre = nombre;
    }

    public EstacionMuestreoDTO(int idEstacionMuestreo, String nombre) {
        super();
        this.idEstacionMuestreo = idEstacionMuestreo;
        this.nombre = nombre;
    }

    public EstacionMuestreoDTO(int idEstacionMuestreo) {
        super();
        this.idEstacionMuestreo = idEstacionMuestreo;
    }

    @Override
    public String toString() {
        return  nombre ;
    }
}
