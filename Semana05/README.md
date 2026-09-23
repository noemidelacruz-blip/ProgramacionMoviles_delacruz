# Clínica Salud+
Aplicación Móvil Android desarrollada con Jetpack Compose para la búsqueda de médicos, agendamiento de citas de salud y gestión de reservas.

---

## Descripción del Proyecto

Clínica Salud+ es una solución móvil nativa para Android diseñada para facilitar a los pacientes la búsqueda de médicos por especialidad (Cardiología, Pediatría, Dermatología), la programación de citas en horarios disponibles y la gestión en tiempo real de sus reservas médicas.

La aplicación permite seleccionar la fecha, la hora, el motivo de la consulta y el método de pago (Yape/Plin o Tarjeta), mostrando avisos de confirmación instantáneos y listando las citas agendadas en la pantalla principal.

---

## Tecnologías y Herramientas Utilizadas

* Lenguaje de Programación: Kotlin (100% Nativo)
* UI Framework: Jetpack Compose (Declarativo)
* Arquitectura: Clean Architecture / MVVM con delegación de estado mediante StateFlow
* Lógica de Estado UI: mutableStateListOf, remember, StateFlow
* Diseño y Componentes: Material Design 3 (Scaffold, LazyColumn, AlertDialog, Snackbar, BottomNavigationBar)
* IDE Recomendado: Android Studio
* Control de Versiones: Git y GitHub

---
## Promtd usados:

Prompt 1: Reestructuración del Estado (Migración a ViewModel):
Reestructura el manejo de estado actual de la aplicación Clínica Salud+. Extrae la lista mutable de citas de MainActivity.kt hacia un nuevo ClinicaViewModel.kt utilizando StateFlow. Ajusta MainActivity.kt para inyectar este ViewModel y actualizar la lógica de reserva y navegación ya existentes sin alterar la estructura UI funcional.
Prompt 2: Reestructuración de UI (Filtro y Búsqueda de Médicos):
Reestructura PantallaInicio.kt para integrar el campo de texto de búsqueda (OutlinedTextField) existente dentro del flujo de la LazyColumn de médicos. Optimiza el algoritmo de filtrado en tiempo real para que responda dinámicamente a la lista actual de doctores y especialidades (Cardiología, Pediatría, Dermatología) reutilizando los componentes visuales e íconos previamente definidos.
Prompt 3: Reestructuración de Interacción (Diálogo de Confirmación):
Reestructura el flujo de cancelación de citas en PantallaMisCitas.kt. Modifica la acción del botón 'X' en las tarjetas de citas agendadas para que, en lugar de eliminar el registro directamente, active un modal de confirmación (AlertDialog) reutilizando los datos del médico agendado antes de llamar a la función de eliminación.
Prompt 4: Reestructuración de Autenticación (Pantalla de Login / Iniciar Sesión):
Reestructura el acceso inicial en PantallaLogin.kt añadiendo una experiencia de navegación dual. Implementa el formulario tradicional de inicio de sesión con validación de credenciales (correo y contraseña), junto con un botón secundario de 'Continuar como invitado'. Ambas opciones deben permitir la entrada a la aplicación para consultar médicos, agendar citas y realizar pagos, diferenciando el estado de sesión del usuario en el flujo de navegación sin bloquear el uso de la app.
Prompt 5 (Interacción): Diálogo AlertDialog para confirmación de cancelación de citas.

## Capturas de Pantalla

### 1. Inicio de Sesión
Permite el ingreso seguro del paciente a la aplicación o también como invitada. 

<img width="300" alt="image" src="https://github.com/user-attachments/assets/c617aefe-3730-4c99-9c19-59d9b88d71be" />

---

### 2. Búsqueda y Filtro de Médicos
Muestra el catálogo de doctores con opción de búsqueda por nombre y filtrado por especialidades.

<img width="300" alt="image" src="https://github.com/user-attachments/assets/eb7efe4b-eaa7-47f0-a191-75c5143d599d" />

---

### 3. Registro de Cita Médica
Formulario emergente para seleccionar la fecha, la hora, el motivo de consulta y la forma de pago.

<img width="300" alt="image" src="https://github.com/user-attachments/assets/832090e4-b6fa-48f4-9dd6-de0b5de8f484" />

---
### 4. Perfil medico
Visualización del perfil del medio y especialidad y sede

<img width="330" alt="image" src="https://github.com/user-attachments/assets/f57295e6-e6ba-4f33-987b-6d17ce1511b4" />

---

### 5. Historial de Citas Agendadas
Listado completo de todas las citas agendadas por el paciente con opción para gestionarlas o cancelarlas.

 <img width="300" alt="image" src="https://github.com/user-attachments/assets/2f6de249-5220-46be-9014-274a71bf3851" />
 
---
