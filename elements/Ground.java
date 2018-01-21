package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.Screen;

/**
 * Created by Jessé Piologo on 5/3/2017.
 */
public class Ground extends GameObject {
    private Screen screen;
    private InvertColor invertColor;
    private Bitmap rawSprite;
    private Bitmap scaledImage;
    private Bitmap[] grayScaleSprite;

    private int spriteWidth;
    private int spriteHeight;

    public Ground(Screen screen, InvertColor invertColor, Bitmap res) {
        this.screen = screen;
        this.invertColor = invertColor;

        xPos = 0;
        yPos = (int) (screen.getHeight() / 1.62);
        dx = -screen.getWidth() / 100;

        spriteWidth = 534;
        spriteHeight = 12;

        this.width = screen.getWidth();
        this.height = (int) ((screen.getHeight() / 320f) * spriteHeight);

        grayScaleSprite = new Bitmap[18];

        for (int j = 0; j < 18; j++) {
            rawSprite = Bitmap.createBitmap(res, (j * 534), 0, spriteWidth, spriteHeight);
            scaledImage = Bitmap.createScaledBitmap(rawSprite, width, height, false);
            grayScaleSprite[j] = scaledImage;
        }
    }

    public void update() {
        xPos += dx;

        if (xPos < -screen.getWidth()) {
            xPos += screen.getWidth();
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(grayScaleSprite[invertColor.getShade()], xPos, yPos, null);
        if (xPos < 0) {
            canvas.drawBitmap(grayScaleSprite[invertColor.getShade()], xPos + screen.getWidth(), yPos, null);
        }
    }

    public int getDx() {
        return dx;
    }

    public void resetDx() {
        dx = -screen.getWidth() / 100;
    }
}
