import Map.Entities.WorldMap;
import Simulation.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the simulation game!");
        WorldMap map = new WorldMap(20, 20);
        Simulation simulation = new Simulation(map);
        simulation.start();
    }
}