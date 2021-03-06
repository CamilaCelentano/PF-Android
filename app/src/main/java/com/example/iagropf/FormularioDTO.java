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

    @Expose(serialize = true)
    public transient int idFormulario;
    @Expose(serialize = false)
    public String nombre;
    @Expose
    public String resumen;
    @Expose
    public String casillas;

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

    public String getCasillas() {
        return casillas;
    }

    public void setCasillas(String casillas) {
        this.casillas = casillas;
    }

    public FormularioDTO() {
    }

    @Override
    public String toString() {
        return

                "Nombre= " + nombre  ;
    }

    public FormularioDTO(String nombre, String resumen) {
        super();
        this.nombre = nombre;
        this.resumen = resumen;
    }

    public FormularioDTO(String nombre) {
        this.nombre = nombre;
    }

    public FormularioDTO(int idFormulario){
        super();
        this.idFormulario = idFormulario;
    }

    public FormularioDTO(int idFormulario, String nombre) {
        super();
        this.idFormulario = idFormulario;
        this.nombre = nombre;
    }


}
