package com.delacruz.tecsupfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaDetalleClase(clase: ClaseFit, onBack: () -> Unit, onReservar: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
            }
            Text("Detalle de clase", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth().height(140.dp).background(VerdeClaroBg, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = VerdeTecsup, modifier = Modifier.size(64.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(clase.nombre, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("${clase.hora} · ${clase.sala} · ${clase.duracion}", color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Text(clase.descripcion, fontSize = 14.sp, color = Color.DarkGray)

        Spacer(modifier = Modifier.height(20.dp))
        Text("${clase.cuposDisponibles} de ${clase.cuposTotales} cupos disponibles", fontSize = 14.sp, fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onReservar,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeTecsup),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Reservar cupo", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PantallaConfirmacion(nombre: String, hora: String, sala: String, onVerReservas: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(80.dp).background(VerdeClaroBg, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Check, contentDescription = null, tint = VerdeTecsup, modifier = Modifier.size(40.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("¡Cupo reservado!", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(nombre, fontSize = 16.sp, color = Color.Gray)
        Text("Hoy, $hora · $sala", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onVerReservas,
            colors = ButtonDefaults.buttonColors(containerColor = GrisTarjeta),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Text("Ver mis reservas", color = Color.Black)
        }
    }
}