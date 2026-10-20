package tienda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/tienda/view/principal.fxml")), 760, 560);
        scene.getStylesheets().add(getClass().getResource("/tienda/view/estilos.css").toExternalForm());
        stage.setTitle("Tienda - Registro de productos");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}