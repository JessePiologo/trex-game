package com.jessepiologo.DinoMeteorShower.engine;

/**
 * Created by Jessé Piologo on 7/22/2017.
 */

public class InvertColor {
    private boolean invertColor;
    private int shade = 0;
    private int transitionCounter = 0;
    private int counter = 0;
    private boolean white;

    private int invertTimer = 0;
    private boolean inverted;
    private int invertCounter;
    private int moonPhaseCounter = 0;

    public void update() {
        if (invertTimer == 700) {
            inverted = true;
            invertCounter = 0;
            invertTimer = 0;

            moonPhaseCounter++;

            if (moonPhaseCounter == 8) {
                moonPhaseCounter = 0;
            }
            setInvertColor(true);
        }

        if (inverted && invertCounter == 300) {

            inverted = false;
            invertCounter = 0;

            setInvertColor(true);
        }

        if (invertColor) {
            if (transitionCounter <= 48) {
                if (transitionCounter % 3 == 0) {
                    if (counter == 0) {
                        white = true;
                    }

                    if (counter == 17) {
                        white = false;
                    }

                    if (white) {
                        counter++;
                    } else {
                        counter--;
                    }
                    setShade(counter);
                }
            }
            transitionCounter++;

            if (transitionCounter >= 49) {
                invertColor = false;
                white = false;
                //counter = 0;
                transitionCounter = 0;
            }
        }
    }

    public void setInvertColor(boolean invertColor) {
        this.invertColor = invertColor;
    }

    public void setShade(int shade) {
        this.shade = shade;
    }

    public int getShade() {
        return shade;
    }

    public void resetInvertColor() {
        invertTimer = 0;
        inverted = false;
        invertCounter = 0;
    }

    public void increaseTimer() {
        invertTimer++;
        invertCounter++;
    }

    public boolean getInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public int getMoonPhase() {
        return moonPhaseCounter;
    }
}
