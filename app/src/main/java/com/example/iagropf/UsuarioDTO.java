package com.example.iagropf;

public class UsuarioDTO {
    private String nombre, password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioDTO(String usuario) {
        nombre = usuario;
        this.password = password;
    }

}
