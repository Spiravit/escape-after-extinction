package group7.entities;

import java.awt.*;

// this class will be changed to abstract later
// it is not abstract for now just for testing
public  class Entity {
    double positionX,positionY;
    double height;

    public Entity(double positionX, double positionY, double height, double width) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.height = height;
        this.width = width;
    }

    double width;
    // Getters and Setters
    public double getPositionX() {
        return positionX;
    }
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    public double getPositionY() {
        return positionY;
    }
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    public double getHeight() {
        return height;
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


    public void renderEntity(Graphics g){
        g.fillRect((int)positionX,(int)positionY,(int)width,(int)height);
    }
}
