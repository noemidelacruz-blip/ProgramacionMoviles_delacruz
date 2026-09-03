# Laboratorio 02 – Sistema de Tarifas de Estacionamiento en Kotlin

**Estudiante:** Noemi de la Cruz  
**Tienda/Institución:** TECSUP  
**Lenguaje:** Kotlin  


## 1. Descripción del proyecto

El proyecto consiste en una aplicación de consola para gestionar y calcular el cobro de un estacionamiento según el tipo de vehículo (moto, auto, camioneta) y la cantidad de horas de permanencia.

El programa procesa las tarifas base por hora, aplica recargos progresivos según el tiempo transcurrido (a partir de la 3ra hora) y otorga un descuento especial del 10% para clientes frecuentes. Al finalizar, muestra un reporte detallado por vehículo y un resumen global del día.


## 2. Estructura implementada

* **DetalleHora:** Data class que almacena el desglose hora por hora (tarifa base, recargo e importe).
* **VehiculoProcesado:** Data class que agrupa toda la información calculada del vehículo y su cliente.
* **calcularVehiculo():** Función principal encargada de aplicar las reglas de negocio, recargos progresivos y descuentos.
* **main():** Función ejecutable que interactúa con el usuario mediante consola, solicita los datos y muestra los reportes.


## 3. Prompts de IA Utilizados

### Prompt 1: Creación de la Estructura Base y Data Classes
**Prompt:**  
*"Actúa como un desarrollador experto en Kotlin. Ayúdame a estructurar la data class `VehiculoProcesado` y la función `calcularVehiculo` aplicando condicionales `when` y bucles para calcular las tarifas de estacionamiento."*

**Resultado:**  
Generación de las estructuras de datos y la lógica para el cálculo dinámico de tarifas según el tipo de vehículo.


### Prompt 2: Lógica de Recargos y Reporte Final
**Prompt:**  
*"Genera el código en Kotlin para aplicar recargos según el número de horas en un estacionamiento (0% en las primeras 2h, 20% de la 3ra a 5ta h, y 50% de la 6ta en adelante) y formatear el reporte final por consola."*

**Resultado:**  
Implementación del cálculo de recargos por hora y la presentación en consola del detalle individual y el resumen del día.


## 4. Capturas de Ejecución-Resultados

<img width="391" height="882" alt="image" src="https://github.com/user-attachments/assets/ec014457-8d57-4728-991d-f3a86dd40224" />
<img width="396" height="839" alt="image" src="https://github.com/user-attachments/assets/c3302a80-efd4-4228-a0e4-ffe062c9ccf4" />
