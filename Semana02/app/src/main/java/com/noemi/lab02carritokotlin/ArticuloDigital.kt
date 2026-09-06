package com.noemi.lab02carritokotlin

class ArticuloDigital(
    identificador: String,
    denominacion: String,
    precioUnitario: Double,
    val enlaceDescarga: String
) : Articulo(identificador, denominacion, precioUnitario) {

    override fun obtenerImporteTotal(): Double {
        return precioUnitario
    }
}