package com.example.carwash.tablas;

public class Servicio {
    String codigo;
    String nombre;
    double costo;
    String descripcion;

    public Servicio() {
    }

    public Servicio(String codigo, String nombre, double costo, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
