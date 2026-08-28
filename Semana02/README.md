# Laboratorio 02 – Carrito de Compras en Kotlin

**Estudiante:** Noemi de la Cruz  
**Tienda:** TECSUP  
**Lenguaje:** Kotlin  
**Paquete:** `com.noemi.lab02carritokotlin`


## 1. Descripción del proyecto
El proyecto consiste en una aplicación de consola para gestionar un carrito de compras. Se realizó una refactorización para aplicar conceptos de Programación Orientada a Objetos, buscando mejorar la organización, reutilización y mantenimiento del código.

Se aplicaron los cuatro pilares de la POO: abstracción, encapsulamiento, herencia y polimorfismo, además del uso de interfaces y separación de responsabilidades.


## 2. Estructura implementada
* **`Vendible`**: Define el contrato de los productos.
* **`Articulo`**: Clase abstracta con características comunes.
* **`ArticuloFisico`**: Representa productos físicos y su flete.
* **`ArticuloDigital`**: Representa productos digitales.
* **`GestorCompras`**: Administra los productos del carrito.
* **`ServicioReporte`**: Genera la información de la compra.
* **`Main`**: Ejecuta y prueba el funcionamiento del sistema.


## 3. Pilares de la POO aplicados

* **Abstracción:** Mediante `Vendible` y `Articulo`.
* **Encapsulamiento:** Mediante `private val elementosSeleccionados`.
* **Herencia:** Entre `Articulo` y sus clases hijas.
* **Polimorfismo:** Permite trabajar con artículos físicos y digitales mediante el mismo tipo.


## 4. Historial de commits

| Commit | Cambio realizado |
| :---: | :--- |
| **1** | Creación de la interfaz `Vendible`. |
| **2** | Implementación de herencia con los diferentes artículos. |
| **3** | Creación de `GestorCompras` y encapsulamiento del carrito. |
| **4** | Creación de `ServicioReporte` aplicando SRP. |
| **5** | Integración del sistema en `Main.kt`. |
| **6** | Documentación del proyecto en `README.md`. |

---

## 5. Evidencia de ejecución
La aplicación fue ejecutada correctamente en consola, comprobando el registro de productos, eliminación por ID y generación del reporte de compra.

<img width="606" height="716" alt="image" src="https://github.com/user-attachments/assets/4c235405-2ee2-4a3f-92e6-aebda0df46c7" />
