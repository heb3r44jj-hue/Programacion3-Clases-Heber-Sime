package tienda.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.transformation.FilteredList;
import tienda.model.*;

public class ConsultaController {
    @FXML
    private TableView<Producto> tabla;
    @FXML
    private TableColumn<Producto, String> productoCol, categoriaCol, precioCol;
    @FXML
    private TableColumn<Producto, Integer> cantidadCol;
    @FXML
    private ComboBox<Categoria> filtroCategoria;
    @FXML
    private Label total;
    @FXML
    private Label estado;
    private FilteredList<Producto> filtrados;

    @FXML
    private void initialize() {
        productoCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getNombre()));
        categoriaCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getCategoriaNombre()));
        precioCol.setCellValueFactory(p -> new ReadOnlyStringWrapper(String.format("RD$%.2f", p.getValue().getPrecio())));
        cantidadCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getCantidad()));

        filtrados = new FilteredList<>(Datos.productos(), p -> true);
        tabla.setItems(filtrados);

        filtroCategoria.setItems(Datos.categorias());
        filtroCategoria.valueProperty().addListener((obs, antes, ahora) -> filtrar());

        actualizarTotal();
        Datos.productos().addListener((javafx.collections.ListChangeListener<Producto>) cambio -> actualizarTotal());
    }

    @FXML
    private void filtrar() {
        Categoria elegida = filtroCategoria.getValue();
        filtrados.setPredicate(p -> elegida == null || p.getCategoria() == elegida);
    }

    @FXML
    private void limpiarFiltro() {
        filtroCategoria.setValue(null);
    }

    @FXML
    private void eliminar() {
        Producto elegido = tabla.getSelectionModel().getSelectedItem();
        if (elegido == null) {
            estado.setText("Selecciona un producto");
            return;
        }
        Datos.productos().remove(elegido);
        estado.setText("Producto eliminado");
    }

    @FXML
    private void cerrar() {
        tabla.getScene().getWindow().hide();
    }

    private void actualizarTotal() {
        double suma = 0;
        for (Producto p : Datos.productos()) {
            suma = suma + p.getValor();
        }
        total.setText(String.format("Valor total del inventario: RD$%.2f", suma));
    }
}