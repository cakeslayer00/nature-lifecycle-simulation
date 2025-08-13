package dev.sv;

import dev.sv.action.*;
import dev.sv.map.GameMap;
import dev.sv.renderer.Renderer;
import dev.sv.search.BFSSearchService;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {

    private static final int SIMULATION_DELAY_MS = 500;
    private static final int PAUSED_CHECK_DELAY_MS = 100;
    private static final int INITIAL_DELAY_MS = 5000;
    private static final String DELIMITER = "----------------------";

    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final Renderer renderer;
    private final GameMap gameMap;

    public Simulation(GameMap gameMap, Renderer renderer) {
        this.gameMap = gameMap;
        this.renderer = renderer;
    }

    public void start() {
        InitialSpawnAction initialSpawn = new InitialSpawnAction(gameMap);
        Action[] actions = {
                new FindPathAction(gameMap, new BFSSearchService(gameMap)),
                new MoveAction(gameMap),
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

        System.out.println("Simulation ended.");
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
                    System.err.println("Input error: " + e.getMessage());
                }
            }
        }, "InputHandler");

        inputThread.setDaemon(true);
        inputThread.start();
    }

    private void handleInput(String input) {
        switch (input) {
            case "":
            case "p":
            case "pause":
                boolean nowPaused = paused.getAndSet(!paused.get());
                System.out.println(nowPaused ? "RESUMED" : "PAUSED");
                break;
            case "q":
            case "quit":
            case "exit":
                running.set(false);
                System.out.println("Stopping simulation...");
                break;
            case "h":
            case "help":
                printControls();
                break;
            default:
                System.out.println("Unknown command: '" + input + "'. Type 'h' for help.");
                break;
        }
    }

    private void printControls() {
        System.out.println("Controls: [ENTER/p] = pause/resume, [q] = quit, [h] = help");
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