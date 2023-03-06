package group7.levels;
import group7.Game;
import group7.utils.AssetLoader;
import group7.utils.Direction;

import static group7.Game.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * LevelData
 * - stores whether a tile is valid or invalid (can be moved on or not) 
 */
public class LevelData {
    private int playerX;
    private int playerY;

    private boolean levelData[][];

    private ArrayList<PathNode> openList = new ArrayList<PathNode>(); // nodes to check
    private ArrayList<PathNode> closedList = new ArrayList<PathNode>(); // nodes checked

    public LevelData(int width, int height) {
        levelData = new boolean[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                levelData[i][j] = false;
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

    public void setPlayer(int x, int y) {
        playerX = x;
        playerY = y;
    }

    public Direction findPlayer(int x, int y, int range) {
        System.out.println("range: " + range);
        openList.add(new PathNode(
            x,
            y, 
            0, 
            Math.abs(playerX - x) + Math.abs(playerY - y), 
            Math.abs(playerX - x) + Math.abs(playerY - y) , 
            null
        ));

        while (!openList.isEmpty()) {
            // get the lowest f cost node, remove it from open list and add it to closed list
            PathNode currentNode = openList.get(0);
            openList.remove(0);
            closedList.add(currentNode);

            // found player
            if (currentNode.getX() == playerX && currentNode.getY() == playerY) {

                // clear lists
                openList.clear();
                closedList.clear();

                // player is out of range
                if (currentNode.getG() > range) {
                    return Direction.NONE;
                }

                // loop through parents until we get right before the enemy tile
                while (currentNode.getParent() != null 
                && !(currentNode.getParent().getX() == (int) x && currentNode.getParent().getY() == (int) y)){
                    currentNode = currentNode.getParent();
                }
                
                if (currentNode.getParent() == null) {
                    return Direction.NONE;
                }
                System.out.println("Current node x: " + currentNode.getX() + " y: " + currentNode.getY());
                //System.out.println("Parent node x: " + currentNode.getParent().getX() + " y: " + currentNode.getParent().getY());
                
                // return the direction of the first tile
                if (currentNode.getX() == x + 1) {
                    return Direction.RIGHT;
                } else if (currentNode.getX() == x - 1) {
                    return Direction.LEFT;
                } else if (currentNode.getY() == y + 1) {
                    return Direction.DOWN;
                } else if (currentNode.getY() == y - 1) {
                    return Direction.UP;
                }
                return Direction.NONE;
            }

            // add adjacent tiles to open list
            addPathNode(currentNode, currentNode.getX() + 1, currentNode.getY());
            addPathNode(currentNode, currentNode.getX() - 1, currentNode.getY());
            addPathNode(currentNode, currentNode.getX(), currentNode.getY() + 1);
            addPathNode(currentNode, currentNode.getX(), currentNode.getY() - 1);
        }

        System.out.println("Player not found");
        return Direction.NONE;
    }

    private void addPathNode(PathNode currentNode, int x, int y) {
        // only add if tile is valid
        if (canMove(x, y)) {
            PathNode pathnode = new PathNode(
                x, 
                y, 
                currentNode.getG() + 1, // child node will always be 1 away from start
                Math.abs(x - playerX) + Math.abs(y - playerY), // distance from end
                currentNode.getG() + 1 + Math.abs(x - playerX) + Math.abs(y - playerY), // distance from start + distance from end
                currentNode
            );

            // check if node is already in closed list
            for (int i = 0; i < closedList.size(); i++) {
                if (pathnode.getX() == closedList.get(i).getX() && pathnode.getY() == closedList.get(i).getY()) {
                    return;
                }
            }

            // add node to open list
            if (openList.size() == 0) {
                openList.add(pathnode);
            } else {
                for (int i = 0; i < openList.size(); i++) {
                    if (pathnode.getF() < openList.get(i).getF()) {
                        openList.add(i, pathnode);
                        break;
                    } else if (i == openList.size() - 1) {
                        openList.add(pathnode);
                        break;
                    }
                }
            }
            
        }
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

} // End of LevelData.java


class PathNode {
    private int x;
    private int y;
    private int g; // distance from start
    private int h; // distance from end
    private int f; // g + h
    private PathNode parent;

    public PathNode(int x, int y, int g, int h, int f, PathNode parent) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.f = f;
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public int getF() {
        return f;
    }

    public PathNode getParent() {
        return parent;
    }
}
