package com.javalessons.sokoban.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest {

    @Test
    public void testLoadLevelSetsGridAndPlayerPosition() {
        GameModel model = new GameModel();
        TileType[][] grid = model.loadLevel();

        assertNotNull(grid, "Grid should not be null");
        assertEquals(model.getGridHeight(), grid.length, "Grid height should match constant");
        assertEquals(model.getGridWidth(), grid[0].length, "Grid width should match constant");

        int expectedX = 11; // '@' column index
        int expectedY = 8;  // '@' row index
        assertEquals(expectedX, model.getPlayerX(), "Player X coordinate should match level file");
        assertEquals(expectedY, model.getPlayerY(), "Player Y coordinate should match level file");
    }
}
