package com.jessepiologo.DinoMeteorShower.engine;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.jessepiologo.DinoMeteorShower.R;

/**
 * Created by Jessé Piologo on 5/12/2017.
 */

public class Sound {
    private SoundPool soundPool;
    public static int BUTTONPRESS;
    public static int SCORE;
    public static int COLLISION;

    public Sound(Context context) {
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        BUTTONPRESS = soundPool.load(context, R.raw.buttonpress, 1);
        SCORE = soundPool.load(context, R.raw.score, 1);
        COLLISION = soundPool.load(context, R.raw.collision, 1);
    }

    public void playSound(int sound) {
        soundPool.play(sound, 1, 1, 1, 0, 1);
    }
}
