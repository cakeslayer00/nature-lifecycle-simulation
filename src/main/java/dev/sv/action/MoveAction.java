package dev.sv.action;

import dev.sv.entity.Creature;
import dev.sv.map.GameMap;
import dev.sv.search.SearchService;

public class MoveAction extends Action {

    private final SearchService searchService;

    public MoveAction(GameMap gameMap,
                      SearchService searchService) {
        super(gameMap);
        this.searchService = searchService;
    }

    @Override
    public void execute() {
        gameMap.getTargetEntities(Creature.class)
                .forEach(target -> target.makeMove(gameMap, searchService));
    }

}
