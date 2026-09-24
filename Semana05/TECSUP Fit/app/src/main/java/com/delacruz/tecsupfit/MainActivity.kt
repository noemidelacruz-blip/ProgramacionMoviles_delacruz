package com.delacruz.tecsupfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.delacruz.tecsupfit.ui.theme.TECSUPFitTheme
import com.delacruz.tecsupfit.ui.theme.VerdeTecsup
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

data class ItemNavegacion(
    val ruta: String,
    val titulo: String,
    val iconoSeleccionado: ImageVector,
    val iconoNoSeleccionado: ImageVector
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TECSUPFitTheme {
                TecsupFitApp()
            }
        }
    }
}

@Composable
fun TecsupFitApp() {
    val navController = rememberNavController()

    // Estado global de la aplicación
    val clasesState = remember { mutableStateListOf(*DatosMock.clasesIniciales.toTypedArray()) }
    val reservasState = remember { mutableStateListOf(*DatosMock.reservasIniciales.toTypedArray()) }
    var usuarioState by remember { mutableStateOf(DatosMock.usuarioDefault) }

    val itemsBottomNav = listOf(
        ItemNavegacion(DestinoFit.Inicio.ruta, "Inicio", Icons.Filled.Home, Icons.Outlined.Home),
        ItemNavegacion(DestinoFit.Reservas.ruta, "Reservas", Icons.Filled.EventAvailable, Icons.Outlined.EventAvailable),
        ItemNavegacion(DestinoFit.Rutinas.ruta, "Rutinas", Icons.Filled.FitnessCenter, Icons.Outlined.FitnessCenter),
        ItemNavegacion(DestinoFit.Perfil.ruta, "Perfil", Icons.Filled.Person, Icons.Outlined.Person)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    // Visibilidad de la barra inferior (Ocultar en pantallas de detalle y confirmación)
    val mostrarBottomBar = rutaActual in itemsBottomNav.map { it.ruta }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = mostrarBottomBar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    itemsBottomNav.forEach { item ->
                        val estaSeleccionado = rutaActual == item.ruta

                        NavigationBarItem(
                            selected = estaSeleccionado,
                            onClick = {
                                if (rutaActual != item.ruta) {
                                    navController.navigate(item.ruta) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (estaSeleccionado) item.iconoSeleccionado else item.iconoNoSeleccionado,
                                    contentDescription = item.titulo,
                                    tint = if (estaSeleccionado) VerdeTecsup else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                            label = {
                                Text(
                                    item.titulo,
                                    fontSize = 12.sp,
                                    fontWeight = if (estaSeleccionado) FontWeight.Bold else FontWeight.Normal,
                                    color = if (estaSeleccionado) VerdeTecsup else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = VerdeTecsup.copy(alpha = 0.12f)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DestinoFit.Inicio.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            // 1. Pantalla Inicio
            composable(DestinoFit.Inicio.ruta) {
                PantallaInicio(
                    clases = clasesState,
                    usuario = usuarioState,
                    onClaseClick = { clase ->
                        navController.navigate(DestinoFit.DetalleClase.crearRuta(clase.id))
                    },
                    onVerPerfilClick = {
                        navController.navigate(DestinoFit.Perfil.ruta)
                    }
                )
            }

            // 2. Pantalla Reservas
            composable(DestinoFit.Reservas.ruta) {
                PantallaReservas(
                    reservas = reservasState,
                    onCancelarReserva = { reservaACancelar ->
                        val index = reservasState.indexOfFirst { it.id == reservaACancelar.id }
                        if (index != -1) {
                            // Actualizar estado a Cancelada
                            reservasState[index] = reservasState[index].copy(estado = "Cancelada")

                            // Devolver cupo a la clase
                            val claseIndex = clasesState.indexOfFirst { it.id == reservaACancelar.claseId }
                            if (claseIndex != -1) {
                                val claseActual = clasesState[claseIndex]
                                clasesState[claseIndex] = claseActual.copy(
                                    cuposDisponibles = (claseActual.cuposDisponibles + 1).coerceAtMost(claseActual.cuposTotales)
                                )
                            }
                        }
                    },
                    onExplorarClases = {
                        navController.navigate(DestinoFit.Inicio.ruta) {
                            popUpTo(DestinoFit.Inicio.ruta) { inclusive = true }
                        }
                    }
                )
            }

            // 3. Pantalla Rutinas
            composable(DestinoFit.Rutinas.ruta) {
                PantallaRutinas()
            }

            // 4. Pantalla Perfil
            composable(DestinoFit.Perfil.ruta) {
                PantallaPerfil(usuario = usuarioState)
            }

            // 5. Detalle de Clase
            composable(
                route = DestinoFit.DetalleClase.ruta,
                arguments = listOf(navArgument("claseId") { type = NavType.IntType })
            ) { backStackEntry ->
                val claseId = backStackEntry.arguments?.getInt("claseId") ?: 0
                val clase = clasesState.find { it.id == claseId } ?: clasesState.first()

                val yaReservada = reservasState.any { it.claseId == clase.id && it.estado == "Confirmada" }

                PantallaDetalleClase(
                    clase = clase,
                    yaReservada = yaReservada,
                    onBack = { navController.popBackStack() },
                    onReservar = {
                        val index = clasesState.indexOfFirst { it.id == clase.id }
                        if (index != -1 && clasesState[index].cuposDisponibles > 0) {
                            // Descontar cupo
                            clasesState[index] = clasesState[index].copy(
                                cuposDisponibles = clasesState[index].cuposDisponibles - 1
                            )

                            // Agregar nueva reserva
                            val nuevaReserva = Reserva(
                                id = (reservasState.maxOfOrNull { it.id } ?: 100) + 1,
                                claseId = clase.id,
                                claseNombre = clase.nombre,
                                entrenador = clase.entrenador,
                                horario = "Hoy, ${clase.hora}",
                                sala = clase.sala,
                                estado = "Confirmada"
                            )
                            reservasState.add(0, nuevaReserva)

                            // Incrementar contador del perfil
                            usuarioState = usuarioState.copy(
                                clasesTomadas = usuarioState.clasesTomadas + 1
                            )

                            // Navegar a Confirmación
                            navController.navigate(
                                DestinoFit.Confirmacion.crearRuta(
                                    claseNombre = clase.nombre,
                                    horario = "Hoy, ${clase.hora}",
                                    sala = clase.sala
                                )
                            ) {
                                popUpTo(DestinoFit.Inicio.ruta)
                            }
                        }
                    }
                )
            }

            // 6. Pantalla Confirmación
            composable(
                route = DestinoFit.Confirmacion.ruta,
                arguments = listOf(
                    navArgument("claseNombre") { type = NavType.StringType },
                    navArgument("horario") { type = NavType.StringType },
                    navArgument("sala") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val nombreDec = URLDecoder.decode(backStackEntry.arguments?.getString("claseNombre") ?: "", StandardCharsets.UTF_8.name())
                val horaDec = URLDecoder.decode(backStackEntry.arguments?.getString("horario") ?: "", StandardCharsets.UTF_8.name())
                val salaDec = URLDecoder.decode(backStackEntry.arguments?.getString("sala") ?: "", StandardCharsets.UTF_8.name())

                PantallaConfirmacion(
                    claseNombre = nombreDec,
                    horario = horaDec,
                    sala = salaDec,
                    onVerReservas = {
                        navController.navigate(DestinoFit.Reservas.ruta) {
                            popUpTo(DestinoFit.Inicio.ruta)
                        }
                    },
                    onVolverInicio = {
                        navController.navigate(DestinoFit.Inicio.ruta) {
                            popUpTo(DestinoFit.Inicio.ruta) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
