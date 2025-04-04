/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javalessons.sokoban.controller;

/**
 *
 * @author mikhail
 */
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import com.javalessons.sokoban.model.*;
import com.javalessons.sokoban.view.*;

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
        // Check if the move is valid
        if (model.getCell(newY, newX) == TileType.FLOOR || model.getCell(newY, newX) ==  TileType.TARGET) { 
            // If new position is FLOOR or TARGET - we can move there freely
            model.setPlayerX(newX);
            model.setPlayerY(newY);
        // if (newX >= 0 && newX < model.getGridWidth() && newY >= 0 && newY < model.getGridHeight()) {
        //    model.setPlayerX(newX);
        //    model.setPlayerY(newY);
        } else if (model.getCell(newY, newX) == TileType.CRATE || model.getCell(newY, newX) == TileType.CRATE_ON_TARGET) {
            // If new position is CRATE or CRATE_ON_TARGET - we need to push the crate forward
            int crateNewX = newX + dx;
            int crateNewY = newY + dy;

            if (model.getCell(crateNewY,crateNewX) == TileType.FLOOR) {// || grid[crateNewY][crateNewX] == TileType.TARGET) {
                // If new position is FLOOR - we can move crate here
                // Move crate
                model.setCell(newY, newX, (model.getCell(newY, newX) == TileType.TARGET) ? TileType.TARGET : TileType.FLOOR); // Restore target if needed
                model.setCell(crateNewY, crateNewX, TileType.CRATE); // Move crate
                model.setPlayerX(newX);
                model.setPlayerY(newY);
            } else if (model.getCell(crateNewY, crateNewX) == TileType.TARGET) {
                // If new position is TARGET - we can move crate here, but we need to restore previous position 
                // Move crate
                model.setCell(newY, newX, (model.getCell(newY, newX) == TileType.CRATE_ON_TARGET) ? TileType.TARGET : TileType.FLOOR); // Restore tile behind
                model.setCell(crateNewY, crateNewX, TileType.CRATE_ON_TARGET); // Move crate on target
                model.setPlayerX(newX);
                model.setPlayerY(newY);
            }
        }
        view.drawGrid(newX, newY);
        // Update board to show new positions of moved items
    }
}