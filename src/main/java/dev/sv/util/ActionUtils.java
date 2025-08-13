package dev.sv.util;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;
import dev.sv.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public final class ActionUtils {

    private ActionUtils() {
    }

    public static <T extends Entity> List<T> gatherTargetEntities(GameMap gameMap,
                                                                  Class<T> target) {
        List<T> entities = new ArrayList<>();
        for (int y = 0; y < gameMap.getVerticalBound(); y++) {
            for (int x = 0; x < gameMap.getHorizontalBound(); x++) {
                Entity entity = gameMap.getEntity(new Coordinate(x, y));
                if (target.isInstance(entity)) {
                    entities.add(target.cast(entity));
                }
            }
        }
        return entities;
    }

}
