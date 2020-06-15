package com.example.carwash.tablas;

public class Trabajador {
    private String codigo_trabajador;
    private String nombre;
    private String ape_pat;
    private String ape_mat;
    private String telefono;
    private String fecha;

    public Trabajador() {
    }

    public Trabajador(String codigo_trabajador, String nombre, String ape_pat, String ape_mat, String telefono, String fecha) {
        this.codigo_trabajador = codigo_trabajador;
        this.nombre = nombre;
        this.ape_pat = ape_pat;
        this.ape_mat = ape_mat;
        this.telefono = telefono;
        this.fecha = fecha;
    }

    public String getCodigo_trabajador() {
        return codigo_trabajador;
    }

    public void setCodigo_trabajador(String codigo_trabajador) {
        this.codigo_trabajador = codigo_trabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe_pat() {
        return ape_pat;
    }

    public void setApe_pat(String ape_pat) {
        this.ape_pat = ape_pat;
    }

    public String getApe_mat() {
        return ape_mat;
    }

    public void setApe_mat(String ape_mat) {
        this.ape_mat = ape_mat;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
