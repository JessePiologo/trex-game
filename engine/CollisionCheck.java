package com.jessepiologo.DinoMeteorShower.engine;

import com.jessepiologo.DinoMeteorShower.elements.Trex;

/**
 * Created by Jessé Piologo on 5/11/2017.
 */

public class CollisionCheck {
    private Trex tRex;
    private ObstacleFactory obstacleFactory;

    public CollisionCheck(Trex tRex, ObstacleFactory obstacleFactory) {
        this.tRex = tRex;
        this.obstacleFactory = obstacleFactory;
    }

    public boolean checkForCollision() {
        return obstacleFactory.checkForCollision(tRex);
    }
}
