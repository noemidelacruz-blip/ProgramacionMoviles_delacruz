package com.delacruz.tecsupfit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.EventBusy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delacruz.tecsupfit.ui.theme.*

@Composable
fun PantallaReservas(
    reservas: List<Reserva>,
    onCancelarReserva: (Reserva) -> Unit,
    onExplorarClases: () -> Unit
) {
    var estadoFiltro by remember { mutableStateOf("Todas") }
    var reservaACancelar by remember { mutableStateOf<Reserva?>(null) }

    val reservasFiltradas = reservas.filter { r ->
        when (estadoFiltro) {
            "Confirmadas" -> r.estado == "Confirmada"
            "Completadas" -> r.estado == "Completada"
            "Canceladas" -> r.estado == "Cancelada"
            else -> true
        }
    }

    // Modal de confirmación para cancelar reserva
    reservaACancelar?.let { reserva ->
        AlertDialog(
            onDismissRequest = { reservaACancelar = null },
            icon = { Icon(Icons.Default.Warning, contentDescription = null, tint = RojoAlerta) },
            title = { Text("¿Cancelar reserva?", fontWeight = FontWeight.Bold) },
            text = { Text("Liberarás tu cupo en '${reserva.claseNombre}'. Esta acción no se puede deshacer.") },
            confirmButton = {
                Button(
                    onClick = {
                        onCancelarReserva(reserva)
                        reservaACancelar = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RojoAlerta)
                ) {
                    Text("Sí, Cancelar", color = Color.White)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { reservaACancelar = null }) {
                    Text("Volver")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 2.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Mis Reservas",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Surface(
                        color = VerdeClaroBg,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "${reservas.count { it.estado == "Confirmada" }} activas",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = VerdeTecsup,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Scrollable Tabs
                TabRow(
                    selectedTabIndex = listOf("Todas", "Confirmadas", "Completadas", "Canceladas").indexOf(estadoFiltro),
                    containerColor = Color.Transparent,
                    contentColor = VerdeTecsup,
                    divider = {}
                ) {
                    listOf("Todas", "Confirmadas", "Completadas", "Canceladas").forEach { title ->
                        Tab(
                            selected = estadoFiltro == title,
                            onClick = { estadoFiltro = title },
                            text = {
                                Text(
                                    title,
                                    fontWeight = if (estadoFiltro == title) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            }
                        )
                    }
                }
            }
        }

        // Body List
        if (reservasFiltradas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Outlined.EventBusy,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "No tienes reservas en este filtro",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "Explora la lista de clases disponibles y reserva tu cupo.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = onExplorarClases,
                        colors = ButtonDefaults.buttonColors(containerColor = VerdeTecsup),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Ver Clases Disponibles", color = Color.White)
                    }
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(reservasFiltradas) { reserva ->
                    ItemReservaCard(
                        reserva = reserva,
                        onSolicitarCancelar = { reservaACancelar = it }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemReservaCard(
    reserva: Reserva,
    onSolicitarCancelar: (Reserva) -> Unit
) {
    val esConfirmada = reserva.estado == "Confirmada"
    val esCompletada = reserva.estado == "Completada"

    val colorBorde = when {
        esConfirmada -> VerdeTecsup
        esCompletada -> AzulDeportivo
        else -> RojoAlerta
    }

    val colorFondoBadge = when {
        esConfirmada -> VerdeClaroBg
        esCompletada -> AzulDeportivo.copy(alpha = 0.15f)
        else -> RojoAlerta.copy(alpha = 0.15f)
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicator Bar
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(colorBorde)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        reserva.claseNombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Surface(
                        color = colorFondoBadge,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            reserva.estado,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            color = colorBorde,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "📅 ${reserva.horario} · ${reserva.sala}",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "🏋️ Coach: ${reserva.entrenador}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (esConfirmada) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = { onSolicitarCancelar(reserva) },
                            colors = ButtonDefaults.textButtonColors(contentColor = RojoAlerta)
                        ) {
                            Icon(Icons.Default.Cancel, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Cancelar Cupo", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
