package dev.sv.renderer;

import dev.sv.Coordinate;
import dev.sv.entity.*;
import dev.sv.map.GameMap;

import java.util.List;
import java.util.Optional;

public class ConsoleRenderer implements Renderer {

    private static final int CELL_WIDTH = 2;

    private static final int DEFAULT_COLS = 80;
    private static final int DEFAULT_ROWS = 24;

    private static final String CLEAR_SCREEN = "\033[2J\033[H";

    private final GameMap gameMap;

    public ConsoleRenderer(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void render(List<String> footerLines) {
        int termCols = terminalSize("cols", DEFAULT_COLS);
        int termRows = terminalSize("lines", DEFAULT_ROWS);

        int gridWidth = gameMap.getVerticalBound() * CELL_WIDTH;
        int totalHeight = gameMap.getHorizontalBound() + footerLines.size();

        String gridLeftPad = " ".repeat(Math.max(0, (termCols - gridWidth) / 2));
        int topPad = Math.max(0, (termRows - totalHeight) / 2);

        StringBuilder sb = new StringBuilder();
        sb.append(CLEAR_SCREEN);
        sb.repeat("\n", topPad);

        for (int i = 0; i < gameMap.getHorizontalBound(); i++) {
            sb.append(gridLeftPad);
            for (int j = 0; j < gameMap.getVerticalBound(); j++) {
                Optional<Entity> opt = gameMap.getEntity(new Coordinate(j, i));
                Entity entity = opt.orElse(null);

                switch (entity) {
                    case Prey _ -> sb.append("\uD83D\uDC07");
                    case Predator _ -> sb.append("\uD83D\uDC3A");
                    case Grass _ -> sb.append("\ud83c\udf3f");
                    case Tree _ -> sb.append("\ud83c\udf33");
                    case Rock _ -> sb.append("⛰️");
                    case null -> sb.append("⬛");
                    default -> throw new IllegalStateException("Unexpected value: " + opt.getClass().getSimpleName());
                }
            }
            sb.append('\n');
        }

        for (String line : footerLines) {
            int linePad = Math.max(0, (termCols - line.length()) / 2);
            sb.repeat(" ", linePad).append(line).append('\n');
        }

        System.out.print(sb);
        System.out.flush();
    }

    private int terminalSize(String name, int fallback) {
        try (Process p = new ProcessBuilder("tput", name)
                .redirectInput(ProcessBuilder.Redirect.INHERIT)
                .start()) {
            String out = new String(p.getInputStream().readAllBytes()).trim();
            p.waitFor();
            return out.isEmpty() ? fallback : Integer.parseInt(out);
        } catch (Exception e) {
            return fallback;
        }
    }

}
