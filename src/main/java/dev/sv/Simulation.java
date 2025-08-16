package dev.sv;

import dev.sv.action.*;
import dev.sv.map.GameMap;
import dev.sv.renderer.Renderer;
import dev.sv.search.SearchService;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {

    private static final int SIMULATION_DELAY_MS = 500;
    private static final int PAUSED_CHECK_DELAY_MS = 100;
    private static final int INITIAL_DELAY_MS = 3000;

    private static final String EMPTY_INPUT = "";
    private static final String PAUSE = "p";
    private static final String QUIT = "q";
    private static final String HELP = "h";

    private static final String CONTROLS_BANNER = "Controls: [ENTER/p] = pause/resume, [q] = quit, [h] = help";
    private static final String DELIMITER = "----------------------";

    private static final String UNKNOWN_COMMAND_ACKNOWLEDGEMENT = "Unknown command: '%s'. Type 'h' for help.%n";
    private static final String SIMULATION_STOP_ACKNOWLEDGEMENT = "Stopping simulation...";
    private static final String RESUMED_ACKNOWLEDGEMENT = "RESUMED";
    private static final String PAUSED_ACKNOWLEDGEMENT = "PAUSED";
    private static final String INPUT_ERROR_ACKNOWLEDGEMENT = "Input error: %s.%n";

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

        try (Scanner scanner = new Scanner(System.in)) {
            startInputHandler(scanner);

            initialSpawn.execute();
            renderer.render();
            printDelimiter();
            sleep(INITIAL_DELAY_MS);

            while (running.get()) {
                if (!paused.get()) {
                    executeGameCycle(actions);
                    renderer.render();
                    printDelimiter();
                    sleep(SIMULATION_DELAY_MS);
                } else {
                    sleep(PAUSED_CHECK_DELAY_MS);
                }
            }
        }
    }

    private void executeGameCycle(Action... actions) {
        for (Action action : actions) {
            action.execute();
        }
    }

    private void startInputHandler(Scanner scanner) {
        Thread inputThread = new Thread(() -> {
            printControls();
            while (running.get()) {
                try {
                    String input = scanner.nextLine().trim().toLowerCase();
                    handleInput(input);
                } catch (Exception e) {
                    System.err.printf(INPUT_ERROR_ACKNOWLEDGEMENT, e.getMessage());
                }
            }
        }, INPUT_HANDLER_NAME);

        inputThread.setDaemon(true);
        inputThread.start();
    }

    private void handleInput(String input) {
        switch (input) {
            case EMPTY_INPUT, PAUSE -> {
                boolean nowPaused = paused.getAndSet(!paused.get());
                System.out.println(nowPaused ? RESUMED_ACKNOWLEDGEMENT : PAUSED_ACKNOWLEDGEMENT);
            }
            case QUIT -> {
                running.set(false);
                System.out.println(SIMULATION_STOP_ACKNOWLEDGEMENT);
            }
            case HELP -> printControls();
            default -> System.out.printf(UNKNOWN_COMMAND_ACKNOWLEDGEMENT, input);
        }
    }

    private void printControls() {
        System.out.println(CONTROLS_BANNER);
    }

    private void printDelimiter() {
        System.out.println(DELIMITER);
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