package com.delacruz.tecsupfit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delacruz.tecsupfit.ui.theme.*

@Composable
fun PantallaPerfil(
    usuario: PerfilUsuario
) {
    var notificacionesActivadas by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Header Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VerdeTecsup)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Mi Perfil Fit",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Avatar Circle
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            usuario.iniciales,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = VerdeTecsup
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        usuario.nombre,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        usuario.email,
                        fontSize = 13.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        color = VerdeEsmeralda,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Verified,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                usuario.plan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        // Stats Row
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ItemStatCard(
                    valor = "${usuario.clasesTomadas}",
                    etiqueta = "Clases",
                    icono = Icons.Default.CheckCircle,
                    colorIcono = VerdeTecsup,
                    modifier = Modifier.weight(1f)
                )

                ItemStatCard(
                    valor = "${usuario.rachas} sem",
                    etiqueta = "Racha 🔥",
                    icono = Icons.Default.LocalFireDepartment,
                    colorIcono = NaranjaCalorias,
                    modifier = Modifier.weight(1f)
                )

                ItemStatCard(
                    valor = "${usuario.caloriasQuemadas}",
                    etiqueta = "kcal",
                    icono = Icons.Default.Bolt,
                    colorIcono = AzulDeportivo,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Section Settings
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    "Ajustes y Preferencias",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                )

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column {
                        // Toggle Notifications
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Outlined.Notifications, contentDescription = null, tint = VerdeTecsup)
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text("Notificaciones de clases", fontWeight = FontWeight.Medium, fontSize = 14.sp)
                                    Text("Recordatorio 1 hora antes de la clase", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                            Switch(
                                checked = notificacionesActivadas,
                                onCheckedChange = { notificacionesActivadas = it },
                                colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = VerdeTecsup)
                            )
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(horizontal = 16.dp))

                        ItemOpcionPerfil(
                            icono = Icons.Outlined.FitnessCenter,
                            titulo = "Sedes y Gimnasio TECSUP",
                            subtitulo = "Campus Principal - Edificio G",
                            onClick = {}
                        )

                        HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(horizontal = 16.dp))

                        ItemOpcionPerfil(
                            icono = Icons.Outlined.History,
                            titulo = "Historial de asistencias",
                            subtitulo = "Ver registros pasados",
                            onClick = {}
                        )

                        HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(horizontal = 16.dp))

                        ItemOpcionPerfil(
                            icono = Icons.Outlined.Info,
                            titulo = "Reglamento del gimnasio",
                            subtitulo = "Políticas de uso y vestidores",
                            onClick = {}
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Logout Button
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = RojoAlerta)
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cerrar Sesión", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ItemStatCard(
    valor: String,
    etiqueta: String,
    icono: ImageVector,
    colorIcono: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icono, contentDescription = null, tint = colorIcono, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(6.dp))
            Text(valor, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.onSurface)
            Text(etiqueta, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun ItemOpcionPerfil(
    icono: ImageVector,
    titulo: String,
    subtitulo: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icono, contentDescription = null, tint = VerdeTecsup)
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(titulo, fontWeight = FontWeight.Medium, fontSize = 14.sp)
            Text(subtitulo, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Icon(
            Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}
