package dev.sv.action;

import dev.sv.map.GameMap;
import dev.sv.util.ActionUtils;

public class MaintenanceSpawnAction extends SpawnAction {

    public MaintenanceSpawnAction(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    public void execute() {
        maintainGrass();
        maintainPrey();
    }

    private void maintainPrey() {
        int count = ActionUtils.gatherPrey(gameMap).size();

        if (count < 3) {
            generatePrey(2);
        }
    }

    private void maintainGrass() {
        int count = ActionUtils.gatherGrass(gameMap).size();

        if (count < 4) {
            generateGrass(2);
        }
    }

}
