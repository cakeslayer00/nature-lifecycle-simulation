package dev.sv;

import dev.sv.action.*;
import dev.sv.map.GameMap;
import dev.sv.renderer.Renderer;
import dev.sv.search.SearchService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {

    private static final int SIMULATION_DELAY_MS = 500;
    private static final int PAUSED_CHECK_DELAY_MS = 100;
    private static final int INITIAL_DELAY_MS = 3000;

    private static final char QUIT_KEY = 'q';
    private static final char PAUSE_KEY = 'p';
    private static final char SPACE_KEY = ' ';
    private static final char ENTER_CR = '\r';
    private static final char ENTER_LF = '\n';

    private static final String CONTROLS_BANNER = "Controls: [SPACE/p] pause/resume   [q] quit";

    private static final String RUNNING_LABEL = "RUNNING";
    private static final String PAUSED_LABEL = "PAUSED";
    private static final String SIMULATION_STOP_ACKNOWLEDGEMENT = "Stopping simulation...";

    private static final String INPUT_HANDLER_NAME = "InputHandler";

    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final AtomicBoolean running = new AtomicBoolean(true);

    private final Renderer renderer;
    private final GameMap gameMap;
    private final SearchService searchService;

    public Simulation(GameMap gameMap,
                      Renderer renderer,
                      SearchService searchService) {
        this.gameMap = gameMap;
        this.renderer = renderer;
        this.searchService = searchService;
    }

    public void start() {
        InitialSpawnAction initialSpawn = new InitialSpawnAction(gameMap);
        Action[] actions = {
                new MoveAction(gameMap, searchService),
                new SweepDeadCreaturesAction(gameMap),
                new MaintenanceSpawnAction(gameMap)
        };

        String originalTerminalSettings = enableRawMode();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> restoreMode(originalTerminalSettings)));

        try {
            startInputHandler();

            initialSpawn.execute();
            drawFrame();
            sleep(INITIAL_DELAY_MS);

            boolean lastDrawnPaused = false;
            while (running.get()) {
                if (!paused.get()) {
                    executeGameCycle(actions);
                    drawFrame();
                    lastDrawnPaused = false;
                    sleep(SIMULATION_DELAY_MS);
                } else {
                    if (!lastDrawnPaused) {
                        drawFrame();
                        lastDrawnPaused = true;
                    }
                    sleep(PAUSED_CHECK_DELAY_MS);
                }
            }
        } finally {
            restoreMode(originalTerminalSettings);
            System.out.println(SIMULATION_STOP_ACKNOWLEDGEMENT);
        }
    }

    private void executeGameCycle(Action... actions) {
        for (Action action : actions) {
            action.execute();
        }
    }

    private void drawFrame() {
        String status = "Status: " + (paused.get() ? PAUSED_LABEL : RUNNING_LABEL);
        renderer.render(List.of("", CONTROLS_BANNER, status));
    }

    private void startInputHandler() {
        Thread inputThread = new Thread(
                () -> {
                    try {
                        int key;
                        while (running.get() && (key = System.in.read()) != -1) {
                            handleInput((char) key);
                        }
                    } catch (IOException e) {
                        running.set(false);
                    }
                },
                INPUT_HANDLER_NAME);

        inputThread.setDaemon(true);
        inputThread.start();
    }

    private void handleInput(char key) {
        switch (key) {
            case SPACE_KEY, ENTER_CR, ENTER_LF, PAUSE_KEY -> paused.set(!paused.get());
            case QUIT_KEY -> running.set(false);
        }
    }

    private String enableRawMode() {
        String original = stty("-g");
        stty("-icanon min 1 -echo");
        return original;
    }

    private void restoreMode(String settings) {
        if (settings != null && !settings.isEmpty()) {
            stty(settings);
        }
    }

    private String stty(String args) {
        try (Process p = new ProcessBuilder("sh", "-c", "stty " + args + " < /dev/tty")
                .redirectErrorStream(true)
                .start()) {
            String out = new String(p.getInputStream().readAllBytes()).trim();
            p.waitFor();
            return out;
        } catch (Exception e) {
            return "";
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            running.set(false);
        }
    }

}
