package com.noemi.lab02carritokotlin

fun main() {
    val libro = ArticuloFisico("F01", "Libro Kotlin", 60.0, 1.5)
    val productoDigital = ArticuloDigital("D01", "Ebook Jetpack Compose", 45.0, "https://descarga.com/ebook")

    val gestor = GestorCompras()
    gestor.agregarElemento(libro)
    gestor.agregarElemento(productoDigital)

    // Usamos el servicio dedicado a reportes
    val reporte = ServicioReporte()
    reporte.generarResumenCompra(gestor)
}