package Actions.Models;

import Map.Entities.WorldMap;

public abstract class Action {
    protected WorldMap map;

    public Action(WorldMap map) {
        this.map = map;
    }

    public abstract void execute();
}
