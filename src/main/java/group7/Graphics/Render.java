package group7.Graphics;

import java.awt.image.BufferedImage;
import java.awt.*;


public class Render {
    private GraphicsPanel gamePanel;
    private java.awt.Graphics g;

    private final static float scale = 32 * 2.5f;
    private int unitsWide;
    private int unitsHigh;

    public Render(GraphicsPanel gamePanel, int unitsWide, int unitsHigh) {
        this.gamePanel = gamePanel;
        this.unitsWide = unitsWide;
        this.unitsHigh = unitsHigh;
    }

    public void render(Graphics g, BufferedImage image, double posX, double posY) {
        g.drawImage(
            image, 
            (int) (posX * scale), 
            (int) (posY * scale), 
            (int) (scale),
            (int) (scale),
            null
        );
    }
}
