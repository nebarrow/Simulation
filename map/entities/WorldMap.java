package map.entities;

import animals.models.Entity;
import map.models.WorldMapBase;

import java.util.*;
import java.util.stream.Collectors;

public class WorldMap implements WorldMapBase {
    private Map<Coordinate, Entity> cells = new HashMap<>();
    private int width;
    private int height;
    private Random random = new Random();

    public WorldMap(int x, int y) {
        this.width = x;
        this.height = y;
    }

    @Override
    public void setEntity(Coordinate coordinate, Entity entity) {
        if (isValidCoordinates(coordinate)) {
            throw new IndexOutOfBoundsException("Coordinates are out of bounds");
        }
        cells.put(coordinate, entity);
    }

    @Override
    public void removeEntity(Coordinate coordinate) {
        if (isValidCoordinates(coordinate)) {
            throw new IndexOutOfBoundsException("Coordinates are out of bounds");
        } else if (isCellEmpty(coordinate)) {
            throw new NoSuchElementException("This cell is empty");
        }
        cells.remove(coordinate);
    }

    @Override
    public Entity getEntityByCoordinates(Coordinate coordinate) {
        if (isValidCoordinates(coordinate)) {
            throw new IndexOutOfBoundsException("Coordinates are out of bounds");
        }
        return cells.get(coordinate);
    }

    @Override
    public Set<Coordinate> getCoordinatesByEntity(Class<? extends Entity> entity) {
        return cells.entrySet().stream()
                .filter(entry -> entity.isInstance(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValidCoordinates(Coordinate coordinate) {
        return (coordinate.getX() < 0 || coordinate.getX() >= width) ||
                (coordinate.getY() < 0 || coordinate.getY() >= height);
    }

    public boolean isCellEmpty(Coordinate coordinate) {
        return !cells.containsKey(coordinate);
    }

    public Coordinate getFreePosition() {
        Coordinate coordinate = new Coordinate(random.nextInt(width), random.nextInt(height));
        while (!isCellEmpty(coordinate)) {
            coordinate = new Coordinate(random.nextInt(width), random.nextInt(height));
        }
        return coordinate;
    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
