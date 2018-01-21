package com.jessepiologo.DinoMeteorShower.elements;

/**
 * Created by Jessé Piologo on 4/30/2017.
 */

public class GameObject {
    protected int xPos;
    protected int yPos;
    protected int dx;
    protected int width;
    protected int height;
    protected int shade;

    public void setXPos(int x) {
        this.xPos = x;
    }

    public int getXPos() {
        return xPos;
    }

    public void setYPos(int y) {
        this.yPos = y;
    }

    public int getYPos() {
        return yPos;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setShade(int shade) {
        this.shade = shade;
    }

    public int getShade() {
        return shade;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }
}
