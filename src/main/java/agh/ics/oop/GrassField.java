package agh.ics.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap {
    private MapBoundary mapBoundary;
    private int grassCount;
    private Map<Vector2d, Grass> grass = new HashMap<>();
    public GrassField(int grassCount) {
        mapBoundary = new MapBoundary(animals, grass);
        this.grassCount = grassCount;
        generateGrass();

    }
    private MapVisualizer mapVisualizer = new MapVisualizer(this);

    private void generateGrass() {
        int n = 0;
        while (n < grassCount) {
            Grass newGrass = new Grass(new Vector2d((int) (Math.random()*((int) (Math.sqrt(10*grassCount)))), (int) (Math.random()*((int) (Math.sqrt(10*grassCount))))));
            if (objectAt(newGrass.getLocation()) != null) {
                if (objectAt(newGrass.getLocation()).toString() == "*") {
                    grass.put(newGrass.getLocation(), newGrass);
                    mapBoundary.addElement(newGrass.getLocation());
                    n+=1;
                }
            } else {
                grass.put(newGrass.getLocation(), newGrass);
                mapBoundary.addElement(newGrass.getLocation());
                n+=1;
            }
        }
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        if (animals.containsKey(position)) {
            return true;
        }
        if (grass.containsKey(position)) {
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }
        if (grass.containsKey(position)) {
            return grass.get(position);
        }
        return null;
    }

    @Override
    public boolean place(Animal animal) {
        mapBoundary.addElement(animal.getLocation());
        return super.place(animal);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animals.put(newPosition, animals.get(oldPosition));
        animals.remove(oldPosition);
        mapBoundary.positionChanged(oldPosition, newPosition);
    }
    public Vector2d getLowerLeft() {
        return mapBoundary.getLowerLeft();
    }

    public Vector2d getUpperRight() {
        return mapBoundary.getUpperRight();
    }

    public String toString() {
        Vector2d lowerLeft = mapBoundary.getLowerLeft();
        Vector2d upperRight = mapBoundary.getUpperRight();
        return this.mapVisualizer.draw(lowerLeft, upperRight);
    }
}
