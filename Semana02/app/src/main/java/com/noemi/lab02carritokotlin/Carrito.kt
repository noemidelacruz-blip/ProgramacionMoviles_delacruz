package com.noemi.lab02carritokotlin

class Carrito {
    private val listaElementos = mutableListOf<Vendible>()

    fun agregarElemento(elemento: Vendible) {
        listaElementos.add(elemento)
    }

    fun calcularTotalGeneral(): Double {
        return listaElementos.sumOf { it.obtenerImporteTotal() }
    }

    fun obtenerElementos(): List<Vendible> {
        return listaElementos
    }
}