package com.jessepiologo.DinoMeteorShower.graphics;

import android.graphics.Bitmap;

import com.jessepiologo.DinoMeteorShower.elements.GameObject;
import com.jessepiologo.DinoMeteorShower.engine.Screen;

/**
 * Created by James on 9/21/2017.
 */

public class GameOverInverterFactory extends GameObject {
    private Bitmap image;
    private Bitmap scaledImage;
    private Bitmap[] grayScaleSpriteArray = new Bitmap[18];

    private int spriteWidth;
    private int spriteHeight;
    private int spriteY = 0;

    public GameOverInverterFactory(Screen screen, Bitmap res) {
        spriteWidth = 192;
        spriteHeight = 66;

        width = (int) ((screen.getHeight() / 320f) * spriteWidth);
        height = (int) ((screen.getHeight() / 320f) * spriteHeight);
        System.out.println("gameoverInverte res: " +width + "  x " + height);

        for (int i = 0; i < 18; i++) {
            image = Bitmap.createBitmap(res, (i * spriteWidth), spriteY, spriteWidth, spriteHeight);
            scaledImage = Bitmap.createScaledBitmap(image, width, height, false);
            grayScaleSpriteArray[i] = scaledImage;
        }
    }

    public Bitmap getGrayScaleSprite(int shade) {
        return grayScaleSpriteArray[shade];
    }
}
