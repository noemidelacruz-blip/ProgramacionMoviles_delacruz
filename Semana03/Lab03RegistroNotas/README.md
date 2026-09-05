##REGISTRO DE NOTAS

**Estudiante:** Noemi de la Cruz Zavala  
**Curso:** Desarrollo de Aplicaciones Móviles  
**Plataforma:** Android (Kotlin + Jetpack Compose)  

## Descripcion del Proyecto

Aplicacion movil desarrollada en Android Studio utilizando Jetpack Compose. Permite calcular el promedio ponderado del ciclo a partir de las notas asignadas mediante deslizadores (Slider), evaluar observaciones de rendimiento mediante la estructura when de Kotlin y personalizar visualmente la interfaz aplicando estados de recomposicion en tiempo real y componentes de Material Design 3.

## Requisitos Evaluables Cumplidos

De acuerdo con las especificaciones del laboratorio, se implementaron todos los puntos requeridos:

1. Recomposicion en Vivo:
   * Cada Slider actualiza de forma instantanea su badge correspondiente con valores enteros en el rango de 0 a 20.
   * El boton CALCULAR PROMEDIO cambia su estado visual de inactivo (gris) a activo (morado) unicamente al marcar el Checkbox de confirmacion.

2. Reglas de Negocio y Logica con when:
   * Evaluacion de la observacion final basada en los rangos oficiales mediante la estructura when:
     * Mayor o igual a 17.00: EXCELENTE (Texto verde oscuro / Fondo verde claro)
     * Mayor o igual a 13.00: APROBADO (Texto verde / Fondo verde suave)
     * Mayor o igual a 10.00: EN RECUPERACION (Texto ambar / Fondo amarillo claro)
     * Menor a 10.00: DESAPROBADO (Texto rojo / Fondo rojo suave)

3. Diseno y Legibilidad:
   * Fondo con degradado vertical suave (Brush.verticalGradient).
   * Tarjetas blancas (Card) y contenedores con elevacion y sombras ligeras para garantizar contraste y legibilidad sobre el degradado.

4. Validacion de Casos de Prueba:
   * La aplicacion arroja los valores numericos y las observaciones exactas indicadas en la tabla de evaluacion.


## Retos Opcionales Implementados

Se incorporaron las siguientes mejoras opcionales:

- Semaforo en Badges de Notas: Indicador de color dinamico en la nota de cada asignatura (Verde si es mayor o igual a 13 / Rojo si es menor a 13).
- Desglose de Aportes Ponderados: Detalle matematico del puntaje individual aportado por cada curso al promedio total (20%, 25%, 30% y 25%).
- Boton Limpiar Campos: Opcion para restablecer todos los Sliders, Switch, Checkbox y tarjeta de resultados a sus estados iniciales.

## Tabla de Casos de Prueba

| Caso | Fundamentos (20%) | POO (25%) | Moviles (30%) | BD (25%) | Redondeo | Prom. Ponderado | Prom. Final | Observacion |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 15 | 14 | 16 | 13 | No | 14.75 | 14.75 | APROBADO |
| 2 | 08 | 10 | 09 | 11 | No | 9.55 | 9.55 | DESAPROBADO |
| 3 | 18 | 17 | 19 | 18 | Si | 18.05 | 18 | EXCELENTE |
| 4 | 11 | 12 | 10 | 11 | No | 10.95 | 10.95 | EN RECUPERACION |

##Capturas
<img width="714" height="1599" alt="image" src="https://github.com/user-attachments/assets/8f58cc48-0ca7-46ea-8522-7624599e479e" />
<img width="714" height="1599" alt="image" src="https://github.com/user-attachments/assets/3bcbbcd4-14c0-4cd9-a38f-9d46c5e11f2c" />
<img width="714" height="1599" alt="image" src="https://github.com/user-attachments/assets/8b05326f-c581-420b-b262-aa87fb55b679" />

