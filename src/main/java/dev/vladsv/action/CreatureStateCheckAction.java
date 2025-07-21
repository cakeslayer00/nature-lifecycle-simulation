package dev.vladsv.action;

import dev.vladsv.entity.Creature;
import dev.vladsv.map.GameMap;

import java.util.List;

public class CreatureStateCheckAction extends Action {

    public CreatureStateCheckAction(GameMap map) {
        super(map);
    }

    @Override
    public void execute() {
        getCreatures().forEach(this::sweep);
    }

    private List<Creature> getCreatures() {
        return map.getEntities().values().stream()
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast)
                .toList();
    }

    private void sweep(Creature creature) {
        if (!creature.isAlive()) {
            map.getEntities().remove(creature.getPath().getFirst());
        }
    }
}
