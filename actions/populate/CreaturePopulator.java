package actions.populate;

import animals.models.Entity;
import animals.entities.Herbivore;
import animals.entities.Predator;
import map.entities.Coordinate;
import map.entities.WorldMap;

import java.util.HashSet;
import java.util.Set;

public class CreaturePopulator {
    private WorldMap map;
    private final int COUNT_OF_CREATURE;

    public CreaturePopulator(WorldMap map) {
        this.map = map;
        COUNT_OF_CREATURE = (int) Math.ceil((map.getWidth() * map.getHeight()) * 0.1);
    }

    public void populate() {
        Entity predator = new Predator(100, 1);
        Entity herbivore = new Herbivore(6, 1);

        for (int currentCreature = 0; currentCreature < COUNT_OF_CREATURE; currentCreature++) {
            addCreatureOnMap(predator);
            addCreatureOnMap(herbivore);
        }
    }

    private void addCreatureOnMap(Entity entity) {
        Coordinate coordinate = map.getFreePosition();
        map.setEntity(coordinate, entity);
    }
}
