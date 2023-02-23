package group7.Graphics;

import java.awt.image.BufferedImage;
import java.awt.*;


public class GraphicsGrid {
    private GraphicsPanel graphicsPanel;

    private double scaleX;
    private double scaleY;
    private int unitsWide;
    private int unitsHigh;

    public GraphicsGrid(GraphicsPanel graphicsPanel, int unitsWide, int unitsHigh) {
        this.graphicsPanel = graphicsPanel;
        this.unitsWide = unitsWide;
        this.unitsHigh = unitsHigh;
        calculateScale();
    }

    public void setGridSize(int unitsWide, int unitsHigh) {
        this.unitsWide = unitsWide;
        this.unitsHigh = unitsHigh;
        calculateScale();
    }

    private void calculateScale() {
        // TODO: call graphicsPanel to get the width and height
        // removing the + 1 causes the objects to be drawn off screen
        scaleX = 1280 / (unitsWide + 1);
        scaleY = 720 / (unitsHigh + 1);
    }


    /**
     * translates the given x and y coordinates to the correct position on the screen 
     * by multiplying them by the given grid sizes
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
        System.out.println("posX on screen: " + (posX* scaleX) + " posX: " + posX);
        System.out.println("posY on screen: " + (posY* scaleY) + " posY: " + posY);
        g.drawImage(
            image, 
            (int) (posX * scaleX), 
            (int) (posY * scaleY), 
            (int) (width * scaleX),
            (int) (height * scaleY),
            null
        );
    }
}
