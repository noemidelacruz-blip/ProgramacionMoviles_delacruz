package com.delacruz.lab02tarifas

import java.util.Scanner

// Estructura básica para almacenar los datos del vehículo
data class Vehiculo(
    val placa: String,
    val tipo: String,
    val horas: Int,
    val cliente: String,
    val esFrecuente: Boolean,
    val tarifaBase: Double
)

fun main() {
    val scanner = Scanner(System.`in`)
    val listaVehiculos = mutableListOf<Vehiculo>()

    println("=== COMMIT 1: ESTRUCTURA BASE Y REGISTRO DE VEHÍCULOS ===")

    // Lectura de la cantidad de vehículos iniciales
    print("Ingrese la cantidad de vehículos a registrar: ")
    val cantidad = scanner.nextInt()
    scanner.nextLine() // Limpiar búfer

    var i = 1
    while (i <= cantidad) {
        println("\n--- Registro de Vehículo $i / $cantidad ---")

        print("Placa: ")
        val placa = scanner.nextLine().uppercase()

        // Validación de Tipo de Vehículo
        var tipo = ""
        while (tipo !in listOf("moto", "vehiculo", "auto", "camioneta", "trailer")) {
            print("Tipo (moto / vehiculo / camioneta / trailer): ")
            tipo = scanner.nextLine().lowercase()
        }

        // Validación de Horas (Mínimo 1 hora)
        var horas = 0
        while (horas < 1) {
            print("Horas de permanencia (mínimo 1): ")
            horas = scanner.nextInt()
            scanner.nextLine()
        }

        print("Nombre del Cliente: ")
        val cliente = scanner.nextLine()

        print("¿Es cliente frecuente? (s/n): ")
        val esFrecuente = scanner.nextLine().lowercase() == "s"

        // Asignación de Tarifa Básica por Hora según la tabla
        val tarifaBase = when (tipo) {
            "moto" -> 2.0
            "vehiculo", "auto" -> 4.0
            "camioneta" -> 10.0
            "trailer" -> 20.0
            else -> 0.0
        }

        val vehiculo = Vehiculo(placa, tipo, horas, cliente, esFrecuente, tarifaBase)
        listaVehiculos.add(vehiculo)

        i++
    }

    // Reporte inicial de confirmación
    println("\n========================================")
    println("      RESUMEN DE INGRESO (COMMIT 1)")
    println("========================================")
    for (v in listaVehiculos) {
        println("Placa: ${v.placa} | Tipo: ${v.tipo.uppercase()} | Horas: ${v.horas} | Cliente: ${v.cliente} | Tarifa Base/Hora: S/ %.2f".format(v.tarifaBase))
    }
}