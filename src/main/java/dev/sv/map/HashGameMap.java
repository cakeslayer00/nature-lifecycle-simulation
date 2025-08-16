package dev.sv.map;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static dev.sv.util.CoordinateUtils.isOutOfBounds;

public final class HashGameMap implements GameMap {

    private final Map<Coordinate, Entity> entities;
    private final int horizontal;
    private final int vertical;

    public HashGameMap(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.entities = new HashMap<>();
    }

    @Override
    public Optional<Entity> getEntity(Coordinate coordinate) {
        if (isOutOfBounds(coordinate, horizontal, vertical)) {
            throw new IllegalArgumentException("Coordinate not found");
        }

        return Optional.ofNullable(entities.get(coordinate));
    }

    @Override
    public void putEntity(Coordinate coordinate, Entity entity) {
        if (isOutOfBounds(coordinate, horizontal, vertical)) {
            throw new IllegalArgumentException("Coordinate not found");
        }

        entities.put(coordinate, entity);
    }

    @Override
    public void removeEntity(Coordinate coordinate) {
        if (isOutOfBounds(coordinate, horizontal, vertical)) {
            throw new IllegalArgumentException("Coordinate not found");
        }

        entities.remove(coordinate);
    }

    @Override
    public boolean containsEntity(Coordinate coordinate) {
        if (isOutOfBounds(coordinate, horizontal, vertical)) {
            throw new IllegalArgumentException("Coordinate not found");
        }

        return entities.getOrDefault(coordinate, null) != null;
    }

    @Override
    public int getVerticalBound() {
        return vertical;
    }

    @Override
    public int getHorizontalBound() {
        return horizontal;
    }

}
