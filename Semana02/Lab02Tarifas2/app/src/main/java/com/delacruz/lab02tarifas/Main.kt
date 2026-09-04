package com.delacruz.lab02tarifas

import java.util.Scanner

// Estructura para el desglose hora por hora
data class DetalleHora(
    val numeroHora: Int,
    val tarifaBase: Double,
    val recargoPorcentaje: Double,
    val importe: Double
)

// Estructura completa para el cálculo del vehículo
data class VehiculoProcesado(
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
    val baseImponible: Double,
    val igv: Double,
    val totalFinal: Double
)

fun calcularVehiculo(placa: String, tipo: String, horas: Int, cliente: String, esFrecuente: Boolean): VehiculoProcesado {
    // 1. Tarifa básica según tipo
    val tarifaBase = when (tipo) {
        "moto" -> 2.0
        "vehiculo", "auto" -> 4.0
        "camioneta" -> 10.0
        "trailer" -> 20.0
        else -> 0.0
    }

    // 2. Cálculo hora por hora con recargos progresivos
    val detalles = mutableListOf<DetalleHora>()
    var subtotal = 0.0

    for (h in 1..horas) {
        val recargoPorcentaje = when {
            h in 1..2 -> 0.0        // Horas 1-2: 0% recargo
            h in 3..5 -> 0.20       // Horas 3-5: +20% recargo
            h in 6..10 -> 0.40      // Horas 6-10: +40% recargo
            else -> 0.50            // Horas >10: +50% recargo
        }
        val importe = tarifaBase * (1 + recargoPorcentaje)
        subtotal += importe
        detalles.add(DetalleHora(h, tarifaBase, recargoPorcentaje * 100, importe))
    }

    // 3. Aplicación de Descuentos
    val descuentoFrecuente = if (esFrecuente) subtotal * 0.10 else 0.0
    val subtotalConFrecuente = subtotal - descuentoFrecuente

    // Descuento adicional si el acumulado supera los S/ 500
    val descuentoMontoAlto = if (subtotalConFrecuente > 500.0) subtotalConFrecuente * 0.20 else 0.0

    // 4. Base Imponible, IGV (18%) y Total Final
    val baseImponible = subtotalConFrecuente - descuentoMontoAlto
    val igv = baseImponible * 0.18
    val totalFinal = baseImponible + igv

    return VehiculoProcesado(
        placa, tipo, horas, cliente, esFrecuente, tarifaBase, detalles,
        subtotal, descuentoFrecuente, descuentoMontoAlto, baseImponible, igv, totalFinal
    )
}

fun main() {
    val scanner = Scanner(System.`in`)
    val listaVehiculos = mutableListOf<VehiculoProcesado>()

    println("=== COMMIT 2: CÁLCULO DE LIQUIDACIÓN CON RECARGOS Y DESCUENTOS ===")

    print("Ingrese la cantidad de vehículos a procesar: ")
    val cantidad = scanner.nextInt()
    scanner.nextLine()

    for (i in 1..cantidad) {
        println("\n--- Datos del Vehículo $i / $cantidad ---")

        print("Placa: ")
        val placa = scanner.nextLine().uppercase()

        var tipo = ""
        while (tipo !in listOf("moto", "vehiculo", "auto", "camioneta", "trailer")) {
            print("Tipo (moto / vehiculo / camioneta / trailer): ")
            tipo = scanner.nextLine().lowercase()
        }

        var horas = 0
        while (horas !in 1..24) {
            print("Horas estimadas de permanencia (Mínimo: 1 - Máximo: 24): ")
            if (scanner.hasNextInt()) {
                horas = scanner.nextInt()
                scanner.nextLine()
                if (horas !in 1..24) {
                    println("❌ Error: Debe ingresar un rango entre 1 y 24 horas.")
                }
            } else {
                println("❌ Error: Debe ingresar un número entero válido.")
                scanner.nextLine()
            }
        }

        print("Nombre del Cliente: ")
        val cliente = scanner.nextLine()

        print("¿Es cliente frecuente? (s/n): ")
        val esFrecuente = scanner.nextLine().lowercase() == "s"

        val vehiculoProcesado = calcularVehiculo(placa, tipo, horas, cliente, esFrecuente)
        listaVehiculos.add(vehiculoProcesado)
    }

    // Impresión de Boletas
    println("\n==================================================")
    println("              BOLETAS GENERADAS                   ")
    println("==================================================")

    for (v in listaVehiculos) {
        println("\n========================================")
        println("          BOLETA DE LIQUIDACIÓN         ")
        println("========================================")
        println("Placa: ${v.placa}")
        println("Tipo: ${v.tipo.uppercase()}")
        println("Horas: ${v.horas}")
        println("Cliente: ${v.cliente} (Frecuente: ${if (v.esFrecuente) "SÍ" else "NO"})")
        println("Tarifa Básica/Hora: S/ %.2f".format(v.tarifaBase))
        println("----------------------------------------")
        println("%-6s | %-8s | %-10s | %-8s".format("HORA", "TARIFA", "RECARGO", "IMPORTE"))
        println("----------------------------------------")
        for (d in v.detalles) {
            println("%-6d | %-8.2f | %-9.0f%% | S/ %-6.2f".format(d.numeroHora, d.tarifaBase, d.recargoPorcentaje, d.importe))
        }
        println("----------------------------------------")
        println("Subtotal Bruto:         S/ %.2f".format(v.subtotal))
        if (v.esFrecuente) {
            println("Descuento Frecuente(10%):-S/ %.2f".format(v.descuentoFrecuente))
        }
        if (v.descuentoMontoAlto > 0) {
            println("Descuento >S/500 (20%): -S/ %.2f".format(v.descuentoMontoAlto))
        }
        println("Base Imponible:         S/ %.2f".format(v.baseImponible))
        println("IGV (18%%):               +S/ %.2f".format(v.igv))
        println("TOTAL A PAGAR:          S/ %.2f".format(v.totalFinal))
        println("========================================")
    }
}