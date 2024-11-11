package Actions.MoveActions;

import Actions.Models.Action;
import Animals.Models.Creature;
import Animals.Models.Entity;
import Map.Entities.Coordinate;
import Map.Entities.WorldMap;
import Objects.StaticEntities.Earth;
import Search.Entities.BreadthFirstSearch;

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
            if (!isEmptySquare(coordinate)) {
                Entity entity = map.getEntityByCoordinates(coordinate);
                Creature creature = (Creature) entity;
                int countOfMoves = creature.getSpeed();
                for (int numberOfMove = 0; numberOfMove < countOfMoves; numberOfMove++) {
                    creature.makeMove(map, coordinate, searcher);
                }
            }
        }
    }

    private boolean isEmptySquare(Coordinate coordinate) {
        Entity entity = map.getEntityByCoordinates(coordinate);
        return entity instanceof Earth;
    }
}
