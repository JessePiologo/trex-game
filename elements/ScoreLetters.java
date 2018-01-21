package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.Screen;
import com.jessepiologo.DinoMeteorShower.graphics.HiScoreLetterInverterFactory;

/**
 * Created by James on 9/20/2017.
 */

public class ScoreLetters extends GameObject {
    private InvertColor invertColor;
    private HiScoreLetterInverterFactory hiScoreLetterInverterFactory;

    private int space;

    private boolean draw;

    public ScoreLetters(Screen screen, InvertColor invertColor, CurrentScore currentScore,
                        HiScoreLetterInverterFactory hiScoreLetterInverterFactory) {
        this.invertColor = invertColor;
        this.hiScoreLetterInverterFactory = hiScoreLetterInverterFactory;

        xPos = currentScore.getXPos() - (hiScoreLetterInverterFactory.getWidth() * 9);
        yPos = hiScoreLetterInverterFactory.getHeight() * 3;
        space = (screen.getHeight() / 32);
    }

    public void draw(Canvas canvas) {
        if (draw) {
            for (int i = 0; i < 3; i++) {
                canvas.drawBitmap(hiScoreLetterInverterFactory.getGrayScaleSprite(i + 10,
                        invertColor.getShade()), xPos + (i * space), yPos, null);
            }
        }
    }

    public void setDraw(boolean b) {
        this.draw = b;
    }
}


