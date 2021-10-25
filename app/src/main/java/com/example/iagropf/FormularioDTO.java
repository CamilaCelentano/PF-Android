package com.example.iagropf;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(value = { "casilla" })
public class FormularioDTO {
    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    @SerializedName("formulario")

    @Expose
    public transient int idFormulario;
    @Expose
    public String nombre;
    @Expose
    public String resumen;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getResumen() {
        return resumen;
    }
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public FormularioDTO() {
    }

    @Override
    public String toString() {
        return "nombre= " + nombre + "\r" ;
    }

    public FormularioDTO(String nombre, String resumen) {
        this.nombre = nombre;
        this.resumen = resumen;
    }

    public FormularioDTO(String nombre) {
        this.nombre = nombre;
    }
}
