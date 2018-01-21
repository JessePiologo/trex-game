package com.jessepiologo.DinoMeteorShower.engine;

/**
 * Created by James on 4/30/2017.
 */

public class Animation {
    private int[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;

    public void setFrames(int[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void update() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if (currentFrame == frames.length) {
            currentFrame = 0;
        }
    }

    public void setDelay(long d) {
        delay = d;
    }

    public int getFrame() {
        return currentFrame;
    }
}
