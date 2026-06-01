package com.reservamed;

/**
 * Representa a un paciente con sus datos básicos.
 */
public class Paciente {
    private final String id;
    private final String nombre;
    private final String documento;
    private final String telefono;
    private final String correo;

    public Paciente(String id, String nombre, String documento, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre != null ? nombre.trim() : "";
        this.documento = documento != null ? documento.trim() : "";
        this.telefono = telefono != null ? telefono.trim() : "";
        this.correo = correo != null ? correo.trim() : "";
    }

    public Paciente(String nombre, String documento, String telefono, String correo) {
        this(java.util.UUID.randomUUID().toString(), nombre, documento, telefono, correo);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }
}
