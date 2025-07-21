package dev.vladsv.entity;

import dev.vladsv.Coordinate;
import dev.vladsv.map.GameMap;
import dev.vladsv.util.MovementIntent;

import java.util.List;

public abstract class Creature extends Entity {

    protected int speedPerCell;
    protected int healthPoints;
    protected int foodPoints;
    protected List<Coordinate> path;

    public Creature(int speedPerCell, int healthPoints, int foodPoints) {
        this.speedPerCell = speedPerCell;
        this.healthPoints = healthPoints;
        this.foodPoints = foodPoints;
    }

    public abstract MovementIntent makeMove(GameMap gameMap);

    public abstract void starve();

    public abstract void feed();

    public boolean isAlive() {
        return healthPoints > 0 && foodPoints > 0;
    }

    public boolean hasPath() {
        return path != null && !(path.size() > 1);
    }

    public List<Coordinate> getPath() {
        return path;
    }

    public void setPath(List<Coordinate> path) {
        this.path = path;
    }

}
