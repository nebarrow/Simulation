package Sprite;

import Animals.Models.Entity;
import Animals.Entities.Herbivore;
import Animals.Entities.Predator;
import Objects.DynamicEntities.NutritiousPlant;
import Objects.StaticEntities.Earth;
import Objects.StaticEntities.Rock;
import Objects.StaticEntities.Tree;

import java.util.HashMap;
import java.util.Map;

public class SpriteChoicer {
    private final Map<Class<? extends Entity>, EntitySprite> entitySprites = new HashMap<>();

    public SpriteChoicer() {
        entitySprites.put(Earth.class, EntitySprite.EARTH);
        entitySprites.put(Predator.class, EntitySprite.PREDATOR);
        entitySprites.put(Herbivore.class, EntitySprite.HERBIVORE);
        entitySprites.put(NutritiousPlant.class, EntitySprite.PLANT);
        entitySprites.put(Rock.class, EntitySprite.ROCK);
        entitySprites.put(Tree.class, EntitySprite.TREE);
    }

    public String getSpriteForEntity(Entity entity) {
        EntitySprite sprite = entitySprites.get(entity.getClass());
        return sprite.getSymbol();
    }
}
