package com.noemi.lab02carritokotlin

class GestorCompras {
    // Encapsulamiento: la lista es privada y mutable solo internamente
    private val _itemsSeleccionados = mutableListOf<Vendible>()

    // Exponemos una lista de solo lectura hacia afuera
    val itemsSeleccionados: List<Vendible>
        get() = _itemsSeleccionados

    fun agregarElemento(item: Vendible) {
        _itemsSeleccionados.add(item)
    }

    fun removerElementoPorId(id: String): Boolean {
        return _itemsSeleccionados.removeIf { it.identificador == id }
    }

    fun calcularMontoTotal(): Double {
        return _itemsSeleccionados.sumOf { it.obtenerImporteTotal() }
    }
}