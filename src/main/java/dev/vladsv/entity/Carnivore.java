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
                     int attackStrength) {
        super(speedPerCell, healthPoints, foodPoints);
        this.attackStrength = attackStrength;
    }

    @Override
    public MovementIntent makeMove(GameMap gameMap) {
        Map<Coordinate, Entity> entities = gameMap.getEntities();
        Coordinate current = path.getFirst();

        for (Coordinate neighbour : CoordinateUtils.getNeighbourCoordinates(current)) {
            if (entities.get(neighbour) instanceof Herbivore) {
                return new MovementIntent(MovementType.CONSUME, current, neighbour);
            }
        }

        return new MovementIntent(MovementType.MOVE, path.removeFirst(), path.getFirst());
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
