package dev.sv.action;

import dev.sv.Coordinate;
import dev.sv.entity.*;
import dev.sv.map.GameMap;
import dev.sv.search.SearchService;
import dev.sv.util.ActionUtils;

import java.util.List;

public class FindPathAction extends Action {

    private final SearchService searchService;

    public FindPathAction(GameMap gameMap, SearchService searchService) {
        super(gameMap);
        this.searchService = searchService;
    }

    @Override
    public void execute() {
        List<Creature> creatures = ActionUtils.gatherCreatures(gameMap);

        for (Creature creature : creatures) {
            Coordinate coordinate = creature.getPosition();

            List<Coordinate> path = switch (creature) {
                case Predator _ -> searchService.search(coordinate, Prey.class);
                case Prey _ -> searchService.search(coordinate, Grass.class);
                default -> throw new RuntimeException("Invalid creature type");
            };

            creature.setPath(path);
        }
    }
}
