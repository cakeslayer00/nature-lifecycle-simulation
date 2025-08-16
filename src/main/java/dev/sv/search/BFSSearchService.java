package dev.sv.search;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;
import dev.sv.map.GameMap;
import dev.sv.util.CoordinateUtils;

import java.util.*;

import static dev.sv.util.CoordinateUtils.isOutOfBounds;

public class BFSSearchService implements SearchService {

    private final GameMap gameMap;

    public BFSSearchService(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public List<Coordinate> search(Coordinate start,
                                   Class<? extends Entity> target) {
        Queue<Coordinate> queue = new LinkedList<>(Collections.singleton(start));
        Set<Coordinate> visited = new HashSet<>(Collections.singleton(start));
        Map<Coordinate, Coordinate> parent = new HashMap<>();

        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.poll();
            List<Coordinate> coordinates = CoordinateUtils.getSurroundingCoordinates(currentCoordinate);

            for (Coordinate neighbourCoordinate : coordinates) {
                if (visited.contains(neighbourCoordinate)) {
                    continue;
                }

                if (isOutOfBounds(neighbourCoordinate, gameMap.getHorizontalBound(), gameMap.getVerticalBound())) {
                    continue;
                }

                if (!isTargetOrEmptyCoordinate(target, neighbourCoordinate)) {
                    continue;
                }

                visited.add(neighbourCoordinate);
                parent.put(neighbourCoordinate, currentCoordinate);

                Optional<Entity> opt = gameMap.getEntity(neighbourCoordinate);
                if (opt.isPresent()) {
                    Entity entity = opt.get();
                    if (target.isInstance(entity)) {
                        return constructPath(parent, neighbourCoordinate);
                    }
                }

                queue.offer(neighbourCoordinate);
            }
        }

        return List.of();
    }

    private boolean isTargetOrEmptyCoordinate(Class<? extends Entity> target,
                                              Coordinate coordinate) {
        Optional<Entity> opt = gameMap.getEntity(coordinate);
        return opt.map(target::isInstance).orElse(true);
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
