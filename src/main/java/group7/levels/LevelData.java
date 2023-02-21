package group7.levels;

public class LevelData {
    private boolean levelData[][];

    /**
     * Create a new LevelData object
     * @param width
     * width of the level
     * @param height
     * height of the level
     */
    public LevelData(int width, int height) {
        levelData = new boolean[width][height];
        
        // Set all tiles to false
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                levelData[x][y] = true; // should be changed to false when we have a level
            }
        }
    }

    /**
     * Set a tile valid or invalid
     * @param x 
     * x coordinate
     * @param y
     * y coordinate
     * @param value
     * true if valid, false if invalid
     */
    public void set(int x, int y, boolean value) {
        levelData[x][y] = value;
    }

    /**
     * Check if a tile is valid
     * @param x
     * x coordinate
     * @param y
     * y coordinate
     * @return
     * true if valid, false if invalid
     */
    public boolean canMove(int x, int y) {
        if (x < 0 || x >= levelData.length || y < 0 || y >= levelData[0].length) {
            return false;
        }
        return levelData[x][y];
    }
}
