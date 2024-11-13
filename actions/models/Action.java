package actions.models;

import map.entities.WorldMap;

public abstract class Action {
    protected WorldMap map;

    public Action(WorldMap map) {
        this.map = map;
    }

    public abstract void execute();
}
