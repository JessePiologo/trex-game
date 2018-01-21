package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.Animation;
import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.Screen;
import com.jessepiologo.DinoMeteorShower.engine.Sound;

/**
 * Created by Jessé Piologo on 4/30/2017.
 */

public class Trex extends GameObject {
    private Bitmap[] tRexCollisionGrayScale;
    private Bitmap[] tRexJumpingingGrayScale;
    private Bitmap[] tRexBlinkingGrayScale;
    private Screen screen;
    private InvertColor invertColor;
    //private Sound sound;

    private Bitmap tRexCollision;
    private Bitmap tRexJumping;
    private Bitmap tRexBlinking;
    private Bitmap[][] tRexRunningGrayScale;

    private boolean up;
    private boolean playing;
    private boolean collision = false;
    private Animation animation = new Animation();
    private int[] frames = new int[2];

    private boolean isJumping;
    private boolean newGame;

    private Bitmap[] imageArray;
    private Bitmap[] scaledImageArray;

    protected double gDelta;
    protected double vDelta = 0;
    protected double vy;
    protected int restingY;

    private int spriteWidth;
    private int spriteHeight;

    private boolean blink;
    private long startTime;
    private int count = 0;

    public Trex(Screen screen, InvertColor invertColor, Sound sound, Bitmap res) {
        this.screen = screen;
        this.invertColor = invertColor;
        //this.sound = sound;

        xPos = (int) (screen.getWidth() / 40);
        newGame = true;

        spriteWidth = 44;
        spriteHeight = 47;

        this.width = (int) ((screen.getHeight() / 320f) * spriteWidth);
        this.height = (int) ((screen.getHeight() / 320f) * spriteHeight);

        yPos = (int) (screen.getHeight() / 1.97f);
        restingY = yPos;

        this.vy = -screen.getHeight() / 27f;
        gDelta = screen.getHeight() / 400f;

        vDelta = vy;
        tRexJumpingingGrayScale = new Bitmap[18];
        tRexCollisionGrayScale = new Bitmap[18];
        tRexBlinkingGrayScale = new Bitmap[18];

        for (int j = 0; j < 18; j++) {
            tRexJumping = Bitmap.createBitmap(res, (j * 220) + 88, 0, spriteWidth, spriteHeight);
            tRexJumping = Bitmap.createScaledBitmap(tRexJumping, width, height, false);
            tRexJumpingingGrayScale[j] = tRexJumping;
        }
        for (int j = 0; j < 18; j++) {
            tRexCollision = Bitmap.createBitmap(res, (j * 220) + 132, 0, spriteWidth, spriteHeight);
            tRexCollision = Bitmap.createScaledBitmap(tRexCollision, width, height, false);
            tRexCollisionGrayScale[j] = tRexCollision;
        }
        for (int j = 0; j < 18; j++) {
            tRexBlinking = Bitmap.createBitmap(res, (j * 220) + 176, 0, spriteWidth, spriteHeight);
            tRexBlinking = Bitmap.createScaledBitmap(tRexBlinking, width, height, false);
            tRexBlinkingGrayScale[j] = tRexBlinking;
        }

        imageArray = new Bitmap[2];
        scaledImageArray = new Bitmap[2];
        tRexRunningGrayScale = new Bitmap[18][2];

        for (int c = 0; c < 18; c++) {

            for (int l = 0; l < imageArray.length; l++) {
                imageArray[l] = Bitmap.createBitmap(res, (c * 220) + (l * spriteWidth), 0, spriteWidth, spriteHeight);
                scaledImageArray[l] = Bitmap.createScaledBitmap(imageArray[l], width, height, false);
                tRexRunningGrayScale[c][l] = scaledImageArray[l];
            }

        }
        //would be better to pass just ints rather the whole image
        animation.setFrames(frames);
        animation.setDelay(80);


    }

    public void setUp(boolean b) {
        up = b;
    }

    public void update() {
        animation.update();
        if (up) {
            yPos += vDelta;
            vDelta += gDelta;
            isJumping = true;

            if (yPos >= restingY) {
                yPos = restingY;
                vDelta = vy;
                setUp(false);
                isJumping = false;
            }
        }
    }

    public void draw(Canvas canvas) {
        if (!collision) {
            if (up) {
                canvas.drawBitmap(tRexJumpingingGrayScale[invertColor.getShade()], xPos, yPos,
                        null);
            } else if (newGame) {//change to waiting
                //the blink animation goes here, but i do not know how to animate yet
                if (blink) {
                    count++;
                    if (count > 15) {
                        blink = false;
                        count = 0;
                    }
                    canvas.drawBitmap(tRexBlinkingGrayScale[invertColor.getShade()], xPos, yPos,
                            null);
                } else {
                    canvas.drawBitmap(tRexJumpingingGrayScale[invertColor.getShade()], xPos, yPos,
                            null);
                }
            } else {
                canvas.drawBitmap(tRexRunningGrayScale[invertColor.getShade()][animation.getFrame()]
                        , xPos,
                        yPos, null);
            }
        } else {
            canvas.drawBitmap(tRexCollisionGrayScale[invertColor.getShade()], xPos, yPos, null);
        }
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public void resetDY() {
        yPos = (int) (screen.getHeight() / 1.97f);
    }

    public void setCollision(boolean b) {
        collision = b;
    }

    public boolean getJumping() {
        return isJumping;
    }

    public void setNewGame(boolean b) {
        newGame = b;
    }

    public void setEyeBlink(boolean b) {
        blink = b;
    }

    public boolean outOfScreen() {
        return xPos > screen.getWidth();
    }
}
