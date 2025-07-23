package dev.vladsv.map;

import dev.vladsv.Coordinate;
import dev.vladsv.entity.Entity;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface GameMap {

    Entity getEntity(Coordinate coordinate);

    void putEntity(Coordinate coordinate, Entity entity);

    Entity removeEntity(Coordinate coordinate);

    Collection<Entity> getEntities();

    Set<Map.Entry<Coordinate, Entity>> entrySet();
}
