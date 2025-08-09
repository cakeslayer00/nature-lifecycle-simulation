package dev.sv.util;

import dev.sv.Coordinate;
import dev.sv.Simulation;
import dev.sv.entity.Creature;
import dev.sv.entity.Entity;
import dev.sv.entity.Grass;
import dev.sv.entity.Prey;
import dev.sv.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class ActionUtils {

    public static List<Entity> gatherPrey(GameMap gameMap) {
        List<Entity> preys = new ArrayList<>();

        for (int i = 0; i < Simulation.HORIZONTAL_BOUND; i++) {
            for (int j = 0; j < Simulation.VERTICAL_BOUND; j++) {
                if (gameMap.getEntity(new Coordinate(j, i)) instanceof Prey prey) {
                    preys.add(prey);
                }
            }
        }
        return preys;
    }

    public static List<Entity> gatherGrass(GameMap gameMap) {
        List<Entity> grasses = new ArrayList<>();

        for (int i = 0; i < Simulation.HORIZONTAL_BOUND; i++) {
            for (int j = 0; j < Simulation.VERTICAL_BOUND; j++) {
                if (gameMap.getEntity(new Coordinate(j, i)) instanceof Grass grass) {
                    grasses.add(grass);
                }
            }
        }
        return grasses;
    }

    public static List<Creature> gatherCreatures(GameMap gameMap) {
        List<Creature> creatures = new ArrayList<>();

        for (int i = 0; i < Simulation.HORIZONTAL_BOUND; i++) {
            for (int j = 0; j < Simulation.VERTICAL_BOUND; j++) {
                if (gameMap.getEntity(new Coordinate(j, i)) instanceof Creature creature) {
                    creatures.add(creature);
                }
            }
        }
        return creatures;
    }

}
