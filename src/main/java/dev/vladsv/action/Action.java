package dev.vladsv.action;

import dev.vladsv.map.GameMap;

public abstract class Action {

    protected final GameMap map;

    protected Action(GameMap map) {
        this.map = map;
    }

    public abstract void execute();

}
