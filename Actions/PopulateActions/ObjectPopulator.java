package Actions.PopulateActions;

import Animals.Models.Entity;
import Map.Entities.Coordinate;
import Map.Entities.WorldMap;
import Objects.DynamicEntities.NutritiousPlant;
import Objects.StaticEntities.Rock;
import Objects.StaticEntities.Tree;

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
        for (int currentCreature = 0; currentCreature < COUNT_OF_OBJECT; currentCreature++) {
            addObjectOnMap(new Rock(), 1);
            addObjectOnMap(new NutritiousPlant(), 1);
            addObjectOnMap(new Tree(), 1);
        }
    }
    private void addObjectOnMap(Entity entity, int entityCount) {
        Set<Coordinate> entityCoordinates = new HashSet<>();
        while (entityCoordinates.size() < entityCount) {
            Coordinate coordinate = map.getFreePosition();
            entityCoordinates.add(coordinate);
            map.setEntity(coordinate, entity);
        }
    }
}
