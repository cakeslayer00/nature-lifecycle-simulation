package dev.sv.map;

import dev.sv.Coordinate;
import dev.sv.Simulation;
import dev.sv.entity.Entity;

import java.util.HashMap;

public class GameMapImpl implements GameMap {

    private final HashMap<Coordinate, Entity> map;

    public GameMapImpl() {
        this.map = new HashMap<>();

        for (int i = 0; i < Simulation.HORIZONTAL_BOUND; i++) {
            for (int j = 0; j < Simulation.VERTICAL_BOUND; j++) {
                map.put(new Coordinate(j, i), null);
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
    public void removeEntity(Coordinate coordinate) {
        map.remove(coordinate);
    }

    @Override
    public boolean contains(Coordinate coordinate) {
        return map.get(coordinate) != null;
    }

}
