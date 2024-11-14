package actions.move;

import actions.models.Action;
import animals.models.Creature;
import animals.models.Entity;
import map.entities.Coordinate;
import map.entities.WorldMap;
import search.entities.BreadthFirstSearch;

import java.util.Set;

public class MoveAction extends Action {
    public MoveAction(WorldMap map) {
        super(map);
    }

    @Override
    public void execute() {
        Set<Coordinate> creatureCoordinate = map.getCoordinatesByEntity(Creature.class);
        BreadthFirstSearch searcher = new BreadthFirstSearch(map);
        for (Coordinate coordinate : creatureCoordinate) {
            if (!map.isCellEmpty(coordinate)) {
                Entity entity = map.getEntityByCoordinates(coordinate);
                Creature creature = (Creature) entity;
                int countOfMoves = creature.getSpeed();
                for (int numberOfMove = 0; numberOfMove < countOfMoves; numberOfMove++) {
                    creature.makeMove(map, coordinate, searcher);
                }
            }
        }
    }
}
