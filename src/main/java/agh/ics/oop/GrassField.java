package agh.ics.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap {
    private int grassCount;
    private List<Grass> grass = new ArrayList<>();
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
                    grass.add(newGrass);
                    n+=1;
                }
            } else {
                grass.add(newGrass);
                n+=1;
            }
        }
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getLocation().equals(position)) {
                return true;
            }
        }
        for (Grass element : grass) {
            if (element.getLocation().equals(position)) {
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
        for (Grass element : grass) {
            if (element.getLocation().equals(position)) {
                return element;
            }
        }
        return null;
    }
    private Vector2d lowerLeft() {
        Vector2d lowerLeft = animals.get(0).getLocation();
        for (Animal animal : animals) {
            lowerLeft.x = Math.min(lowerLeft.x, animal.getLocation().x);
            lowerLeft.y = Math.min(lowerLeft.y, animal.getLocation().y);
        }
        for (Grass element : grass) {
            lowerLeft.x = Math.min(lowerLeft.x, element.getLocation().x);
            lowerLeft.y = Math.min(lowerLeft.y, element.getLocation().y);
        }
        return new Vector2d(lowerLeft.x, lowerLeft.y);

    }

    private Vector2d upperRight() {
        Vector2d upperRight = animals.get(0).getLocation();
        for (Animal animal : animals) {
            upperRight.x = Math.max(upperRight.x, animal.getLocation().x);
            upperRight.y = Math.max(upperRight.y, animal.getLocation().y);
        }
        for (Grass element : grass) {
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
