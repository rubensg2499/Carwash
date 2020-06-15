package com.example.carwash.tablas;

public class Usuario {
    private String codigo;
    private String password;
    private String codigo_trabajador;

    public Usuario(){

    }
    public Usuario(String codigo, String password, String codigo_trabajador) {
        this.codigo = codigo;
        this.password = password;
        this.codigo_trabajador = codigo_trabajador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodigo_trabajador() {
        return codigo_trabajador;
    }

    public void setCodigo_trabajador(String codigo_trabajador) {
        this.codigo_trabajador = codigo_trabajador;
    }
}
