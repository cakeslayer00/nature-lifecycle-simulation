package dev.sv.entity;

import dev.sv.Coordinate;
import dev.sv.map.GameMap;

public class Predator extends Creature {

    private static final int HEALTH_GAIN = 4;

    private final int attackStrength;

    public Predator(int speed, int health, int attackStrength, Coordinate coordinate) {
        super(speed, health, coordinate);
        this.attackStrength = attackStrength;
    }

    @Override
    protected void consume(GameMap gameMap, Coordinate targetCoordinate) {
        Class<Prey> target = getTargetConsumption();
        target.cast(gameMap.getEntity(targetCoordinate).orElseThrow()).getWounded(attackStrength);
        gainHealth(HEALTH_GAIN);
    }

    @Override
    protected Class<Prey> getTargetConsumption() {
        return Prey.class;
    }

}
