package dev.sv.search;

import dev.sv.Coordinate;
import dev.sv.entity.Entity;

import java.util.List;

public interface SearchService {

    List<Coordinate> search(Coordinate start, Class<? extends Entity> target);

}
