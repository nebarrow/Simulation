package render.entities;

import animals.models.Entity;
import render.models.Renderer;
import map.entities.Coordinate;
import map.entities.WorldMap;
import sprite.EntitySprite;
import sprite.SpriteChoicer;

import static sprite.EntitySprite.*;

public class WorldMapRenderer implements Renderer {
    private SpriteChoicer spriteChoicer;
    private WorldMap map;

    public WorldMapRenderer(WorldMap map) {
        this.map = map;
        this.spriteChoicer = new SpriteChoicer();
    }

    public void render() {
        for (int row = 0; row < map.getWidth(); ++row) {
            for (int column = 0; column < map.getHeight(); ++column) {
                final Coordinate coordinate = new Coordinate(row, column);
                String entitySprite;
                if (map.isCellEmpty(coordinate)) {
                     entitySprite = EARTH.getSymbol();
                } else {
                    Entity entity = map.getEntityByCoordinates(coordinate);
                    entitySprite = spriteChoicer.getSpriteForEntity(entity);
                }
                System.out.print(BACKGROUND.getSymbol() + entitySprite + RESET.getSymbol());

            }
            System.out.println();
        }
    }
}
