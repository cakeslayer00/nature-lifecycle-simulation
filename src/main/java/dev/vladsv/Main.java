package dev.vladsv;

import dev.vladsv.action.CreatureMoveAction;
import dev.vladsv.action.CreaturePathSearchAction;
import dev.vladsv.action.CreatureStateCheckAction;
import dev.vladsv.entity.Carnivore;
import dev.vladsv.entity.Entity;
import dev.vladsv.entity.Grass;
import dev.vladsv.entity.Herbivore;
import dev.vladsv.map.GameMap;
import dev.vladsv.map.GameMapImpl;
import dev.vladsv.render.ConsoleRenderer;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMapImpl();
        ConsoleRenderer consoleRenderer = new ConsoleRenderer();

        new Simulation(gameMap, consoleRenderer).startSimulation();

        Map<Coordinate, Entity> entities = gameMap.getEntities();

        for (int i = 0; i < Simulation.LOWER_BOUND; i++) {
            for (int j = 0; j < Simulation.RIGHT_BOUND; j++) {
                entities.put(new Coordinate(i, j), null);
            }
        }

        entities.put(new Coordinate(10, 4), new Carnivore(1,10,10, 4));
        entities.put(new Coordinate(10,3), new Herbivore(1,10,10));
        entities.put(new Coordinate(10,7), new Grass());
        entities.put(new Coordinate(10,5), new Grass());
        entities.put(new Coordinate(7,4), new Grass());
        entities.put(new Coordinate(6,8), new Grass());
        entities.put(new Coordinate(9,2), new Grass());
        entities.put(new Coordinate(1,4), new Herbivore(1,10,10));

        consoleRenderer.render(gameMap);

        while (!gameMap.getEntities().isEmpty()) {
            System.out.println("--------------------------");
            new CreatureMoveAction(gameMap).execute();
            new CreaturePathSearchAction(gameMap).execute();
            new CreatureStateCheckAction(gameMap).execute();
            consoleRenderer.render(gameMap);
        }
    }
}