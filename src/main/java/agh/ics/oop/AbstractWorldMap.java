package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals = new ArrayList<>();
    @Override
    public boolean canMoveTo(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getLocation().equals(position)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getLocation())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getLocation().equals(position)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getLocation().equals(position)) {
                return animal;
            }
        }
        return null;
    }

}
