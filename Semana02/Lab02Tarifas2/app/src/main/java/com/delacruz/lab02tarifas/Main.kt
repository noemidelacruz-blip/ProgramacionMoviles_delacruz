package com.delacruz.lab02tarifas

import java.util.Scanner

data class DetalleHora(val numeroHora: Int, val tarifaBase: Double, val recargoPorcentaje: Double, val importe: Double)

data class VehiculoProcesado(
    val placa: String,
    val tipo: String,
    val horas: Int,
    val cliente: String,
    val esFrecuente: Boolean,
    val tarifaBase: Double,
    val detalles: List<DetalleHora>,
    val subtotal: Double,
    val descuento: Double,
    val total: Double
)

fun calcularVehiculo(placa: String, tipo: String, horas: Int, cliente: String, esFrecuente: Boolean): VehiculoProcesado {
    val tarifaBase = when (tipo) {
        "moto" -> 2.0
        "auto" -> 4.5
        "camioneta" -> 10.0
        else -> 0.0
    }

    val detalles = mutableListOf<DetalleHora>()
    var subtotal = 0.0

    for (h in 1..horas) {
        val recargoPorcentaje = when {
            h <= 2 -> 0.0
            h in 3..5 -> 0.20
            else -> 0.50
        }
        val importe = tarifaBase * (1 + recargoPorcentaje)
        subtotal += importe
        detalles.add(DetalleHora(h, tarifaBase, recargoPorcentaje * 100, importe))
    }

    val descuento = if (esFrecuente) subtotal * 0.10 else 0.0
    val total = subtotal - descuento

    return VehiculoProcesado(placa, tipo, horas, cliente, esFrecuente, tarifaBase, detalles, subtotal, descuento, total)
}

fun main() {
    val scanner = Scanner(System.`in`)
    val listaVehiculos = mutableListOf<VehiculoProcesado>()

    println("=== CÁLCULOS Y OPERACIONES ===")
    print("¿Cuántos vehículos desea procesar?: ")
    val cantidad = scanner.nextInt()
    scanner.nextLine()

    var i = 1
    while (i <= cantidad) {
        println("\n--- Vehículo $i ---")
        print("Placa: ")
        val placa = scanner.nextLine()

        var tipo = ""
        while (tipo !in listOf("moto", "auto", "camioneta")) {
            print("Tipo (moto / auto / camioneta): ")
            tipo = scanner.nextLine().lowercase()
        }

        var horas = 0
        while (horas < 1) {
            print("Horas (mínimo 1): ")
            horas = scanner.nextInt()
            scanner.nextLine()
        }

        print("Cliente: ")
        val cliente = scanner.nextLine()

        print("¿Es cliente frecuente? (s/n): ")
        val esFrecuente = scanner.nextLine().lowercase() == "s"

        val v = calcularVehiculo(placa, tipo, horas, cliente, esFrecuente)
        listaVehiculos.add(v)
        i++
    }

    println("\n Cálculos realizados internamente para ${listaVehiculos.size} vehículos.")
}