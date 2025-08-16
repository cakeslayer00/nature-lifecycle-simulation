package dev.sv.action;

import dev.sv.Coordinate;
import dev.sv.entity.*;
import dev.sv.map.GameMap;

import java.util.Random;
import java.util.function.Function;

public abstract class SpawnAction extends Action {

    protected static final int PREDATOR_SPEED = 1;
    protected static final int PREDATOR_HEALTH = 1;
    protected static final int PREY_SPEED = 1;
    protected static final int PREY_HEALTH = 1;
    protected static final int PREDATOR_ATTACK_STRENGTH = 4;

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
            if (!gameMap.containsEntity(curr)) {
                return curr;
            }
        }
        throw new IllegalStateException("There's no fucking empty cell apparently");
    }

}
