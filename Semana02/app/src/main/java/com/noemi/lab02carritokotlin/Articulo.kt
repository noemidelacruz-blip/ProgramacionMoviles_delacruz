package com.noemi.lab02carritokotlin

abstract class Articulo(
    override val identificador: String,
    override val denominacion: String,
    override val precioUnitario: Double
) : Vendible