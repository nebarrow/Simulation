package Simulation;

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
        System.out.println("Enter 0 - for pause, 1 - for quit from simulation, 2 - for next iteration, 3 - for endless loop: ");
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