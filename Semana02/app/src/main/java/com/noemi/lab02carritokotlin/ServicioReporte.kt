package com.noemi.lab02carritokotlin

class ServicioReporte {

    fun generarResumenCompra(gestor: GestorCompras) {
        println("\n========================================")
        println("          DETALLE DE LA COMPRA          ")
        println("========================================")

        for (item in gestor.itemsSeleccionados) {
            println("• [${item.identificador}] ${item.denominacion} -> S/ ${item.obtenerImporteTotal()}")
        }

        println("----------------------------------------")
        println(" Cantidad de productos: ${gestor.itemsSeleccionados.size}")
        println(" MONTO TOTAL A PAGAR: S/ ${gestor.calcularMontoTotal()}")
        println("========================================\n")
    }
}