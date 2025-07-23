package dev.vladsv;

import dev.vladsv.action.CreatureMoveAction;
import dev.vladsv.action.CreaturePathSearchAction;
import dev.vladsv.action.CreatureStateCheckAction;
import dev.vladsv.action.SpawnEntitiesAction;
import dev.vladsv.map.GameMap;
import dev.vladsv.render.Renderer;

public final class Simulation {

    public static final int MAP_HEIGHT = 10;
    public static final int MAP_WIDTH = 15;

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
        new SpawnEntitiesAction(gameMap).execute();

        renderer.render(gameMap);

        while (!gameMap.getEntities().isEmpty()) {
            System.out.println("--------------------------");
            new CreaturePathSearchAction(gameMap).execute();
            new CreatureMoveAction(gameMap).execute();
            new CreatureStateCheckAction(gameMap).execute();
            renderer.render(gameMap);
        }
    }

    public void pauseSimulation() {
        isPaused = !isPaused;
    }
}
