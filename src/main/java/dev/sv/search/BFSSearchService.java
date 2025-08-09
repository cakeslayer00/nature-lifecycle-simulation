package dev.sv.search;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;
import dev.sv.map.GameMap;
import dev.sv.util.CoordinateUtils;

import java.util.*;

public class BFSSearchService implements SearchService {

    private final GameMap map;

    public BFSSearchService(GameMap map) {
        this.map = map;
    }

    @Override
    public List<Coordinate> search(Coordinate start,
                                   Class<? extends Entity> target) {
        Queue<Coordinate> queue = new LinkedList<>(Collections.singleton(start));
        Set<Coordinate> visited = new HashSet<>(Collections.singleton(start));
        Map<Coordinate, Coordinate> parent = new HashMap<>();

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            List<Coordinate> neighbors = CoordinateUtils.getSurroundingCoordinates(current);

            for (Coordinate neighbor : neighbors) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                if (!isValidCoordinate(target, neighbor)) {
                    continue;
                }

                visited.add(neighbor);
                parent.put(neighbor, current);

                Entity entity = map.getEntity(neighbor);
                if (target.isInstance(entity)) {
                    return constructPath(parent, neighbor);
                }

                queue.offer(neighbor);
            }
        }

        return List.of();
    }

    private boolean isValidCoordinate(Class<? extends Entity> target,
                                      Coordinate coordinate) {
        Entity entity = map.getEntity(coordinate);
        return (entity == null || target.isInstance(entity))
                && !CoordinateUtils.isOutOfBounds(coordinate);
    }

    private List<Coordinate> constructPath(Map<Coordinate, Coordinate> map,
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
