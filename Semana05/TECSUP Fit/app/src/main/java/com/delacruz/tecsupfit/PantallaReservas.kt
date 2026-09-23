package com.delacruz.tecsupfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaReservas(reservas: List<Reserva>) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp)) {
        Text("Mis reservas", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(reservas) { reserva ->
                val esConfirmada = reserva.estado == "Confirmada"
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = GrisTarjeta),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(50.dp)
                                .background(if (esConfirmada) VerdeTecsup else Color.Gray, RoundedCornerShape(2.dp))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(reserva.claseNombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(reserva.horario, color = Color.Gray, fontSize = 13.sp)
                            Spacer(modifier = Modifier.height(6.dp))
                            Surface(
                                color = if (esConfirmada) VerdeClaroBg else Color(0xFFE0E0E0),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    reserva.estado,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    color = if (esConfirmada) VerdeTecsup else Color.DarkGray,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}