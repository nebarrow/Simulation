package Search.Models;

import Animals.Models.Creature;
import Animals.Models.Entity;
import Map.Entities.Coordinate;
import Map.Entities.WorldMap;

import java.util.List;

public interface Search {
    List<Coordinate> shortestPathFinder(WorldMap map, Coordinate start, Class<? extends Entity> target);

    List<Coordinate> getNeighboursCoordinates(Coordinate coordinate, Creature creature);
}
