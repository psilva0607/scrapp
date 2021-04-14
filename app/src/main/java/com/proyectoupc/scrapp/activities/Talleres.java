package com.proyectoupc.scrapp.activities;

public class Talleres {

    private String fechaHoraTaller;
    private String nombreTaller;
    private String descripcionTaller;

    public Talleres(String fechaHoraTaller, String nombreTaller, String descripcionTaller) {
        this.fechaHoraTaller = fechaHoraTaller;
        this.nombreTaller = nombreTaller;
        this.descripcionTaller = descripcionTaller;
    }

    public String getfechaHoraTaller() {
        return fechaHoraTaller;
    }

    public void setfechaHoraTaller(String fechaHoraTaller) {
        this.fechaHoraTaller = fechaHoraTaller;
    }

    public String getnombreTaller() {
        return nombreTaller;
    }

    public void setnombreTaller(String nombreTaller) {
        this.nombreTaller = nombreTaller;
    }

    public String getdescripcionTaller() {
        return descripcionTaller;
    }

    public void setdescripcionTaller(String descripcionTaller) {
        this.descripcionTaller = descripcionTaller;
    }
}