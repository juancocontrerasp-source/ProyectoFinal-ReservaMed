# ReservaMed

ReservaMed es una aplicación web sencilla para gestionar reservas de citas médicas usando HTML, CSS y JavaScript puro.

## Problema que soluciona

Muchas clínicas y consultorios necesitan un sistema simple para registrar y administrar citas médicas sin usar herramientas complejas. ReservaMed ayuda a:

- Registrar citas con datos del paciente y del médico.
- Evitar horarios duplicados para el mismo médico.
- Actualizar el estado de cada cita.
- Visualizar todas las citas en una tabla clara.

## Cómo ejecutar el proyecto

1. Abrir la carpeta del proyecto en el navegador.
2. Ejecutar el archivo `index.html`.
3. Llenar el formulario de reserva y hacer clic en "Registrar cita".

> No se requiere servidor. Basta con abrir `index.html` directamente en el navegador.

## Reglas de negocio

- Todos los campos del formulario son obligatorios.
- No se puede registrar dos citas con el mismo médico, en la misma fecha y a la misma hora.
- Las citas comienzan en estado `Pendiente`.
- Se pueden cambiar estados a `Confirmada`, `Cancelada` o `Atendida` con los botones correspondientes.

## Estructura del proyecto

- `index.html`: interfaz principal, formulario y tabla de citas.
- `styles.css`: estilos visuales para el formulario y la tabla.
- `app.js`: lógica de manejo de citas, clases y renderizado.
- `tests.js`: pruebas unitarias simples con `console.assert`.
- `README.md`: información del proyecto.

## Pruebas unitarias

Las pruebas incluidas en `tests.js` verifican:

- Que no se registre una cita con datos vacíos.
- Que se registre una cita válida.
- Que no se permita una doble reserva con el mismo médico, fecha y hora.
- Que una cita pendiente pueda confirmarse.
- Que una cita pueda cancelarse.

Para revisar las pruebas, abrir la consola del navegador después de cargar `index.html`.

## Integrantes

- Estudiante: [Nombre del Estudiante]
- Curso: Proyecto final académico
