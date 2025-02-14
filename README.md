# 🛒 Proyecto de Compra de Frutas

Este es un proyecto en **Java Swing** que permite comprar frutas, agregarlas a un carrito y realizar el pago con un sistema de euros y céntimos.

## 📌 Características
- Selección de frutas (**Manzana, Plátano, Naranja**) mediante **JRadioButton**.
- Cálculo automático del **precio total**.
- Uso de **JList** para administrar el carrito de compras.
- Eliminación de productos específicos del carrito.
- Simulación de pago con una combinación de **JComboBox** y **JSpinner**.
- Cálculo del cambio a devolver.
- Botón de "Nueva Compra" para reiniciar la compra.

## 🚀 Instalación y Ejecución
1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/compra-frutas.git
   ```
2. Abre el proyecto en **IntelliJ IDEA, NetBeans o Eclipse**.
3. Compila y ejecuta `Compra.java`.

## 🖥️ Captura de Pantalla
![Interfaz](screenshot.png)

## 📜 Código Principal
```java
private void agregarAlCarrito() {
    String fruta = obtenerFrutaSeleccionada();
    if (fruta == null) return;
    
    double precio = obtenerPrecioFruta(fruta);
    carritoModel.addElement(fruta);
    total += precio;
    actualizarTotal();
}
```

## 🛠️ Tecnologías Utilizadas
- **Java 8+**
- **Swing (GUI Builder en IntelliJ)**
- **JList, JComboBox, JSpinner, JTextField**

## 📄 Licencia
Este proyecto está bajo la licencia MIT. ¡Úsalo y modifícalo libremente! 🎉
