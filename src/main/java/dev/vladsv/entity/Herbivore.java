package dev.vladsv.entity;

import dev.vladsv.Coordinate;
import dev.vladsv.map.GameMap;
import dev.vladsv.util.CoordinateUtils;
import dev.vladsv.util.MovementIntent;
import dev.vladsv.util.MovementType;

public final class Herbivore extends Creature {

    public Herbivore(int speedPerCell,
                     int healthPoints,
                     int foodPoints,
                     Coordinate position) {
        super(speedPerCell, healthPoints, foodPoints, position);
    }

    @Override
    public MovementIntent makeMove(GameMap gameMap) {
        for (Coordinate neighbour : CoordinateUtils.getNeighbourCoordinates(position)) {
            if (gameMap.getEntity(neighbour) instanceof Grass) {
                return new MovementIntent(MovementType.CONSUME, position, neighbour);
            }
        }

        Coordinate prev = position;
        position = path.removeFirst();

        return new MovementIntent(MovementType.MOVE, prev, position);
    }

    @Override
    public void starve() {
        foodPoints -= 2;
    }

    @Override
    public void feed() {
        foodPoints += 2;
    }

}
