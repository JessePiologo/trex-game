package com.jessepiologo.DinoMeteorShower.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.jessepiologo.DinoMeteorShower.elements.GameObject;
import com.jessepiologo.DinoMeteorShower.engine.InvertColor;
import com.jessepiologo.DinoMeteorShower.engine.Screen;

/**
 * Created by Jessé Piologo on 23/04/2017.
 */
public class Background extends GameObject {
    private Paint paint;
    private InvertColor invertColor;
    private int n = 255;
    private int a = 255;
    private int r = 255;
    private int g = 255;
    private int b = 255;

    public Background(Screen screen, InvertColor invertColor) {
        paint = new Paint();
        this.invertColor = invertColor;
        this.width = screen.getWidth();
        this.height = screen.getHeight();
    }

    public void update() {
        r = n - (invertColor.getShade() * 15);
        g = n - (invertColor.getShade() * 15);
        b = n - (invertColor.getShade() * 15);
    }

    public void draw(Canvas canvas) {
        paint.setARGB(a, r, g, b);
        canvas.drawRect(0, 0, width, height, paint);
    }

    public void resetRGB() {
        r = 255;
        g = 255;
        b = 255;
    }
}
