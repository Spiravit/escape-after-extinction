package group7.entities;

import java.awt.Rectangle;
import java.awt.Graphics;

// this class will be changed to abstract later
// it is not abstract for now just for testing
public  class Entity {
    double positionX;
    double positionY;
    double height;
    double width;

    public Entity(double positionX, double positionY, double height, double width) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.height = height;
        this.width = width;
    }

    // Getters and Setters
    public double getPositionX() {
        return this.positionX;
    }
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    public double getPositionY() {
        return this.positionY;
    }
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    public double getHeight() {
        return this.height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public Rectangle getHitbox() {
        return new Rectangle((int)positionX,(int)positionY,(int)width,(int)height);
    }

    public void renderEntity(Graphics g){
        g.fillRect((int)positionX,(int)positionY,(int)width,(int)height);
    }
}
