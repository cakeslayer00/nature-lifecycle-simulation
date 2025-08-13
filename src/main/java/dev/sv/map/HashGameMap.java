package dev.sv.map;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class HashGameMap implements GameMap {

    private final Map<Coordinate, Entity> entities;
    private final int horizontal;
    private final int vertical;

    public HashGameMap(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.entities = new HashMap<>();
    }

    @Override
    public Entity getEntity(Coordinate coordinate) {
        return entities.get(coordinate);
    }

    @Override
    public void putEntity(Coordinate coordinate, Entity entity) {
        entities.put(coordinate, entity);
    }

    @Override
    public void removeEntity(Coordinate coordinate) {
        entities.remove(coordinate);
    }

    @Override
    public boolean contains(Coordinate coordinate) {
        return !isEmptyCoordinate(coordinate);
    }

    @Override
    public int getVerticalBound() {
        return vertical;
    }

    @Override
    public int getHorizontalBound() {
        return horizontal;
    }

    private boolean isEmptyCoordinate(Coordinate coordinate) {
        return entities.getOrDefault(coordinate, null) == null;
    }

}
