package dev.vladsv;

import dev.vladsv.action.CreatureMoveAction;
import dev.vladsv.action.CreaturePathSearchAction;
import dev.vladsv.action.CreatureStateCheckAction;
import dev.vladsv.action.SpawnEntitiesAction;
import dev.vladsv.entity.Carnivore;
import dev.vladsv.entity.Grass;
import dev.vladsv.entity.Herbivore;
import dev.vladsv.map.GameMap;
import dev.vladsv.map.GameMapImpl;
import dev.vladsv.render.ConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMapImpl();
        ConsoleRenderer consoleRenderer = new ConsoleRenderer();

        new Simulation(gameMap, consoleRenderer).startSimulation();

    }

}