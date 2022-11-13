package agh.ics.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap {
    private int grassCount;
    private Map<Vector2d, Grass> grass = new HashMap<>();
    public GrassField(int grassCount) {
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
                    n+=1;
                }
            } else {
                grass.put(newGrass.getLocation(), newGrass);
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
    private Vector2d lowerLeft() {
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (Animal animal : animals.values()) {
            lowerLeft.x = Math.min(lowerLeft.x, animal.getLocation().x);
            lowerLeft.y = Math.min(lowerLeft.y, animal.getLocation().y);
        }
        for (Grass element : grass.values()) {
            lowerLeft.x = Math.min(lowerLeft.x, element.getLocation().x);
            lowerLeft.y = Math.min(lowerLeft.y, element.getLocation().y);
        }
        return new Vector2d(lowerLeft.x, lowerLeft.y);

    }

    private Vector2d upperRight() {
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Animal animal : animals.values()) {
            upperRight.x = Math.max(upperRight.x, animal.getLocation().x);
            upperRight.y = Math.max(upperRight.y, animal.getLocation().y);
        }
        for (Grass element : grass.values()) {
            upperRight.x = Math.max(upperRight.x, element.getLocation().x);
            upperRight.y = Math.max(upperRight.y, element.getLocation().y);
        }
        return new Vector2d(upperRight.x, upperRight.y);

    }

    public String toString() {
        Vector2d lowerLeft = this.lowerLeft();
        Vector2d upperRight = this.upperRight();
        return this.mapVisualizer.draw(lowerLeft, upperRight);
    }
}
