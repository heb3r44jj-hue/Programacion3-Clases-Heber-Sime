import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main extends Application {

    ObservableList<RegistroProducto> productos = FXCollections.observableArrayList();
    TableView<RegistroProducto> tabla = new TableView<>();

    TextField txtCodigo = new TextField();
    TextField txtNombre = new TextField();
    TextField txtPrecio = new TextField();
    TextField txtCantidad = new TextField();

    TextField txtCodigoVenta = new TextField();
    TextField txtCantidadVenta = new TextField();

    @Override
    public void start(Stage stage) {
        try {
            productos.addAll(Archivos.cargarProductos());
        } catch (IOException e) {
            mostrarError("No se pudo leer el archivo productos.txt");
        }

        TableColumn<RegistroProducto, String> colCodigo = new TableColumn<>("Codigo");
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colCodigo.setPrefWidth(90);

        TableColumn<RegistroProducto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(180);

        TableColumn<RegistroProducto, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPrecio.setPrefWidth(100);

        TableColumn<RegistroProducto, Integer> colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colCantidad.setPrefWidth(90);

        TableColumn<RegistroProducto, Double> colTotal = new TableColumn<>("Valor total");
        colTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colTotal.setPrefWidth(120);

        tabla.getColumns().add(colCodigo);
        tabla.getColumns().add(colNombre);
        tabla.getColumns().add(colPrecio);
        tabla.getColumns().add(colCantidad);
        tabla.getColumns().add(colTotal);
        tabla.setItems(productos);

        tabla.getSelectionModel().selectedItemProperty().addListener((obs, anterior, nuevo) -> {
            if (nuevo != null) {
                txtCodigoVenta.setText(nuevo.getCodigo());
            }
        });

        GridPane formProducto = new GridPane();
        formProducto.setHgap(8);
        formProducto.setVgap(8);
        formProducto.add(new Label("Codigo:"), 0, 0);
        formProducto.add(txtCodigo, 1, 0);
        formProducto.add(new Label("Nombre:"), 0, 1);
        formProducto.add(txtNombre, 1, 1);
        formProducto.add(new Label("Precio:"), 0, 2);
        formProducto.add(txtPrecio, 1, 2);
        formProducto.add(new Label("Cantidad:"), 0, 3);
        formProducto.add(txtCantidad, 1, 3);

        Button btnRegistrar = new Button("Registrar producto");
        btnRegistrar.setMaxWidth(Double.MAX_VALUE);
        btnRegistrar.setOnAction(e -> registrarProducto());

        GridPane formVenta = new GridPane();
        formVenta.setHgap(8);
        formVenta.setVgap(8);
        formVenta.add(new Label("Codigo:"), 0, 0);
        formVenta.add(txtCodigoVenta, 1, 0);
        formVenta.add(new Label("Cantidad:"), 0, 1);
        formVenta.add(txtCantidadVenta, 1, 1);

        Button btnVender = new Button("Vender");
        btnVender.setMaxWidth(Double.MAX_VALUE);
        btnVender.setOnAction(e -> vender());

        Button btnVerVentas = new Button("Ver ventas");
        btnVerVentas.setMaxWidth(Double.MAX_VALUE);
        btnVerVentas.setOnAction(e -> verVentas());

        Label tituloProducto = new Label("Registro de productos");
        tituloProducto.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        Label tituloVenta = new Label("Registro de ventas");
        tituloVenta.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        Label ayuda = new Label("Seleccione un producto en la tabla");

        VBox panelIzquierdo = new VBox(10);
        panelIzquierdo.setPadding(new Insets(10));
        panelIzquierdo.setPrefWidth(280);
        panelIzquierdo.getChildren().add(tituloProducto);
        panelIzquierdo.getChildren().add(formProducto);
        panelIzquierdo.getChildren().add(btnRegistrar);
        panelIzquierdo.getChildren().add(tituloVenta);
        panelIzquierdo.getChildren().add(ayuda);
        panelIzquierdo.getChildren().add(formVenta);
        panelIzquierdo.getChildren().add(btnVender);
        panelIzquierdo.getChildren().add(btnVerVentas);

        BorderPane raiz = new BorderPane();
        raiz.setLeft(panelIzquierdo);
        raiz.setCenter(tabla);
        BorderPane.setMargin(tabla, new Insets(10));

        Scene escena = new Scene(raiz, 900, 480);
        stage.setTitle("Inventario y ventas");
        stage.setScene(escena);
        stage.show();
    }

    public void registrarProducto() {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty()) {
            mostrarError("Debe escribir el codigo y el nombre del producto");
            return;
        }
        if (codigo.contains(";") || nombre.contains(";")) {
            mostrarError("El codigo y el nombre no pueden contener punto y coma");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("El precio debe ser un numero valido");
            return;
        }
        if (precio <= 0) {
            mostrarError("El precio debe ser mayor que cero");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidad.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("La cantidad debe ser un numero entero");
            return;
        }
        if (cantidad < 0) {
            mostrarError("La cantidad no puede ser negativa");
            return;
        }

        if (buscarRegistroProducto(codigo) != null) {
            mostrarError("Ya existe un producto con el codigo " + codigo);
            return;
        }

        RegistroProducto nuevo = new RegistroProducto(nombre, precio, cantidad, codigo);
        productos.add(nuevo);
        try {
            Archivos.guardarProductos(productos);
        } catch (IOException e) {
            productos.remove(nuevo);
            mostrarError("No se pudo guardar en productos.txt");
            return;
        }

        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();
        mostrarInfo("Producto registrado correctamente");
    }

    public void vender() {
        String codigo = txtCodigoVenta.getText().trim();
        if (codigo.isEmpty()) {
            mostrarError("Seleccione un producto o escriba su codigo");
            return;
        }

        RegistroProducto p = buscarRegistroProducto(codigo);
        if (p == null) {
            mostrarError("El producto con codigo " + codigo + " no existe");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidadVenta.getText().trim());
        } catch (NumberFormatException e) {
            mostrarError("La cantidad a vender debe ser un numero entero");
            return;
        }
        if (cantidad <= 0) {
            mostrarError("La cantidad a vender debe ser mayor que cero");
            return;
        }
        if (cantidad > p.getCantidad()) {
            mostrarError("Inventario insuficiente. Solo hay " + p.getCantidad() + " unidades disponibles");
            return;
        }

        double total = redondear(cantidad * p.getPrecio());
        int cantidadAnterior = p.getCantidad();
        p.setCantidad(cantidadAnterior - cantidad);

        try {
            Archivos.guardarProductos(productos);
        } catch (IOException e) {
            p.setCantidad(cantidadAnterior);
            mostrarError("No se pudo actualizar productos.txt. La venta no se realizo");
            return;
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fecha = LocalDateTime.now().format(formato);
        String linea = fecha + ";" + p.getCodigo() + ";" + p.getNombre() + ";" + cantidad + ";" + p.getPrecio() + ";" + total;

        try {
            Archivos.registrarVenta(linea);
        } catch (IOException e) {
            p.setCantidad(cantidadAnterior);
            try {
                Archivos.guardarProductos(productos);
            } catch (IOException e2) {
            }
            mostrarError("No se pudo guardar en ventas.txt. La venta no se realizo");
            return;
        }

        tabla.refresh();
        txtCantidadVenta.clear();
        mostrarInfo("Venta realizada. Total a cobrar: RD$ " + total);
    }

    public void verVentas() {
        ArrayList<String> lineas;
        try {
            lineas = Archivos.leerVentas();
        } catch (IOException e) {
            mostrarError("No se pudo leer el archivo ventas.txt");
            return;
        }

        StringBuilder texto = new StringBuilder();
        double totalGeneral = 0;
        int cantidadVentas = 0;

        for (int i = 0; i < lineas.size(); i++) {
            String[] datos = lineas.get(i).split(";");
            if (datos.length == 6) {
                try {
                    double totalVenta = Double.parseDouble(datos[5]);
                    totalGeneral = totalGeneral + totalVenta;
                    cantidadVentas++;
                    texto.append(datos[0]).append("  |  ").append(datos[1]).append(" - ").append(datos[2]).append("  |  ").append("Cant: ").append(datos[3]).append("  |  ").append("Precio: ").append(datos[4]).append("  |  ").append("Total: RD$ ").append(datos[5]).append("\n");
                } catch (NumberFormatException e) {
                }
            }
        }

        if (cantidadVentas == 0) {
            mostrarInfo("No hay ventas registradas");
            return;
        }

        TextArea area = new TextArea(texto.toString());
        area.setEditable(false);
        area.setPrefSize(650, 300);
        area.setStyle("-fx-font-family: monospace;");

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Ventas realizadas");
        alerta.setHeaderText("Ventas: " + cantidadVentas + "    Total generado: RD$ " + redondear(totalGeneral));
        alerta.getDialogPane().setContent(area);
        alerta.showAndWait();
    }

    public RegistroProducto buscarRegistroProducto(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return productos.get(i);
            }
        }
        return null;
    }

    public double redondear(double valor) {
        return Math.round(valor * 100) / 100.0;
    }

    public void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void mostrarInfo(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Informacion");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}