package dev.sv.action;

import dev.sv.map.GameMap;

public abstract class Action {

    protected final GameMap gameMap;

    protected Action(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public abstract void execute();
}
