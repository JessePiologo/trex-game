package com.jessepiologo.DinoMeteorShower.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jessepiologo.DinoMeteorShower.R;
import com.jessepiologo.DinoMeteorShower.elements.CurrentScore;
import com.jessepiologo.DinoMeteorShower.elements.Ground;
import com.jessepiologo.DinoMeteorShower.elements.HiScoreLetter;
import com.jessepiologo.DinoMeteorShower.elements.ScoreLetters;
import com.jessepiologo.DinoMeteorShower.elements.Trex;
import com.jessepiologo.DinoMeteorShower.graphics.Background;
import com.jessepiologo.DinoMeteorShower.graphics.CloudInverterFactory;
import com.jessepiologo.DinoMeteorShower.graphics.GameOver;
import com.jessepiologo.DinoMeteorShower.graphics.GameOverInverterFactory;
import com.jessepiologo.DinoMeteorShower.graphics.GroundBumpInverterFactory;
import com.jessepiologo.DinoMeteorShower.graphics.HiScoreLetterInverterFactory;
import com.jessepiologo.DinoMeteorShower.graphics.MoonInverterFactory;
import com.jessepiologo.DinoMeteorShower.graphics.ObstacleInverterFactory;
import com.jessepiologo.DinoMeteorShower.graphics.ScoreLetterInverterFactory;
import com.jessepiologo.DinoMeteorShower.graphics.StarInverterFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Jessé Piologo on 23/04/2017.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private Screen screen;
    private Sound sound;
    private MainThread thread;
    private ObstacleInverterFactory obstacleInverterFactory;
    private MoonInverterFactory moonInverterFactory;
    private StarInverterFactory starInverterFactory;
    private CloudInverterFactory cloudInverterFactory;
    private GroundBumpInverterFactory groundBumpInverterFactory;
    private ScoreLetterInverterFactory scoreInverterFactory;
    private HiScoreLetterInverterFactory hiScoreLetterInverterFactory;
    private GameOverInverterFactory gameOverInverterFactory;
    private Background background;
    private Ground ground;
    private GameOver gameOver;
    private CloudFactory cloudFactory;
    private MoonFactory moonFactory;
    private StarFactory starFactory;
    private ObstacleFactory obstacleFactory;
    private GroundBumpFactory groundBumpFactory;
    private InvertColor invertColor;
    private Trex tRex;
    private CollisionCheck collision;

    private boolean isGameOver;
    private long startTime;

    private double speedCoeficient = 1.07;
    private int meterCoeficient = 100;
    private int speedVector;

    private int score = 0;
    private HiScoreLetter hiScore;
    private CurrentScore currentScore;
    private int hiScoreNum = 0;
    private boolean flashing;
    private int flashCounter;
    private int animationFrameCount = 0;
    private int currentScoreAnimation;
    private int stillScore;

    private File file;
    private File path;
    private BufferedReader br;
    private ScoreLetters scoreLetters;

    public GamePanel(Context context) {
        super(context);

        this.screen = new Screen(context);
        this.context = context;

        getHolder().addCallback(this);
        thread = new MainThread(context, getHolder(), this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        sound = new Sound(context);
        invertColor = new InvertColor();
        background = new Background(screen, invertColor);

        Bitmap groundResource = BitmapFactory.decodeResource(getResources(), R.drawable.ground534px100shades);
        ground = new Ground(screen, invertColor, groundResource);

        Bitmap groundBumpResource = BitmapFactory.decodeResource(getResources(), R.drawable.groundbump100shades);
        groundBumpInverterFactory = new GroundBumpInverterFactory(screen, groundBumpResource);
        groundBumpFactory = new GroundBumpFactory(screen, invertColor, groundBumpInverterFactory);

        Bitmap cloudResource = BitmapFactory.decodeResource(getResources(), R.drawable.cloudsolid100shades);
        cloudInverterFactory = new CloudInverterFactory(screen, cloudResource);
        cloudFactory = new CloudFactory(screen, invertColor, cloudInverterFactory);

        Bitmap moonResource = BitmapFactory.decodeResource(getResources(), R.drawable.moon100shades);
        moonInverterFactory = new MoonInverterFactory(screen, moonResource);
        moonFactory = new MoonFactory(screen, invertColor, moonInverterFactory);

        Bitmap starResource = BitmapFactory.decodeResource(getResources(), R.drawable.stars100shades);
        starInverterFactory = new StarInverterFactory(screen, starResource);
        starFactory = new StarFactory(screen, invertColor, starInverterFactory);

        Bitmap obstacleResource = BitmapFactory.decodeResource(getResources(), R.drawable.obstacles100shades);
        obstacleInverterFactory = new ObstacleInverterFactory(screen, obstacleResource);
        obstacleFactory = new ObstacleFactory(screen, invertColor, obstacleInverterFactory,
                this, ground);

        tRex = new Trex(screen, invertColor, sound, BitmapFactory.decodeResource(getResources(), R.drawable.trex100shades));

        Bitmap gameOverResource = BitmapFactory.decodeResource(getResources(), R.drawable.gameover100shades);
        gameOverInverterFactory = new GameOverInverterFactory(screen, gameOverResource);
        gameOver = new GameOver(screen, invertColor, gameOverInverterFactory);

        collision = new CollisionCheck(tRex, obstacleFactory);

        Bitmap scoreResource = BitmapFactory.decodeResource(getResources(), R.drawable.numbers100shades);
        scoreInverterFactory = new ScoreLetterInverterFactory(screen, scoreResource);

        Bitmap hiScoreResource = BitmapFactory.decodeResource(getResources(), R.drawable.numbershi100shades);
        hiScoreLetterInverterFactory = new HiScoreLetterInverterFactory(screen, hiScoreResource);

        currentScore = new CurrentScore(screen, invertColor, this, scoreInverterFactory);
        hiScore = new HiScoreLetter(screen, invertColor, currentScore, hiScoreLetterInverterFactory);
        scoreLetters = new ScoreLetters(screen, invertColor, currentScore, hiScoreLetterInverterFactory);

        //moonPhaseCounter = getRandomNum(0, 7);

        path = context.getFilesDir();
        file = new File(path, "hiscore.txt");

        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = br.readLine()) != null) {
                hiScoreNum = Integer.parseInt(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

        startTime = System.nanoTime();

        if (thread.getState() == Thread.State.TERMINATED) {
            thread = new MainThread(context, getHolder(), this);
            thread.setRunning(true);
            thread.start();

        } else {
            thread.setRunning(true);

            thread.start();
        }

        if (hiScoreNum > 0) {
            hiScore.setImageNumbers(ScoreManager.intToArray(hiScoreNum));
            scoreLetters.setDraw(true);
            hiScore.setDraw(true);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;

        while (retry && counter < 1000) {
            counter++;
            try {

                thread.setRunning(false);
                thread.join();
                retry = false;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() throws IOException {

        if (tRex.getPlaying()) {

            long elapsed = (System.nanoTime() - startTime) / 1000000;

            if (elapsed > meterCoeficient) {
                score++;
                invertColor.increaseTimer();

                startTime = System.nanoTime();

                if (score % 100 == 0) {
                    sound.playSound(Sound.SCORE);

                    if (score < 900) {

                        speedVector = (int) ((Math.ceil(-ground.getDx() * speedCoeficient))
                                * -1);

                        obstacleFactory.setObstacleGap((screen.getWidth() *
                                Math.abs(ground.getDx())) / (screen.getWidth() / 30));

                        meterCoeficient /= 1.07;

                        ground.setDx(speedVector);
                        groundBumpFactory.setDx(speedVector);
                        obstacleFactory.setDx(speedVector);
                    }
                }
            }

            setCurrentScoreAnimation(score);

            if (score % 100 == 0 || flashing) {

                flashing = true;
                animationFrameCount++;

                if (score % 100 == 0) {
                    currentScoreAnimation = score;
                    stillScore = score;
                }

                if (flashCounter <= 15) {

                    currentScore.setDraw(false);
                    flashCounter++;

                } else if (flashCounter <= 30) {
                    currentScore.setDraw(true);

                    setCurrentScoreAnimation(stillScore);

                    flashCounter++;

                } else if (flashCounter > 30) {

                    flashCounter = 0;
                }

                if (animationFrameCount >= 120) {

                    flashCounter = 0;
                    flashing = false;
                    animationFrameCount = 0;
                    setCurrentScoreAnimation(score);
                }
            }

            background.update();
            ground.update();
            groundBumpFactory.update();
            starFactory.update();
            cloudFactory.update();
            obstacleFactory.update();
            moonFactory.update();
            tRex.update();
            currentScore.update();

            invertColor.update();

            if (collision.checkForCollision()) {
                tRex.setPlaying(false);
                tRex.setCollision(true);
                isGameOver = true;
                sound.playSound(Sound.COLLISION);

                if (hiScoreNum < score) {
                    hiScoreNum = score;
                    hiScore.setImageNumbers(ScoreManager.intToArray(hiScoreNum));
                    scoreLetters.setDraw(true);
                    hiScore.setDraw(true);

                    String s = Integer.toString(score);

                    path = context.getFilesDir();
                    file = new File(path, "hiscore.txt");
                    FileOutputStream stream = new FileOutputStream(file);

                    try {
                        stream.write(s.getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        stream.close();
                    }
                }
            }

        } else {
            long elapsedEyeBlink = (System.nanoTime() - startTime) / 1000000;

            if (elapsedEyeBlink > getRandomNum(3, 10) * 1000) {
                startTime = System.nanoTime();
                tRex.setEyeBlink(true);
            }
        }
    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            background.draw(canvas);
            ground.draw(canvas);

            groundBumpFactory.draw(canvas);
            starFactory.draw(canvas);
            moonFactory.draw(canvas);
            cloudFactory.draw(canvas);
            obstacleFactory.draw(canvas);
            scoreLetters.draw(canvas);
            currentScore.draw(canvas);
            hiScore.draw(canvas);
            tRex.draw(canvas);

            if (isGameOver) {
                gameOver.draw(canvas);
            }

        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //running
            if (tRex.getPlaying()) {

                tRex.setUp(true);

                if (!tRex.getJumping()) {
                    sound.playSound(Sound.BUTTONPRESS);
                }

            } else if (isGameOver) {
                //the touchListener just set variables, don't execute
                reset();
                tRex.setPlaying(true);
                sound.playSound(Sound.BUTTONPRESS);
                isGameOver = false; //I need to check if this IF is really useful
                //start running
            } else {
                tRex.setNewGame(false);
                tRex.setPlaying(true);
                tRex.setUp(true);
                sound.playSound(Sound.BUTTONPRESS);
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public int getScore() {
        return score;
    }

    public int getRandomNum(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public int getCurrentAnimationScore() {
        return currentScoreAnimation;
    }

    public void setCurrentScoreAnimation(int currentScoreAnimation) {
        this.currentScoreAnimation = currentScoreAnimation;
    }

    public void reset() {
        currentScore.setDraw(true);
        flashing = false;
        animationFrameCount = 0;
        flashCounter = 0;
        currentScoreAnimation = 0;

        invertColor.resetInvertColor();

        startTime = System.nanoTime();
        tRex.setNewGame(false);//yet an issue
        tRex.setCollision(false);
        tRex.resetDY();
        //score start with one is due a startTime issue
        score = 1;
        meterCoeficient = 100;
        ground.resetDx();

        cloudFactory = new CloudFactory(screen, invertColor, cloudInverterFactory);
        groundBumpFactory = new GroundBumpFactory(screen, invertColor, groundBumpInverterFactory);
        obstacleFactory = new ObstacleFactory(screen, invertColor, obstacleInverterFactory,
                this, ground);
        collision = new CollisionCheck(tRex, obstacleFactory);

        background.resetRGB();
        invertColor.setShade(0);
        invertColor.setInverted(false);
    }
}

