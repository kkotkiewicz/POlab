package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }
    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getLocation())) {
            animals.put(animal.getLocation(), animal);
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException(animal.getLocation().toString() + " is not available");
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (animals.containsKey(position)) {
            return true;
        }
        return false;
    }
    @Override
    public IMapElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }
        return null;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animals.put(newPosition, animals.get(oldPosition));
        animals.remove(oldPosition);
    }

    public abstract Vector2d getLowerLeft();

    public abstract Vector2d getUpperRight();
}
