package com.noemi.lab02carritokotlin

class ServicioReporte {

    fun generarResumenCompra(gestor: GestorCompras) {
        println("\n========================================")
        println("   CARRITO DE COMPRAS - TIENDA TECSUP   ")
        println("========================================")
        println("Cliente: ${gestor.cliente}\n")

        println("--------- DETALLE DEL CARRITO ---------")
        var contador = 1
        for (item in gestor.obtenerLista()) {
            val detalleTipo = when (item) {
                is ArticuloFisico -> "(Fisico - ${item.pesoKg}kg)"
                is ArticuloDigital -> "(Digital - 25MB [VITALICIA])"
                else -> ""
            }

            // Formateo alineado
            val linea = String.format("%d. %-22s x1  S/%8.2f %s", contador, item.denominacion, item.obtenerImporteTotal(), detalleTipo)
            println(linea)
            contador++
        }
        println("----------------------------------------\n")

        val subtotal = gestor.calcularSubtotal()
        val igv = gestor.calcularIGV()
        val total = gestor.calcularMontoTotal()

        println(String.format("Cantidad de productos : %d", gestor.obtenerCantidadTotalProductos()))
        println(String.format("Subtotal              : S/ %8.2f", subtotal))
        println(String.format("IGV (18%%)             : S/ %8.2f", igv))
        println(String.format("TOTAL A PAGAR         : S/ %8.2f", total))
        println("========================================\n")
    }
}