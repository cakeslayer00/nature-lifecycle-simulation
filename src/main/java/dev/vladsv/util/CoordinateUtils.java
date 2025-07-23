package dev.vladsv.util;

import dev.vladsv.Coordinate;
import dev.vladsv.Simulation;

import java.util.List;

public class CoordinateUtils {

    public static List<Coordinate> getNeighbourCoordinates(Coordinate coordinate) {
        int x = coordinate.x();
        int y = coordinate.y();
        return List.of(
                new Coordinate(x, y+1),
                new Coordinate(x, y-1),
                new Coordinate(x+1, y),
                new Coordinate(x+1, y-1),
                new Coordinate(x+1, y+1),
                new Coordinate(x-1, y),
                new Coordinate(x-1, y-1),
                new Coordinate(x-1, y+1));

    }

    public static boolean isOutOfBounds(Coordinate coordinate) {
        return (coordinate.x() < 0 || coordinate.y() < 0) ||
                (coordinate.x() >= Simulation.MAP_WIDTH || coordinate.y() >= Simulation.MAP_HEIGHT);
    }

}
