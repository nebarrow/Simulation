package animals.entities;

import animals.models.Creature;
import animals.models.Entity;
import map.entities.Coordinate;
import map.entities.WorldMap;

public class Predator extends Creature {
    private final double DAMAGE_POWER = 3;
    private final double HEALTH_WHEN_CREATURE_DIE = 0;

    public Predator(double HP, int speed) {
        super(HP, speed);
    }

    @Override
    public boolean isCanKillTarget(WorldMap map, Coordinate coordinate, Entity target) {
        Herbivore herbivore = (Herbivore) target;
        herbivore.applyDamage(DAMAGE_POWER);
        if (herbivore.getHP() <= HEALTH_WHEN_CREATURE_DIE) {
            map.removeEntity(coordinate);
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