package search.entities;

import animals.models.Creature;
import animals.models.Entity;
import map.entities.Coordinate;
import map.entities.WorldMap;
import search.models.Search;

import java.util.*;


public class BreadthFirstSearch implements Search {
    private final int[] X_DIR = {-1, -1, -1, 0, 0, 1, 1, 1};
    private final int[] Y_DIR = {-1, 1, 0, -1, 1, -1, 1, 0};

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
            if (isTargetReached(current, target)) {
                return buildPathToTarget(current);
            }
            checkNeighbours(current, creature);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Coordinate> getNeighboursCoordinates(Coordinate coordinate, Creature creature) {
        List<Coordinate> neighbours = new ArrayList<>();
        for (int i = 0; i < X_DIR.length; i++) {
            Coordinate neighbourCoordinate = new Coordinate(coordinate.getX() + X_DIR[i], coordinate.getY() + Y_DIR[i]);
            if (isValidNeighbourCoordinate(neighbourCoordinate, creature) && !visited.contains(neighbourCoordinate)) {
                neighbours.add(neighbourCoordinate);
            }
        }
        return neighbours;
    }

    private void checkNeighbours(Coordinate currentCoordinate, Creature creature) {
        List<Coordinate> neighbours = getNeighboursCoordinates(currentCoordinate, creature);
        for (Coordinate neighbourCoordinate : neighbours) {
            queue.add(neighbourCoordinate);
            visited.add(neighbourCoordinate);
            previous.put(neighbourCoordinate, currentCoordinate);
        }
    }

    private List<Coordinate> buildPathToTarget(Coordinate targetCoordinate) {
        List<Coordinate> path = new LinkedList<>();
        Coordinate current = targetCoordinate;
        while (!(current == null)) {
            path.addFirst(current);
            current = previous.get(current);
        }
        path.removeFirst();
        return path;
    }

    private boolean isTargetReached(Coordinate coordinate, Class<? extends Entity> targetClass) {
        Entity entity = map.getEntityByCoordinates(coordinate);
        return entity != null && targetClass.isAssignableFrom(entity.getClass());
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
        if (map.isCellEmpty(coordinate)) {
            return true;
        }

        Entity entity = map.getEntityByCoordinates(coordinate);
        return creature.getTargetType().isAssignableFrom(entity.getClass());
    }
}
