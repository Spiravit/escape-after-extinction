package group7.gameObjects;

import group7.Graphics.GraphicsGrid;

public class KeyCard extends Collectables {
    // Constructor
    public KeyCard(int x, int y, int objectType) {
        super(x, y, objectType);
        initHitbox();
        xDrawOffset = (int) (0.5 * xScale);
        yDrawOffset = (int) (0.7 * yScale);
    }
    
}
