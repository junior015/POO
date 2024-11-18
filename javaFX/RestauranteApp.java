package javaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RestauranteApp extends Application {

   
    private final List<String> carrinho = new ArrayList<>();

    @Override
    public void start(Stage stage) {
       
        stage.setTitle("Restaurantes");

        
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        
        Label title = new Label("Restaurantes");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        
        Button carrinhoButton = new Button("Carrinho");
        carrinhoButton.setStyle("-fx-font-size: 14px;");
        carrinhoButton.setOnAction(e -> abrirCarrinho(stage));

        HBox carrinhoBox = new HBox(carrinhoButton);
        carrinhoBox.setStyle("-fx-alignment: top-right;");

       
        GridPane grid = new GridPane();
        grid.setVgap(15);
        grid.setHgap(20);

       
        addRestaurante(grid, "Brasileira", "Retira no Local", "Sabor Tropical", 0);
        addRestaurante(grid, "Japonesa", "Retira no Local", "Sushi Zen", 1);
        addRestaurante(grid, "Mexicana", "Retira no Local", "El Taco Loco", 2);
        addRestaurante(grid, "Francesa", "Não Retirar no Local", "La Baguette", 3);

        
        root.getChildren().addAll(carrinhoBox, title, grid);

        
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void addRestaurante(GridPane grid, String tipo, String retirada, String nome, int row) {
        Label tipoLabel = new Label(tipo);
        Label retiradaLabel = new Label(retirada);
        Label nomeLabel = new Label(nome);
        Button verMaisButton = new Button("Adicionar ao Carrinho");

        
        verMaisButton.setOnAction(e -> {
            carrinho.add(nome);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, nome + " foi adicionado ao carrinho!", ButtonType.OK);
            alert.showAndWait();
        });

        VBox infoBox = new VBox(5, tipoLabel, retiradaLabel);
        VBox nomeBox = new VBox(5, nomeLabel, verMaisButton);

        grid.add(infoBox, 0, row);
        grid.add(nomeBox, 1, row);
    }

    private void abrirCarrinho(Stage parentStage) {
        
        Stage carrinhoStage = new Stage();
        carrinhoStage.setTitle("Carrinho");
        carrinhoStage.initModality(Modality.WINDOW_MODAL);
        carrinhoStage.initOwner(parentStage);

        
        VBox carrinhoRoot = new VBox(10);
        carrinhoRoot.setPadding(new Insets(20));

        Label carrinhoTitle = new Label("Itens no Carrinho:");
        carrinhoTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ListView<String> carrinhoList = new ListView<>();
        carrinhoList.getItems().addAll(carrinho);

       
        Button removerButton = new Button("Remover Item");
        removerButton.setOnAction(e -> {
            String selectedItem = carrinhoList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                carrinho.remove(selectedItem);
                carrinhoList.getItems().remove(selectedItem);
            }
        });

        
        Button confirmarButton = new Button("Confirmar Compra");
        confirmarButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Compra confirmada!", ButtonType.OK);
            alert.showAndWait();
            carrinho.clear();
            carrinhoList.getItems().clear();
        });

        
        Button fecharButton = new Button("Fechar");
        fecharButton.setOnAction(e -> carrinhoStage.close());

        
        HBox buttonBox = new HBox(10, removerButton, confirmarButton, fecharButton);
        buttonBox.setStyle("-fx-alignment: center;");

        carrinhoRoot.getChildren().addAll(carrinhoTitle, carrinhoList, buttonBox);

        Scene carrinhoScene = new Scene(carrinhoRoot, 300, 300);
        carrinhoStage.setScene(carrinhoScene);
        carrinhoStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
