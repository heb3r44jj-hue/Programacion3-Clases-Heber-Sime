import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;



public class App extends javafx.application.Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label titulo = new Label("evaluar estudiante");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; #410CF0; -fx-padding: 10px;");

        TextField campoNombre = new TextField();
        campoNombre.setPromptText("Ingrese el nombre");

        TextField campoNota = new TextField();
        campoNota.setPromptText("Ingrese la nota");

        Button evaluar = new Button("Evaluar");
        evaluar.setStyle("-fx-background-color: #410CF0; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultado = new Label();
        resultado.setMaxWidth(Double.MAX_VALUE);
        resultado.setAlignment(Pos.CENTER);
        resultado.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px;");

        evaluar.setOnAction(e ->{
            String nombre = campoNombre.getText().trim();

            try{
                int nota = Integer.parseInt(campoNota.getText().trim());
                
                if(nota >= 70 && nota <= 100) {
                    resultado.setText(nombre + " aprobado con: " + nota);
                    resultado.setStyle("-fx-text-fill: green; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px;");
                }else if(nota >= 65 && nota <= 69){
                    resultado.setText(nombre + " completivo con: " + nota);
                    resultado.setStyle("-fx-text-fill: orange; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px;");
                }else{
                    resultado.setText(nombre + " se quemo con: " + nota);
                    resultado.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px;");
                }

             }catch(NumberFormatException ex) {
                resultado.setText("Ingrese una nota valida ");
                resultado.setStyle("-fx-text-fill: gray; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px;");
            }

        });

        VBox root = new VBox(12);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(titulo,campoNombre,campoNota,evaluar,resultado);

        Scene scene = new Scene(root,400,350);
        stage.setTitle("nota");
        stage.setScene(scene);
        stage.show();
        
    }
}

    

