package com.delacruz.clinicasalud

data class Doctor(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val calificacion: Double,
    val resenas: Int,
    val experiencia: String,
    val biografia: String
)

data class Cita(
    val doctorNombre: String,
    val fecha: String,
    val hora: String,
    val estado: String // "Confirmada" o "Completada"
)

// Datos iniciales de prueba
val listaMedicosPrueba = listOf(
    Doctor(1, "Dra. Ana Torres", "Cardiología", 4.9, 128, "12 años exp.", "Especialista en arritmias e hipertensión."),
    Doctor(2, "Dr. Luis Vega", "Pediatría", 4.7, 95, "8 años exp.", "Atención integral infantil y pediatría preventiva."),
    Doctor(3, "Dra. Rosa Díaz", "Dermatología", 4.8, 110, "10 años exp.", "Especialista en dermatología clínica y estética.")
)