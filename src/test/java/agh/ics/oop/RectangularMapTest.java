package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canMoveTo() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        Animal animal2 = new Animal(map, new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);
        assertTrue(map.canMoveTo(new Vector2d(3,3)));
        assertTrue(map.canMoveTo(new Vector2d(3,5)));
        assertTrue(map.canMoveTo(new Vector2d(3,4)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        assertFalse(map.canMoveTo(new Vector2d(2, 3)));
        assertFalse(map.canMoveTo(new Vector2d(-1, 0)));
        assertFalse(map.canMoveTo(new Vector2d(2, 6)));
        animal1.move(Direction.BACKWARD);
        assertFalse(map.canMoveTo(new Vector2d(2, 1)));
        assertFalse(map.canMoveTo(new Vector2d(6, 5)));
        assertTrue(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    void place() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        Animal animal2 = new Animal(map, new Vector2d(2, 3));
        assertTrue(map.place(animal1));
        assertTrue(map.place(animal2));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(2,2))));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(2,6))));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(2,-2))));

    }

    @Test
    void isOccupied() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        Animal animal2 = new Animal(map, new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);
        assertFalse(map.isOccupied(new Vector2d(2, 4)));
        assertFalse(map.isOccupied(new Vector2d(0,0)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
        assertFalse(map.isOccupied(new Vector2d(4,4)));
        map.place(new Animal(map, new Vector2d(4, 4)));
        assertTrue(map.isOccupied(new Vector2d(4,4)));
    }

    @Test
    void objectAt() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal1 = new Animal(map, new Vector2d(2, 2));
        Animal animal2 = new Animal(map, new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
        assertEquals(animal2, map.objectAt(new Vector2d(2, 3)));
        assertEquals(null, map.objectAt(new Vector2d(1, 2)));
        assertEquals(null, map.objectAt(new Vector2d(1, 1)));
        animal1.move(Direction.BACKWARD);
        animal2.move(Direction.FORWARD);
        assertEquals(animal1, map.objectAt(new Vector2d(2, 1)));
        assertEquals(animal2, map.objectAt(new Vector2d(2, 4)));
        assertEquals(null, map.objectAt(new Vector2d(2, 2)));
        assertEquals(null, map.objectAt(new Vector2d(2, 3)));
    }
}