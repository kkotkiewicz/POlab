package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    private MapBoundary mapBoundary;
    private int width;
    MapVisualizer mapVisualizer = new MapVisualizer(this);
    private int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        mapBoundary = new MapBoundary(animals, new HashMap<>());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height)) && !this.isOccupied(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animals.put(newPosition, animals.get(oldPosition));
        animals.remove(oldPosition);
        mapBoundary.positionChanged(oldPosition, newPosition);
    }
    public String toString() {
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(width, height));
    }

    @Override
    public Vector2d getLowerLeft() {
        return new Vector2d(0,0);
    }

    public Vector2d getUpperRight() {
        return new Vector2d(width, height);
    }
}
