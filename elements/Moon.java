package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.Screen;
import com.jessepiologo.DinoMeteorShower.graphics.MoonInverterFactory;

/**
 * Created by Jessé Piologo on 5/14/2017.
 */
public class Moon extends GameObject {
    private InvertColor invertColor;
    private MoonInverterFactory moonInverterFactory;

    private int moonPhase;

    private int aux = 0;

    public Moon(InvertColor invertColor, MoonInverterFactory moonInverterFactory, int xPos,
                int yPos, int moonPhase) {
        this.invertColor = invertColor;
        this.moonInverterFactory = moonInverterFactory;
        this.moonPhase = moonPhase;

        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void update() {
        aux++;
        if (aux % 2 == 0) {
            xPos += dx;
        }
    }

    public void draw(Canvas canvas) {
        if (invertColor.getInverted()) {
            canvas.drawBitmap(moonInverterFactory.getGrayScaleSprite(moonPhase,
                    invertColor.getShade()), xPos, yPos, null);
        }
    }

    public boolean outOfScreen() {
        return this.xPos + this.moonInverterFactory.getWidth() < 0;
    }

    public void setVector(int dx) {
        this.dx = dx;
    }

    public void setMoonPhase(int moonPhase) {
        this.moonPhase = moonPhase;
    }

}
