package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.graphics.CloudInverterFactory;

/**
 * Created by Jessé Piologo on 5/14/2017.
 */
public class Cloud extends GameObject {
    private InvertColor invertColor;
    private final CloudInverterFactory cloudInverterFactory;

    public Cloud(InvertColor invertColor, CloudInverterFactory cloudInverterFactory, int xPos,
                 int yPos) {
        this.invertColor = invertColor;
        this.cloudInverterFactory = cloudInverterFactory;

        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void update() {
        xPos += dx;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(cloudInverterFactory.getGrayScaleSprite(invertColor.getShade()),
                xPos, yPos, null);
    }

    public boolean outOfScreen() {
        return this.xPos + this.cloudInverterFactory.getWidth() < 0;
    }

    public void setVector(int dx) {
        this.dx = dx;
    }
}
