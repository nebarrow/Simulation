package simulation;

import java.util.Scanner;

public class SimulationInputHandler {
    private final String COMMAND_FOR_PAUSE = "0";
    private final String COMMAND_FOR_STOP = "1";
    private final String COMMAND_FOR_CONTINUE = "2";
    private final String COMMAND_FOR_ENDLESS_LOOP = "3";

    private Simulation simulation;
    private Scanner scanner;

    public SimulationInputHandler(Simulation simulation) {
        this.simulation = simulation;
        this.scanner = new Scanner(System.in);
    }

    public void processCommand() {
        System.out.printf("Enter %s - for pause, %s - for quit from simulation, %s - for next iteration, %s - for endless loop: %n", COMMAND_FOR_PAUSE, COMMAND_FOR_STOP, COMMAND_FOR_CONTINUE, COMMAND_FOR_ENDLESS_LOOP);
        String command = scanner.nextLine();
        switch (command) {
            case COMMAND_FOR_PAUSE:
                simulation.pause();
                System.out.println("Simulation paused.");
                break;
            case COMMAND_FOR_STOP:
                simulation.stop();
                System.out.println("You quit from simulation.");
                return;
            case COMMAND_FOR_CONTINUE:
                simulation.resume();
                System.out.println("The next iteration was displayed.");
                break;
            case COMMAND_FOR_ENDLESS_LOOP:
                simulation.startEndlessLoop();
                System.out.println("Endless loop started.");
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
    }
}