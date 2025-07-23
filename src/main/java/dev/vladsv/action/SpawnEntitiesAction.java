package dev.vladsv.action;

import dev.vladsv.Coordinate;
import dev.vladsv.Simulation;
import dev.vladsv.entity.*;
import dev.vladsv.map.GameMap;

import java.util.Random;

public class SpawnEntitiesAction extends Action {

    private final Random random = new Random();

    public SpawnEntitiesAction(GameMap map) {
        super(map);
    }

    @Override
    public void execute() {
        generateCarnivores(4);
        generateHerbivores(4);
        generateGrass(8);
        generateRocks(5);
        generateTrees(3);
    }

    private void generateCarnivores(int count) {
        for (int i = 0; i < count; i++) {
            Coordinate pos = getRandomEmptyPosition();
            map.putEntity(pos, new Carnivore(1, 10, 10, 4, pos));
        }
    }

    private void generateHerbivores(int count) {
        for (int i = 0; i < count; i++) {
            Coordinate pos = getRandomEmptyPosition();
            map.putEntity(pos, new Herbivore(1, 10, 10, pos));
        }
    }

    private void generateGrass(int count) {
        for (int i = 0; i < count; i++) {
            Coordinate pos = getRandomEmptyPosition();
            map.putEntity(pos, new Grass());
        }
    }

    private void generateRocks(int count) {
        for (int i = 0; i < count; i++) {
            Coordinate pos = getRandomEmptyPosition();
            map.putEntity(pos, new Rock());
        }
    }

    private void generateTrees(int count) {
        for (int i = 0; i < count; i++) {
            Coordinate pos = getRandomEmptyPosition();
            map.putEntity(pos, new Tree());
        }
    }

    private Coordinate getRandomEmptyPosition() {
        Coordinate pos;
        int attempts = 0;
        do {
            int x = random.nextInt(Simulation.MAP_WIDTH) + 1;
            int y = random.nextInt(Simulation.MAP_HEIGHT) + 1;
            pos = new Coordinate(x, y);
            attempts++;

            if (attempts > 1000) {
                throw new RuntimeException("Could not find empty position after 1000 attempts");
            }
        } while (map.getEntity(pos) != null);

        return pos;
    }
}
