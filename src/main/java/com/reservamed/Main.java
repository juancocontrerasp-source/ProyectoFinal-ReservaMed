package com.reservamed;

import java.util.List;

/**
 * Aplicación principal de ReservaMed para ejecutar una pequeña demostración.
 */
public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        Paciente paciente = new Paciente("P-001", "Laura López", "98765432", "laura.lopez@example.com");
        Medico medico = new Medico("M-001", "Dr. Ramírez", "Pediatría");

        Cita cita = new Cita("2026-06-20", "09:00", paciente, medico);
        agenda.agregarCita(cita);

        System.out.println("Cita registrada:");
        imprimirCitas(agenda.consultarCitas());

        agenda.confirmarCita(cita.getId());
        System.out.println("\nCita confirmada:");
        imprimirCitas(agenda.consultarCitas());
    }

    private static void imprimirCitas(List<Cita> citas) {
        citas.forEach(cita -> System.out.println(
                cita.getId() + " | " + cita.getFecha() + " " + cita.getHora() + " | "
                        + cita.getPaciente().getNombre() + " - " + cita.getMedico().getNombre() + " | "
                        + cita.getEstado()));
    }
}
