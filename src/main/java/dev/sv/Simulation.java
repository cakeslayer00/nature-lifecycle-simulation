package dev.sv;

import dev.sv.action.*;
import dev.sv.map.GameMapImpl;
import dev.sv.renderer.ConsoleRenderer;
import dev.sv.search.BFSSearchService;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {

    public static final int HORIZONTAL_BOUND = 10;
    public static final int VERTICAL_BOUND = 10;

    private static final int SIMULATION_DELAY_MS = 500;
    private static final int PAUSED_CHECK_DELAY_MS = 100;
    private static final int INITIAL_DELAY_MS = 5000;
    private static final String DELIMITER = "----------------------";

    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final AtomicBoolean running = new AtomicBoolean(true);

    public void run() {
        GameMapImpl map = new GameMapImpl();
        ConsoleRenderer consoleRenderer = new ConsoleRenderer(map);

        InitialSpawnAction initialSpawn = new InitialSpawnAction(map);
        Action[] actions = {
                new FindPathAction(map, new BFSSearchService(map)),
                new MoveAction(map),
                new SweepDeadCreaturesAction(map),
                new MaintenanceSpawnAction(map)
        };

        try (Scanner scanner = new Scanner(System.in)) {
            startInputHandler(scanner);

            initialSpawn.execute();
            consoleRenderer.render();
            printDelimiter();
            sleep(INITIAL_DELAY_MS);

            while (running.get()) {
                if (!paused.get()) {
                    executeGameCycle(actions);
                    consoleRenderer.render();
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