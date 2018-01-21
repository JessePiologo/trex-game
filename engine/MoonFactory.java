package com.jessepiologo.DinoMeteorShower.engine;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.elements.Moon;
import com.jessepiologo.DinoMeteorShower.graphics.MoonInverterFactory;

/**
 * Created by Jessé Piologo on 5/14/2017.
 */

public class MoonFactory {
    private InvertColor invertColor;
    private MoonInverterFactory moonInverterFactory;
    private Moon moon;
    private Screen screen;
    private int moonHeight;
    private int minGap;
    private int maxGap;
    private boolean draw = true;
    private int moonPhase = 0;
    private int moonPhaseCounter = 0;

    public MoonFactory(Screen screen, InvertColor invertColor,
                       MoonInverterFactory moonInverterFactory) {
        this.screen = screen;
        this.invertColor = invertColor;
        this.moonInverterFactory = moonInverterFactory;
        minGap = screen.getWidth() / 6;
        maxGap = screen.getWidth() / 2;

        moonHeight = screen.getHeight() / 5;
        int position = screen.getWidth() / 2;
        position += getRandomNum(minGap, maxGap);
        moon = new Moon(invertColor, moonInverterFactory, position, moonHeight, moonPhase);
    }

    public void update() {
        setMoonPhase(invertColor.getMoonPhase());
        moon.setVector(-screen.getWidth() / 800);
        moon.update();

        if (moon.outOfScreen()) {
            moon.setXPos(screen.getWidth());
        }
    }

    public void draw(Canvas canvas) {
        if (invertColor.getInverted()) {
            moon.draw(canvas);
        }
    }

    public int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public void setMoonPhase(int moonPhase) {
        moon.setMoonPhase(moonPhase);
    }

}
