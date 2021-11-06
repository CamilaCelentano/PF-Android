package com.example.iagropf;

public class MetodoMuestreoDTO {

    private long idMetodoMuestreo;
    private String nombre;

    public long getIdMetodoMuestreo() {
        return idMetodoMuestreo;
    }
    public void setIdMetodoMuestreo(long idMetodoMuestreo) {
        this.idMetodoMuestreo = idMetodoMuestreo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MetodoMuestreoDTO() {
    }

    public MetodoMuestreoDTO(long idMetodoMuestreo, String nombre) {
        super();
        this.idMetodoMuestreo = idMetodoMuestreo;
        this.nombre = nombre;
    }

    public MetodoMuestreoDTO(String nombre) {
        super();
        this.nombre = nombre;
    }

    public MetodoMuestreoDTO(long idMetodoMuestreo) {
        super();
        this.idMetodoMuestreo = idMetodoMuestreo;
    }

    @Override
    public String toString() {
        return nombre ;
    }
}
