function ejecutarPruebas() {
  console.group('Pruebas ReservaMed');

  const agendaTest = new Agenda();
  const pacienteValido = new Paciente('Ana Gómez', '111222333', '3001234567', 'ana@example.com');
  const medicoValido = new Medico('Dr. Solano', 'Dermatología');
  const fechaValida = '2026-06-15';
  const horaValida = '10:30';

  // Prueba 1: no se registre una cita con datos vacíos.
  let errorRegistroVacio = false;
  try {
    agendaTest.agregarCita(new Cita(new Paciente('', '', '', ''), new Medico('', ''), '', ''));
  } catch (error) {
    errorRegistroVacio = true;
  }
  console.assert(errorRegistroVacio, 'No se debe registrar una cita con datos vacíos.');

  // Prueba 2: se registre una cita válida.
  const citaValida = new Cita(pacienteValido, medicoValido, fechaValida, horaValida);
  agendaTest.agregarCita(citaValida);
  console.assert(agendaTest.citas.length === 1, 'La cita válida debe registrarse.');

  // Prueba 3: no se permita doble reserva con el mismo médico, fecha y hora.
  let errorDuplicado = false;
  try {
    agendaTest.agregarCita(new Cita(pacienteValido, medicoValido, fechaValida, horaValida));
  } catch (error) {
    errorDuplicado = true;
  }
  console.assert(errorDuplicado, 'No se debe permitir doble reserva con el mismo médico, fecha y hora.');
  console.assert(agendaTest.citas.length === 1, 'El calendario no debe agregar la cita duplicada.');

  // Prueba 4: una cita pendiente pueda confirmarse.
  agendaTest.confirmarCita(citaValida.id);
  console.assert(citaValida.estado === 'Confirmada', 'La cita pendiente debe cambiar a confirmada.');

  // Prueba 5: una cita pueda cancelarse.
  const citaParaCancelar = new Cita(pacienteValido, new Medico('Dr. Vega', 'Neurología'), '2026-06-16', '11:00');
  agendaTest.agregarCita(citaParaCancelar);
  agendaTest.cancelarCita(citaParaCancelar.id);
  console.assert(citaParaCancelar.estado === 'Cancelada', 'La cita debe poder cancelarse.');

  console.groupEnd();
}

window.addEventListener('load', ejecutarPruebas);
