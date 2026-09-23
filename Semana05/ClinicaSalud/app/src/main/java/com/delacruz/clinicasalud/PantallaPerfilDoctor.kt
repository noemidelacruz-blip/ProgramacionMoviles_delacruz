package com.delacruz.clinicasalud

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

/**
 * EJERCICIO 1 - FASE 1: PERFIL DEL DOCTOR CON NAVEGACIÓN DRAWER
 * Semana 05 - Clínica Salud
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfilDoctor() {
    val contexto = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // ESTRUCTURA PRINCIPAL: MODAL NAVIGATION DRAWER (Menú Lateral Desplegable)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Menú Clínica Salud",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0066CC)
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text("Perfil del Doctor") },
                    selected = true,
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Mis Citas Médicas") },
                    selected = false,
                    onClick = {
                        Toast.makeText(contexto, "Sección de Citas", Toast.LENGTH_SHORT).show()
                        coroutineScope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    ) {
        // CONTENIDO DE LA PANTALLA
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Perfil Profesional", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir Menú Lateral",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF0066CC),
                        titleContentColor = Color.White
                    )
                )
            }
        ) { paddingValores ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValores)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // =========================================================================
                // REQUERIMIENTO 1: FICHA INFORMATIVA DEL DOCTOR
                // Muestra la foto, nombre, especialidad y credenciales médicas.
                // =========================================================================

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(3.dp, Color(0xFF0066CC), CircleShape)
                        .background(Color(0xFFE3F2FD)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Foto de perfil del doctor",
                        modifier = Modifier.size(70.dp),
                        tint = Color(0xFF0066CC)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Dr. Alejandro De La Cruz",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )
                Text(
                    text = "Especialista en Cardiología Clínica",
                    fontSize = 15.sp,
                    color = Color(0xFF666666)
                )
                Text(
                    text = "Colegiatura CMP: 84920 | 12 años de exp.",
                    fontSize = 13.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(20.dp))

                // =========================================================================
                // REQUERIMIENTO 2: MÉTRICAS DE IMPACTO Y VALORACIÓN
                // Tarjeta de estadísticas con estrellas, pacientes y reseñas.
                // =========================================================================
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F4F8)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ItemMetrica(titulo = "Calificación", valor = "4.9 ★", subtexto = "240 reseñas")

                        HorizontalDivider(
                            modifier = Modifier
                                .height(40.dp)
                                .width(1.dp),
                            color = Color.LightGray
                        )

                        ItemMetrica(titulo = "Atenciones", valor = "+1,500", subtexto = "Pacientes")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // =========================================================================
                // REQUERIMIENTO 3: ESTADO DE DISPONIBILIDAD Y ACCIÓN PRIMARIA
                // Muestra disponibilidad y botón para agendar cita.
                // =========================================================================

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF2E7D32))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Disponible para citas hoy",
                        color = Color(0xFF2E7D32),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }

                Button(
                    onClick = {
                        Toast.makeText(
                            contexto,
                            "Iniciando proceso de reserva con el Dr. Alejandro...",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066CC))
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Agendar Cita Médica",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ItemMetrica(titulo: String, valor: String, subtexto: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = titulo, fontSize = 12.sp, color = Color.Gray)
        Text(text = valor, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0066CC))
        Text(text = subtexto, fontSize = 12.sp, color = Color.DarkGray)
    }
}