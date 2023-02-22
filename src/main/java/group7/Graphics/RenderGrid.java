package group7.Graphics;

import java.awt.image.BufferedImage;
import java.awt.*;


public class RenderGrid {
    private GraphicsPanel gamePanel;

    private float scaleX;
    private float scaleY;
    private int unitsWide;
    private int unitsHigh;

    public RenderGrid(GraphicsPanel gamePanel, int unitsWide, int unitsHigh) {
        this.gamePanel = gamePanel;
        this.unitsWide = unitsWide;
        this.unitsHigh = unitsHigh;
        calculateScale();
    }

    public void setUnitsWide(int unitsWide) {
        this.unitsWide = unitsWide;
        calculateScale();
    }

    public void setUnitsHigh(int unitsHigh) {
        this.unitsHigh = unitsHigh;
        calculateScale();
    }

    private void calculateScale() {
        int width = gamePanel.getWidth();
        int height = gamePanel.getHeight();

        scaleX = (float) width / unitsWide;
        scaleY = (float) height / unitsHigh;
    }


    /**
     * translates the given x and y coordinates to the correct position on the screen 
     * by multiplying them by the scale given in the constructor
     * @param g
     * the graphics object to draw on
     * @param image
     * the image to draw
     * @param posX
     * x coordinate
     * @param posY
     * y coordinate
     */
    public void render(Graphics g, BufferedImage image, double posX, double posY, double width, double height) {
        g.drawImage(
            image, 
            (int) (posX * scaleX), 
            (int) (posY * scaleY), 
            (int) (width * scaleX),
            (int) (width * scaleY),
            null
        );
    }
}
