package Animals.Models;

import Map.Entities.Coordinate;
import Map.Entities.WorldMap;
import Objects.StaticEntities.Earth;
import Search.Entities.BreadthFirstSearch;

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

    private void interactWithTarget(WorldMap map, Coordinate currentCoordinate, Coordinate targetCoordinate) {
        Entity entity = map.getEntityByCoordinates(targetCoordinate);
        boolean hasInteracted = false;
        if (isEntityTarget(entity) && isCanKillTarget(map, currentCoordinate, entity)) {
            hasInteracted = true;
        } else if (entity instanceof Earth) {
            hasInteracted = true;
        }
        if (hasInteracted) {
            map.setEntity(currentCoordinate, new Earth());
            map.setEntity(targetCoordinate, this);
        }
    }

    private List<Coordinate> searchTargetCoordinates(WorldMap map, Coordinate currentCoordinate, BreadthFirstSearch searcher) {
        return searcher.shortestPathFinder(map, currentCoordinate, getTargetType());
    }
}