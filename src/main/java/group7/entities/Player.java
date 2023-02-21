package group7.entities;

import group7.levels.LevelData;

public class Player extends Animate {
    private int health = 100;
    private int stamina = 100;

    public Player(double positionX, double positionY, double height, double width, LevelData levelData) {
        super(positionX, positionY, height, width, levelData);
    }
}
