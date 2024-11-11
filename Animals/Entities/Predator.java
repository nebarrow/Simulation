package Animals.Entities;

import Animals.Models.Creature;
import Animals.Models.Entity;
import Map.Entities.Coordinate;
import Map.Entities.WorldMap;
import Objects.StaticEntities.Earth;
import Search.Entities.BreadthFirstSearch;

public class Predator extends Creature {
    private final double DAMAGE_POWER = 3;
    private final double HEALTH_WHEN_CREATURE_DIE = 0;

    public Predator(double HP, int speed) {
        super(HP, speed);
    }

    @Override
    public Coordinate makeMove(WorldMap map, Coordinate currentCoordinate, BreadthFirstSearch targetSearcher) {
        return move(map, currentCoordinate, targetSearcher);
    }

    @Override
    public boolean isCanKillTarget(WorldMap map, Coordinate coordinate, Entity target) {
        Herbivore herbivore = (Herbivore) target;
        herbivore.applyDamage(DAMAGE_POWER);
        if (herbivore.getHP() <= HEALTH_WHEN_CREATURE_DIE) {
            map.setEntity(coordinate, new Earth());
            return true;
        }
        return false;
    }

    @Override
    public Class<Herbivore> getTargetType() {
        return Herbivore.class;
    }

    @Override
    public boolean isEntityTarget(Entity entity) {
        return entity instanceof Herbivore;
    }
}