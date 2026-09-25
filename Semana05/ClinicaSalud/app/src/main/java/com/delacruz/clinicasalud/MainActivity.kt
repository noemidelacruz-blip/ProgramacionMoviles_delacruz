package com.delacruz.clinicasalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*

sealed class Pantalla {
    object Inicio : Pantalla()
    data class PerfilDoctor(val doctor: Doctor) : Pantalla()
    data class AgendarCita(val doctor: Doctor) : Pantalla()
    data class Confirmacion(val doctorNombre: String, val fecha: String, val hora: String) : Pantalla()
    object MisCitas : Pantalla()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppClinicaSalud()
            }
        }
    }
}

@Composable
fun AppClinicaSalud() {
    var pantallaActual by remember { mutableStateOf<Pantalla>(Pantalla.Inicio) }
    val citasAgendadas = remember {
        mutableStateListOf(
            Cita("Dra. Ana Torres", "Ayer", "15:00", "Completada"),
            Cita("Dr. Luis Vega", "Hoy", "10:30", "Confirmada")
        )
    }

    ContenedorPrincipalDrawer(
        onNavegarInicio = { pantallaActual = Pantalla.Inicio },
        onNavegarMisCitas = { pantallaActual = Pantalla.MisCitas }
    ) { abrirDrawer ->
        when (val pantalla = pantallaActual) {
            is Pantalla.Inicio -> {
                PantallaInicio(
                    onAbrirDrawer = abrirDrawer,
                    onDoctorSeleccionado = { doctor ->
                        pantallaActual = Pantalla.PerfilDoctor(doctor)
                    }
                )
            }
            is Pantalla.PerfilDoctor -> {
                PantallaPerfilDoctor(
                    doctor = pantalla.doctor,
                    onVolverInicio = { pantallaActual = Pantalla.Inicio },
                    onAgendarCitaClick = {
                        pantallaActual = Pantalla.AgendarCita(pantalla.doctor)
                    }
                )
            }
            is Pantalla.AgendarCita -> {
                PantallaAgendarCita(
                    doctor = pantalla.doctor,
                    onVolverPerfil = {
                        pantallaActual = Pantalla.PerfilDoctor(pantalla.doctor)
                    },
                    onConfirmarCita = { fecha, hora ->
                        citasAgendadas.add(
                            0,
                            Cita(
                                doctorNombre = pantalla.doctor.nombre,
                                fecha = fecha,
                                hora = hora,
                                estado = "Confirmada"
                            )
                        )
                        pantallaActual = Pantalla.Confirmacion(
                            doctorNombre = pantalla.doctor.nombre,
                            fecha = fecha,
                            hora = hora
                        )
                    }
                )
            }
            is Pantalla.Confirmacion -> {
                PantallaConfirmacion(
                    doctorNombre = pantalla.doctorNombre,
                    fecha = pantalla.fecha,
                    hora = pantalla.hora,
                    onVerMisCitasClick = {
                        pantallaActual = Pantalla.MisCitas
                    }
                )
            }
            is Pantalla.MisCitas -> {
                PantallaMisCitas(
                    citas = citasAgendadas,
                    onAbrirDrawer = abrirDrawer
                )
            }
        }
    }
}