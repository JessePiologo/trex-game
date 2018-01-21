package com.jessepiologo.DinoMeteorShower.graphics;

import android.graphics.Bitmap;

import com.jessepiologo.DinoMeteorShower.elements.GameObject;
import com.jessepiologo.DinoMeteorShower.engine.Screen;

/**
 * Created by Jessé Piologo on 6/19/2017.
 */

public class CloudInverterFactory extends GameObject {
    private Bitmap image;
    private Bitmap scaledImage;
    private Bitmap[] grayScaleSpriteArray = new Bitmap[18];

    private int spriteWidth;
    private int spriteHeight;

    private int spriteY = 0;

    public CloudInverterFactory(Screen screen, Bitmap res) {
        spriteWidth = 46;
        spriteHeight = 13;

        width = (int) ((screen.getHeight() / 320f) * spriteWidth);
        height = (int) ((screen.getHeight() / 320f) * spriteHeight);

        for (int i = 0; i < 7; i++) {
            for (int l = 0; l < 18; l++) {
                image = Bitmap.createBitmap(res, l * spriteWidth, spriteY, spriteWidth,
                        spriteHeight);
                scaledImage = Bitmap.createScaledBitmap(image, width, height, false);
                grayScaleSpriteArray[l] = scaledImage;
            }
        }
    }

    public Bitmap getGrayScaleSprite(int shade) {
        return grayScaleSpriteArray[shade];
    }
}
