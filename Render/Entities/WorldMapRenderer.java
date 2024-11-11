package Render.Entities;

import Animals.Models.Entity;
import Render.Models.Renderer;
import Map.Entities.Coordinate;
import Map.Entities.WorldMap;
import Objects.StaticEntities.Earth;
import Sprite.SpriteChoicer;

import static Sprite.EntitySprite.*;

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
                if (map.isCellEmpty(coordinate)) {
                    map.setEntity(coordinate, new Earth());
                }
                Entity entity = map.getEntityByCoordinates(coordinate);
                String entitySprite = spriteChoicer.getSpriteForEntity(entity);
                System.out.print(BACKGROUND.getSymbol() + entitySprite + RESET.getSymbol());
            }
            System.out.println();
        }
    }
}
