package com.delacruz.lab04carritotecsup

data class Producto(
    val nombre: String,
    val precio: Double,
    val cantidad: Int
) {
    val importe: Double
        get() = precio * cantidad
}