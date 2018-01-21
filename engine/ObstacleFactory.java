package com.jessepiologo.DinoMeteorShower.engine;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.elements.Ground;
import com.jessepiologo.DinoMeteorShower.elements.Obstacle;
import com.jessepiologo.DinoMeteorShower.elements.Trex;
import com.jessepiologo.DinoMeteorShower.graphics.ObstacleInverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Jessé Piologo on 5/9/2017.
 */


public class ObstacleFactory {
    private Screen screen;
    private InvertColor invertColor;
    private ObstacleInverterFactory obstacleInverterFactory;
    private GamePanel gamePanel;
    private Ground ground;

    private int position;
    private List<Obstacle> obstacles = new ArrayList<>();

    private static int OBS_QUANTITY = 4;
    private int minGap;
    private int maxGap;
    private int gap = 0;

    int count = 0;
    private int rand = 0;

    public ObstacleFactory(Screen screen, InvertColor invertColor,
                           ObstacleInverterFactory obstacleInverterFactory, GamePanel gamePanel,
                           Ground ground) {
        this.screen = screen;
        this.invertColor = invertColor;
        this.obstacleInverterFactory = obstacleInverterFactory;
        this.gamePanel = gamePanel;
        this.ground = ground;

        minGap = (int) (screen.getWidth() * 0.3);
        maxGap = (int) (screen.getWidth() * 0.55);
        position = screen.getWidth() * 2;

        for (int i = 0; i < OBS_QUANTITY; i++) {
            position += screen.getWidth() / 2;
            count++;
            Obstacle obstacle = new Obstacle(screen, invertColor, obstacleInverterFactory,
                    getRandomNum(0, 4), position, ground);
            obstacle.setDx(-screen.getWidth() / 100);
            obstacles.add(obstacle);
        }
    }

    public void update() {
        ListIterator<Obstacle> iterator = obstacles.listIterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.update();

            if (obstacle.outOfScreen()) {
                iterator.remove();

                if (gamePanel.getScore() > 240) {
                    rand = getRandomNum(0, 6);
                } else if (gamePanel.getScore() > 150) {
                    rand = getRandomNum(0, 5);
                } else {
                    rand = getRandomNum(0, 4);
                }

                if (rand == 6) {
                    minGap = (screen.getWidth() * Math.abs(ground.getDx())) / (screen.getWidth()
                            / 30);
                    maxGap = (int) (minGap * 2.3);
                    gap = getRandomNum(minGap, maxGap);
                    gap *= 1.5;

                } else {
                    minGap = (screen.getWidth() * Math.abs(ground.getDx())) / (screen.getWidth()
                            / 30);
                    maxGap = (int) (minGap * 2.3);
                    gap = getRandomNum(minGap, maxGap);
                }

                Obstacle anotherObs = new Obstacle(screen, invertColor, obstacleInverterFactory,
                        rand, getMax() + gap, ground);
                anotherObs.setDx(ground.getDx());
                iterator.add(anotherObs);
            }
        }
    }

    public void draw(Canvas canvas) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(canvas);
        }
    }

    public int getMax() {
        int max = 0;
        for (Obstacle obstacle : obstacles) {
            max = Math.max(obstacle.getXPos(), max);
        }
        return max;
    }

    public int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public boolean checkForCollision(Trex tRex) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.checkForVerticalCollision(tRex)
                    && obstacle.checkForHorizontalCollision(tRex)) {
                return true;
            }
        }
        return false;
    }

    public void setDx(int dx) {
        ListIterator<Obstacle> iterator = obstacles.listIterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            obstacle.setDx(dx);
        }
    }

    public void setObstacleGap(int gap) {
        this.maxGap = (int) (minGap * 2.3);
    }
}