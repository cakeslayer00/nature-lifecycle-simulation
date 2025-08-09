package dev.sv.action;

import dev.sv.map.GameMap;

public class InitialSpawnAction extends SpawnAction {

    private static final int TREE_COUNT = 5;
    private static final int ROCK_COUNT = 5;
    private static final int GRASS_COUNT = 5;
    private static final int PREY_COUNT = 5;
    private static final int PREDATOR_COUNT = 5;

    public InitialSpawnAction(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    public void execute() {
        generateTree(TREE_COUNT);
        generateGrass(GRASS_COUNT);
        generateRock(ROCK_COUNT);
        generatePredator(PREDATOR_COUNT);
        generatePrey(PREY_COUNT);
    }
}
