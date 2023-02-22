package group7.levels;

public class Level1 extends Level {
    int width = 20;
    int height = 20;

    public void draw(Graphics g) {
        for (int j = 0; j < NUMBER_OF_TILES_IN_HEIGHT; j++)
            for (int i = 0; i < NUMBER_OF_TILES_IN_WIDTH; i++) {
                int index = levelOne.getLevelDataSprite(j, i);
                g.drawImage(levelSprite[index], (int) GAME_SIZE_SCALE * TILES_SIZE * i, (int) GAME_SIZE_SCALE * TILES_SIZE * j, (int) GAME_SIZE_SCALE * TILES_SIZE, (int) GAME_SIZE_SCALE * TILES_SIZE, null);
            }
    }
}
