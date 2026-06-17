package dev.sv.map;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface GameMap {

    Optional<Entity> getEntity(Coordinate coordinate);

    <T extends Entity> List<T> getTargetEntities(Class<T> target);

    <T> long getTargetEntityCount(Class<T> target);

    void putEntity(Coordinate coordinate, Entity entity);

    void removeEntity(Coordinate coordinate);

    boolean containsEntity(Coordinate coordinate);

    int getVerticalBound();

    int getHorizontalBound();

}
