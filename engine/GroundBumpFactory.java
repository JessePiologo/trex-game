package com.jessepiologo.DinoMeteorShower.engine;

/**
 * Created by Jessé Piologo on 5/25/2017.
 */

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.elements.GroundBump;
import com.jessepiologo.DinoMeteorShower.graphics.GroundBumpInverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class GroundBumpFactory {
    private GroundBumpInverterFactory groundBumpInverterFactory;
    private InvertColor invertColor;
    private int initialPosition;

    private List<GroundBump> groundBumps = new ArrayList<>();
    private static int OBS_QUANTITY = 7;
    private int minGap;
    private int maxGap;

    ListIterator<GroundBump> iterator;

    public GroundBumpFactory(Screen screen, InvertColor invertColor,
                             GroundBumpInverterFactory groundBumpInverterFactory) {
        this.invertColor = invertColor;
        this.groundBumpInverterFactory = groundBumpInverterFactory;

        minGap = (int) (screen.getWidth() * 0.1);
        maxGap = screen.getWidth() * 2;
        initialPosition = screen.getWidth();

        for (int i = 0; i < OBS_QUANTITY; i++) {
            initialPosition += getRandomNum(minGap, maxGap);
            GroundBump groundBump = new GroundBump(screen, invertColor, groundBumpInverterFactory,
                    initialPosition, getRandomNum(0, 1), 0);
            groundBump.setVector(-screen.getWidth() / 100);
            groundBumps.add(groundBump);
        }
    }

    public void update() {
        iterator = groundBumps.listIterator();
        while (iterator.hasNext()) {
            GroundBump groundBump = iterator.next();
            groundBump.update();

            if (groundBump.outOfScreen()) {
                groundBump.setXPos(getMax() + getRandomNum(minGap, maxGap));
                groundBump.setRand(getRandomNum(0, 1));
            }
        }
    }

    public void draw(Canvas canvas) {
        for (GroundBump groundBump : groundBumps) {
            groundBump.draw(canvas);
        }
    }

    public int getMax() {
        int max = 0;
        for (GroundBump groundBump : groundBumps) {
            max = Math.max(groundBump.getXPos(), max);
        }
        return max;
    }

    public int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public void setDx(int dx) {
        iterator = groundBumps.listIterator();
        while (iterator.hasNext()) {
            GroundBump groundBump = iterator.next();
            groundBump.setVector(dx);
        }
    }

    public void setShade(int shade) {
        for (GroundBump groundBump : groundBumps) {
            groundBump.setShade(shade);
        }
    }
}
