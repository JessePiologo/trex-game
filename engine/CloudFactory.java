package com.jessepiologo.DinoMeteorShower.engine;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.elements.Cloud;
import com.jessepiologo.DinoMeteorShower.graphics.CloudInverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by James on 5/14/2017.
 */

public class CloudFactory {
    CloudInverterFactory cloudInverterFactory;
    private List<Cloud> clouds = new ArrayList<>();
    private Screen screen;

    private static int CLOUDS_QUANTITY = 6;

    private int minGap;
    private int maxGap;
    private int minHeight;
    private int maxHeight;

    public CloudFactory(Screen screen, InvertColor invertColor,
                        CloudInverterFactory cloudInverterFactory) {
        this.cloudInverterFactory = cloudInverterFactory;
        this.screen = screen;

        minGap = screen.getWidth() / 6;
        maxGap = screen.getWidth() / 2;
        minHeight = (int) (screen.getHeight() / 2.2);
        maxHeight = screen.getHeight() / 6;

        int initialPos = screen.getWidth() - minGap;

        for (int i = 0; i < CLOUDS_QUANTITY; i++) {
            initialPos += getRandomNum(minGap, maxGap);
            clouds.add(new Cloud(invertColor, cloudInverterFactory, initialPos,
                    getRandomNum(minHeight, maxHeight)));
        }
    }

    public void update() {
        ListIterator<Cloud> iterator = clouds.listIterator();

        while (iterator.hasNext()) {
            Cloud cloud = iterator.next();
            cloud.setVector(-screen.getWidth() / 390);
            cloud.update();

            if (cloud.outOfScreen()) {
                cloud.setXPos(getMax() + getRandomNum(minGap, maxGap));
                cloud.setYPos(getRandomNum(minHeight, maxHeight));
            }
        }
    }

    public void draw(Canvas canvas) {
        for (Cloud cloud : clouds) {
            cloud.draw(canvas);
        }
    }

    public int getMax() {
        int max = 0;
        for (Cloud cloud : clouds) {
            max = Math.max(cloud.getXPos(), max);
        }
        return max;
    }

    public int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public void setShade(int shade) {
        for (Cloud cloud : clouds) {
            cloud.setShade(shade);
        }
    }
}
