package group7.Graphics;

import java.awt.image.BufferedImage;
import java.awt.*;

import static group7.Graphics.GraphicsPanel.panelHeight;
import static group7.Graphics.GraphicsPanel.panelWidth;


/** 
* The class Graphic Grid constructs an object of Graphic Grip type, 
* that maps out x and y coordinates on the GraphicPanel as 1 x 1 squares tileset.
* Used to make the rendering of graphics or objects in the game window easier.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class GraphicsGrid {
    public static int scaleX;
    public static int scaleY;
    public static int gridWidth;
    public static int gridHeight;

    /**
     * Constructor, creates a new GraphicsGrid 
     * @param unitsWide (the width of this grid)
     * @param unitsHigh (the height of this grid)
     */
    public GraphicsGrid( int unitsWide, int unitsHigh) {
        gridWidth = unitsWide;
        gridHeight = unitsHigh;
        calculateScale();
    }

    /**
     * Calculates the scale of the grid, 
     * using the size of the screen and the size of the grid.
     */
    private static void calculateScale() {
        scaleX = panelWidth / gridWidth;
        scaleY = panelHeight / gridHeight;
    }

    /**
     * Translates the given x and y coordinates to the correct position on the screen 
     * by multiplying them by the given grid sizes, and Paints the given image there.
     * @param g (the gridics object to draw on)
     * @param image (the image to draw)
     * @param posX (x coordinate)
     * @param posY (y coordinate)
     * @param width (width of the image)
     * @param height (height of the image)
     */
    public static void render(Graphics g, BufferedImage image, double posX, double posY, double width, double height) {
        g.drawImage(
            image, 
            (int) (posX * scaleX), 
            (int) (posY * scaleY), 
            (int) (width * scaleX),
            (int) (height * scaleY),
            null
        );
    }

    /**
     * Creates a rectangle on the screen for debugging purposes.
     * @param g
     * @param posX
     * @param posY
     * @param width
     * @param height
     */
    public static void drawRect(Graphics g, double posX, double posY, double width, double height) {
        g.drawRect(
            (int) (posX * scaleX), 
            (int) (posY * scaleY), 
            (int) (width * scaleX),
            (int) (height * scaleY)
        );
    }

    // Getters and Setters
    
    /**
     * Sets the grid size. 
     * @param unitsWide (the width of the grid)
     * @param unitsHigh (the height of the grid)
     */
    public static void setGridSize(int unitsWide, int unitsHigh) {
        gridWidth = unitsWide;
        gridHeight = unitsHigh;
        calculateScale();
    } 

    /**
     * Return the scaling of the width
     * @return int scaleY
     */
    public static int getScaleY() {
        return scaleY;
    }

    /**
     * Return the scaling of the height
     * @return int scaleX
     */
    public static int getScaleX() {
        return scaleX;
    }

    /**
     * Return the (1x1) tileset width of the panel
     * @return int gridWidth
     */
    public static int getGridWidth() {
        return gridWidth;
    }

    /**
     * Return the (1x1) tileset height of the panel
     * @return int gridHeight
     */
    public static int getGridHeight() {
        return gridHeight;
    }
}
