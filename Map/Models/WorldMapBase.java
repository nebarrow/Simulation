package Map.Models;

import Animals.Models.Entity;
import Map.Entities.Coordinate;

import java.util.Set;

public interface WorldMapBase {
    void setEntity(Coordinate coordinate, Entity entity);

    void removeEntity(Coordinate coordinate);

    Entity getEntityByCoordinates(Coordinate coordinate);

    Set<Coordinate> getCoordinatesByEntity(Class<? extends Entity> entityClass);

    boolean isValidCoordinates(Coordinate coordinate);
}
