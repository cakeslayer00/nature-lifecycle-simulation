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
        List<Creature> creatures = ActionUtils.gatherTargetEntities(gameMap, Creature.class);

        for (Creature creature : creatures) {
            Coordinate coordinate = creature.getCoordinate();
            List<Coordinate> path = searchService.search(coordinate, creature.getTargetConsumption());
            creature.setPath(path);
        }
    }
}
