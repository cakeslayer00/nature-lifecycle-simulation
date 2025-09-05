package dev.sv.action;

import dev.sv.Coordinate;
import dev.sv.entity.Creature;
import dev.sv.map.GameMap;
import dev.sv.util.ActionUtils;

import java.util.List;

public class SweepDeadCreaturesAction extends Action {

    public SweepDeadCreaturesAction(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    public void execute() {
        List<Creature> creatures = ActionUtils.gatherTargetEntities(gameMap, Creature.class);

        for (Creature creature : creatures) {
            if (creature.isDead()) {
                Coordinate pos = creature.getCoordinate();
                gameMap.removeEntity(pos);
            }
        }
    }

}
