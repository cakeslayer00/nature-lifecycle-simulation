package dev.vladsv;

import dev.vladsv.map.GameMap;
import dev.vladsv.render.Renderer;

public final class Simulation {

    public static final int LOWER_BOUND = 10;
    public static final int RIGHT_BOUND = 15;

    private final Renderer renderer;
    private final GameMap gameMap;

    private boolean isPaused;

    public Simulation(GameMap gameMap, Renderer renderer) {
        this.gameMap = gameMap;
        this.renderer = renderer;
    }

    public void nextTurn() {

    }

    public void startSimulation() {

    }

    public void pauseSimulation() {
        isPaused = !isPaused;
    }
}
