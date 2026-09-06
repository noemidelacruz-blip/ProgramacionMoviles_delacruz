package com.noemi.lab02carritokotlin

class ArticuloFisico(
    identificador: String,
    denominacion: String,
    override val precioUnitario: Double,
    val pesoKg: Double
) : Articulo(identificador, denominacion, precioUnitario) {

    override fun obtenerImporteTotal(): Double {
        val recargoEnvio = pesoKg * 2.5
        return precioUnitario + recargoEnvio
    }
}