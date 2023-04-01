package group7.levels;

import group7.helperClasses.Direction;

import java.util.ArrayList;

/**
* The class Pathfinding is used to store the data of a level, 
* and find a path for the enemy towards the player.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
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

    /**
     * Constructor: initializes pathfinding by setting all tiles that 
     * can be used in a path toward the player as false. 
     * @param width
     * @param height
     */
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
     * @param x (x coordinate of the tile)
     * @param y (y coordinate of the tile)
     * @param value (true if valid, false if invalid)
     */
    public void set(int x, int y, boolean value) {
        if (!withinBounds(x, y)) {
            return;
        }
        pathfinding[x][y] = value; 
    }

    /**
     * Sets location of the player.
     * @param x (current x coordinate of tile player is in)
     * @param y (current y coordinate of tile player is in)
     */
    public void setPlayer(int x, int y) {
        if (!withinBounds(x, y)) {
            return;
        }
        playerX = x;
        playerY = y;
    }

    /**
     * Sets directions the Enemy needs to walk in to reach the player.
     * @param x (current tile x coordinate)
     * @param y (current tile y coordinate)
     * @param range (how far out, tilewise, to search)
     * @return direction {@link Direction}
     */
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

                if (currentNode.getG() > range) {
                    // player is out of range
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
     * @param x (cureent tile x coordinate)
     * @param y (current tile y coordinate)
     */
    private void addPathNode(PathNode currentNode, int x, int y) {
        // only add if tile is valid
        if (isValidTile(x, y)) {
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
     * @param x (current tile x coordinate)
     * @param y (current tile y coordinate)
     * @return True if valid 
     * @return False if invalid
     */
    public boolean isValidTile(int x, int y) {
        if (!withinBounds(x, y)) {
            return false;
        }
        return pathfinding[x][y];
    }


    /**
     * Check if a tile is within the bounds of the map
     * @param x
     * x coordinate of tile
     * @param y
     * y coordinate of tile
     * @return
     * True if within bounds, false if not
     */
    private boolean withinBounds(int x, int y) {
        if (x < 0 || x >= pathfinding.length || y < 0 || y >= pathfinding[0].length) {
            return false;
        }
        return true;
    }

} 

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

    /**
     * Constructor: initializes node, to hold a valid path data
     * @param x (x coordinate of current tile being searched)
     * @param y (y coordinate of current tile being searched)
     * @param g (distance from start)
     * @param h (distance from end)
     * @param f (total distance)
     * @param parent
     */
    public PathNode(int x, int y, int g, int h, int f, PathNode parent) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.f = f;
        this.parent = parent;
    }

    /**
     * Return x coordinate of current tile being searched.
     * @return int x 
     */
    public int getX() {
        return x;
    }

    /**
     * Return y coordinate of current tile being searched.
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Return distance from start.
     * @return int g
     */
    public int getG() {
        return g;
    }

    /**
     * Return distance from end.
     * @return int h
     */
    public int getH() {
        return h;
    }

    /**
     * Return total distance of the path
     * @return int f
     */
    public int getF() {
        return f;
    }

    /**
     * Return pointer to the head node of the path 
     * @return ParentNode parent
     */
    public PathNode getParent() {
        return parent;
    }
}
