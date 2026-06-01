class Paciente {
  constructor(nombre, documento, telefono, correo) {
    this.nombre = nombre.trim();
    this.documento = documento.trim();
    this.telefono = telefono.trim();
    this.correo = correo.trim();
  }
}

class Medico {
  constructor(nombre, especialidad) {
    this.nombre = nombre.trim();
    this.especialidad = especialidad.trim();
  }
}

class Cita {
  constructor(paciente, medico, fecha, hora, estado = 'Pendiente') {
    this.id = `${Date.now()}-${Math.random().toString(36).slice(2)}`;
    this.paciente = paciente;
    this.medico = medico;
    this.fecha = fecha;
    this.hora = hora;
    this.estado = estado;
  }
}

class Agenda {
  constructor() {
    this.citas = [];
  }

  validarDatos(cita) {
    if (!cita.paciente.nombre || !cita.paciente.documento || !cita.paciente.telefono || !cita.paciente.correo || !cita.medico.nombre || !cita.medico.especialidad || !cita.fecha || !cita.hora) {
      throw new Error('Todos los campos son obligatorios.');
    }
  }

  existeDuplicado(cita) {
    return this.citas.some((item) => {
      return item.medico.nombre.toLowerCase() === cita.medico.nombre.toLowerCase() &&
             item.fecha === cita.fecha &&
             item.hora === cita.hora;
    });
  }

  agregarCita(cita) {
    this.validarDatos(cita);
    if (this.existeDuplicado(cita)) {
      throw new Error('Ya existe una cita con ese médico, fecha y hora.');
    }
    this.citas.push(cita);
  }

  cambiarEstado(citaId, nuevoEstado) {
    const cita = this.citas.find((item) => item.id === citaId);
    if (!cita) {
      throw new Error('Cita no encontrada.');
    }
    cita.estado = nuevoEstado;
  }

  confirmarCita(citaId) {
    this.cambiarEstado(citaId, 'Confirmada');
  }

  cancelarCita(citaId) {
    this.cambiarEstado(citaId, 'Cancelada');
  }

  atenderCita(citaId) {
    this.cambiarEstado(citaId, 'Atendida');
  }
}

const agenda = new Agenda();
const formulario = document.getElementById('citaForm');
const mensajeElemento = document.getElementById('mensaje');
const citasBody = document.getElementById('citasBody');

function crearCitaDesdeFormulario() {
  const paciente = new Paciente(
    document.getElementById('nombrePaciente').value,
    document.getElementById('documentoPaciente').value,
    document.getElementById('telefonoPaciente').value,
    document.getElementById('correoPaciente').value
  );

  const medico = new Medico(
    document.getElementById('nombreMedico').value,
    document.getElementById('especialidadMedico').value
  );

  const fecha = document.getElementById('fechaCita').value;
  const hora = document.getElementById('horaCita').value;

  return new Cita(paciente, medico, fecha, hora);
}

function mostrarMensaje(texto, esError = true) {
  mensajeElemento.textContent = texto;
  mensajeElemento.style.color = esError ? 'var(--danger)' : 'var(--success)';
  setTimeout(() => {
    mensajeElemento.textContent = '';
  }, 3500);
}

function limpiarFormulario() {
  formulario.reset();
}

function crearEtiquetaEstado(estado) {
  const span = document.createElement('span');
  span.classList.add('status');
  switch (estado) {
    case 'Confirmada':
      span.classList.add('status-confirmada');
      break;
    case 'Cancelada':
      span.classList.add('status-cancelada');
      break;
    case 'Atendida':
      span.classList.add('status-atendida');
      break;
    default:
      span.classList.add('status-pendiente');
      break;
  }
  span.textContent = estado;
  return span;
}

function crearBoton(texto, clase, evento) {
  const boton = document.createElement('button');
  boton.type = 'button';
  boton.textContent = texto;
  boton.className = clase;
  boton.addEventListener('click', evento);
  return boton;
}

function renderCitas() {
  citasBody.innerHTML = '';

  if (agenda.citas.length === 0) {
    const filaVacia = document.createElement('tr');
    filaVacia.innerHTML = '<td colspan="10" class="empty">No hay citas registradas.</td>';
    citasBody.appendChild(filaVacia);
    return;
  }

  agenda.citas.forEach((cita) => {
    const fila = document.createElement('tr');

    fila.innerHTML = `
      <td>${cita.paciente.nombre}</td>
      <td>${cita.paciente.documento}</td>
      <td>${cita.paciente.telefono}</td>
      <td>${cita.paciente.correo}</td>
      <td>${cita.medico.nombre}</td>
      <td>${cita.medico.especialidad}</td>
      <td>${cita.fecha}</td>
      <td>${cita.hora}</td>
      <td></td>
      <td class="action-buttons"></td>
    `;

    const estadoCelda = fila.querySelector('td:nth-child(9)');
    estadoCelda.appendChild(crearEtiquetaEstado(cita.estado));

    const accionesCelda = fila.querySelector('td:nth-child(10)');

    const confirmarBoton = crearBoton('Confirmar', 'btn-confirmar', () => {
      try {
        agenda.confirmarCita(cita.id);
        renderCitas();
      } catch (error) {
        mostrarMensaje(error.message);
      }
    });

    const cancelarBoton = crearBoton('Cancelar', 'btn-cancelar', () => {
      try {
        agenda.cancelarCita(cita.id);
        renderCitas();
      } catch (error) {
        mostrarMensaje(error.message);
      }
    });

    const atendidaBoton = crearBoton('Atendida', 'btn-atendida', () => {
      try {
        agenda.atenderCita(cita.id);
        renderCitas();
      } catch (error) {
        mostrarMensaje(error.message);
      }
    });

    accionesCelda.appendChild(confirmarBoton);
    accionesCelda.appendChild(cancelarBoton);
    accionesCelda.appendChild(atendidaBoton);

    citasBody.appendChild(fila);
  });
}

formulario.addEventListener('submit', (event) => {
  event.preventDefault();

  try {
    const nuevaCita = crearCitaDesdeFormulario();
    agenda.agregarCita(nuevaCita);
    renderCitas();
    limpiarFormulario();
    mostrarMensaje('Cita registrada con éxito.', false);
  } catch (error) {
    mostrarMensaje(error.message);
  }
});

renderCitas();
