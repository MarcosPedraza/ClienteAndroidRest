package com.mareinc.marcospedraza.clienteandroidrest.modelos;

/**
 * Created by Marcos Pedraza on 07/03/2018.
 */

public class Usuario {
    int id;
    String nombre;
    Double monto;
    String estado;

    public Usuario() {
    }

    public Usuario(int id, String nombre, Double monto, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
        this.estado = estado;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUser() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getMonto() {
        return monto;
    }

    public String getEstado() {
        return estado;
    }
}
