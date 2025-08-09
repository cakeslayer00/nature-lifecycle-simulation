package dev.sv.util;

import dev.sv.Coordinate;
import dev.sv.Simulation;

import java.util.List;

public class CoordinateUtils {

    public static List<Coordinate> getSurroundingCoordinates(Coordinate pos) {

        return List.of(new Coordinate(pos.x() + 1, pos.y() + 1),
                new Coordinate(pos.x() + 1, pos.y() - 1),
                new Coordinate(pos.x() + 1, pos.y()),
                new Coordinate(pos.x(), pos.y() + 1),
                new Coordinate(pos.x(), pos.y() - 1),
                new Coordinate(pos.x() - 1, pos.y()),
                new Coordinate(pos.x() - 1, pos.y() + 1),
                new Coordinate(pos.x() - 1, pos.y() - 1));

    }

    public static boolean isOutOfBounds(Coordinate pos) {
        if (pos == null) {
            return false;
        }

        return pos.x() < 0 || pos.y() < 0
                || pos.x() >= Simulation.VERTICAL_BOUND
                || pos.y() >= Simulation.HORIZONTAL_BOUND;
    }
}
