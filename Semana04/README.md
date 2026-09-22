# Laboratorio 04: Carrito de Compras en Jetpack Compose

Proyecto correspondiente al Laboratorio 04 de desarrollo de aplicaciones móviles utilizando Jetpack Compose y Material Design 3 (TECSUP).

---

## Respuesta a la Pregunta del Laboratorio

### ¿Por qué la lista se declara con val y aún así podemos agregarle elementos?

La variable `listaProductos` se declara con la palabra clave `val` debido a que la referencia del objeto en memoria permanece inmutable; es decir, no se puede reasignar la variable a una nueva instancia mediante el operador de asignación (`=`).

Sin embargo, el objeto instanciado mediante `remember { mutableStateListOf(...) }` pertenece a la clase `SnapshotStateList`. Esta estructura de datos posee un estado interno mutable y observable. Esto permite invocar métodos para modificar sus elementos (`add()`, `remove()`, entre otros) mientras notifica de manera automática a Jetpack Compose para desencadenar el proceso de recomposición de la interfaz gráfica en tiempo real.

---

## Funcionalidades Implementadas

- **Componente Reutilizable (`TarjetaProducto`)**: Tarjeta independiente que gestiona la visualización de datos de cada producto y delega el evento de eliminación.
- **Formulario de Registro**: Campos de entrada de texto (`OutlinedTextField`) para añadir nuevos productos a la lista reactiva.
- **Estado Vacío (`Box`)**: Componente que despliega un mensaje centralizado cuando la lista no contiene elementos.
- **Panel de Totales**: Cálculo en tiempo real de Subtotal, IGV (18%) y TOTAL con formato numérico de dos decimales.

## Retos Opcionales Desarrollados

1. **Confirmación de Borrado**: Implementación de un `AlertDialog` para requerir confirmación del usuario antes de eliminar un producto.
2. **Descuento Dinámico (`when`)**: Aplicación de reglas de negocio para descuentos condicionales (5% para subtotales mayores a S/ 3,000.00 y 10% para subtotales mayores a S/ 5,000.00).

---

## Capturas de Pantalla de la Aplicación

## Capturas de Pantalla de la Aplicación

| Captura 1: Interfaz Principal | Captura 2: Confirmación de Borrado |
| :---: | :---: |
|<img width="320" alt="image" src="https://github.com/user-attachments/assets/03c20f4b-77d2-4a08-bc6c-88dd82bab1f0" /> | <img width="320" alt="image" src="https://github.com/user-attachments/assets/e6297907-5b7f-429b-a688-04db5b6875ee" /> |

--- 
## Tecnologías Utilizadas

- **Lenguaje de Programación**: Kotlin
- **Framework de UI**: Jetpack Compose
- **Sistema de Diseño**: Material Design 3
- **Control de Versiones**: Git
