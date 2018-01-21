package com.jessepiologo.DinoMeteorShower.graphics;

import android.graphics.Bitmap;
import com.jessepiologo.DinoMeteorShower.elements.GameObject;
import com.jessepiologo.DinoMeteorShower.engine.Screen;

/**
 * Created by James on 9/20/2017.
 */

public class ScoreLetterInverterFactory extends GameObject{
    private Bitmap image;
    private Bitmap scaledImage;
    private Bitmap[][] grayScaleSpriteArray = new Bitmap[11][18];

    private int spriteWidth;
    private int spriteHeight;
    private int spriteX;
    private int spriteY = 0;

    public ScoreLetterInverterFactory(Screen screen, Bitmap res) {
        spriteX = 111;
        spriteWidth = 10;
        spriteHeight = 13;

        width = (int) ((screen.getHeight() / 320f) * spriteWidth);
        height = (int) ((screen.getHeight() / 320f) * spriteHeight);

        for (int c = 0; c < 18; c++) {
            for (int l = 0; l < 11; l++) {
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
