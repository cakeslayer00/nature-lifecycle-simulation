package dev.sv.entity;

import dev.sv.Coordinate;
import dev.sv.Intent;
import dev.sv.MoveIntent;
import dev.sv.map.GameMap;
import dev.sv.util.CoordinateUtils;

import java.util.List;

public class Prey extends Creature{

    public Prey(int speed, int health) {
        super(speed, health);
    }

    @Override
    public MoveIntent makeMove(GameMap map) {
        List<Coordinate> surroundingCoords = CoordinateUtils.getSurroundingCoordinates(position);

        for (Coordinate coordinate : surroundingCoords) {
            Entity entity = map.getEntity(coordinate);
            if (entity == null) continue;

            if (entity instanceof Grass) {
                return new MoveIntent(Intent.CONSUME, coordinate);
            }
        }

        if (path.isEmpty()) return new MoveIntent(Intent.STUCK, position);

        return new MoveIntent(Intent.TRAVEL, path.removeFirst());
    }

}
