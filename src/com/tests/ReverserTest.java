package com.tests;

import com.classes.*;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A class that tests the Reverser class.
 * 
 * @author Juan Jovel (www.github.com/JuanJovel)
 * @version 1.0.0
 */
public class ReverserTest {
    private Reverser reverser;

    /**
     * Sets up the reverser before testing.
     */
    public void setUp() {
        reverser = new Reverser();
    }


    /**
     * Tests the .reverse() method.
     */
    @Test
    public void testReverse() {
        setUp();
        assertEquals("ih", reverser.reverse("hi"));
    }


    /**
     * Tests the .reverse() method.
     */
    @Test
    public void testReverseNull() {
        setUp();
        Exception exception = null;
        try {
            reverser.reverse(null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        System.out.println(exception.getMessage());

        assertNotNull(exception);
    }


    /**
     * Tests the isReverseOf() method.
     */
    @Test
    public void testReverseOf() {
        setUp();
        assertTrue(reverser.isReverseOf("ih", "hi"));
    }


    /**
     * Tests the .isReverseOf() method if an argument is null.
     */
    @Test
    public void testReverseOfNull() {
        setUp();
        Exception exception = null;
        try {
            reverser.isReverseOf(null, null);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        System.out.println(exception.getMessage());

        assertNotNull(exception);

    }

}
