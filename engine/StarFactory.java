package com.jessepiologo.DinoMeteorShower.engine;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.elements.Star;
import com.jessepiologo.DinoMeteorShower.graphics.StarInverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Jessé Piologo on 5/14/2017.
 */

public class StarFactory {
    private StarInverterFactory starInverterFactory;
    private List<Star> stars = new ArrayList<>();
    private Screen screen;
    private static int STAR_QUANTITY = 3;

    private int minGap;
    private int maxGap;
    private int minHeight;
    private int maxHeight;
    private boolean draw = true;

    public StarFactory(Screen screen, InvertColor invertColor,
                       StarInverterFactory starInverterFactory) {
        this.screen = screen;
        this.starInverterFactory = starInverterFactory;

        minGap = screen.getWidth() / 6;
        maxGap = screen.getWidth() / 2;
        minHeight = (int) (screen.getHeight() / 3.2);
        maxHeight = screen.getHeight() / 14;

        int position = screen.getWidth() / 2;

        for (int i = 0; i < STAR_QUANTITY; i++) {
            position += getRandomNum(minGap, maxGap);
            stars.add(new Star(invertColor, starInverterFactory, position,
                    getRandomNum(minHeight, maxHeight), getRandomNum(0, 2)));
        }
    }

    public void update() {
        ListIterator<Star> iterator = stars.listIterator();

        while (iterator.hasNext()) {
            Star star = iterator.next();
            star.setDx(-screen.getWidth() / 800);
            star.update();

            if (star.outOfScreen()) {
                star.setYPos(getRandomNum(minHeight, maxHeight));
                star.setXPos(screen.getWidth());
                star.setRand(getRandomNum(0, 2));
            }
        }
    }

    public void draw(Canvas canvas) {
        if (draw) {
            for (Star star : stars) {
                star.draw(canvas);
            }
        }
    }

    public int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
