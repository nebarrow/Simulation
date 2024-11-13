package search.models;

import animals.models.Creature;
import animals.models.Entity;
import map.entities.Coordinate;
import map.entities.WorldMap;

import java.util.List;

public interface Search {
    List<Coordinate> shortestPathFinder(WorldMap map, Coordinate start, Class<? extends Entity> target);

    List<Coordinate> getNeighboursCoordinates(Coordinate coordinate, Creature creature);
}
