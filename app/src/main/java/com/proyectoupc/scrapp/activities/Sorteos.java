package com.proyectoupc.scrapp.activities;


public class Sorteos {

    private String fechaHoraSorteo;
    private String nombreSorteo;
    private String descripcionSorteo;

    public Sorteos(String fechaHoraSorteo, String nombreSorteo, String descripcionSorteo) {
        this.fechaHoraSorteo = fechaHoraSorteo;
        this.nombreSorteo = nombreSorteo;
        this.descripcionSorteo = descripcionSorteo;
    }

    public String getfechaHoraSorteo() {
        return fechaHoraSorteo;
    }

    public void setfechaHoraSorteo(String fechaHoraSorteo) {
        this.fechaHoraSorteo = fechaHoraSorteo;
    }

    public String getnombreSorteo() {
        return nombreSorteo;
    }

    public void setnombreSorteo(String nombreSorteo) {
        this.nombreSorteo = nombreSorteo;
    }

    public String getdescripcionSorteo() {
        return descripcionSorteo;
    }

    public void setdescripcionSorteo(String descripcionSorteo) {
        this.descripcionSorteo = descripcionSorteo;
    }
}
