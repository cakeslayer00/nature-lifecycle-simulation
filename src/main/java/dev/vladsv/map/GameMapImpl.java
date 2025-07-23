package dev.vladsv.map;

import dev.vladsv.Coordinate;
import dev.vladsv.Simulation;
import dev.vladsv.entity.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GameMapImpl implements GameMap {

    private final HashMap<Coordinate, Entity> map = new HashMap<>();

    public GameMapImpl() {
        for (int i = 0; i < Simulation.MAP_HEIGHT; i++) {
            for (int j = 0; j < Simulation.MAP_WIDTH; j++) {
                map.put(new Coordinate(i, j), null);
            }
        }
    }

    @Override
    public Entity getEntity(Coordinate coordinate) {
        return map.get(coordinate);
    }

    @Override
    public void putEntity(Coordinate coordinate, Entity entity) {
        map.put(coordinate, entity);
    }

    @Override
    public Entity removeEntity(Coordinate coordinate) {
        Entity removed = map.remove(coordinate);
        map.put(coordinate, null);
        return removed;
    }

    @Override
    public Collection<Entity> getEntities() {
        return map.values();
    }

    @Override
    public Set<Map.Entry<Coordinate, Entity>> entrySet() {
        return map.entrySet();
    }
}
