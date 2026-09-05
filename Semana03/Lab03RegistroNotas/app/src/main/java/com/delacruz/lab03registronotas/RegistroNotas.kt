package com.delacruz.lab03registronotas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun RegistroNotas() {
    val purplePrimary = Color(0xFF65558F)
    val purpleTextLight = Color(0xFF79747E)
    val purpleBadge = Color(0xFFE8DEF8)

    // Estados para las notas
    var notaFundamentos by remember { mutableFloatStateOf(0f) }
    var notaPoo by remember { mutableFloatStateOf(0f) }
    var notaMoviles by remember { mutableFloatStateOf(0f) }
    var notaBd by remember { mutableFloatStateOf(0f) }

    // Estados para Switch y Checkbox
    var redondearPromedio by remember { mutableStateOf(false) }
    var notasConfirmadas by remember { mutableStateOf(false) }

    // Estado para controlar si ya se hizo clic en CALCULAR
    var calculado by remember { mutableStateOf(false) }

    // Fondo degradado
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFCBB2FF),
            Color.White
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Barra superior
            Spacer(
                modifier = Modifier
                    .windowInsetsTopHeight(WindowInsets.statusBars)
                    .fillMaxWidth()
            )
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = purplePrimary
            ) {
                Text(
                    text = "Registro de Notas",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(20.dp)
                )
            }

            // Cuerpo principal con scroll
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Notas del ciclo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Desliza para asignar cada nota (0 a 20)",
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Sliders de Cursos
                ItemCursoSlider(
                    nombre = "Fundamentos de Programación",
                    porcentaje = "(20%)",
                    valor = notaFundamentos,
                    onValueChange = {
                        notaFundamentos = it
                        calculado = false
                    },
                    badgeColor = purpleBadge,
                    purpleText = purpleTextLight,
                    thumbColor = purplePrimary
                )

                ItemCursoSlider(
                    nombre = "Programación Orientada a Objetos",
                    porcentaje = "(25%)",
                    valor = notaPoo,
                    onValueChange = {
                        notaPoo = it
                        calculado = false
                    },
                    badgeColor = purpleBadge,
                    purpleText = purpleTextLight,
                    thumbColor = purplePrimary
                )

                ItemCursoSlider(
                    nombre = "Programación en Móviles",
                    porcentaje = "(30%)",
                    valor = notaMoviles,
                    onValueChange = {
                        notaMoviles = it
                        calculado = false
                    },
                    badgeColor = purpleBadge,
                    purpleText = purpleTextLight,
                    thumbColor = purplePrimary
                )

                ItemCursoSlider(
                    nombre = "Base de Datos",
                    porcentaje = "(25%)",
                    valor = notaBd,
                    onValueChange = {
                        notaBd = it
                        calculado = false
                    },
                    badgeColor = purpleBadge,
                    purpleText = purpleTextLight,
                    thumbColor = purplePrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Redondear promedio final",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                    Switch(
                        checked = redondearPromedio,
                        onCheckedChange = {
                            redondearPromedio = it
                            calculado = false
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = purplePrimary
                        )
                    )
                }

                // Checkbox
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = notasConfirmadas,
                        onCheckedChange = {
                            notasConfirmadas = it
                            if (!it) calculado = false
                        }
                    )
                    Text(
                        text = "Confirmo que las notas son correctas",
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botón
                Button(
                    onClick = { calculado = true },
                    enabled = notasConfirmadas,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = purplePrimary,
                        disabledContainerColor = Color(0xFFE0E0E0),
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Text(
                        text = "CALCULAR PROMEDIO",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Área de Resultados o Mensaje Previo
                if (!calculado) {
                    Text(
                        text = "Asigna las notas y confirma para calcular",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )
                } else {
                    val f = notaFundamentos.roundToInt() * 0.20f
                    val p = notaPoo.roundToInt() * 0.25f
                    val m = notaMoviles.roundToInt() * 0.30f
                    val b = notaBd.roundToInt() * 0.25f
                    val totalPonderado = f + p + m + b

                    val promedioFinalTexto = if (redondearPromedio) {
                        "${totalPonderado.roundToInt()}"
                    } else {
                        String.format(Locale.US, "%.2f", totalPonderado)
                    }

                    val promedioEvaluar = if (redondearPromedio) totalPonderado.roundToInt().toFloat() else totalPonderado

                    val (obsTexto, obsTextColor, obsBgColor) = when {
                        promedioEvaluar >= 18f -> Triple("EXCELENTE", Color(0xFF0B421A), Color(0xFFC8E6C9))
                        promedioEvaluar >= 13f -> Triple("APROBADO", Color(0xFF2E7D32), Color(0xFFE8F5E9))
                        promedioEvaluar >= 10f -> Triple("EN RECUPERACIÓN", Color(0xFFB56500), Color(0xFFFFF8E1))
                        else -> Triple("DESAPROBADO", Color(0xFFC62828), Color(0xFFFFEBEE))
                    }

                    // Tarjeta de Resultados (Dentro solo van los datos)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Promedio ponderado: ${String.format(Locale.US, "%.2f", totalPonderado)}",
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "Promedio final: $promedioFinalTexto",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = purplePrimary
                            )
                            if (redondearPromedio) {
                                Text(
                                    text = "(redondeado)",
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Surface(
                                color = obsBgColor,
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = obsTexto,
                                    color = obsTextColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mensaje fuera de la tarjeta
                    Text(
                        text = "✓ Promedio calculado correctamente",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color(0xFF2E7D32),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Desarrollado por: Noemi de la Cruz Zavala",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun ItemCursoSlider(
    nombre: String,
    porcentaje: String,
    valor: Float,
    onValueChange: (Float) -> Unit,
    badgeColor: Color,
    purpleText: Color,
    thumbColor: Color
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = porcentaje,
                    fontSize = 11.sp,
                    color = purpleText
                )
            }
            Surface(
                color = badgeColor,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "${valor.roundToInt()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)
                )
            }
        }

        Slider(
            value = valor,
            onValueChange = onValueChange,
            valueRange = 0f..20f,
            colors = SliderDefaults.colors(
                thumbColor = thumbColor,
                activeTrackColor = thumbColor,
                inactiveTrackColor = Color(0xFFE7E0EC)
            )
        )
    }
}