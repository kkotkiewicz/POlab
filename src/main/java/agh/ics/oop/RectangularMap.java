package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    private int width;
    MapVisualizer mapVisualizer = new MapVisualizer(this);
    private int height;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width, height)) && !this.isOccupied(position);
    }

    public String toString() {
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(width, height));
    }


}
