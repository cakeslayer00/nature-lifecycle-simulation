package dev.sv.action;

import dev.sv.entity.Grass;
import dev.sv.entity.Prey;
import dev.sv.map.GameMap;
import dev.sv.util.ActionUtils;

public class MaintenanceSpawnAction extends SpawnAction {

    private static final int GRASS_COUNT = 3;
    private static final int TO_GENERATE_GRASS_AMOUNT = 2;
    private static final int PREY_COUNT = 4;
    private static final int TO_GENERATE_PREY_AMOUNT = 2;

    public MaintenanceSpawnAction(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    public void execute() {
        maintainGrass();
        maintainPrey();
    }

    private void maintainPrey() {
        int count = ActionUtils.gatherTargetEntities(gameMap, Prey.class).size();

        if (count < PREY_COUNT) {
            spawn(gameMap, (coordinate -> new Prey(PREY_SPEED, PREY_HEALTH, coordinate)), TO_GENERATE_PREY_AMOUNT);
        }
    }

    private void maintainGrass() {
        int count = ActionUtils.gatherTargetEntities(gameMap, Grass.class).size();

        if (count < GRASS_COUNT) {
            spawn(gameMap, (_ -> new Grass()), TO_GENERATE_GRASS_AMOUNT);
        }
    }

}
