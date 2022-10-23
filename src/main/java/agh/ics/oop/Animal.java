package agh.ics.oop;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d location = new Vector2d(2, 2);
    public MapDirection getDirection() {
        return direction;
    }
    public Vector2d getLocation() {
        return location;
    }
    public void setDirection(MapDirection newDirection) {
        direction = newDirection;
    }
    public void setLocation(Vector2d newLocation) {
        location = newLocation;
    }
    public String toString() {
        return "Koordynaty: " + getLocation().toString() + "Kierunek: " + getDirection().toString();
    }
    public boolean isAt(Vector2d position) {
        if ( this.location.equals(position) ) {
            return true;
        }
        return false;
    }
    public void move(Direction direction) {
        Vector2d vector0 = new Vector2d(0,0);
        Vector2d vector4 = new Vector2d(4,4);
        switch (direction) {
            case RIGHT:
                setDirection(getDirection().next());
                break;
            case LEFT:
                setDirection(getDirection().previous());
                break;
            case FORWARD:
                if (getLocation().add(getDirection().toUnitVector()).follows(vector0) && getLocation().add(getDirection().toUnitVector()).precedes(vector4)) {
                    setLocation(getLocation().add(getDirection().toUnitVector()));
                }
                break;
            case BACKWARD:
                if (getLocation().add(getDirection().toUnitVector()).follows(vector0) && getLocation().add(getDirection().toUnitVector()).precedes(vector4)) {
                    setLocation(getLocation().add(getDirection().toUnitVector()));
                }
                break;
        }
    }
}
