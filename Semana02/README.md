# Laboratorio 02: Carrito de Compras en Kotlin

**Estudiante:** Noemi De La Cruz Zavala
**Curso:** Programación Móvil  

## Descripción del Proyecto

Este programa simula el funcionamiento de un carrito de compras para una tienda en línea utilizando Kotlin. Permite gestionar una lista de productos, calcular automáticamente los totales del pedido con impuestos y aplicar descuentos según el monto acumulado.

### Funciones Implementadas
* **`mostrarDetalle(productos)`**: Imprime en consola la lista de productos agregados en formato de tabla con columnas alineadas (número, nombre, cantidad e importe).
* **`calcularSubtotal(productos)`**: Suma el valor total de la compra multiplicando el precio de cada producto por su cantidad.
* **`calcularIGV(subtotal)`**: Calcula el 18% correspondiente al Impuesto General a las Ventas sobre el subtotal.
* **`calcularTotal(subtotal, igv)`**: Retorna la suma del subtotal y el IGV.
* **`calcularDescuento(total)`**: Aplica un descuento progresivo (10% si supera S/ 5000 y 5% si supera S/ 3000) utilizando la estructura `when`.
* **Identificación del producto más caro**: Utiliza la función de colecciones `maxByOrNull` para ubicar el artículo con mayor precio unitario.


## Captura del resultado en la consola
<img width="528" height="621" alt="image" src="https://github.com/user-attachments/assets/df97735d-5422-46ad-81ff-674fd6de4b95" />

# Respuesta Parte 2: Modelo de datos 

### ¿Por qué nombre y precio son val pero cantidad es var?
nombre y precio son propiedades inmutables (`val`) porque son datos básicos del producto y no deberían cambiar durante la compra. En cambio, cantidad es mutable (`var`) porque el cliente puede aumentarla o disminuirla según las unidades que necesite.

### ¿Qué pasaría si intentas cambiar el precio después de crear el producto?
Kotlin mostrará un error de compilación (`Val cannot be reassigned`), porque una variable declarada con `val` no puede cambiar su valor después de haber sido asignado. Por eso, el programa no podrá ejecutarse hasta corregir esa propiedad.
