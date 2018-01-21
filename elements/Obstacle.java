package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.Animation;
import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.Screen;
import com.jessepiologo.DinoMeteorShower.graphics.ObstacleInverterFactory;

/**
 * Created by Jessé Piologo on 5/9/2017.
 */
public class Obstacle extends GameObject {
    private final Animation animation = new Animation();
    private InvertColor invertColor;
    private ObstacleInverterFactory obstacleInverterFactory;

    private int spriteWidth;
    private int spriteHeight;

    private int[] frames = new int[2];

    private Screen screen;
    private Ground ground;

    private int spriteX;
    private int spriteY;
    private long startTime;

    private int rand;
    private double speedCoefficient;

    private int[] pteroRandomHeight;
    private double[] pteroRandomSpeedCoefficient;

    public Obstacle(Screen screen, InvertColor invertColor,
                    ObstacleInverterFactory obstacleInverterFactory, int rand, int initialPos,
                    Ground ground) {
        this.invertColor = invertColor;
        this.obstacleInverterFactory = obstacleInverterFactory;
        this.rand = rand;
        this.screen = screen;
        this.ground = ground;

        pteroRandomHeight = new int[]{(int) (screen.getHeight() / 2.69),
                (int)(screen.getHeight() / 1.89)};
        pteroRandomSpeedCoefficient = new double[] {1, 1.1, 1.2};

        xPos = initialPos;
        switch (rand) {
            case 0:
                spriteX = 0;
                spriteWidth = 17;
                spriteHeight = 35;
                break;
            case 1:
                spriteX = 17;
                spriteWidth = 34;
                spriteHeight = 35;
                break;
            case 2:
                spriteX = 51;
                spriteWidth = 51;
                spriteHeight = 35;
                break;
            case 3:
                spriteX = 102;
                spriteWidth = 25;
                spriteHeight = 50;
                break;
            case 4:
                spriteX = 127;
                spriteWidth = 50;
                spriteHeight = 50;
                break;
            case 5:
                spriteX = 177;
                spriteWidth = 75;
                spriteHeight = 50;
                break;
            default:
                break;
        }
        if (rand == 6) {
            spriteX = 252;
            spriteWidth = 46;
            spriteHeight = 40;

            width = (int) ((screen.getHeight() / 320f) * spriteWidth);
            height = (int) ((screen.getHeight() / 320f) * spriteHeight);

            animation.setFrames(frames);
            animation.setDelay(100);
           // startTime = System.nanoTime();

            yPos = pteroRandomHeight[getRandomNum(0, 1)];
            speedCoefficient = pteroRandomSpeedCoefficient[getRandomNum(0, 2)];

        } else {
            width = (int) ((screen.getHeight() / 320f) * spriteWidth);
            height = (int) ((screen.getHeight() / 320f) * spriteHeight);

            yPos = ((int) (screen.getHeight() / 1.53f) - height);
        }
    }

    public void update() {
        if (rand == 6) {
            if (xPos < screen.getWidth()) {
                animation.update();
                xPos += dx * speedCoefficient;
            } else {
                xPos += dx;
            }
        } else {
            xPos += dx;
        }
    }

    public void draw(Canvas canvas) {
        if (rand == 6) {
            canvas.drawBitmap(obstacleInverterFactory.getGrayScalePterodactylSprite
                    (invertColor.getShade(), animation.getFrame()), xPos, yPos, null);
        } else {
            canvas.drawBitmap(obstacleInverterFactory.getGrayScaleCactusSprite(rand,
                    invertColor.getShade()), xPos, yPos, null);
        }
    }

    public boolean outOfScreen() {
        return xPos + width < 0;
    }

    public boolean checkForVerticalCollision(Trex tRex) {
        return tRex.getYPos() + (tRex.getHeight() / 1.6) > yPos
                && tRex.getYPos() < yPos + (this.getHeight() / 1.6);
    }

    public boolean checkForHorizontalCollision(Trex tRex) {
        return tRex.getXPos() + (tRex.getWidth() / 1.6) > xPos
                && tRex.getXPos() < xPos + (this.getWidth() / 1.6);
    }

    public int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }
}
