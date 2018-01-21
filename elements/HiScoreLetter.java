package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.Screen;
import com.jessepiologo.DinoMeteorShower.graphics.HiScoreLetterInverterFactory;

/**
 * Created by Jessé Piologo on 5/29/2017.
 */

public class HiScoreLetter extends GameObject {
    private InvertColor invertColor;
    private HiScoreLetterInverterFactory hiScoreLetterInverterFactory;
    private int[] numbers = new int[5];

    private int space;
    private boolean draw;

    public HiScoreLetter(Screen screen, InvertColor invertColor, CurrentScore currentScore,
                         HiScoreLetterInverterFactory hiScoreLetterInverterFactory) {
        this.invertColor = invertColor;
        this.hiScoreLetterInverterFactory = hiScoreLetterInverterFactory;

        xPos = currentScore.getXPos() - (hiScoreLetterInverterFactory.getWidth() * 6);
        yPos = hiScoreLetterInverterFactory.getHeight() * 3;
        space = (screen.getHeight() / 32);
    }

    public void draw(Canvas canvas) {
        if (draw) {
            for (int i = 0; i < 5; i++) {
                canvas.drawBitmap(hiScoreLetterInverterFactory.getGrayScaleSprite(numbers[i],
                        invertColor.getShade()), xPos + (i * space), yPos, null);
            }
        }
    }

    public void setDraw(boolean b) {
        this.draw = b;
    }

    public void setImageNumbers(int[] numbers) {
        this.numbers = numbers;
    }
}


