package agh.ics.oop;

import agh.ics.oop.gui.ISimulationObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThreadedSimulationEngine implements IEngine, Runnable {
    public Direction[] directions;
    public IWorldMap map;
    public Vector2d[] positions;
    public List<Animal> animalArray = new ArrayList<>();
    private List<ISimulationObserver> observers = new ArrayList<>();
    private int length;
    private int moveDelay = 300;
    public ThreadedSimulationEngine(Direction[] directions, IWorldMap map, Vector2d[] positions) {
        setDirections(directions);
        this.map = map;
        this.positions = positions;
        length = createAnimals();
    }

    public ThreadedSimulationEngine(IWorldMap map, Vector2d[] positions) {
        this.map = map;
        this.positions = positions;
        length = createAnimals();
    }
    private int createAnimals() {
        int amountOfAnimals = 0;
        for (int i = 0; i < positions.length; i++) {
            Animal animal = new Animal(map, positions[i]);
            if (map.place(animal)) {
                animalArray.add(animal);
                amountOfAnimals += 1;
            }

        }
        return amountOfAnimals;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < directions.length; i++) {
                System.out.println(map.toString());
                animalArray.get(i % length).move(directions[i]);

                refreshObserver();
                Thread.sleep(moveDelay);
            }
        } catch (InterruptedException e) {
        }

    }

    public void addObserver(ISimulationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ISimulationObserver observer) {
        observers.remove(observer);
    }

    void refreshObserver() {
        for (ISimulationObserver observer: observers) {
            observer.refresh();
        }
    }

    public void setDirections(Direction[] directions) {
        this.directions = directions;
    }
}
