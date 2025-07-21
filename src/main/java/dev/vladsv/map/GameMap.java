package dev.vladsv.map;

import dev.vladsv.Coordinate;
import dev.vladsv.entity.Entity;

import java.util.Map;

public interface GameMap {

    Entity getEntity(Coordinate coordinate);

    void putEntity(Coordinate coordinate, Entity entity);

    Entity removeEntity(Coordinate coordinate);

    Map<Coordinate, Entity> getEntities();
}
