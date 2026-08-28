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

    print("¿Cuántos vehículos desea procesar?: ")
    val cantidad = scanner.nextInt()
    scanner.nextLine()

    var i = 1
    while (i <= cantidad) {
        println("\n====================================")
        println("       REGISTRO DE VEHÍCULO $i")
        println("====================================")
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

        print("Nombre del Cliente: ")
        val cliente = scanner.nextLine()

        print("¿Es cliente frecuente? (s/n): ")
        val esFrecuente = scanner.nextLine().lowercase() == "s"

        val v = calcularVehiculo(placa, tipo, horas, cliente, esFrecuente)
        listaVehiculos.add(v)
        i++
    }

    println("\n========================================")
    println("        REPORTE FINAL DE PAGOS")
    println("========================================")

    for (v in listaVehiculos) {
        println("\nPlaca: ${v.placa}")
        println("Tipo: ${v.tipo.uppercase()}")
        println("Horas: ${v.horas}")
        println("Cliente: ${v.cliente} (Frecuente: ${if (v.esFrecuente) "SÍ" else "NO"})")
        println("Tarifa Básica: S/ %.2f".format(v.tarifaBase))
        println("----------------------------------------")
        println("%-6s | %-8s | %-10s | %-8s".format("HORA", "TARIFA", "RECARGO", "IMPORTE"))
        println("----------------------------------------")
        for (d in v.detalles) {
            println("%-6d | %-8.2f | %-9.0f%% | S/ %-6.2f".format(d.numeroHora, d.tarifaBase, d.recargoPorcentaje, d.importe))
        }
        println("----------------------------------------")
        println("Subtotal:            S/ %.2f".format(v.subtotal))
        if (v.esFrecuente) {
            println("Descuento Frecuente: -S/ %.2f".format(v.descuento))
        }
        println("TOTAL A PAGAR:       S/ %.2f".format(v.total))
    }
}