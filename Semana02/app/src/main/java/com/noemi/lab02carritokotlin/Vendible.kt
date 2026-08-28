package com.noemi.lab02carritokotlin

interface Vendible {
    val identificador: String
    val denominacion: String
    val precioUnitario: Double

    fun obtenerImporteTotal(): Double
}