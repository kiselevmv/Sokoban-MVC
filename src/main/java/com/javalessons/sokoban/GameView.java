/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javalessons.sokoban;

/**
 *
 * @author mikhail
 */
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// GameView.java (Draws the game grid and player)
public class GameView {
    private final int TILE_SIZE = 40;
    private int gridWidth;
    private int gridHeight;
    
    private GridPane grid = new GridPane();
    private Rectangle player;
    
    private Image playerImage = new Image(getClass().getResourceAsStream("Character4.png"));
    private Image wallImage = new Image(getClass().getResourceAsStream("Wall_Brown.png"));
    private Image floorImage = new Image(getClass().getResourceAsStream("Ground_Concrete.png"));
    private Image crateImage = new Image(getClass().getResourceAsStream("CrateDark_Blue.png"));
    private Image targetImage = new Image(getClass().getResourceAsStream("EndPoint_Red.png"));
    private Image crateOnTargetImage = new Image(getClass().getResourceAsStream("Crate_Yellow.png"));
    
    private TileType[][] map;


    public GameView(int width, int height, TileType[][] levelMap) {
        gridWidth = width;
        gridHeight = height;
        map = levelMap;
        // drawGrid(2, 2);
        // We do not know initial player position
        // player = new Rectangle(TILE_SIZE, TILE_SIZE, Color.BLUE);
        // grid.add(player, 2, 2); // Initial player position
    }

    void drawGrid(int playerX, int playerY) {
        grid.getChildren().clear();
        for (int row = 0; row < gridWidth; row++) {
            for (int col = 0; col < gridHeight; col++) {
                ImageView tileView = new ImageView();
                tileView.setFitWidth(TILE_SIZE);
                tileView.setFitHeight(TILE_SIZE);
                 switch (map[col][row]) {
                    case FLOOR -> tileView.setImage(floorImage);
                    case WALL -> tileView.setImage(wallImage);
                    case CRATE -> tileView.setImage(crateImage);
                    case TARGET -> tileView.setImage(targetImage);
                    case CRATE_ON_TARGET -> tileView.setImage(crateOnTargetImage);
                }
                //Rectangle cell = new Rectangle(TILE_SIZE, TILE_SIZE, Color.LIGHTGRAY);
                //cell.setStroke(Color.BLACK);
                grid.add(tileView, row, col);
            }
        }
        ImageView tileView = new ImageView();
        tileView.setFitWidth(TILE_SIZE);
        tileView.setFitHeight(TILE_SIZE);
        // tileView above was inside for loop. It was local variable of loop.
        tileView.setImage(playerImage);
        grid.add(tileView, playerX, playerY);
        // We just put player tile above the grid at the end of draw board cycle. 
        // So it does not "shade" tiles below the player.
    }

    public GridPane getGrid() {
        return grid;
    }
    
    public int GridWidth() {
        return (gridWidth * TILE_SIZE);
    }
    
    public int GridHeight() {
        return (gridHeight * TILE_SIZE);
    }

    public void updatePlayerPosition(int x, int y) {
        GridPane.setColumnIndex(player, x);
        GridPane.setRowIndex(player, y);
    }
}