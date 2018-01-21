package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.graphics.StarInverterFactory;

/**
 * Created by Jessé Piologo on 5/14/2017.
 */
public class Star extends GameObject {
    private InvertColor invertColor;
    private StarInverterFactory starInverterFactory;

    private int rand;

    public Star(InvertColor invertColor, StarInverterFactory starInverterFactory, int xPos,
                int yPos, int rand) {
        this.invertColor = invertColor;
        this.starInverterFactory = starInverterFactory;
        this.rand = rand;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void update() {
        xPos += dx;
    }

    public void draw(Canvas canvas) {
        if (invertColor.getInverted()) {
            canvas.drawBitmap(starInverterFactory.getGrayScaleSprite(rand, invertColor.getShade()),
                    xPos, yPos, null);
        }
    }

    public boolean outOfScreen() {
        return this.xPos + this.starInverterFactory.getWidth() < 0;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }
}
