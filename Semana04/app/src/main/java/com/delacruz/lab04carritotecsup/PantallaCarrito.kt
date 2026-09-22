package com.delacruz.lab04carritotecsup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCarrito() {
    // Lista reactiva observable
    val listaProductos = remember {
        mutableStateListOf(
            Producto(1, "Laptop HP", 2500.00, 1),
            Producto(2, "Mouse Logitech", 45.50, 2),
            Producto(3, "USB Kingston 64GB", 25.00, 3)
        )
    }

    // Estados del Formulario
    var nombreInput by remember { mutableStateOf("") }
    var precioInput by remember { mutableStateOf("") }
    var cantidadInput by remember { mutableStateOf("") }

    // Estado del Reto 1: Confirmación de borrado
    var productoAEliminar by remember { mutableStateOf<Producto?>(null) }

    // Generador de ID
    var siguienteId by remember { mutableStateOf(4) }

    // Cálculos de Totales
    val subtotalBruto = listaProductos.sumOf { it.importe }

    // Reto 2: Descuento dinámico con when
    val porcentajeDescuento = when {
        subtotalBruto > 5000 -> 0.10
        subtotalBruto > 3000 -> 0.05
        else -> 0.0
    }
    val montoDescuento = subtotalBruto * porcentajeDescuento
    val subtotalConDescuento = subtotalBruto - montoDescuento
    val igv = subtotalConDescuento * 0.18
    val totalFinal = subtotalConDescuento + igv

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mi Carrito TECSUP",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // --- FORMULARIO DE REGISTRO DE PRODUCTOS ---
            OutlinedTextField(
                value = nombreInput,
                onValueChange = { nombreInput = it },
                label = { Text("Nombre del producto") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = precioInput,
                    onValueChange = { precioInput = it },
                    label = { Text("Precio (S/)") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = cantidadInput,
                    onValueChange = { cantidadInput = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val precio = precioInput.toDoubleOrNull()
                    val cantidad = cantidadInput.toIntOrNull()

                    if (nombreInput.isNotBlank() && precio != null && cantidad != null && cantidad > 0) {
                        listaProductos.add(
                            Producto(
                                id = siguienteId++,
                                nombre = nombreInput.trim(),
                                precio = precio,
                                cantidad = cantidad
                            )
                        )
                        nombreInput = ""
                        precioInput = ""
                        cantidadInput = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("AGREGAR", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- LISTA DE PRODUCTOS O ESTADO VACÍO (BOX) ---
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (listaProductos.isEmpty()) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Tu carrito está vacío",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Agrega tu primer producto",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.LightGray
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 4.dp)
                    ) {
                        items(
                            items = listaProductos,
                            key = { it.id }
                        ) { producto ->
                            TarjetaProducto(
                                producto = producto,
                                onEliminar = {
                                    productoAEliminar = producto
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // --- PANEL FIJO DE TOTALES ---
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Productos: ${listaProductos.size}",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Subtotal")
                        Text(String.format(Locale.US, "S/ %.2f", subtotalBruto))
                    }

                    if (porcentajeDescuento > 0.0) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Descuento (${(porcentajeDescuento * 100).toInt()}%):",
                                color = Color(0xFF388E3C),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = String.format(Locale.US, "- S/ %.2f", montoDescuento),
                                color = Color(0xFF388E3C),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("IGV (18%)")
                        Text(String.format(Locale.US, "S/ %.2f", igv))
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "TOTAL",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = String.format(Locale.US, "S/ %.2f", totalFinal),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }

    // --- RETO 1: DIÁLOGO DE CONFIRMACIÓN AL ELIMINAR ---
    productoAEliminar?.let { producto ->
        AlertDialog(
            onDismissRequest = { productoAEliminar = null },
            title = { Text("¿Eliminar este producto?") },
            text = { Text("¿Estás seguro de quitar \"${producto.nombre}\" del carrito de compras?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        listaProductos.remove(producto)
                        productoAEliminar = null
                    }
                ) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { productoAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}