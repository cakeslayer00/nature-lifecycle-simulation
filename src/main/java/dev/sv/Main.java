package dev.sv;

import dev.sv.map.HashGameMap;
import dev.sv.renderer.ConsoleRenderer;
import dev.sv.search.BFSSearchService;

public class Main {
    public static void main(String[] args) {
        HashGameMap gameMap = new HashGameMap(15,15);
        ConsoleRenderer renderer = new ConsoleRenderer(gameMap);
        BFSSearchService bfsSearchService = new BFSSearchService(gameMap);
        new Simulation(gameMap, renderer, bfsSearchService).start();
    }
}