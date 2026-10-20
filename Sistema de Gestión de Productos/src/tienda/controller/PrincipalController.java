package tienda.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import tienda.model.*;

public class PrincipalController {
    @FXML
    private TextField nombre;
    @FXML
    private ComboBox<Categoria> categoria;
    @FXML
    private TextField precio;
    @FXML
    private TextField cantidad;
    @FXML
    private Label estado;
    @FXML
    private Label contador;

    @FXML
    private void initialize() {

        categoria.setItems(Datos.categorias());

        contador.setText("Productos registrados: " + Datos.productos().size());
        Datos.productos().addListener((javafx.collections.ListChangeListener<Producto>) cambio -> contador.setText("Productos registrados: " + Datos.productos().size()));
    }

    @FXML
    private void guardar() {
        String texto = nombre.getText().trim();
        if (texto.isEmpty()) {
            estado.setText("El nombre del producto no puede estar vacio");
            return;
        }
        if (categoria.getValue() == null) {
            estado.setText("Selecciona una categoria");
            return;
        }

        double valorPrecio;
        try {
            valorPrecio = Double.parseDouble(precio.getText().trim());
        } catch (NumberFormatException e) {
            estado.setText("El precio debe ser un número (ej. 65.50)");
            return;
        }
        if (valorPrecio <= 0) {
            estado.setText("El precio debe ser mayor que cero");
            return;
        }

        int valorCantidad;
        try {
            valorCantidad = Integer.parseInt(cantidad.getText().trim());
        } catch (NumberFormatException e) {
            estado.setText("La cantidad debe ser un número entero (ej. 20)");
            return;
        }
        if (valorCantidad < 0) {
            estado.setText("La cantidad no puede ser negativa");
            return;
        }

        Datos.productos().add(new Producto(texto, categoria.getValue(), valorPrecio, valorCantidad));
        nombre.clear();
        precio.clear();
        cantidad.clear();
        estado.setText("Producto guardado correctamente");
    }

    @FXML
    private void abrirCategorias() {
        Categoria elegida = categoria.getValue();
        Ventanas.abrir("categorias", "Administrar categorias", nombre.getScene().getWindow());
        if (elegida != null && !Datos.categorias().contains(elegida)) {
            categoria.setValue(null);
        }
    }

    @FXML
    private void abrirConsulta() {
        Ventanas.abrir("consulta", "Consultar productos", nombre.getScene().getWindow());
    }
}