/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javalessons.sokoban;

/**
 *
 * @author mikhail
 */
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

// GameController.java (Handles movement)
public class GameController {
    private GameModel model;
    private GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void handleKeyPress(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) movePlayer(0, -1);
            if (event.getCode() == KeyCode.DOWN) movePlayer(0, 1);
            if (event.getCode() == KeyCode.LEFT) movePlayer(-1, 0);
            if (event.getCode() == KeyCode.RIGHT) movePlayer(1, 0);
            view.updatePlayerPosition(model.getPlayerX(), model.getPlayerY());
        });
    }
    
    public void movePlayer(int dx, int dy) {
        int newX = model.getPlayerX() + dx;
        int newY = model.getPlayerY() + dy;
        if (newX >= 0 && newX < model.getGridWidth() && newY >= 0 && newY < model.getGridHeight()) {
            model.setPlayerX(newX);
            model.setPlayerY(newY);
        }
    }
}