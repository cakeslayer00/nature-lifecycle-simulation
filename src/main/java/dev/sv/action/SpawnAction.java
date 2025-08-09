package dev.sv.action;

import dev.sv.Coordinate;
import dev.sv.Simulation;
import dev.sv.entity.*;
import dev.sv.map.GameMap;

import java.util.Random;

public abstract class SpawnAction extends Action {

    private final Random random;

    public SpawnAction(GameMap gameMap) {
        super(gameMap);
        this.random = new Random();
    }

    protected void generatePredator(int count) {
        for (int i = 0; i < count; i++) {
            int tries = 100;
            while (tries-- > 0) {
                int x = random.nextInt(Simulation.VERTICAL_BOUND);
                int y = random.nextInt(Simulation.HORIZONTAL_BOUND);

                Coordinate curr = new Coordinate(x, y);
                if (!gameMap.contains(curr)) {
                    Predator entity = new Predator(1, 10, 4);
                    entity.setPosition(curr);
                    gameMap.putEntity(curr, entity);
                    break;
                }
            }
        }
    }

    protected void generatePrey(int count) {
        for (int i = 0; i < count; i++) {
            int tries = 100;
            while (tries-- > 0) {
                int x = random.nextInt(Simulation.VERTICAL_BOUND);
                int y = random.nextInt(Simulation.HORIZONTAL_BOUND);

                Coordinate curr = new Coordinate(x, y);
                if (!gameMap.contains(curr)) {
                    Prey entity = new Prey(1, 10);
                    entity.setPosition(curr);
                    gameMap.putEntity(curr, entity);
                    break;
                }
            }
        }
    }

    protected void generateGrass(int count) {
        for (int i = 0; i < count; i++) {
            int tries = 100;
            while (tries-- > 0) {
                int x = random.nextInt(Simulation.VERTICAL_BOUND);
                int y = random.nextInt(Simulation.HORIZONTAL_BOUND);

                Coordinate curr = new Coordinate(x, y);
                if (!gameMap.contains(curr)) {
                    Grass entity = new Grass();
                    entity.setPosition(curr);
                    gameMap.putEntity(curr, entity);
                    break;
                }
            }
        }
    }

    protected void generateRock(int count) {
        for (int i = 0; i < count; i++) {
            int tries = 100;
            while (tries-- > 0) {
                int x = random.nextInt(Simulation.VERTICAL_BOUND);
                int y = random.nextInt(Simulation.HORIZONTAL_BOUND);

                Coordinate curr = new Coordinate(x, y);
                if (!gameMap.contains(curr)) {
                    Rock entity = new Rock();
                    entity.setPosition(curr);
                    gameMap.putEntity(curr, entity);
                    break;
                }
            }
        }
    }

    protected void generateTree(int count) {
        for (int i = 0; i < count; i++) {
            int tries = 100;
            while (tries-- > 0) {
                int x = random.nextInt(Simulation.VERTICAL_BOUND);
                int y = random.nextInt(Simulation.HORIZONTAL_BOUND);

                Coordinate curr = new Coordinate(x, y);
                if (!gameMap.contains(curr)) {
                    Tree entity = new Tree();
                    entity.setPosition(curr);
                    gameMap.putEntity(curr, entity);
                    break;
                }
            }
        }
    }


}
