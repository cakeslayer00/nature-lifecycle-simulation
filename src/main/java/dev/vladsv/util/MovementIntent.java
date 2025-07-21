package dev.vladsv.util;

import dev.vladsv.Coordinate;

public record MovementIntent(MovementType type,
                             Coordinate origin,
                             Coordinate target) {
}
