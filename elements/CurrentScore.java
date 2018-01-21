package com.jessepiologo.DinoMeteorShower.elements;

import android.graphics.Canvas;

import com.jessepiologo.DinoMeteorShower.engine.GamePanel;
import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.ScoreManager;
import com.jessepiologo.DinoMeteorShower.engine.Screen;
import com.jessepiologo.DinoMeteorShower.graphics.ScoreLetterInverterFactory;

/**
 * Created by Jessé Piologo on 5/19/2017.
 */

public class CurrentScore extends GameObject {
    private GamePanel gamePanel;
    private InvertColor invertColor;
    private ScoreLetterInverterFactory scoreInverterFactory;
    private int[] numbers = new int[5];
    private int space;
    private boolean draw = true;

    public CurrentScore(Screen screen, InvertColor invertColor, GamePanel gamePanel,
                        ScoreLetterInverterFactory scoreInverterFactory) {
        this.invertColor = invertColor;
        this.gamePanel = gamePanel;
        this.scoreInverterFactory = scoreInverterFactory;

        xPos = screen.getWidth() - (scoreInverterFactory.getWidth() * 7);
        yPos = scoreInverterFactory.getHeight() * 3;
        space = (screen.getHeight() / 32);
    }

    public void update() {
        numbers = ScoreManager.intToArray(gamePanel.getCurrentAnimationScore());
    }

    public void draw(Canvas canvas) {
        if (draw) {
            for (int i = 0; i < 5; i++) {
                canvas.drawBitmap(scoreInverterFactory.getGrayScaleSprite(numbers[i],
                        invertColor.getShade()), xPos + (i * space), yPos, null);
            }
        }
    }

    public void setDraw(boolean b) {
        this.draw = b;
    }
}


