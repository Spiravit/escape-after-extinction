package group7.levels;
import group7.Game;
import group7.utils.AssetLoader;
import group7.utils.Direction;

import static group7.Game.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * - used to store the data of a level and pathfind the player
 */
public class Pathfinding {
    // location of the player
    private int playerX;
    private int playerY;

    // level data that stores if a tile is valid or not (true = valid, false = invalid)
    private boolean pathfinding[][];

    // nodes to check for pathfinding
    private ArrayList<PathNode> openList = new ArrayList<PathNode>(); 
    // nodes that have already been checked for pathfinding
    private ArrayList<PathNode> closedList = new ArrayList<PathNode>();

    public Pathfinding(int width, int height) {
        pathfinding = new boolean[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pathfinding[i][j] = false;
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
        pathfinding[x][y] = value;
    }

    public void setPlayer(int x, int y) {
        playerX = x;
        playerY = y;
    }

    public Direction findPlayer(int x, int y, int range) {
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

                // loop through parents until we get right before the first node
                while (currentNode.getParent() != null 
                && !(currentNode.getParent().getX() == (int) x && currentNode.getParent().getY() == (int) y)){
                    currentNode = currentNode.getParent();
                }
                
                // if we are at the first node, return none
                if (currentNode.getParent() == null) {
                    return Direction.NONE;
                }
                                
                // return the direction of the 2nd node compared to the first node
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

        return Direction.NONE;
    }

    /**
     * Add a node to the open list
     * @param currentNode
     * the node that is being checked
     * @param x
     * x coordinate
     * @param y
     * y coordinate
     */
    private void addPathNode(PathNode currentNode, int x, int y) {
        // only add if tile is valid
        if (canMove(x, y)) {
            PathNode pathnode = new PathNode(
                x, 
                y, 
                currentNode.getG() + 1, // child node will be 1 away from start
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
        if (x < 0 || x >= pathfinding.length || y < 0 || y >= pathfinding[0].length) {
            return false;
        }
        return pathfinding[x][y];
    }

} // End of LevelData.java

/**
 * A node in the pathfinding algorithm
 */
class PathNode {
    private int x;
    private int y;
    private int g; // distance from start
    private int h; // distance from end
    private int f; // g + h (total distance)
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
