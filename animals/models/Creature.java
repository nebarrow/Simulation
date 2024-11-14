package animals.models;

import map.entities.Coordinate;
import map.entities.WorldMap;
import search.entities.BreadthFirstSearch;

import java.util.List;

public abstract class Creature extends Entity {
    private int speed;
    private double HP;

    public Creature(double HP, int speed) {
        this.speed = speed;
        this.HP = HP;
    }

    public Coordinate move(WorldMap map, Coordinate currentCoordinate, BreadthFirstSearch targetSearcher) {
        List<Coordinate> path = searchTargetCoordinates(map, currentCoordinate, targetSearcher);
        if (path.isEmpty()) {
            return currentCoordinate;
        }
        Coordinate nextCoordinate = path.getFirst();
        interactWithTarget(map, currentCoordinate, nextCoordinate);

        return nextCoordinate;
    }

    private void interactWithTarget(WorldMap map, Coordinate currentCoordinate, Coordinate targetCoordinate) {
        if (map.isCellEmpty(targetCoordinate)) {
            makeStep(map, currentCoordinate, targetCoordinate);
            return;
        }
        Entity entity = map.getEntityByCoordinates(targetCoordinate);
        if (isEntityTarget(entity) && isCanKillTarget(map, targetCoordinate, entity)) {
            makeStep(map, currentCoordinate, targetCoordinate);
        }
    }

    private void makeStep(WorldMap map, Coordinate currentCoordinate, Coordinate nextCoordinate) {
        map.removeEntity(currentCoordinate);
        map.setEntity(nextCoordinate, this);
    }

    private List<Coordinate> searchTargetCoordinates(WorldMap map, Coordinate currentCoordinate, BreadthFirstSearch searcher) {
        return searcher.shortestPathFinder(map, currentCoordinate, getTargetType());
    }

    public int getSpeed() {
        return speed;
    }

    public double getHP() {
        return HP;
    }

    protected void setHP(double HP) {
        this.HP = HP;
    }

    public abstract Coordinate makeMove(WorldMap map, Coordinate currentCoordinate, BreadthFirstSearch targetSearcher);

    public abstract boolean isCanKillTarget(WorldMap map, Coordinate coordinate, Entity target);

    public abstract Class<? extends Entity> getTargetType();

    public abstract boolean isEntityTarget(Entity entity);
}