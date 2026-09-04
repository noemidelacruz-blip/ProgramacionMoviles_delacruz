package com.delacruz.lab02tarifas

import java.util.Scanner

data class DetalleHora(
    val numeroHora: Int,
    val tarifaBase: Double,
    val recargoPorcentaje: Double,
    val importe: Double
)

data class VehiculoCalculado(
    val placa: String,
    val tipo: String,
    val horas: Int,
    val cliente: String,
    val esFrecuente: Boolean,
    val tarifaBase: Double,
    val detalles: List<DetalleHora>,
    val subtotal: Double,
    val descuentoFrecuente: Double,
    val descuentoMontoAlto: Double,
    val totalBruto: Double
)

fun calcularVehiculo(placa: String, tipo: String, horas: Int, cliente: String, esFrecuente: Boolean): VehiculoCalculado {
    val tarifaBase = when (tipo) {
        "moto" -> 2.0
        "vehiculo", "auto" -> 4.0
        "camioneta" -> 10.0
        "trailer" -> 20.0
        else -> 0.0
    }

    val detalles = mutableListOf<DetalleHora>()
    var subtotal = 0.0

    for (h in 1..horas) {
        val recargoPorcentaje = when {
            h in 1..2 -> 0.0
            h in 3..5 -> 0.20
            h in 6..10 -> 0.40
            else -> 0.50
        }
        val importe = tarifaBase * (1 + recargoPorcentaje)
        subtotal += importe
        detalles.add(DetalleHora(h, tarifaBase, recargoPorcentaje * 100, importe))
    }

    val descuentoFrecuente = if (esFrecuente) subtotal * 0.10 else 0.0
    val subtotalConFrecuente = subtotal - descuentoFrecuente
    val descuentoMontoAlto = if (subtotalConFrecuente > 500.0) subtotalConFrecuente * 0.20 else 0.0
    val totalBruto = subtotalConFrecuente - descuentoMontoAlto

    return VehiculoCalculado(
        placa, tipo, horas, cliente, esFrecuente, tarifaBase,
        detalles, subtotal, descuentoFrecuente, descuentoMontoAlto, totalBruto
    )
}

fun main() {
    val scanner = Scanner(System.`in`)
    val listaVehiculos = mutableListOf<VehiculoCalculado>()

    println("=== COMMIT 2: CÁLCULOS, RECARGOS Y DESCUENTOS ===")

    // Validar Aforo Máximo (1 a 10)
    var aforo = 0
    while (aforo !in 1..10) {
        print("Ingrese la cantidad de vehículos a procesar (Aforo Máx. 10): ")
        if (scanner.hasNextInt()) {
            aforo = scanner.nextInt()
            scanner.nextLine()
            if (aforo !in 1..10) {
                println("❌ Error: El aforo no puede superar los 10 vehículos ni ser menor a 1.\n")
            }
        } else {
            println("❌ Error: Debe ingresar un número válido.\n")
            scanner.nextLine()
        }
    }

    var i = 1
    while (i <= aforo) {
        println("\n--- Vehículo $i / $aforo ---")
        print("Placa: ")
        val placa = scanner.nextLine()

        var tipo = ""
        while (tipo !in listOf("moto", "vehiculo", "auto", "camioneta", "trailer")) {
            print("Tipo (moto / vehiculo / camioneta / trailer): ")
            tipo = scanner.nextLine().lowercase()
        }

        var horas = 0
        while (horas < 1) {
            print("Horas (mínimo 1): ")
            horas = scanner.nextInt()
            scanner.nextLine()
        }

        print("Nombre del Cliente: ")
        val cliente = scanner.nextLine()

        print("¿Es cliente frecuente? (s/n): ")
        val esFrecuente = scanner.nextLine().lowercase() == "s"

        val v = calcularVehiculo(placa, tipo, horas, cliente, esFrecuente)
        listaVehiculos.add(v)
        i++
    }

    println("\n[COMMIT 2 COMPLETADO]: Cálculos de recargos y regla de descuento > S/ 500 procesados internamente.")
}