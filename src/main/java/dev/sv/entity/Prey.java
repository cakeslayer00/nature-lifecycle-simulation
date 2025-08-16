package dev.sv.entity;

import dev.sv.Coordinate;
import dev.sv.map.GameMap;

public class Prey extends Creature {

    public static final int HEALTH_GAIN = 2;

    public Prey(int speed, int health, Coordinate coordinate) {
        super(speed, health, coordinate);
    }

    @Override
    protected void consume(GameMap gameMap, Coordinate targetCoordinate) {
        gameMap.removeEntity(targetCoordinate);
        gainHealth(HEALTH_GAIN);
    }

    @Override
    protected Class<Grass> getTargetConsumption() {
        return Grass.class;
    }

}
