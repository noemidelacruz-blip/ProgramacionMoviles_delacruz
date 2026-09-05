# Laboratorio 03: Registro de Producto

**Estudiante:** Noemi de la Cruz  
**Curso:** Programación en Móviles  
**Rama:** mejora-ia  

---

## Descripción del Proyecto
Aplicación desarrollada en Android Studio utilizando Jetpack Compose para la gestión y registro de productos. Permite ingresar el nombre, precio y cantidad de cada artículo, calculando de manera dinámica el importe total. Cuenta con validaciones de campos de entrada, manejo de estado mediante listas acumulativas (`LazyColumn`), botones de acción para agregar o limpiar el formulario y un diseño estructurado con componentes de Material Design 3.

---

## Mejora con IA

| Prompt que usé | Qué generó Gemini | Qué acepté o corregí (y por qué) |
| :--- | :--- | :--- |
| "Agrega validación de campos vacíos en PantallaRegistro y un botón Limpiar para reiniciar el formulario en Jetpack Compose." | Código con la lógica de `isBlank()` para desplegar un mensaje de error y un botón Limpiar que borraba tanto los campos como el estado del producto registrado. | **Acepté:** Las validaciones de entradas vacías y el diseño del botón Limpiar.<br>**Corregí:** Implementé una lista acumulativa (`LazyColumn`) para conservar los productos registrados al presionar Limpiar, corregí el flujo para que solo se borren las cajas de texto y agregué el botón Eliminar en cada tarjeta. |

---

## Capturas de Pantalla

| Formulario / Validación de Error | Productos Registrados en Lista |
| :---: | :---: |
|<img width="714" height="1599" alt="image" src="https://github.com/user-attachments/assets/d619005d-454e-494e-b4f9-859c5d4ef5eb" />
 | <img width="714" height="1599" alt="image" src="https://github.com/user-attachments/assets/3126cf7d-58af-4279-a6ca-693153598fb5" />
 |<img width="720" height="1612" alt="image" src="https://github.com/user-attachments/assets/7ed3352c-52a4-4ae9-870b-12018960e0ed" />
