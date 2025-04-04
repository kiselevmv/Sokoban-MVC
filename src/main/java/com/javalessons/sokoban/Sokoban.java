package com.javalessons.sokoban;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

enum TileType {
        FLOOR, WALL, PLAYER, CRATE, TARGET, CRATE_ON_TARGET
    }

public class Sokoban extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameModel model = new GameModel();
        TileType[][] levelMap = model.loadLevel();
        GameView view = new GameView(model.getGridWidth(), model.getGridHeight(), levelMap);
        GameController controller = new GameController(model, view);

        StackPane root = new StackPane(view.getGrid());
        Scene scene = new Scene(root, view.GridWidth(), view.GridHeight());
        
        controller.handleKeyPress(scene);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Sokoban");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}