package com.delacruz.tecsupfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaPerfil(usuario: PerfilUsuario) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Mi perfil", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier.size(90.dp).background(VerdeClaroBg, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(usuario.iniciales, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = VerdeTecsup)
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(usuario.nombre, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(usuario.plan, fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GrisTarjeta)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("${usuario.clasesTomadas}", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text("Clases", fontSize = 13.sp, color = Color.Gray)
                }
            }

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = GrisTarjeta)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("${usuario.rachas}", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text("Rachas", fontSize = 13.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun PantallaRutinas() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Sección de Rutinas", fontSize = 18.sp, color = Color.Gray)
    }
}