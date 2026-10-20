package tienda.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import tienda.model.*;

public class CategoriasController {
    @FXML
    private TextField nombre;
    @FXML
    private ListView<Categoria> lista;
    @FXML
    private Label estado;

    @FXML
    private void initialize() {
        lista.setItems(Datos.categorias());
    }

    @FXML
    private void agregar() {
        String texto = nombre.getText().trim();
        if (texto.isEmpty()) {
            estado.setText("Escribe el nombre de la categoria");
            return;
        }
        if (Datos.categorias().stream().anyMatch(c -> c.getNombre().equalsIgnoreCase(texto))) {
            estado.setText("Esa categoria ya existe");
            return;
        }
        Datos.categorias().add(new Categoria(texto));
        nombre.clear();
        estado.setText("Categoria agregada ya aparece en la ventana principal");
    }

    @FXML
    private void eliminar() {
        Categoria elegida = lista.getSelectionModel().getSelectedItem();
        if (elegida == null) {
            estado.setText("Selecciona una categoria");
            return;
        }
        boolean usada = Datos.productos().stream().anyMatch(p -> p.getCategoria() == elegida);
        if (usada) {
            estado.setText("No se puede eliminar tiene productos asociados");
            return;
        }
        Datos.categorias().remove(elegida);
        estado.setText("Categoria eliminada");
    }

    @FXML
    private void cerrar() {
        nombre.getScene().getWindow().hide();
    }
}