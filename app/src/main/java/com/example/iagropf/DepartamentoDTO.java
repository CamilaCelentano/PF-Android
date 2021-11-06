package com.example.iagropf;

public class DepartamentoDTO {

    private int idDepartamento;
    private String nombre;

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DepartamentoDTO() {
    }
    public DepartamentoDTO(String nombre) {
        super();
        this.nombre = nombre;
    }

    public DepartamentoDTO(int idDepartamento, String nombre) {
        super();
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
    }

    public DepartamentoDTO(int idDepartamento) {
        super();
        this.idDepartamento = idDepartamento;
    }

    @Override
    public String toString() {
        return getNombre() ;
    }
}