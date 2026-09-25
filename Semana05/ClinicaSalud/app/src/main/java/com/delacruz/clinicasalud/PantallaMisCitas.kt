package com.delacruz.clinicasalud

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMisCitas(
    citas: List<Cita>,
    onAbrirDrawer: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis citas", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onAbrirDrawer) {
                        Icon(Icons.Default.Menu, contentDescription = "Abrir Menú")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // LazyColumn con la lista de citas agendadas y sus estados
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(citas) { cita ->
                    TarjetaCitaItem(cita = cita)
                }
            }
        }
    }
}

@Composable
fun TarjetaCitaItem(cita: Cita) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicador lateral de color según estado
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(50.dp)
                    .background(
                        if (cita.estado == "Confirmada") Color(0xFF4A148C) else Color.LightGray,
                        shape = RoundedCornerShape(2.dp)
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cita.doctorNombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${cita.fecha}, ${cita.hora}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Badge / Chip visual para el estado ("Confirmada" / "Completada")
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (cita.estado == "Confirmada") Color(0xFFE8F5E9) else Color(0xFFE0E0E0)
                ) {
                    Text(
                        text = cita.estado,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (cita.estado == "Confirmada") Color(0xFF2E7D32) else Color.DarkGray,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}