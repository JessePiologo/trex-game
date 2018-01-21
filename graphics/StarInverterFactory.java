package com.jessepiologo.DinoMeteorShower.graphics;

/**
 * Created by Jessé Piologo on 6/27/2017.
 */

import android.graphics.Bitmap;

import com.jessepiologo.DinoMeteorShower.elements.GameObject;
import com.jessepiologo.DinoMeteorShower.engine.Screen;


public class StarInverterFactory extends GameObject {
    private Bitmap image;
    private Bitmap scaledImage;
    private Bitmap[][] grayScaleSpriteArray = new Bitmap[3][18];

    private int spriteWidth;
    private int spriteHeight;
    private int spriteX;
    private int spriteY = 0;

    public StarInverterFactory(Screen screen, Bitmap res) {
        spriteX = 30;
        spriteWidth = 10;
        spriteHeight = 11;

        width = (int) ((screen.getHeight() / 320f) * spriteWidth);
        height = (int) ((screen.getHeight() / 320f) * spriteHeight);

        for (int c = 0; c < 18; c++) {
            for (int l = 0; l < 3; l++) {
                image = Bitmap.createBitmap(res, (c * spriteX) + (l * spriteWidth), spriteY,
                        spriteWidth, spriteHeight);
                scaledImage = Bitmap.createScaledBitmap(image, width, height, false);
                grayScaleSpriteArray[l][c] = scaledImage;
            }
        }
    }

    public Bitmap getGrayScaleSprite(int rand, int shade) {
        return grayScaleSpriteArray[rand][shade];
    }
}
