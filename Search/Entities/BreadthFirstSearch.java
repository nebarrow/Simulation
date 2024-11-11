package Search.Entities;

import Animals.Models.Creature;
import Animals.Models.Entity;
import Map.Entities.Coordinate;
import Map.Entities.WorldMap;
import Objects.StaticEntities.Earth;
import Search.Models.Search;

import java.util.*;


public class BreadthFirstSearch implements Search {
    private final Queue<Coordinate> queue;
    private final Set<Coordinate> visited;
    private final Map<Coordinate, Coordinate> previous;
    private final WorldMap map;

    public BreadthFirstSearch(WorldMap map) {
        this.map = map;
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
        this.previous = new HashMap<>();
    }

    @Override
    public List<Coordinate> shortestPathFinder(WorldMap map, Coordinate start, Class<? extends  Entity> target) {
        Creature creature = (Creature) map.getEntityByCoordinates(start);
        clearData();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (isTarget(current, target)) {
                return constructedTargetPath(current);
            }
            checkNeighbours(current, creature);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Coordinate> getNeighboursCoordinates(Coordinate coordinate, Creature creature) {
        List<Coordinate> neighbours = new ArrayList<>();
        int x = coordinate.getX();
        int y = coordinate.getY();
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                Coordinate neighbour = new Coordinate(i, j);
                if (isValidNeighbourCoordinate(neighbour, creature) && !visited.contains(neighbour)) {
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    private boolean isTarget(Coordinate coordinate, Class<? extends Entity> targetClass) {
        Entity entity = map.getEntityByCoordinates(coordinate);
        return entity != null && targetClass.isAssignableFrom(entity.getClass());
    }

    private void checkNeighbours(Coordinate currentCoordinate, Creature creature) {
        List<Coordinate> neighbours = getNeighboursCoordinates(currentCoordinate, creature);
        for (Coordinate neighbourCoordinate : neighbours) {
            queue.add(neighbourCoordinate);
            visited.add(neighbourCoordinate);
            previous.put(neighbourCoordinate, currentCoordinate);
        }
    }

    private List<Coordinate> constructedTargetPath(Coordinate targetCoordinate) {
        List<Coordinate> path = new LinkedList<>();
        Coordinate current = targetCoordinate;
        while (current != null) {
            path.addFirst(current);
            current = previous.get(current);
        }
        path.removeFirst();
        return path;
    }

    private void clearData() {
        queue.clear();
        visited.clear();
        previous.clear();
    }

    private boolean isValidNeighbourCoordinate(Coordinate coordinate, Creature creature) {
        if (coordinate.getX() < 0 || coordinate.getY() < 0 || coordinate.getX() >= map.getWidth() || coordinate.getY() >= map.getHeight()) {
            return false;
        }
        Entity entity = map.getEntityByCoordinates(coordinate);

        return entity instanceof Earth || (creature.getTargetType().isAssignableFrom(entity.getClass()));
    }
}
