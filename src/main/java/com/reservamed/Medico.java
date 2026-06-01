package com.reservamed;

/**
 * Representa a un médico con nombre y especialidad.
 */
public class Medico {
    private final String id;
    private final String nombre;
    private final String especialidad;

    public Medico(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre != null ? nombre.trim() : "";
        this.especialidad = especialidad != null ? especialidad.trim() : "";
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}
