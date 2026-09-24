# TECSUP Fit — Aplicación Móvil de Gimnasio Campus

* Estudiante: De La Cruz
* Curso: Programación en Móviles — Ciclo 4
* Institución: TECSUP

Aplicación móvil desarrollada en Android con Jetpack Compose para la gestión de clases deportivas, reserva de cupos en tiempo real, seguimiento de rutinas de entrenamiento y perfil de rendimiento para la comunidad de TECSUP.

---

## Descripción del Proyecto

El proyecto toma como punto de partida una arquitectura base funcional y la evoluciona mediante una reestructuración asistida por Inteligencia Artificial. Se implementó una interfaz de usuario moderna basada en Material Design 3, integrando navegación animada por pestañas, filtrado dinámico en tiempo real, gestión de estado reactivo global para el control de aforo y un flujo de reservas con confirmación mediante boleto digital con código QR.

---

## PROMDT: 

## Mejoras e Innovaciones en Front-End (UI / UX)

### 1. Sistema de Diseño y Tokens Visuales (ui/theme/)
* **Paleta de Colores Institucional TECSUP**: Integración de tokens cromáticos personalizados (`VerdeTecsup`, `VerdeEsmeralda`, `AzulDeportivo`, `NaranjaCalorias`, `RojoAlerta`) garantizando identidad visual y accesibilidad en contrastes.
* **Soporte Dinámico Claro/Oscuro**: Adaptación de superficies, bordes y coloración de la barra de estado del sistema (`StatusBar`) utilizando `MaterialTheme.colorScheme`.
* **Microinteracciones y Feedback Visual**: Transiciones de color suaves al seleccionar categorías y barras de progreso (`LinearProgressIndicator`) para reflejar visualmente la saturación de cupos.

### 2. Experiencia de Navegación y Shell UI (MainActivity.kt)
* **BottomNavigationBar Animada**: Barra de navegación inferior basada en Scaffold de Material 3 con 4 pestañas interactivas (Inicio, Reservas, Rutinas y Perfil).
* **Navegación Inmersiva (UI Hiding)**: Ocultamiento dinámico de la barra inferior al navegar a pantallas de detalle, confirmación o modales para eliminar distracciones visuales.
* **Actualización del Canvas en Tiempo Real**: Re-renderizado reactivo inmediato en la interfaz al agendar o cancelar clases sin necesidad de recargar la pantalla.

---

## Rediseño y Desarrollo de Pantallas (Front-End Focus)

### 1. PantallaInicio.kt — Hub Principal de Exploración
* **Header Degradado**: Saludo personalizado con badge animado de racha activa y buscador en vivo (`OutlinedTextField`) que filtra la `LazyColumn` por nombre o coach.
* **Chips de Filtrado Horizontal**: Componentes seleccionables con animación de estado para alternar entre categorías (Todas, Fuerza, Cardio, Mente & Cuerpo).
* **Cards de Clases Deportivas**: Tarjetas con elevación, etiquetas de intensidad de color (Alta/Media), estimador de calorías quemadas e indicador de cupos restantes.

### 2. PantallaReservas.kt — Centro de Gestión y Tickets Digitales
* **UI Tipo Ticket por Estados**: Borde indicador según el estado de la reserva (Verde para Confirmadas, Azul para Completadas y Rojo para Canceladas).
* **Diálogo Modal Interactivo**: Ventana `AlertDialog` estilizada para confirmar la cancelación de la cita sin romper la fluidez visual.
* **State Layout (Vista Vacía)**: Ilustración vectorial integrada con botón directo de acción (*Call-to-Action*) cuando el usuario no tiene reservas activas.

### 3. PantallaRutinas.kt — Guías de Entrenamiento Interactivas
* **Cards Expandibles**: Tarjetas plegables para evitar sobrecarga de información, mostrando series, repeticiones y tiempos de descanso al hacer clic.
* **Checkboxes de Progreso**: Componentes interactivos que permiten al estudiante marcar manualmente sus ejercicios completados durante la rutina libre.

### 4. PantallaPerfil.kt — Dashboard del Alumno TECSUP
* **Header de Identidad**: Avatar circular con iniciales, correo institucional y badge distintivo de membresía (`Plan Alumno TECSUP`).
* **KPIs Visuales de Rendimiento**: Tarjetas compactas de métricas rápidas (clases asistidas, semanas en racha y calorías quemadas).
* **Ajustes de Preferencias**: Componentes `Switch` para gestionar alertas de clases y selectores de sedes del campus.

### 5. PantallaDetalleYConfirmacion.kt — Modal Hero y Ticket QR
* **Vista Detalle**: Tarjeta Hero superior con ícono distintivo de la disciplina, chips de especificaciones (hora, sala, calorías) y sección de recomendaciones del gimnasio.
* **Vista de Confirmación**: Iconografía de éxito tipo check animado, tarjeta resumen con código QR (*placeholder*) y acciones de retorno rápido.

## Tecnologías Utilizadas

* Lenguaje: Kotlin (100% Nativo)
* UI Framework: Jetpack Compose
* Componentes: Material Design 3 (Scaffold, LazyColumn, OutlinedTextField, LinearProgressIndicator, AlertDialog)
* Navegación: Navigation Compose (androidx.navigation:navigation-compose)
* Arquitectura: Clean Architecture / MVVM con gestión de estado reactivo

---

## Capturas de Pantalla

### 1. Pantalla Principal y Catálogo de Clases
Buscador en tiempo real, filtrado por categorías y estado de cupos disponibles por clase.

<img width="300" alt="image" src="https://github.com/user-attachments/assets/3432eebf-a12e-44d9-b338-80518986414a" />

---

### 2. Detalle de Clase
Vista detallada con información del entrenador, horarios, requerimientos de la sala y botón de reserva.

<img width="300" alt="image" src="https://github.com/user-attachments/assets/0a0054ee-75fe-4f28-9646-1346a9b95ce5" />

---

### 3. Confirmación de Reserva
Generación de boleto digital con código QR al completar el agendamiento del cupo.

<img width="300" alt="image" src="https://github.com/user-attachments/assets/047e7870-f29a-4544-9b9e-833702bc3007" />

---

### 4. Historial de Reservas Activas
Gestión de reservas clasificadas por estado con opción para cancelar y liberar el cupo.

<img width="300" alt="image" src="https://github.com/user-attachments/assets/133d9302-49be-4b66-8ad2-2294c6c5e7db" />

---

### 5. Perfil de Usuario
Métricas de rendimiento personal, plan activo del campus y configuraciones de cuenta.

<img width="300" alt="image" src="https://github.com/user-attachments/assets/04b0a111-0c02-4f28-8aad-f21a5b7a4d7f" />

---

### 6. Rutinas de Entrenamiento
Tarjetas expandibles con ejercicios, series y repeticiones para entrenamientos libres.

<img width="300" alt="image" src="https://github.com/user-attachments/assets/d79fd298-1532-4754-ae27-c81aed8f14af" />

---
