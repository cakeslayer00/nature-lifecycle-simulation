package dev.sv.action;

import dev.sv.entity.*;
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
        spawn(gameMap, (_ -> new Rock()), ROCK_COUNT);
        spawn(gameMap, (_ -> new Grass()), GRASS_COUNT);
        spawn(gameMap, (_ -> new Tree()), TREE_COUNT);
        spawn(gameMap, (coordinate -> new Prey(PREY_SPEED, PREY_HEALTH, coordinate)), PREY_COUNT);
        spawn(gameMap, (coordinate -> new Predator(PREDATOR_SPEED, PREDATOR_HEALTH, PREDATOR_ATTACK_STRENGTH, coordinate)), PREDATOR_COUNT);
    }

}
