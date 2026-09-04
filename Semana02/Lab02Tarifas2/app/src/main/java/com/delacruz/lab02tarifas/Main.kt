package com.delacruz.lab02tarifas

import java.util.Scanner

data class DetalleHora(
    val numeroHora: Int,
    val tarifaBase: Double,
    val recargoPorcentaje: Double,
    val importe: Double
)

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

    // --- CAPACIDAD LIBRE DEFINIDA POR EL CLIENTE ---
    var aforoMaximo = 0
    while (aforoMaximo < 1) {
        print("Ingrese la capacidad del estacionamiento: ")
        if (scanner.hasNextInt()) {
            aforoMaximo = scanner.nextInt()
            scanner.nextLine()
            if (aforoMaximo < 1) {
                println("❌ Error: La capacidad debe ser al menos 1.\n")
            }
        } else {
            println("❌ Error: Debe ingresar un número válido.\n")
            scanner.nextLine()
        }
    }

    val vehiculosEstacionados = mutableListOf<VehiculoProcesado>()
    val historialCobrados = mutableListOf<VehiculoProcesado>()

    var opcion = 0

    while (opcion != 4) {
        val ocupacionActual = vehiculosEstacionados.size
        val espaciosDisponibles = aforoMaximo - ocupacionActual

        println("\n==================================================")
        println("       SISTEMA DE CONTROL DE ESTACIONAMIENTO")
        println("  Ocupación: $ocupacionActual / $aforoMaximo  |  Disponibles: $espaciosDisponibles")
        println("==================================================")
        println("1. Registrar Ingreso de Vehículo")
        println("2. Registrar Retiro y Cobro de Vehículo")
        println("3. Ver Vehículos Estacionados Actualmente")
        println("4. Cerrar Día y Ver Resumen Final")
        print("Seleccione una opción (1-4): ")

        if (scanner.hasNextInt()) {
            opcion = scanner.nextInt()
            scanner.nextLine()
        } else {
            println("❌ Opción no válida.")
            scanner.nextLine()
            continue
        }

        when (opcion) {
            1 -> {
                if (vehiculosEstacionados.size >= aforoMaximo) {
                    println("\n⛔ ¡AFORO LLENO! Se alcanzó la capacidad máxima de $aforoMaximo vehículos.")
                } else {
                    println("\n--- REGISTRAR INGRESO ---")
                    print("Placa: ")
                    val placa = scanner.nextLine().uppercase()

                    if (vehiculosEstacionados.any { it.placa == placa }) {
                        println("❌ Error: El vehículo con placa $placa ya está adentro.")
                        continue
                    }

                    var tipo = ""
                    while (tipo !in listOf("moto", "vehiculo", "auto", "camioneta", "trailer")) {
                        print("Tipo (moto / vehiculo / camioneta / trailer): ")
                        tipo = scanner.nextLine().lowercase()
                    }

                    // --- SOLICITUD DE HORAS EN RANGO 1 A 24 ---
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

                    val v = calcularVehiculo(placa, tipo, horas, cliente, esFrecuente)
                    vehiculosEstacionados.add(v)
                    println("✅ Vehículo $placa ingresado. Ocupación: ${vehiculosEstacionados.size}/$aforoMaximo")
                }
            }

            2 -> {
                if (vehiculosEstacionados.isEmpty()) {
                    println("\nℹ️ No hay vehículos estacionados actualmente.")
                } else {
                    println("\n--- REGISTRAR RETIRO Y COBRO ---")
                    print("Ingrese la placa del vehículo a retirar: ")
                    val placaBuscada = scanner.nextLine().uppercase()

                    val vehiculoEncontrado = vehiculosEstacionados.find { it.placa == placaBuscada }

                    if (vehiculoEncontrado != null) {
                        println("\n========================================")
                        println("          BOLETA DE LIQUIDACIÓN")
                        println("========================================")
                        println("Placa: ${vehiculoEncontrado.placa}")
                        println("Tipo: ${vehiculoEncontrado.tipo.uppercase()}")
                        println("Horas: ${vehiculoEncontrado.horas}")
                        println("Cliente: ${vehiculoEncontrado.cliente} (Frecuente: ${if (vehiculoEncontrado.esFrecuente) "SÍ" else "NO"})")
                        println("Tarifa Básica: S/ %.2f".format(vehiculoEncontrado.tarifaBase))
                        println("----------------------------------------")
                        println("%-6s | %-8s | %-10s | %-8s".format("HORA", "TARIFA", "RECARGO", "IMPORTE"))
                        println("----------------------------------------")
                        for (d in vehiculoEncontrado.detalles) {
                            println("%-6d | %-8.2f | %-9.0f%% | S/ %-6.2f".format(d.numeroHora, d.tarifaBase, d.recargoPorcentaje, d.importe))
                        }
                        println("----------------------------------------")
                        println("Subtotal Bruto:         S/ %.2f".format(vehiculoEncontrado.subtotal))
                        if (vehiculoEncontrado.esFrecuente) {
                            println("Descuento Frecuente(10%%): -S/ %.2f".format(vehiculoEncontrado.descuentoFrecuente))
                        }
                        if (vehiculoEncontrado.descuentoMontoAlto > 0) {
                            println("Descuento >S/500 (20%%):  -S/ %.2f".format(vehiculoEncontrado.descuentoMontoAlto))
                        }
                        println("Base Imponible:         S/ %.2f".format(vehiculoEncontrado.baseImponible))
                        println("IGV (18%%):              +S/ %.2f".format(vehiculoEncontrado.igv))
                        println("TOTAL A PAGAR:          S/ %.2f".format(vehiculoEncontrado.totalFinal))
                        println("========================================")

                        vehiculosEstacionados.remove(vehiculoEncontrado)
                        historialCobrados.add(vehiculoEncontrado)
                        println("🚗 Vehículo $placaBuscada retirado. Libres: ${aforoMaximo - vehiculosEstacionados.size}")

                    } else {
                        println("❌ No se encontró la placa $placaBuscada.")
                    }
                }
            }

            3 -> {
                if (vehiculosEstacionados.isEmpty()) {
                    println("\nℹ️ El estacionamiento está vacío.")
                } else {
                    println("\n--- VEHÍCULOS ESTACIONADOS (${vehiculosEstacionados.size}/$aforoMaximo) ---")
                    for (v in vehiculosEstacionados) {
                        println(" • Placa: ${v.placa} | Tipo: ${v.tipo.uppercase()} | Horas: ${v.horas} | Cliente: ${v.cliente}")
                    }
                }
            }

            4 -> println("\nCerrando operaciones...")

            else -> println("❌ Opción inválida.")
        }
    }

    val totalMotos = historialCobrados.count { it.tipo == "moto" }
    val totalVehiculos = historialCobrados.count { it.tipo in listOf("vehiculo", "auto") }
    val totalCamionetas = historialCobrados.count { it.tipo == "camioneta" }
    val totalTrailers = historialCobrados.count { it.tipo == "trailer" }
    val recaudacionTotal = historialCobrados.sumOf { it.totalFinal }
    val vehiculoMayor = historialCobrados.maxByOrNull { it.totalFinal }

    println("\n========================================")
    println("          RESUMEN FINAL DEL DÍA         ")
    println("========================================")
    println("Capacidad del Estacionamiento: $aforoMaximo")
    println("Vehículos Cobrados: ${historialCobrados.size}")
    println("  - Motos:      $totalMotos")
    println("  - Vehículos:  $totalVehiculos")
    println("  - Camionetas: $totalCamionetas")
    println("  - Tráilers:   $totalTrailers")
    println()
    println("Recaudación Total: S/ %.2f".format(recaudacionTotal))
    if (vehiculoMayor != null) {
        println("----------------------------------------")
        println("Vehículo con Mayor Pago:")
        println("  - Placa: ${vehiculoMayor.placa} | Monto: S/ %.2f".format(vehiculoMayor.totalFinal))
    }
    println("========================================")
}