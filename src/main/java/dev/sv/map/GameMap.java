package dev.sv.map;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;

import java.util.Optional;

public interface GameMap {

    Optional<Entity> getEntity(Coordinate coordinate);

    void putEntity(Coordinate coordinate, Entity entity);

    void removeEntity(Coordinate coordinate);

    boolean containsEntity(Coordinate coordinate);

    int getVerticalBound();

    int getHorizontalBound();

}
