package dev.sv.action;

import dev.sv.entity.Creature;
import dev.sv.map.GameMap;
import dev.sv.search.SearchService;

import java.util.List;

import static dev.sv.util.ActionUtils.gatherTargetEntities;

public class MoveAction extends Action {

    private final SearchService searchService;

    public MoveAction(GameMap gameMap, 
                      SearchService searchService) {
        super(gameMap);
        this.searchService = searchService;
    }

    @Override
    public void execute() {
        List<Creature> creatures = gatherTargetEntities(gameMap, Creature.class);

        for (Creature creature : creatures) {
            creature.makeMove(gameMap, searchService);
        }
    }

}
