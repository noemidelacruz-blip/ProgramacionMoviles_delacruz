package com.delacruz.tecsupfit

data class ClaseFit(
    val id: Int,
    val nombre: String,
    val categoria: String = "Funcional",
    val entrenador: String = "Coach Carlos",
    val hora: String,
    val sala: String,
    val duracion: String = "45 min",
    val intensidad: String = "Alta",
    val caloriasEst: String = "350 kcal",
    val descripcion: String = "Entrenamiento de alta intensidad diseñado para tonificar, mejorar resistencia cardiovascular y acelerar el metabolismo.",
    val cuposDisponibles: Int = 8,
    val cuposTotales: Int = 12
)

data class Reserva(
    val id: Int,
    val claseId: Int,
    val claseNombre: String,
    val entrenador: String = "Coach Carlos",
    val horario: String,
    val sala: String,
    val estado: String = "Confirmada" // "Confirmada", "Completada", "Cancelada"
)

data class Ejercicio(
    val nombre: String,
    val series: String,
    val repeticiones: String,
    val descanso: String
)

data class Rutina(
    val id: Int,
    val titulo: String,
    val categoria: String,
    val nivel: String,
    val duracionMin: Int,
    val ejercicios: List<Ejercicio>
)

data class PerfilUsuario(
    val iniciales: String = "DR",
    val nombre: String = "Diego Ramos",
    val email: String = "diego.ramos@tecsup.edu.pe",
    val plan: String = "Plan Alumno TECSUP",
    val clasesTomadas: Int = 14,
    val rachas: Int = 3,
    val caloriasQuemadas: Int = 4200
)

// Datos iniciales de demostración
object DatosMock {
    val usuarioDefault = PerfilUsuario()

    val clasesIniciales = listOf(
        ClaseFit(
            id = 1,
            nombre = "Yoga Funcional & Flow",
            categoria = "Mente & Cuerpo",
            entrenador = "Coach Lucía",
            hora = "7:00 am",
            sala = "Sala 2 (Zen)",
            duracion = "50 min",
            intensidad = "Media",
            caloriasEst = "220 kcal",
            descripcion = "Secuencia fluida de posturas combinadas con respiración consciente para mejorar la flexibilidad, movilidad y liberar estrés.",
            cuposDisponibles = 5,
            cuposTotales = 12
        ),
        ClaseFit(
            id = 2,
            nombre = "Cross Training WOD",
            categoria = "Fuerza",
            entrenador = "Coach Marcos",
            hora = "6:00 pm",
            sala = "Box Principal",
            duracion = "60 min",
            intensidad = "Alta",
            caloriasEst = "500 kcal",
            descripcion = "Entrenamiento funcional variado ejecutado a alta intensidad. Circuitos de fuerza, resistencia y potencia.",
            cuposDisponibles = 2,
            cuposTotales = 10
        ),
        ClaseFit(
            id = 3,
            nombre = "Spinning Cardio Zone",
            categoria = "Cardio",
            entrenador = "Coach Elena",
            hora = "7:30 pm",
            sala = "Sala Ride 3",
            duracion = "45 min",
            intensidad = "Alta",
            caloriasEst = "450 kcal",
            descripcion = "Ciclismo bajo techo ritmado con música motivadora, intervalos de velocidad y resistencia para quemar grasa.",
            cuposDisponibles = 8,
            cuposTotales = 15
        ),
        ClaseFit(
            id = 4,
            nombre = "Pilates Core & Core",
            categoria = "Mente & Cuerpo",
            entrenador = "Coach Lucía",
            hora = "8:30 am",
            sala = "Sala 2 (Zen)",
            duracion = "45 min",
            intensidad = "Media",
            caloriasEst = "280 kcal",
            descripcion = "Fortalecimiento del centro corporal (abdominales, lumbar y glúteos) mediante control preciso y postura.",
            cuposDisponibles = 6,
            cuposTotales = 12
        ),
        ClaseFit(
            id = 5,
            nombre = "Boxeo & Cardio Burn",
            categoria = "Cardio",
            entrenador = "Coach Fernando",
            hora = "5:00 pm",
            sala = "Ring / Zona Combat",
            duracion = "50 min",
            intensidad = "Alta",
            caloriasEst = "480 kcal",
            descripcion = "Combinaciones de golpes en saco, agilidad de pies y acondicionamiento físico de luchador.",
            cuposDisponibles = 4,
            cuposTotales = 10
        )
    )

    val reservasIniciales = listOf(
        Reserva(
            id = 101,
            claseId = 2,
            claseNombre = "Cross Training WOD",
            entrenador = "Coach Marcos",
            horario = "Hoy, 6:00 pm",
            sala = "Box Principal",
            estado = "Confirmada"
        ),
        Reserva(
            id = 102,
            claseId = 1,
            claseNombre = "Yoga Funcional & Flow",
            entrenador = "Coach Lucía",
            horario = "Ayer, 7:00 am",
            sala = "Sala 2 (Zen)",
            estado = "Completada"
        )
    )

    val rutinasMock = listOf(
        Rutina(
            id = 1,
            titulo = "Fuerza Tren Superior",
            categoria = "Fuerza",
            nivel = "Intermedio",
            duracionMin = 45,
            ejercicios = listOf(
                Ejercicio("Press de Banca con Barra", "4", "10 - 12 reps", "90 seg"),
                Ejercicio("Dominadas / Jalón al Pecho", "4", "10 reps", "90 seg"),
                Ejercicio("Press Militar con Mancuernas", "3", "12 reps", "60 seg"),
                Ejercicio("Remo con Mancuerna a una mano", "3", "12 reps/lado", "60 seg"),
                Ejercicio("Fondos en paralelas / Tríceps", "3", "15 reps", "45 seg")
            )
        ),
        Rutina(
            id = 2,
            titulo = "Hipertrofia Pierna & Glúteos",
            categoria = "Fuerza",
            nivel = "Avanzado",
            duracionMin = 50,
            ejercicios = listOf(
                Ejercicio("Sentadilla Trasera con Barra", "4", "8 - 10 reps", "120 seg"),
                Ejercicio("Prensa de Piernas 45°", "4", "12 reps", "90 seg"),
                Ejercicio("Peso Muerto Rumano", "3", "10 - 12 reps", "90 seg"),
                Ejercicio("Zancadas Caminando", "3", "12 reps por pierna", "60 seg"),
                Ejercicio("Elevación de Talones (Gemelos)", "4", "15 - 20 reps", "45 seg")
            )
        ),
        Rutina(
            id = 3,
            titulo = "Core Express & Abdomen Steel",
            categoria = "Core",
            nivel = "Principiante",
            duracionMin = 20,
            ejercicios = listOf(
                Ejercicio("Plancha Isométrica", "3", "45 seg", "30 seg"),
                Ejercicio("Crunches en Esterilla", "3", "20 reps", "30 seg"),
                Ejercicio("Elevación de Piernas colgado", "3", "15 reps", "45 seg"),
                Ejercicio("Russian Twists con disco", "3", "20 reps totales", "30 seg")
            )
        )
    )
}
