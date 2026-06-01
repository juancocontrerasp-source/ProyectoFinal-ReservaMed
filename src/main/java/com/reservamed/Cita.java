package com.reservamed;

import java.util.UUID;

/**
 * Representa una cita médica entre un paciente y un médico.
 */
public class Cita {
    private final String id;
    private final String fecha;
    private final String hora;
    private EstadoCita estado;
    private final Paciente paciente;
    private final Medico medico;

    public Cita(String fecha, String hora, Paciente paciente, Medico medico) {
        this.id = UUID.randomUUID().toString();
        this.fecha = fecha != null ? fecha.trim() : "";
        this.hora = hora != null ? hora.trim() : "";
        this.paciente = paciente;
        this.medico = medico;
        this.estado = EstadoCita.PENDIENTE;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }
}
