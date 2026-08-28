# Carrito de Compras en Kotlin - Refactorización POO

Este proyecto corresponde al **Laboratorio 02** desarrollado por **Noemi de la Cruz** para la tienda **TECSUP**. Se realizó una refactorización guiada desde una implementación estructurada hacia una arquitectura orientada a objetos pura utilizando **Kotlin**, aplicando los 4 pilares de la POO, encapsulamiento de estado, interfaces y separación de responsabilidades (SOLID).


## Estructura del Prompt Original y Prompts Utilizados

### Prompt Original (Inicial)
> "Actúa como una desarrolladora senior en Kotlin y mentora de POO. Necesito refactorizar un proyecto de Carrito de Compras en consola para la tienda TECSUP bajo el paquete com.noemi.lab02carritokotlin. Quiero aplicar los 4 pilares de la POO (Abstracción, Encapsulamiento, Herencia y Polimorfismo), constructor secundario, e interfaces. Guía el desarrollo paso a paso en 6 etapas para realizar commits independientes en Git."


### Prompts Secuenciales Utilizados Paso a Paso

1. **Commit 1 (Abstracción e Interfaz Base):**
   > "Comienza dándome el código y la explicación para el Commit 1 (Interfaz y Abstracción base). Recuerda los requerimientos del dominio: Paquete `com.noemi.lab02carritokotlin`, Moneda `S/`, Tienda `TECSUP`."

2. **Commit 2 (Herencia y Polimorfismo):**
   > "Ya implementé el Commit 1 e hice el push. Ahora dame el código completo, las explicaciones y las instrucciones para el Commit 2: Aplicación de Herencia y Polimorfismo con `ArticuloFisico` y `ArticuloDigital`."

3. **Commit 3 (Encapsulamiento del Carrito):**
   > "Ya subí el Commit 2. Dame el código completo, las explicaciones y las instrucciones para el Commit 3: La Clase `GestorCompras` (Encapsulamiento y Gestión del Estado con `private val elementosSeleccionados`)."

4. **Commit 4 (Separación de Responsabilidades / Servicio de Reportes):**
   > "El Commit 3 está en la rama con-ia. Dame el código completo y las instrucciones para el Commit 4: Servicio de Reportes y Presentación (`ServicioReporte`), aplicando el principio SRP."

5. **Commit 5 (Orquestación del Sistema):**
   > "Ya subí el Commit 4. Dame el código completo y las instrucciones para el Commit 5: Integración del Punto de Entrada (`Main.kt`), agregando productos físicos, digitales, eliminación por ID y uso de `ServicioReporte`."

6. **Commit 6 (Documentación y Ajustes Finales):**
   > "El Commit 5 corrió correctamente. Dame el contenido exacto en Markdown e instrucciones para el Commit 6: Documentación del Proyecto y alineación del historial de commits."


## Historial de Commits (`git log`)

A continuación se detalla el historial de los commits realizados de manera independiente en la rama `con-ia`:

| # | Commit Message | Descripción de Cambios |
|---|---|---|
| 1 | `feat(poo): crear interfaz base Vendible para abstraccion de productos` | Definición de la interfaz `Vendible` que establece el contrato con `identificador`, `denominacion` y método `obtenerImporteTotal()`. |
| 2 | `feat(poo): agregar jerarquia de herencia con Articulo, ArticuloFisico y ArticuloDigital` | Creación de la clase abstracta `Articulo` y sus subclases con recargo por flete en productos físicos e información de licencias en digitales. |
| 3 | `feat(poo): crear GestorCompras encapsulando la lista de items con lista privada` | Implementación de `GestorCompras` protegiendo la lista interna `elementosSeleccionados` mediante visibilidad `private`. |
| 4 | `feat(solid): agregar ServicioReporte aplicando SRP para separar la presentacion` | Creación de la clase `ServicioReporte` para desacoplar el formateo de consola e impresión de recibos de la lógica del carrito. |
| 5 | `fix(main): cambiar producto digital a Licencia Antivirus para alineacion con tienda de tecnologia` | Integración del flujo completo en `Main.kt` con prueba de eliminación por ID, clientes personalizados y alineación del catálogo tecnológico. |
| 6 | `docs: agregar documentacion general del proyecto en README.md` | Creación de la documentación del proyecto con trazabilidad de prompts de IA e historial de cambios. |


## Capturas de Pantalla de la Ejecución

<img width="694" height="719" alt="image" src="https://github.com/user-attachments/assets/d771dacf-1bdc-49ce-8165-59e16b30e4e1" />

