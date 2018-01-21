package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.Screen;
import com.jessepiologo.DinoMeteorShower.graphics.GroundBumpInverterFactory;

/**
 * Created by Jessé Piologo on 5/25/2017.
 */

public class GroundBump extends GameObject {
    private InvertColor invertColor;
    private GroundBumpInverterFactory groundBumpInverterFactory;

    private int rand;

    public GroundBump(Screen screen, InvertColor invertColor,
                      GroundBumpInverterFactory groundBumpInverterFactory,
                      int initialPos, int rand, int shade) {
        this.invertColor = invertColor;
        this.groundBumpInverterFactory = groundBumpInverterFactory;
        this.rand = rand;
        this.shade = shade;

        xPos = initialPos;
        yPos = (int) (screen.getHeight() / 1.62);
    }

    public void update() {
        xPos += dx;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(groundBumpInverterFactory.getGrayScaleSprite(invertColor.getShade(),
                rand), xPos, yPos, null);
    }

    public boolean outOfScreen() {
        return xPos + groundBumpInverterFactory.getWidth() < 0;
    }

    public void setVector(int dx) {
        this.dx = dx;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }
}
