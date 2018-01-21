package com.jessepiologo.DinoMeteorShower.graphics;

import android.graphics.Bitmap;

import com.jessepiologo.DinoMeteorShower.elements.GameObject;
import com.jessepiologo.DinoMeteorShower.engine.Screen;

/**
 * Created by Jessé Piologo on 6/27/2017.
 */

public class GroundBumpInverterFactory extends GameObject {
    private Bitmap image;
    private Bitmap scaledImage;
    private Bitmap[][] grayScaleSpriteArray = new Bitmap[18][2];

    private int spriteWidth;
    private int spriteHeight;
    private int spriteSheetWidth;
    private int spriteY = 0;

    public GroundBumpInverterFactory(Screen screen, Bitmap res) {
        spriteSheetWidth = 116;
        spriteWidth = 58;
        spriteHeight = 9;

        width = (int) ((screen.getHeight() / 320f) * spriteWidth);
        height = (int) ((screen.getHeight() / 320f) * spriteHeight);

        for (int l = 0; l < 18; l++) {
            for (int c = 0; c < 2; c++) {
                image = Bitmap.createBitmap(res, (l * spriteSheetWidth) + c * spriteWidth,
                        spriteY, spriteWidth, spriteHeight);
                scaledImage = Bitmap.createScaledBitmap(image, width, height, false);
                grayScaleSpriteArray[l][c] = scaledImage;
            }
        }
    }

    public Bitmap getGrayScaleSprite(int shade, int rand) {
        return grayScaleSpriteArray[shade][rand];
    }
}
