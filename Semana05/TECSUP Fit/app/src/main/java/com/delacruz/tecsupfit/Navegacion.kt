package com.delacruz.tecsupfit

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class DestinoFit(val ruta: String, val titulo: String = "") {
    object Inicio : DestinoFit("inicio", "Inicio")
    object Reservas : DestinoFit("reservas", "Reservas")
    object Rutinas : DestinoFit("rutinas", "Rutinas")
    object Perfil : DestinoFit("perfil", "Perfil")

    object DetalleClase : DestinoFit("detalle/{claseId}") {
        fun crearRuta(claseId: Int): String {
            return "detalle/$claseId"
        }
    }

    object Confirmacion : DestinoFit("confirmacion/{claseNombre}/{horario}/{sala}") {
        fun crearRuta(claseNombre: String, horario: String, sala: String): String {
            val nombreEnc = URLEncoder.encode(claseNombre, StandardCharsets.UTF_8.name())
            val horaEnc = URLEncoder.encode(horario, StandardCharsets.UTF_8.name())
            val salaEnc = URLEncoder.encode(sala, StandardCharsets.UTF_8.name())
            return "confirmacion/$nombreEnc/$horaEnc/$salaEnc"
        }
    }
}
