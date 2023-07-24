package com.example.appeducativa.clases;

public class Jugador {
    private Usuario usuarios;
    private Actividad actividad;
    private Progreso progreso;
    private int plaver_id;
    private String nombre;

    public Jugador() {
    }

    public Jugador(Usuario usuarios, Actividad actividad, Progreso progreso, String nombre) {
        this.usuarios = usuarios;
        this.actividad = actividad;
        this.progreso = progreso;
        this.nombre = nombre;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Progreso getProgreso() {
        return progreso;
    }

    public void setProgreso(Progreso progreso) {
        this.progreso = progreso;
    }

    public int getPlaver_id() {
        return plaver_id;
    }

    public void setPlaver_id(int plaver_id) {
        this.plaver_id = plaver_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
