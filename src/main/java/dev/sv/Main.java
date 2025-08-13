package dev.sv;

import dev.sv.map.HashGameMap;
import dev.sv.renderer.ConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        HashGameMap gameMap = new HashGameMap(15,15);
        ConsoleRenderer renderer = new ConsoleRenderer(gameMap);
        new Simulation(gameMap, renderer).start();
    }
}