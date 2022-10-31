package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    public Direction[] directions;
    public IWorldMap map;
    public Vector2d[] positions;
    public List<Animal> animalArray = new ArrayList<>();
    public SimulationEngine(Direction[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
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
        int length = this.createAnimals();
        for (int i = 0; i < directions.length; i++) {
            MapVisualizer mapVisualizer = new MapVisualizer(map);
            System.out.println(mapVisualizer.draw(new Vector2d(0,0), new Vector2d(10, 5)));
            animalArray.get(i % length).move(directions[i]);
        }
    }
}
