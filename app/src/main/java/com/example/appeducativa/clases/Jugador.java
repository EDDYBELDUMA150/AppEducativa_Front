package com.example.appeducativa.clases;

public class Jugador {
    private Usuario usuarios;
    private Actividad actividad;
    private Progreso progreso;
    private int plaver_id;
    private int nombre;

    public Jugador() {
    }

    public Jugador(Usuario usuarios, Actividad actividad, Progreso progreso, int plaver_id, int nombre) {
        this.usuarios = usuarios;
        this.actividad = actividad;
        this.progreso = progreso;
        this.plaver_id = plaver_id;
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

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
