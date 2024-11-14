package sprite;

import animals.models.Entity;
import animals.entities.Herbivore;
import animals.entities.Predator;
import objects.dynamics.NutritiousPlant;
import objects.statics.Rock;
import objects.statics.Tree;

import java.util.HashMap;
import java.util.Map;

public class SpriteChoicer {
    private final Map<Class<? extends Entity>, EntitySprite> entitySprites = new HashMap<>();

    public SpriteChoicer() {
        entitySprites.put(Predator.class, EntitySprite.PREDATOR);
        entitySprites.put(Herbivore.class, EntitySprite.HERBIVORE);
        entitySprites.put(NutritiousPlant.class, EntitySprite.PLANT);
        entitySprites.put(Rock.class, EntitySprite.ROCK);
        entitySprites.put(Tree.class, EntitySprite.TREE);
    }

    public String getSpriteForEntity(Entity entity) {
        if (entity == null) {
            return EntitySprite.EARTH.getSymbol();
        }
        EntitySprite sprite = entitySprites.get(entity.getClass());
        return sprite != null ? sprite.getSymbol() : EntitySprite.EARTH.getSymbol();
    }
}
