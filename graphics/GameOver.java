package com.jessepiologo.DinoMeteorShower.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.elements.GameObject;
import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.Screen;

/**
 * Created by Jessé Piologo on 5/15/2017.
 */

public class GameOver extends GameObject {
    private InvertColor invertcolor;
    private final GameOverInverterFactory gameOverInverterFactory;

    public GameOver(Screen screen, InvertColor invertColor,
                    GameOverInverterFactory gameOverInverterFactory) {
        this.invertcolor = invertColor;
        this.gameOverInverterFactory = gameOverInverterFactory;

        yPos = screen.getHeight() / 3;
        xPos = (screen.getWidth() / 2) - (gameOverInverterFactory.getWidth() / 2);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(gameOverInverterFactory.getGrayScaleSprite(invertcolor.getShade()),
                xPos, yPos, null);
    }
}
