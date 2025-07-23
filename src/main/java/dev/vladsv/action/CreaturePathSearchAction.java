package dev.vladsv.action;

import dev.vladsv.Coordinate;
import dev.vladsv.entity.Creature;
import dev.vladsv.entity.Entity;
import dev.vladsv.entity.Grass;
import dev.vladsv.entity.Herbivore;
import dev.vladsv.map.GameMap;
import dev.vladsv.util.SearchUtils;

import java.util.Map;

public class CreaturePathSearchAction extends Action {

    public CreaturePathSearchAction(GameMap map) {
        super(map);
    }

    @Override
    public void execute() {
        findPath();
    }

    private void findPath() {
        for (Map.Entry<Coordinate, Entity> entry : map.entrySet()) {
            if (entry.getValue() instanceof Creature creature && !creature.hasPath()) {
                creature.setPath(creature instanceof Herbivore ?
                        SearchUtils.findByType(entry.getKey(), map, Grass.class) :
                        SearchUtils.findByType(entry.getKey(), map, Herbivore.class));
            }
        }
    }
}
