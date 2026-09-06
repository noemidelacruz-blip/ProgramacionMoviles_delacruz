package com.noemi.lab02carritokotlin

class GestorCompras(var cliente: String = "ANONIMO") {

    // Constructor secundario
    constructor() : this("ANONIMO")

    private val elementosSeleccionados = mutableListOf<Vendible>()

    fun agregarElemento(item: Vendible) {
        elementosSeleccionados.add(item)
        println("Producto agregado: ${item.denominacion}")
    }

    fun removerElementoPorId(id: String): Boolean {
        val eliminado = elementosSeleccionados.removeIf { it.identificador == id }
        if (eliminado) {
            println("Producto con ID '$id' eliminado del carrito.")
        }
        return eliminado
    }

    fun calcularSubtotal(): Double {
        return elementosSeleccionados.sumOf { it.obtenerImporteTotal() }
    }

    fun calcularIGV(): Double {
        return calcularSubtotal() * 0.18
    }

    fun calcularMontoTotal(): Double {
        return calcularSubtotal() + calcularIGV()
    }

    fun obtenerCantidadTotalProductos(): Int {
        return elementosSeleccionados.size
    }

    fun obtenerLista(): List<Vendible> {
        return elementosSeleccionados
    }
}