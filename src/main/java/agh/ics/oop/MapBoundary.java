package agh.ics.oop;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver {
    public MapBoundary(Map<Vector2d,Animal> animals, Map<Vector2d,Grass> grass) {
//        lowerLeft = new Vector2d(0,0);
//        upperRight = new Vector2d(10,10);
        for (Animal animal : animals.values()) {
            orderedX.add(animal.getLocation());
            orderedY.add(animal.getLocation());
        }
        for (Grass element : grass.values()) {
            orderedX.add(element.getLocation());
            orderedY.add(element.getLocation());
        }
//        lowerLeft = new Vector2d(orderedX.first().x, orderedY.first().y);
//        upperRight = new Vector2d(orderedX.last().x, orderedY.last().y);
    }

    class VectorComparatorX implements Comparator<Vector2d> {
        public int compare(Vector2d v1, Vector2d v2) {
            if (v1.x > v2.x) {
                return 1;
            } else if (v1.x == v2.x) {
                return v1.y - v2.y;
            } else {
                return -1;
            }
        }
    }

    class VectorComparatorY implements Comparator<Vector2d> {
        public int compare(Vector2d v1, Vector2d v2) {
            if (v1.y > v2.y) {
                return 1;
            } else if (v1.y == v2.y) {
                return v1.x - v2.x;
            } else {
                return -1;
            }
        }
    }
    private TreeSet<Vector2d> orderedX = new TreeSet<>(new VectorComparatorX());
    private TreeSet<Vector2d> orderedY = new TreeSet<>(new VectorComparatorY());
    private Vector2d upperRight;
    private Vector2d lowerLeft;

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        orderedX.add(newPosition);
        orderedY.add(newPosition);
        orderedX.remove(oldPosition);
        orderedY.remove(oldPosition);
        lowerLeft = new Vector2d(orderedX.first().x, orderedY.first().y);
        upperRight = new Vector2d(orderedX.last().x, orderedY.last().y);
    }

    public void addElement(Vector2d newPosition) {
        orderedX.add(newPosition);
        orderedY.add(newPosition);
        lowerLeft = new Vector2d(orderedX.first().x, orderedY.first().y);
        upperRight = new Vector2d(orderedX.last().x, orderedY.last().y);
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }
    public Vector2d getLowerLeft() {
        return lowerLeft;
    }
}
