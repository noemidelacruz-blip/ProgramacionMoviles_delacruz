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
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun RegistroNotas() {
    val purplePrimary = Color(0xFF65558F)
    val purpleTextLight = Color(0xFF79747E)
    val purpleBadge = Color(0xFFE8DEF8)

    // Estados para las notas de cada curso (0 a 20)
    var notaFundamentos by remember { mutableFloatStateOf(0f) }
    var notaPoo by remember { mutableFloatStateOf(0f) }
    var notaMoviles by remember { mutableFloatStateOf(0f) }
    var notaBd by remember { mutableFloatStateOf(0f) }

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
            // Barra superior morada con tu estructura original
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
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp)
                )
            }

            // Cuerpo principal con scroll
            Column(
                modifier = Modifier
                    .fillMaxSize()
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

                // Curso 1
                ItemCursoSlider(
                    nombre = "Fundamentos de Programación",
                    porcentaje = "(20%)",
                    valor = notaFundamentos,
                    onValueChange = { notaFundamentos = it },
                    badgeColor = purpleBadge,
                    purpleText = purpleTextLight,
                    thumbColor = purplePrimary
                )

                // Curso 2
                ItemCursoSlider(
                    nombre = "Programación Orientada a Objetos",
                    porcentaje = "(25%)",
                    valor = notaPoo,
                    onValueChange = { notaPoo = it },
                    badgeColor = purpleBadge,
                    purpleText = purpleTextLight,
                    thumbColor = purplePrimary
                )

                // Curso 3
                ItemCursoSlider(
                    nombre = "Programación en Móviles",
                    porcentaje = "(30%)",
                    valor = notaMoviles,
                    onValueChange = { notaMoviles = it },
                    badgeColor = purpleBadge,
                    purpleText = purpleTextLight,
                    thumbColor = purplePrimary
                )

                // Curso 4
                ItemCursoSlider(
                    nombre = "Base de Datos",
                    porcentaje = "(25%)",
                    valor = notaBd,
                    onValueChange = { notaBd = it },
                    badgeColor = purpleBadge,
                    purpleText = purpleTextLight,
                    thumbColor = purplePrimary
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
            // Badge para la nota
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