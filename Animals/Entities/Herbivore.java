package Animals.Entities;

import Animals.Models.Creature;
import Animals.Models.Entity;
import Map.Entities.Coordinate;
import Map.Entities.WorldMap;
import Objects.DynamicEntities.NutritiousPlant;
import Search.Entities.BreadthFirstSearch;

public class Herbivore extends Creature {
    public Herbivore(double HP, int speed) {
        super(HP, speed);
    }

    @Override
    public Coordinate makeMove(WorldMap map, Coordinate currentCoordinate, BreadthFirstSearch targetSearcher) {
        return move(map, currentCoordinate, targetSearcher);
    }

    @Override
    public boolean isCanKillTarget(WorldMap map, Coordinate coordinate, Entity target) {
        map.removeEntity(coordinate);
        return true;
    }

    @Override
    public Class<NutritiousPlant> getTargetType() {
        return NutritiousPlant.class;
    }

    @Override
    public boolean isEntityTarget(Entity entity) {
        return entity instanceof NutritiousPlant;
    }

    public void applyDamage(double damagePoint) {
        this.setHP(this.getHP() - damagePoint);
    }
}