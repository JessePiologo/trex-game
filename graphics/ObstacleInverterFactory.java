package com.jessepiologo.DinoMeteorShower.graphics;

import android.graphics.Bitmap;

import com.jessepiologo.DinoMeteorShower.elements.GameObject;
import com.jessepiologo.DinoMeteorShower.engine.Screen;

/**
 * Created by Jessé Piologo on 6/18/2017.
 */

public class ObstacleInverterFactory extends GameObject {
    private Bitmap image;
    private Bitmap scaledImage;
    private Bitmap[][] grayScaleSpriteArrayCactus = new Bitmap[6][18];;
    private Bitmap[][] grayScaleSpriteArrayPterodactyl = new Bitmap[18][2];

    private int spriteWidth;
    private int spriteHeight;

    private int spriteX;
    private int spriteY = 0;
    private int spritesheetWidth;

    public ObstacleInverterFactory(Screen screen, Bitmap res) {
        for (int i = 0; i < 7; i++) {
            switch (i) {
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
            if (i == 6) {
                spriteX = 252;
                spritesheetWidth = 344;
                spriteWidth = 46;
                spriteHeight = 40;

                width = (int) ((screen.getHeight() / 320f) * spriteWidth);
                height = (int) ((screen.getHeight() / 320f) * spriteHeight);

                for (int c = 0; c < 18; c++) {
                    for (int l = 0; l < 2; l++) {
                        image = Bitmap.createBitmap(res, (c * spritesheetWidth) + spriteX +
                                (l * spriteWidth), spriteY, spriteWidth, spriteHeight);
                        scaledImage = Bitmap.createScaledBitmap(image, width, height, false);
                        grayScaleSpriteArrayPterodactyl[c][l] = scaledImage;
                    }
                }

            } else {
                spritesheetWidth = 344;
                width = (int) ((screen.getHeight() / 320f) * spriteWidth);
                height = (int) ((screen.getHeight() / 320f) * spriteHeight);

                for (int l = 0; l < 18; l++) {
                    image = Bitmap.createBitmap(res, (spritesheetWidth * l) + spriteX, spriteY,
                            spriteWidth, spriteHeight);
                    scaledImage = Bitmap.createScaledBitmap(image, width, height, false);
                    grayScaleSpriteArrayCactus[i][l] = scaledImage;
                }
            }
        }
    }

    public Bitmap getGrayScaleCactusSprite(int rand, int shade) {
        return grayScaleSpriteArrayCactus[rand][shade];
    }

    public Bitmap getGrayScalePterodactylSprite(int shade, int frame) {
        return grayScaleSpriteArrayPterodactyl[shade][frame];
    }
}
