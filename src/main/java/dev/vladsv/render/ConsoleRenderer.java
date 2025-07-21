package dev.vladsv.render;

import dev.vladsv.Coordinate;
import dev.vladsv.Simulation;
import dev.vladsv.entity.*;
import dev.vladsv.map.GameMap;
import dev.vladsv.util.Sprites;

public final class ConsoleRenderer implements Renderer {

    @Override
    public void render(GameMap gameMap) {
        for (int i = 0; i < Simulation.LOWER_BOUND; i++) {
            for (int j = 0; j < Simulation.RIGHT_BOUND; j++) {
                Entity entity = gameMap.getEntity(new Coordinate(j, i));

                switch (entity) {
                    case null -> System.out.print(Sprites.EMPTY.getSymbol());
                    case Grass _ -> System.out.print(Sprites.GRASS.getSymbol());
                    case Rock _ -> System.out.print(Sprites.ROCK.getSymbol());
                    case Tree _ -> System.out.print(Sprites.TREE.getSymbol());
                    case Herbivore _ -> System.out.print(Sprites.HERBIVORE.getSymbol());
                    case Carnivore _ -> System.out.print(Sprites.CARNIVORE.getSymbol());
                    default -> throw new IllegalStateException("Unexpected value: " + entity);
                }
                System.out.print(" ");

            }
            System.out.println();
        }
    }

}
