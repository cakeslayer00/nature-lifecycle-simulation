package dev.vladsv.util;

import dev.vladsv.Coordinate;
import dev.vladsv.entity.Entity;
import dev.vladsv.map.GameMap;

import java.util.*;

public class SearchUtils {

    public static <T extends Entity> List<Coordinate> findByType(Coordinate origin,
                                                                 GameMap map,
                                                                 Class<T> targetType) {
        Queue<Coordinate> queue = new LinkedList<>(Collections.singleton(origin));
        Set<Coordinate> visited = new HashSet<>(Collections.singleton(origin));
        Map<Coordinate, Coordinate> parent = new HashMap<>();

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            List<Coordinate> neighbors = CoordinateUtils.getNeighbourCoordinates(current);

            for (Coordinate neighbor : neighbors) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                if (!isValidCoordinate(map, targetType, neighbor)) {
                    continue;
                }

                visited.add(neighbor);
                parent.put(neighbor, current);

                Entity entity = map.getEntity(neighbor);
                if (targetType.isInstance(entity)) {
                    return constructPath(parent, neighbor);
                }

                queue.offer(neighbor);
            }
        }

        return List.of();
    }

    private static <T extends Entity> boolean isValidCoordinate(GameMap map,
                                                                Class<T> targetType,
                                                                Coordinate coordinate) {
        Entity entity = map.getEntity(coordinate);
        return (entity == null || targetType.isInstance(entity))
                && !CoordinateUtils.isOutOfBounds(coordinate);
    }

    private static List<Coordinate> constructPath(Map<Coordinate, Coordinate> map,
                                                  Coordinate target) {
        List<Coordinate> path = new ArrayList<>();
        while (target != null) {
            path.add(target);
            target = map.get(target);
        }
        path.removeLast();
        return path.reversed();
    }

}
