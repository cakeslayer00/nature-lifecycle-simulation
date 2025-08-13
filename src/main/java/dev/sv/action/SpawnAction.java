package dev.sv.action;

import dev.sv.Coordinate;
import dev.sv.entity.*;
import dev.sv.map.GameMap;

import java.util.Random;
import java.util.function.Function;

public abstract class SpawnAction extends Action {

    private final Random random;

    public SpawnAction(GameMap gameMap) {
        super(gameMap);
        this.random = new Random();
    }

    protected void spawn(GameMap gameMap, Function<Coordinate, Entity> mapper, int amount) {
        for (int i = 0; i < amount; i++) {
            Coordinate coordinate = getRandomEmptyCoordinate(gameMap);
            Entity entity = mapper.apply(coordinate);
            gameMap.putEntity(coordinate, entity);
        }
    }

    private Coordinate getRandomEmptyCoordinate(GameMap gameMap) {
        int tries = 100;
        while (tries-- > 0) {
            int x = random.nextInt(gameMap.getVerticalBound());
            int y = random.nextInt(gameMap.getHorizontalBound());

            Coordinate curr = new Coordinate(x, y);
            if (!gameMap.contains(curr)) {
                return curr;
            }
        }
        throw new IllegalStateException("There's no fucking empty cell apparently");
    }

}
