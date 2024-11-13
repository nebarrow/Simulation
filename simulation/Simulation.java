package simulation;

import actions.move.MoveAction;
import actions.populate.PopulateAction;
import animals.entities.Herbivore;
import map.entities.WorldMap;
import render.entities.WorldMapRenderer;

import java.util.concurrent.TimeUnit;

public class Simulation {
    private SimulationInputHandler inputHandler;
    private WorldMap map;
    private WorldMapRenderer worldMapRenderer;
    private MoveAction moveAction;
    private PopulateAction populateAction;

    private boolean isContinues = true;
    private boolean isPaused = false;
    private boolean endlessLoop = false;
    private Thread simulationThread;

    public Simulation(WorldMap map) {
        this.map = map;
        this.worldMapRenderer = new WorldMapRenderer(map);
        this.moveAction = new MoveAction(map);
        this.populateAction = new PopulateAction(map);
        this.inputHandler = new SimulationInputHandler(this);
    }

    public void start() {
        populateAction.execute();
        renderNextStep();
        simulationThread = new Thread(() -> {
            while (isContinues && !isOver()) {
                try {
                    handleSimulationCycle();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Simulation was ended. Good luck!");
        });
        simulationThread.start();
    }

    public void stop() {
        isContinues = false;
    }

    public void pause() {
        isPaused = true;
    }

    public void resume() {
        isPaused = false;
    }

    public void startEndlessLoop() {
        endlessLoop = true;
    }

    private void renderNextStep() {
        worldMapRenderer.render();
        System.out.println();
    }

    private void nextTurn() {
        moveAction.execute();
        renderNextStep();
    }

    private boolean isOver() {
        return map.getCoordinatesByEntity(Herbivore.class).isEmpty();
    }

    private void handleSimulationCycle() throws InterruptedException {
        if (!endlessLoop) {
            inputHandler.processCommand();
        }
        if (!isPaused && isContinues && !endlessLoop) {
            nextTurn();
            TimeUnit.SECONDS.sleep(2);
        } else if (!isContinues) {
            return;
        } else if (endlessLoop){
            runEndlessLoop();
        }

    }

    private void runEndlessLoop() throws InterruptedException {
        nextTurn();
        TimeUnit.SECONDS.sleep(2);
    }
}

