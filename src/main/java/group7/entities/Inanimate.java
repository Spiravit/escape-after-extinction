package group7.entities;

import group7.Graphics.GraphicsGrid;
import group7.levels.LevelData;

public abstract class Inanimate extends Entity {
    private LevelData levelData;

    public Inanimate(double positionX, double positionY, LevelData levelData) {
        super(positionX, positionY);
        this.levelData = levelData;
    }
}
