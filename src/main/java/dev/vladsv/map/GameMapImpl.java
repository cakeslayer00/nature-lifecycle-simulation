package dev.vladsv.map;

import dev.vladsv.Coordinate;
import dev.vladsv.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class GameMapImpl implements GameMap {

    private final HashMap<Coordinate, Entity> map = new HashMap<>();

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
    public Map<Coordinate, Entity> getEntities() {
        return map;
    }
}
