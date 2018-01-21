package com.jessepiologo.DinoMeteorShower.graphics;

import android.graphics.Bitmap;

import com.jessepiologo.DinoMeteorShower.elements.GameObject;
import com.jessepiologo.DinoMeteorShower.engine.Screen;

/**
 * Created by Jessé Piologo on 6/19/2017.
 */

public class MoonInverterFactory extends GameObject {
    private Bitmap image;
    private Bitmap scaledImage;
    private Bitmap[][] grayScaleSpriteArray = new Bitmap[8][18];

    private static int MOON_QUANTITY = 8;

    private int spriteWidth;
    private int spriteHeight;

    private int spriteY = 0;
    private int spriteSheetWidth;

    public MoonInverterFactory(Screen screen, Bitmap res) {
        spriteSheetWidth = 320;
        spriteWidth = 40;
        spriteHeight = 42;

        width = (int) ((screen.getHeight() / 320f) * spriteWidth);
        height = (int) ((screen.getHeight() / 320f) * spriteHeight);

        for (int c = 0; c < 18; c++) {
            for (int l = 0; l < MOON_QUANTITY; l++) {
                image = Bitmap.createBitmap(res, (c * spriteSheetWidth) + (l * spriteWidth),
                        spriteY, spriteWidth, spriteHeight);
                scaledImage = Bitmap.createScaledBitmap(image, width, height, false);
                grayScaleSpriteArray[l][c] = scaledImage;
            }
        }
    }

    public Bitmap getGrayScaleSprite(int rand, int shade) {
        return grayScaleSpriteArray[rand][shade];
    }


}
