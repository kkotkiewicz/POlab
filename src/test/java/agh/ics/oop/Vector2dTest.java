package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    Vector2d vector1 = new Vector2d(1, 2);
    Vector2d vector2 = new Vector2d(3, 4);
    Vector2d vector3 = new Vector2d(-1, 2);
    Vector2d vector4 = new Vector2d(-4, -2);
    Vector2d vector5 = new Vector2d(-1, 5);
    @Test
    void testToString() {
        assertEquals("(1,2)", vector1.toString());
        assertEquals("(3,4)", vector2.toString());
        assertEquals("(-1,2)", vector3.toString());
        assertEquals("(-4,-2)", vector4.toString());
        assertEquals("(-1,5)", vector5.toString());
    }

    @Test
    void precedes() {
        assertTrue(vector1.precedes(vector2));
        assertFalse(vector2.precedes(vector1));
        assertFalse(vector3.precedes(vector4));
        assertFalse(vector1.precedes(vector3));
        assertFalse(vector1.precedes(vector5));
    }

    @Test
    void follows() {
        assertFalse(vector1.follows(vector2));
        assertTrue(vector2.follows(vector1));
        assertTrue(vector3.follows(vector4));
        assertTrue(vector1.follows(vector3));
        assertFalse(vector1.follows(vector5));
    }

    @Test
    void add() {
        assertEquals(new Vector2d(4, 6), vector1.add(vector2));
        assertEquals(new Vector2d(2, 6), vector2.add(vector3));
        assertEquals(new Vector2d(-5, 0), vector3.add(vector4));
        assertEquals(new Vector2d(0, 4), vector1.add(vector3));
        assertEquals(new Vector2d(-3, 0), vector1.add(vector4));
    }

    @Test
    void subtract() {
        assertEquals(new Vector2d(-2, -2), vector1.subtract(vector2));
        assertEquals(new Vector2d(4, 2), vector2.subtract(vector3));
        assertEquals(new Vector2d(3, 4), vector3.subtract(vector4));
        assertEquals(new Vector2d(2, 0), vector1.subtract(vector3));
        assertEquals(new Vector2d(5, 4), vector1.subtract(vector4));
    }

    @Test
    void upperRight() {
        assertEquals(new Vector2d(3, 4), vector1.upperRight(vector2));
        assertEquals(new Vector2d(3, 4), vector2.upperRight(vector3));
        assertEquals(new Vector2d(-1, 2), vector3.upperRight(vector4));
        assertEquals(new Vector2d(1, 2), vector1.upperRight(vector3));
        assertEquals(new Vector2d(1, 2), vector1.upperRight(vector4));
        assertEquals(new Vector2d(1, 5), vector1.upperRight(vector5));
    }

    @Test
    void lowerLeft() {
        assertEquals(new Vector2d(1,2), vector1.lowerLeft(vector2));
        assertEquals(new Vector2d(-1,2), vector2.lowerLeft(vector3));
        assertEquals(new Vector2d(-4, -2), vector3.lowerLeft(vector4));
        assertEquals(new Vector2d(-1, 2), vector1.lowerLeft(vector3));
        assertEquals(new Vector2d(-4, -2), vector1.lowerLeft(vector4));
        assertEquals(new Vector2d(-1, 2), vector1.lowerLeft(vector5));
    }

    @Test
    void opposite() {
        assertEquals(new Vector2d(-1, -2), vector1.opposite());
        assertEquals(new Vector2d(-3, -4), vector2.opposite());
        assertEquals(new Vector2d(1, -2), vector3.opposite());
        assertEquals(new Vector2d(4, 2), vector4.opposite());
        assertEquals(new Vector2d(1, -5), vector5.opposite());
    }

    @Test
    void testEquals() {
        assertTrue(vector1.equals(new Vector2d(1,2)));
        assertTrue(vector2.equals(new Vector2d(3,4)));
        assertTrue(vector3.equals(new Vector2d(-1,2)));
        assertTrue(vector4.equals(new Vector2d(-4,-2)));
        assertTrue(vector5.equals(new Vector2d(-1,5)));
        assertFalse(vector1.equals(vector2));
        assertFalse(vector2.equals(vector5));
        assertFalse(vector1.equals(vector3));
    }
}