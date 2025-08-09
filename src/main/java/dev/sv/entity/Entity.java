package dev.sv.entity;

import dev.sv.Coordinate;

public abstract class Entity {
    protected Coordinate position;

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }
}
