package actions.populate;

import animals.models.Entity;
import map.entities.Coordinate;
import map.entities.WorldMap;
import objects.dynamics.NutritiousPlant;
import objects.models.Object;
import objects.statics.Rock;
import objects.statics.Tree;

import java.util.HashSet;
import java.util.Set;

public class ObjectPopulator {
    private final int COUNT_OF_OBJECT;
    private WorldMap map;

    public ObjectPopulator(WorldMap map) {
        this.map = map;
        COUNT_OF_OBJECT = (int) Math.ceil((map.getWidth() * map.getHeight()) * 0.1);
    }

    public void populate() {
        Rock rock = new Rock();
        NutritiousPlant plant = new NutritiousPlant();
        Tree tree = new Tree();
        for (int currentCreature = 0; currentCreature < COUNT_OF_OBJECT; currentCreature++) {
            addObjectOnMap(rock);
            addObjectOnMap(plant);
            addObjectOnMap(tree);
        }
    }
    private void addObjectOnMap(Entity entity) {
        Coordinate coordinate = map.getFreePosition();
        map.setEntity(coordinate, entity);
    }
}
