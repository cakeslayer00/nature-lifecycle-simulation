package dev.sv.map;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;

public interface GameMap {

    Entity getEntity(Coordinate coordinate);

    void putEntity(Coordinate coordinate, Entity entity);

    void removeEntity(Coordinate coordinate);

    boolean contains(Coordinate coordinate);

    int getVerticalBound();

    int getHorizontalBound();
}
