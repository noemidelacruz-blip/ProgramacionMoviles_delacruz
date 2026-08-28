package com.noemi.lab02carritokotlin

// Interfaz base
interface Vendible {
    val identificador: String
    val denominacion: String
    val precioUnitario: Double
    fun obtenerImporteTotal(): Double
}

// Producto físico (con costo de envío)
class ArticuloFisico(
    override val identificador: String,
    override val denominacion: String,
    override val precioUnitario: Double,
    val pesoKg: Double
) : Vendible {
    override fun obtenerImporteTotal(): Double {
        val recargoEnvio = pesoKg * 2.5
        return precioUnitario + recargoEnvio
    }
}

// Producto digital (con descuento por descarga)
class ArticuloDigital(
    override val identificador: String,
    override val denominacion: String,
    override val precioUnitario: Double,
    val enlaceDescarga: String
) : Vendible {
    override fun obtenerImporteTotal(): Double {
        return precioUnitario // Sin costo de envío
    }
}

fun main() {
    println("Fase 2: Clases ArticuloFisico y ArticuloDigital creadas.")
}