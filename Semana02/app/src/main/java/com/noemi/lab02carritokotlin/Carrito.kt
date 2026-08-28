package com.noemi.lab02carritokotlin

// Interfaz que define el contrato base para cualquier elemento vendible
interface Vendible {
    val identificador: String
    val denominacion: String
    val precioUnitario: Double

    // Método abstracto para calcular el importe neto
    fun obtenerImporteTotal(): Double
}

fun main() {
    println("Fase 1: Interfaz base Vendible creada.")
}