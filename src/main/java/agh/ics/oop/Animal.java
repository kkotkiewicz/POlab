package agh.ics.oop;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d location;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d location) {

        this.map = map;
        this.location = location;
    }
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
        return getDirection().toStringShort();
    }
    public boolean isAt(Vector2d position) {
        if ( this.location.equals(position) ) {
            return true;
        }
        return false;
    }
    public void move(Direction direction) {
        switch (direction) {
            case RIGHT:
                setDirection(getDirection().next());
                break;
            case LEFT:
                setDirection(getDirection().previous());
                break;
            case FORWARD:
                if (map.canMoveTo(this.getLocation().add(getDirection().toUnitVector()))) {
                    setLocation(getLocation().add(getDirection().toUnitVector()));
                }
                break;
            case BACKWARD:
                if (map.canMoveTo(this.getLocation().add(getDirection().toUnitVector().opposite())) ) {
                    setLocation(getLocation().add(getDirection().toUnitVector().opposite()));
                }
                break;
        }
    }
}
