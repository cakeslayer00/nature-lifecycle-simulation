package dev.vladsv.entity;

import dev.vladsv.Coordinate;
import dev.vladsv.map.GameMap;
import dev.vladsv.util.CoordinateUtils;
import dev.vladsv.util.MovementIntent;
import dev.vladsv.util.MovementType;

import java.util.Map;

public final class Herbivore extends Creature {

    public Herbivore(int speedPerCell,
                     int healthPoints,
                     int foodPoints) {
        super(speedPerCell, healthPoints, foodPoints);
    }

    @Override
    public MovementIntent makeMove(GameMap gameMap) {
        Map<Coordinate, Entity> entities = gameMap.getEntities();
        Coordinate current = path.getFirst();

        for (Coordinate neighbour : CoordinateUtils.getNeighbourCoordinates(current)) {
            if (entities.get(neighbour) instanceof Grass) {
                return new MovementIntent(MovementType.CONSUME, current, neighbour);
            }
        }

        return new MovementIntent(MovementType.MOVE, path.removeFirst(), path.getFirst());
    }

    @Override
    public void starve() {
        foodPoints -= 2;
    }

    @Override
    public void feed() {
        foodPoints += 2;
    }

    public void hurt(int damage) {
        healthPoints -= damage;
    }

}
