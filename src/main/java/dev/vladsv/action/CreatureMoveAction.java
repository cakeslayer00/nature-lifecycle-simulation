package dev.vladsv.action;

import dev.vladsv.Coordinate;
import dev.vladsv.entity.*;
import dev.vladsv.map.GameMap;
import dev.vladsv.util.MovementIntent;

import java.util.List;

public class CreatureMoveAction extends Action {
    public CreatureMoveAction(GameMap map) {
        super(map);
    }

    @Override
    public void execute() {
        writePathIfNotPresent();
        getCreatures().stream()
                .map(creature -> creature.makeMove(map))
                .forEach(this::processIntent);
    }

    private void processIntent(MovementIntent movementIntent) {
        switch (movementIntent.type()) {
            case CONSUME -> {
                Coordinate origin = movementIntent.origin();
                Coordinate target = movementIntent.target();
                Creature consumer = (Creature) map.getEntity(origin);
                Entity prey = map.getEntity(target);

                if (!(prey instanceof Grass)) {
                    Herbivore herbivore = (Herbivore) prey;
                    Carnivore carnivore = (Carnivore) consumer;
                    carnivore.hurt(herbivore);
                } else {
                    map.removeEntity(target);
                }
                consumer.feed();

            }
            case MOVE -> {
                Entity entity = map.removeEntity(movementIntent.origin());
                map.putEntity(movementIntent.target(), entity);
            }
        }
    }

    private void writePathIfNotPresent() {
        new CreaturePathSearchAction(map).execute();
    }

    private List<Creature> getCreatures() {
        return map.getEntities().stream()
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast)
                .toList();
    }
}
