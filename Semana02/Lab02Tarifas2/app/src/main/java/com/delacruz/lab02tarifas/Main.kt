package com.delacruz.lab02tarifas

import java.util.Scanner

data class Vehiculo(
    val placa: String,
    val tipo: String,
    val horas: Int,
    val cliente: String,
    val esFrecuente: Boolean
)

fun main() {
    val scanner = Scanner(System.`in`)
    val listaVehiculos = mutableListOf<Vehiculo>()

    println("=== COMMIT 1: INGRESO DE DATOS ===")
    print("¿Cuántos vehículos desea procesar?: ")
    val cantidad = scanner.nextInt()
    scanner.nextLine() // Limpiar buffer

    var i = 1
    while (i <= cantidad) {
        println("\n--- Vehículo $i ---")
        print("Ingrese Placa: ")
        val placa = scanner.nextLine()

        var tipo = ""
        while (tipo !in listOf("moto", "auto", "camioneta")) {
            print("Ingrese Tipo (moto / auto / camioneta): ")
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
        val frecuenteInput = scanner.nextLine().lowercase()
        val esFrecuente = frecuenteInput == "s"

        listaVehiculos.add(Vehiculo(placa, tipo, horas, cliente, esFrecuente))
        i++
    }

    println("\n[COMMIT 1 COMPLETADO]: ${listaVehiculos.size} vehículos registrados con éxito.")
}