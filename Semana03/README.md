# Laboratorio 03: Registro de Producto

**Estudiante:** Noemi de la Cruz
**Curso:** Programación en Móviles

## Descripción
Aplicación en Jetpack Compose para el registro de productos con cálculo de importe automático y manejo de estado.

## Capturas de Pantalla

<img width="720" height="1612" alt="image" src="https://github.com/user-attachments/assets/7333d7a3-ef2c-4aa9-8757-74015d972a7b" />
<img width="720" height="1612" alt="image" src="https://github.com/user-attachments/assets/5d27dbbe-f591-44f8-9dc3-4753e0cb3928" />

## Pregunta de Reflexión
**¿Qué pasaría si declaras las variables de los campos SIN remember?**
Si se declarasen las variables sin usar `remember`, la app no conservaría el estado durante las recomposiciones de la interfaz. Cada vez que el usuario teclee un carácter, Compose detectará el cambio y redibujará la pantalla, provocando que las variables se reinicien a su valor inicial. En consecuencia, las cajas de texto parecerán bloqueadas y no se podrá escribir en ellas.
