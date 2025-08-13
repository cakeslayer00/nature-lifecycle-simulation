package dev.sv.renderer;

import dev.sv.Coordinate;
import dev.sv.entity.*;
import dev.sv.map.GameMap;

public class ConsoleRenderer implements Renderer {

    private final GameMap gameMap;

    public ConsoleRenderer(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void render() {
        for (int i = 0; i < gameMap.getHorizontalBound(); i++) {
            for (int j = 0; j < gameMap.getVerticalBound(); j++) {
                Entity entity = gameMap.getEntity(new Coordinate(j, i));

                switch (entity) {
                    case Prey ignored -> System.out.print("\uD83D\uDC07");
                    case Predator ignored -> System.out.print("\uD83D\uDC3A");
                    case Grass ignored -> System.out.print("\ud83c\udf3f");
                    case Tree ignored -> System.out.print("\ud83c\udf33");
                    case Rock ignored -> System.out.print("⛰️");
                    case null -> System.out.print("⬛");
                    default ->
                            throw new IllegalStateException("Unexpected value: " + entity.getClass().getSimpleName());
                }
            }
            System.out.println();
        }
    }

}
