package com.reservamed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Agenda que administra la lista de citas médicas.
 */
public class Agenda {
    private final List<Cita> citas;

    public Agenda() {
        this.citas = new ArrayList<>();
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }

    /**
     * Agrega una nueva cita a la agenda si es válida y no está duplicada.
     */
    public void agregarCita(Cita cita) {
        validarCita(cita);
        if (!verificarDisponibilidad(cita)) {
            throw new IllegalArgumentException("Ya existe una cita con el mismo médico, fecha y hora.");
        }
        citas.add(cita);
    }

    /**
     * Verifica que no exista otra cita con el mismo médico, fecha y hora.
     */
    public boolean verificarDisponibilidad(Cita cita) {
        return citas.stream()
                .noneMatch(existente -> existente.getMedico().getNombre().equalsIgnoreCase(cita.getMedico().getNombre())
                        && existente.getFecha().equals(cita.getFecha())
                        && existente.getHora().equals(cita.getHora()));
    }

    /**
     * Confirma una cita por su identificador.
     */
    public void confirmarCita(String citaId) {
        Cita cita = buscarCita(citaId);
        cita.setEstado(EstadoCita.CONFIRMADA);
    }

    /**
     * Cancela una cita por su identificador.
     */
    public void cancelarCita(String citaId) {
        Cita cita = buscarCita(citaId);
        cita.setEstado(EstadoCita.CANCELADA);
    }

    /**
     * Marca una cita como atendida por su identificador.
     */
    public void atenderCita(String citaId) {
        Cita cita = buscarCita(citaId);
        cita.setEstado(EstadoCita.ATENDIDA);
    }

    /**
     * Retorna todas las citas registradas.
     */
    public List<Cita> consultarCitas() {
        return getCitas();
    }

    private Cita buscarCita(String citaId) {
        return citas.stream()
                .filter(cita -> cita.getId().equals(citaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada."));
    }

    private void validarCita(Cita cita) {
        if (cita == null || cita.getFecha().isBlank() || cita.getHora().isBlank()) {
            throw new IllegalArgumentException("Fecha e hora son obligatorias.");
        }
        if (cita.getPaciente() == null || cita.getPaciente().getNombre().isBlank() || cita.getPaciente().getDocumento().isBlank()
                || cita.getPaciente().getTelefono().isBlank() || cita.getPaciente().getCorreo().isBlank()) {
            throw new IllegalArgumentException("Todos los datos del paciente son obligatorios.");
        }
        if (cita.getMedico() == null || cita.getMedico().getNombre().isBlank() || cita.getMedico().getEspecialidad().isBlank()) {
            throw new IllegalArgumentException("Todos los datos del médico son obligatorios.");
        }
    }
}
