package dev.vladsv.action;

import dev.vladsv.entity.Creature;
import dev.vladsv.entity.Grass;
import dev.vladsv.entity.Herbivore;
import dev.vladsv.map.GameMap;
import dev.vladsv.util.SearchUtils;

public class CreaturePathSearchAction extends Action {

    public CreaturePathSearchAction(GameMap map) {
        super(map);
    }

    @Override
    public void execute() {
        map.getEntities().entrySet().stream()
                .filter(entry ->
                        entry.getValue() instanceof Creature creature && !creature.hasPath())
                .forEach(entry -> {
                    Creature creature = (Creature) entry.getValue();
                    creature.setPath(creature instanceof Herbivore ?
                            SearchUtils.findByType(entry.getKey(), map, Grass.class) :
                            SearchUtils.findByType(entry.getKey(), map, Herbivore.class));
                });
    }
}
