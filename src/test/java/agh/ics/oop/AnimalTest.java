package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void move() {
        IWorldMap map = new RectangularMap(4, 4);
        Animal animal = new Animal(map, new Vector2d(2, 2));
        animal.move(Direction.FORWARD);

        assertTrue(animal.isAt(new Vector2d(2,3)));

        animal.move(Direction.FORWARD);
        animal.move(Direction.FORWARD);
        animal.move(Direction.FORWARD);
        animal.move(Direction.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2,4)));
        assertEquals(animal.getDirection(), MapDirection.NORTH);

        animal.move(Direction.RIGHT);
        animal.move(Direction.FORWARD);
        animal.move(Direction.FORWARD);
        animal.move(Direction.FORWARD);
        assertTrue(animal.isAt(new Vector2d(4,4)));
        assertEquals(animal.getDirection(), MapDirection.EAST);

        animal.move(Direction.RIGHT);
        animal.move(Direction.FORWARD);
        animal.move(Direction.FORWARD);
        animal.move(Direction.RIGHT);
        assertTrue(animal.isAt(new Vector2d(4,2)));
        assertEquals(animal.getDirection(), MapDirection.WEST);

        animal.move(Direction.LEFT);
        animal.move(Direction.FORWARD);
        animal.move(Direction.FORWARD);
        animal.move(Direction.FORWARD);
        assertTrue(animal.isAt(new Vector2d(4,0)));
        assertEquals(animal.getDirection(), MapDirection.SOUTH);

        animal.move(Direction.LEFT);
        animal.move(Direction.LEFT);
        animal.move(Direction.LEFT);
        animal.move(Direction.FORWARD);
        assertTrue(animal.isAt(new Vector2d(3,0)));
        assertEquals(animal.getDirection(), MapDirection.WEST);

        String[] string = {"f", "forward", "b", "b", "b", "r", "backward", "r", "l", "r"};
        OptionsParser parser = new OptionsParser();
        Direction[] directions = parser.parse(string);

        animal.move(directions[0]);
        assertTrue(animal.isAt(new Vector2d(2,0)));
        assertEquals(animal.getDirection(), MapDirection.WEST);

        animal.move(directions[1]);
        assertTrue(animal.isAt(new Vector2d(1,0)));
        assertEquals(animal.getDirection(), MapDirection.WEST);

        animal.move(directions[2]);
        assertTrue(animal.isAt(new Vector2d(2,0)));
        assertEquals(animal.getDirection(), MapDirection.WEST);

        animal.move(directions[3]);
        assertTrue(animal.isAt(new Vector2d(3,0)));
        assertEquals(animal.getDirection(), MapDirection.WEST);

        animal.move(directions[4]);
        assertTrue(animal.isAt(new Vector2d(4,0)));
        assertEquals(animal.getDirection(), MapDirection.WEST);

        animal.move(directions[5]);
        assertTrue(animal.isAt(new Vector2d(4,0)));
        assertEquals(animal.getDirection(), MapDirection.NORTH);

        animal.move(directions[6]);
        assertTrue(animal.isAt(new Vector2d(4,0)));
        assertEquals(animal.getDirection(), MapDirection.NORTH);

        animal.move(directions[7]);
        assertTrue(animal.isAt(new Vector2d(4,0)));
        assertEquals(animal.getDirection(), MapDirection.EAST);

        animal.move(directions[8]);
        assertTrue(animal.isAt(new Vector2d(4,0)));
        assertEquals(animal.getDirection(), MapDirection.NORTH);

        animal.move(directions[9]);
        assertTrue(animal.isAt(new Vector2d(4,0)));
        assertEquals(animal.getDirection(), MapDirection.EAST);
    }
}