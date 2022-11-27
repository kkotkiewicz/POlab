package agh.ics.oop;

public class Grass implements IMapElement{
    private Vector2d location;
    public Grass(Vector2d position) {
        this.location = position;
    }

    public boolean isAt(Vector2d position) {
        if ( this.location.equals(position) ) {
            return true;
        }
        return false;
    }

    public String getImageResource() {
        return "grass.png";
    }

    public Vector2d getLocation() {
        return location;
    }
    public String toString() {
        return "*";
    }
}
