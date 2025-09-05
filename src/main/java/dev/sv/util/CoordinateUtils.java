package dev.sv.util;

import dev.sv.Coordinate;

import java.util.List;

public final class CoordinateUtils {

    private CoordinateUtils() {}

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

    public static boolean isOutOfBounds(Coordinate pos,
                                        int horizontal,
                                        int vertical) {
        if (pos == null) {
            return false;
        }

        return pos.x() < 0 || pos.y() < 0
                || pos.x() >= vertical
                || pos.y() >= horizontal;
    }

}
