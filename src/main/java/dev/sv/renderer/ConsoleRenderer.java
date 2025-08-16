package dev.sv.renderer;

import dev.sv.Coordinate;
import dev.sv.entity.*;
import dev.sv.map.GameMap;

import java.util.Optional;

public class ConsoleRenderer implements Renderer {

    private final GameMap gameMap;

    public ConsoleRenderer(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void render() {
        for (int i = 0; i < gameMap.getHorizontalBound(); i++) {
            for (int j = 0; j < gameMap.getVerticalBound(); j++) {
                Optional<Entity> opt = gameMap.getEntity(new Coordinate(j, i));
                Entity entity = opt.orElse(null);

                switch (entity) {
                    case Prey _ -> System.out.print("\uD83D\uDC07");
                    case Predator _ -> System.out.print("\uD83D\uDC3A");
                    case Grass _ -> System.out.print("\ud83c\udf3f");
                    case Tree _ -> System.out.print("\ud83c\udf33");
                    case Rock _ -> System.out.print("⛰️");
                    case null -> System.out.print("⬛");
                    default -> throw new IllegalStateException("Unexpected value: " + opt.getClass().getSimpleName());
                }
            }
            System.out.println();
        }
    }

}
