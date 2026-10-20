package tienda.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Datos {

    private static final ObservableList<Categoria> CATEGORIAS = FXCollections.observableArrayList();
    private static final ObservableList<Producto> PRODUCTOS = FXCollections.observableArrayList();

    static {
        Categoria alimentos = new Categoria("Alimentos");
        Categoria bebidas = new Categoria("Bebidas");
        Categoria limpieza = new Categoria("Limpieza");
        Categoria tecnologia = new Categoria("Tecnologia");
        CATEGORIAS.addAll(alimentos, bebidas, limpieza, tecnologia);

        PRODUCTOS.addAll(new Producto("Arroz", alimentos, 65, 20), new Producto("Jugo", bebidas, 85, 15),new Producto("Jabón", limpieza, 120, 10));
    }

    private Datos() {
    }

    public static ObservableList<Categoria> categorias() {
        return CATEGORIAS;
    }

    public static ObservableList<Producto> productos() {
        return PRODUCTOS;
    }
}