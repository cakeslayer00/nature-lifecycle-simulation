package dev.sv.entity;

import dev.sv.Coordinate;
import dev.sv.map.GameMap;
import dev.sv.search.SearchService;

import java.util.List;
import java.util.Optional;

import static dev.sv.util.CoordinateUtils.getSurroundingCoordinates;
import static dev.sv.util.CoordinateUtils.isOutOfBounds;

public abstract class Creature extends Entity {

    protected final int speed;

    protected int health;
    protected Coordinate coordinate;

    public Creature(int speed, int health, Coordinate coordinate) {
        this.speed = speed;
        this.health = health;
        this.coordinate = coordinate;
    }

    protected void move(GameMap gameMap, SearchService searchService) {
        List<Coordinate> path = searchService.search(this.coordinate, getTargetConsumption());
        Coordinate next = null;
        for (int i = 0; i < speed; i++) {
            if (path.isEmpty()) {
                return;
            }
            next = path.removeFirst();
        }
        gameMap.removeEntity(coordinate);
        gameMap.putEntity(next, this);
        this.coordinate = next;
    }

    protected Optional<Coordinate> getNearbyTargetPosition(GameMap gameMap) {
        List<Coordinate> coordinates = getSurroundingCoordinates(this.coordinate);
        for (Coordinate coordinate : coordinates) {
            if (isOutOfBounds(coordinate, gameMap.getHorizontalBound(), gameMap.getVerticalBound())) {
                continue;
            }

            Optional<Entity> opt = gameMap.getEntity(coordinate);
            Class<? extends Entity> target = getTargetConsumption();
            if (opt.isPresent() && target.isInstance(opt.get())) {
                return Optional.of(coordinate);
            }
        }
        return Optional.empty();
    }

    public void makeMove(GameMap gameMap, SearchService searchService) {
        Optional<Coordinate> opt = getNearbyTargetPosition(gameMap);
        if (opt.isPresent()) {
            consume(gameMap, opt.get());
        } else {
            move(gameMap, searchService);
        }
    }

    public void gainHealth(int health) {
        this.health += health;
    }

    public void getWounded(int damage) {
        this.health -= damage;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isDead() {
        return health <= 0;
    }

    protected abstract void consume(GameMap gameMap, Coordinate targetCoordinate);

    protected abstract Class<? extends Entity> getTargetConsumption();
}
