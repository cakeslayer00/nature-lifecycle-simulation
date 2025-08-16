package dev.sv.util;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;
import dev.sv.map.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ActionUtils {

    private ActionUtils() {
    }

    public static <T extends Entity> List<T> gatherTargetEntities(GameMap gameMap,
                                                                  Class<T> target) {
        List<T> entities = new ArrayList<>();
        for (int y = 0; y < gameMap.getVerticalBound(); y++) {
            for (int x = 0; x < gameMap.getHorizontalBound(); x++) {
                Optional<Entity> opt = gameMap.getEntity(new Coordinate(x, y));

                opt.ifPresent(entity -> {
                    if (target.isInstance(entity)) {
                        entities.add(target.cast(entity));
                    }
                });
            }
        }
        return entities;
    }

}
