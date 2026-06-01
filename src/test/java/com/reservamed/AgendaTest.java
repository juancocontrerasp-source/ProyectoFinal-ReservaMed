package com.reservamed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {
    private Agenda agenda;
    private Paciente paciente;
    private Medico medico;

    @BeforeEach
    void setUp() {
        agenda = new Agenda();
        paciente = new Paciente("P-001", "Ana Gómez", "3001234567", "ana@example.com");
        medico = new Medico("M-001", "Dr. Solano", "Dermatología");
    }

    @Test
    void registrarCitaCorrectamente() {
        Cita cita = new Cita("2026-06-15", "10:30", paciente, medico);
        agenda.agregarCita(cita);
        assertEquals(1, agenda.consultarCitas().size());
        assertEquals(EstadoCita.PENDIENTE, cita.getEstado());
    }

    @Test
    void noPermitirCitaDuplicada() {
        Cita primera = new Cita("2026-06-15", "10:30", paciente, medico);
        agenda.agregarCita(primera);

        Cita duplicada = new Cita("2026-06-15", "10:30", paciente, medico);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> agenda.agregarCita(duplicada));
        assertTrue(exception.getMessage().contains("Ya existe una cita"));
        assertEquals(1, agenda.consultarCitas().size());
    }

    @Test
    void confirmarCita() {
        Cita cita = new Cita("2026-06-15", "10:30", paciente, medico);
        agenda.agregarCita(cita);
        agenda.confirmarCita(cita.getId());
        assertEquals(EstadoCita.CONFIRMADA, cita.getEstado());
    }

    @Test
    void cancelarCita() {
        Cita cita = new Cita("2026-06-16", "11:00", paciente, medico);
        agenda.agregarCita(cita);
        agenda.cancelarCita(cita.getId());
        assertEquals(EstadoCita.CANCELADA, cita.getEstado());
    }

    @Test
    void atenderCita() {
        Cita cita = new Cita("2026-06-17", "12:00", paciente, medico);
        agenda.agregarCita(cita);
        agenda.atenderCita(cita.getId());
        assertEquals(EstadoCita.ATENDIDA, cita.getEstado());
    }
}
