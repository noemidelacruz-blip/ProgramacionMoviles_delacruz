package com.noemi.lab02carritokotlin

fun main() {
    println("--- PRUEBA DE CARRITO CON CONSTRUCTOR SECUNDARIO ---")
    val gestorPrueba = GestorCompras()
    println("Cliente creado: ${gestorPrueba.cliente}")
    println("----------------------------------------------------\n")

    println("=== AGREGANDO PRODUCTOS AL CARRITO ===")
    val laptop = ArticuloFisico("F01", "Laptop HP", 2500.0, 2.2)
    val mouse = ArticuloFisico("F02", "Mouse Logitech", 93.25, 0.3)
    val audifonos = ArticuloFisico("F03", "Audifonos Sony Driver", 150.0, 0.5)
    val usb = ArticuloFisico("F04", "USB Kingston 64GB", 76.25, 0.1)
    val software = ArticuloDigital("D01", "Licencia Antivirus 1 Anio", 55.0, "https://tienda.com/licencia-key")

    val gestor = GestorCompras("Noemi de la Cruz")
    gestor.agregarElemento(laptop)
    gestor.agregarElemento(mouse)
    gestor.agregarElemento(audifonos)
    gestor.agregarElemento(usb)
    gestor.agregarElemento(software)

    println("\n=== PRUEBA DE ELIMINACION DE PRODUCTO ===")
    gestor.removerElementoPorId("F03")

    val reporte = ServicioReporte()
    reporte.generarResumenCompra(gestor)
}