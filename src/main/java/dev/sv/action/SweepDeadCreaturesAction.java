package dev.sv.action;

import dev.sv.entity.Creature;
import dev.sv.map.GameMap;

public class SweepDeadCreaturesAction extends Action {

    public SweepDeadCreaturesAction(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    public void execute() {
        gameMap.getTargetEntities(Creature.class).stream()
                .filter(Creature::isDead)
                .map(Creature::getCoordinate)
                .forEach(gameMap::removeEntity);
    }

}
