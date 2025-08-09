package dev.sv.renderer;

import dev.sv.Coordinate;
import dev.sv.Simulation;
import dev.sv.entity.*;
import dev.sv.map.GameMap;

public class ConsoleRenderer implements Renderer {

    private final GameMap gameMap;

    public ConsoleRenderer(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void render() {
        for (int i = 0; i < Simulation.HORIZONTAL_BOUND; i++) {
            for (int j = 0; j < Simulation.VERTICAL_BOUND; j++) {
                Entity entity = gameMap.getEntity(new Coordinate(j, i));

                switch (entity) {
                    case Prey _ -> System.out.print("\uD83D\uDC07");
                    case Predator _ -> System.out.print("\uD83D\uDC3A");
                    case Grass _ -> System.out.print("\ud83c\udf3f");
                    case Tree _ -> System.out.print("\ud83c\udf33");
                    case Rock _ -> System.out.print("⛰\uFE0F");
                    case null -> System.out.print("⬛");
                    default -> throw new IllegalStateException("Unexpected value: " + entity.getClass().getSimpleName());
                }
            }
            System.out.println();
        }
    }

}
