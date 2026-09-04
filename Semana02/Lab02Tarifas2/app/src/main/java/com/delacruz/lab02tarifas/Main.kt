package com.delacruz.lab02tarifas

import java.util.Scanner

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

    println("=== COMMIT 1: INGRESO DE DATOS ===")
    print("¿Cuántos vehículos desea procesar?: ")
    val cantidad = scanner.nextInt()
    scanner.nextLine()

    var i = 1
    while (i <= cantidad) {
        println("\n--- Vehículo $i ---")
        print("Ingrese Placa: ")
        val placa = scanner.nextLine()

        var tipo = ""
        while (tipo !in listOf("moto", "vehiculo", "auto", "camioneta", "trailer")) {
            print("Ingrese Tipo (moto / vehiculo / camioneta / trailer): ")
            tipo = scanner.nextLine().lowercase()
        }

        var horas = 0
        while (horas < 1) {
            print("Ingrese Horas (mínimo 1): ")
            horas = scanner.nextInt()
            scanner.nextLine()
        }

        print("Nombre del Cliente: ")
        val cliente = scanner.nextLine()

        print("¿Es cliente frecuente? (s/n): ")
        val esFrecuente = scanner.nextLine().lowercase() == "s"

        val tarifaBase = when (tipo) {
            "moto" -> 2.0
            "vehiculo", "auto" -> 4.0
            "camioneta" -> 10.0
            "trailer" -> 20.0
            else -> 0.0
        }

        listaVehiculos.add(Vehiculo(placa, tipo, horas, cliente, esFrecuente, tarifaBase))
        i++
    }

    println("\n[COMMIT 1 COMPLETADO]: ${listaVehiculos.size} vehículos registrados con éxito.")
}