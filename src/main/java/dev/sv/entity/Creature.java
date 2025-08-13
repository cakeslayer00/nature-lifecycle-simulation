package dev.sv.entity;

import dev.sv.Coordinate;
import dev.sv.MoveIntent;
import dev.sv.map.GameMap;

import java.util.List;

public abstract class Creature extends Entity {

    protected final int speed;
    protected Coordinate coordinate;
    protected int health;
    protected List<Coordinate> path;

    public Creature(int speed, int health, Coordinate coordinate) {
        this.speed = speed;
        this.health = health;
        this.coordinate = coordinate;
    }

    public List<Coordinate> getPath() {
        return path;
    }

    public void setPath(List<Coordinate> path) {
        this.path = path;
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

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public abstract MoveIntent makeMove(GameMap map);

    public abstract Class<? extends Entity> getTargetConsumption();
}
