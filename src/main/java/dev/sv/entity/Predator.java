package dev.sv.entity;

import dev.sv.Coordinate;
import dev.sv.Intent;
import dev.sv.MoveIntent;
import dev.sv.map.GameMap;
import dev.sv.util.CoordinateUtils;

import java.util.List;

public class Predator extends Creature {

    private final int attackStrength;

    public Predator(int speed, int health, int attackStrength, Coordinate coordinate) {
        super(speed, health, coordinate);
        this.attackStrength = attackStrength;
    }

    @Override
    public MoveIntent makeMove(GameMap map) {
        List<Coordinate> surroundingCoords = CoordinateUtils.getSurroundingCoordinates(coordinate);

        for (Coordinate coordinate : surroundingCoords) {
            Entity entity = map.getEntity(coordinate);
            if (entity == null) {
                continue;
            }

            if (entity instanceof Prey) {
                return new MoveIntent(Intent.CONSUME, coordinate);
            }
        }

        if (path.isEmpty()) {
            return new MoveIntent(Intent.STUCK, coordinate);
        }

        return new MoveIntent(Intent.TRAVEL, path.removeFirst());
    }

    @Override
    public Class<? extends Entity> getTargetConsumption() {
        return Prey.class;
    }

    public int getAttackStrength() {
        return attackStrength;
    }
}
