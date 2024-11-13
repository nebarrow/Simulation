package actions.populate;

import actions.models.Action;
import map.entities.WorldMap;

public class PopulateAction extends Action {
    private final ObjectPopulator objectPopulator;
    private final CreaturePopulator creaturePopulator;

    public PopulateAction(WorldMap map) {
        super(map);
        objectPopulator = new ObjectPopulator(map);
        creaturePopulator = new CreaturePopulator(map);
    }

    @Override
    public void execute() {
        creaturePopulator.populate();
        objectPopulator.populate();
    }
}