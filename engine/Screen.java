package com.jessepiologo.DinoMeteorShower.engine;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Jessé Piologo on 4/27/2017.
 */

public class Screen {
    private final DisplayMetrics metrics;

    public Screen(Context context) {
        WindowManager vm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = vm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    public int getWidth() {
        return metrics.widthPixels;
    }

    public int getHeight() {
        return metrics.heightPixels;
    }
}
