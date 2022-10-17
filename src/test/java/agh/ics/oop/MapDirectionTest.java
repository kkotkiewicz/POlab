package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
    }

    @Test
    void previous() {
        assertEquals(MapDirection.NORTH, MapDirection.EAST.next());
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.next());
        assertEquals(MapDirection.WEST, MapDirection.NORTH.next());
    }
}