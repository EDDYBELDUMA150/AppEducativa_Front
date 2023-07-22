package com.example.appeducativa.clases;


public class Roles {
    private Integer id_rol;
    private String rol_nombre;

    public Roles() {
    }

    public Roles(Integer id_rol, String rol_nombre) {
        this.id_rol = id_rol;
        this.rol_nombre = rol_nombre;
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }
}
