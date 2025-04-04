/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javalessons.sokoban;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.List;
import java.io.*;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 *
 * @author mikhail
 */
// GameModel.java (Handles game state)
public class GameModel {
    private int playerX; // We could (but not have to) move this inside a player class
    private int playerY;
    private final int gridSize = 5;
    private final int GRID_WIDTH = 19;
    private final int GRID_HEIGHT = 11;
    
    TileType[][] grid = new TileType[GRID_HEIGHT][GRID_WIDTH];

    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }
    
    public int getGridWidth () { return GRID_WIDTH; }
    public int getGridHeight() { return GRID_HEIGHT; }

    public void setPlayerX(int pos) { playerX = pos; }
    public void setPlayerY(int pos) { playerY = pos; }
    
    public TileType[][] loadLevel() {

        // File file = new File(ClassLoader.getSystemResource("level2.txt").getFile());
        
        try ( InputStream is = GameModel.class.getResourceAsStream("level2.txt");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr)) {
            List<String> lines = new ArrayList<>();
            String line;
        
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        
            int rows = lines.size();
            int cols = lines.get(0).length();
            System.out.println("Cols: " + cols + ". Rows: " + rows);
        
            grid = new TileType[GRID_HEIGHT][GRID_WIDTH]; // Resize grid
        
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    char c = lines.get(y).charAt(x);
                    switch (c) {
                        case '#' -> grid[y][x] = TileType.WALL;
                        case '.' -> grid[y][x] = TileType.TARGET;
                        case '$' -> grid[y][x] = TileType.CRATE;
                        case '@' -> {
                            grid[y][x] = TileType.FLOOR;
                            playerX = x;
                            playerY = y;
                        }
                        default -> grid[y][x] = TileType.FLOOR;
                    }
                }
            }
        
            System.out.println("File read is finished");

            } catch (IOException e) {
                System.err.println("Error loading level: " + e.getMessage());
            }
        return grid;
    }

}