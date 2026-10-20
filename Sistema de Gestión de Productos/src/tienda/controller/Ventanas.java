package tienda.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;

public final class Ventanas {
    private Ventanas() {
    }

    public static void abrir(String archivo, String titulo, Window propietario) {
        try {
            FXMLLoader loader = new FXMLLoader(Ventanas.class.getResource("/tienda/view/" + archivo + ".fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(Ventanas.class.getResource("/tienda/view/estilos.css").toExternalForm());
            Stage ventana = new Stage();
            ventana.setTitle(titulo);
            ventana.initOwner(propietario);
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.setScene(scene);
            ventana.showAndWait();
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo abrir " + archivo, ex);
        }
    }
}