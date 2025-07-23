package dev.vladsv.entity;

import dev.vladsv.Coordinate;
import dev.vladsv.map.GameMap;
import dev.vladsv.util.CoordinateUtils;
import dev.vladsv.util.MovementIntent;
import dev.vladsv.util.MovementType;

import java.util.Map;

public final class Carnivore extends Creature {

    private final int attackStrength;

    public Carnivore(int speedPerCell,
                     int healthPoints,
                     int foodPoints,
                     int attackStrength,
                     Coordinate position) {
        super(speedPerCell, healthPoints, foodPoints, position);
        this.attackStrength = attackStrength;
    }

    @Override
    public MovementIntent makeMove(GameMap gameMap) {
        for (Coordinate neighbour : CoordinateUtils.getNeighbourCoordinates(position)) {
            if (gameMap.getEntity(neighbour) instanceof Herbivore) {
                return new MovementIntent(MovementType.CONSUME, position, neighbour);
            }
        }

        Coordinate prev = position;
        position = path.removeFirst();

        return new MovementIntent(MovementType.MOVE, prev, position);
    }

    @Override
    public void starve() {
        foodPoints -= 1;
    }

    @Override
    public void feed() {
        foodPoints += 2;
    }

    public void hurt(Creature creature) {
        creature.healthPoints -= attackStrength;
    }
}
