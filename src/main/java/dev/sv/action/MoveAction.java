package dev.sv.action;

import dev.sv.Coordinate;
import dev.sv.Intent;
import dev.sv.MoveIntent;
import dev.sv.entity.*;
import dev.sv.map.GameMap;
import dev.sv.util.ActionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveAction extends Action {

    public MoveAction(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    public void execute() {
        List<Creature> creatures = ActionUtils.gatherCreatures(gameMap);

        Map<MoveIntent, Creature> map = new HashMap<>();
        for (Creature creature : creatures) {
            MoveIntent intent = creature.makeMove(gameMap);
            map.put(intent, creature);
        }

        consumerMove(map);
        travelerMove(map);
    }

    private void travelerMove(Map<MoveIntent, Creature> map) {
        for (Map.Entry<MoveIntent, Creature> entry : map.entrySet()) {
            MoveIntent intent = entry.getKey();
            if (intent.intent() == Intent.TRAVEL) {
                Creature value = entry.getValue();
                gameMap.removeEntity(value.getPosition());
                value.setPosition(intent.targetPosition());
                gameMap.putEntity(value.getPosition(), value);
            }
        }
    }

    private void consumerMove(Map<MoveIntent, Creature> map) {
        for (Map.Entry<MoveIntent, Creature> entry : map.entrySet()) {
            MoveIntent moveIntent = entry.getKey();
            if (moveIntent.intent() == Intent.CONSUME) {
                Creature consumer = entry.getValue();

                Coordinate targetPosition = moveIntent.targetPosition();
                Entity target = gameMap.getEntity(targetPosition);
                switch (target) {
                    case Grass _ -> {
                        gameMap.removeEntity(targetPosition);
                        gameMap.putEntity(targetPosition, null);
                        consumer.gainHealth(2);
                    }
                    case Prey _ -> {
                        Predator predator = (Predator) consumer;
                        ((Creature) gameMap.getEntity(targetPosition)).getWounded(predator.getAttackStrength());
                        predator.gainHealth(2);
                    }
                    case null, default -> {
                    }
                }
            }
        }
    }
}
