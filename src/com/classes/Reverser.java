package com.classes;

/**
 * 
 * A class that reverses Strings.
 * 
 * @author Juan Jovel (www.github.com/JuanJovel)
 * @version 1.1
 *
 */
public class Reverser {
    /**
     * Reverses the String.
     * 
     * @param toBeReversed
     *            The String that will be reversed.
     * @return The reverse of the String provided.
     * @throws IllegalArgumentException
     *             If the parameter is null.
     */
    public String reverse(String toBeReversed) {
        if (toBeReversed == null) {
            throw new IllegalArgumentException(
                "The String parameter for the reverse method cannot be null.");
        }
        String reversed = "";
        for (int i = toBeReversed.length() - 1; i >= 0; i--) {
            reversed += toBeReversed.charAt(i);
        }
        return reversed;
    }


    /**
     * Tests if the first String is the reverse of the second.
     * 
     * @param possibleReverse
     *            The possible reverse of the original String.
     * @param original
     *            The original String whose reverse might be possibleReverse.
     * @return True if possibleReverse is the reverse of original.
     * @throws IllegalArgumentException
     *             if either of the Strings are null.
     */
    public boolean isReverseOf(String possibleReverse, String original) {
        if (possibleReverse == null || original == null) {
            throw new IllegalArgumentException(
                "The String parameters for the isReverseOf method cannot be null.");
        }
        if (this.reverse(possibleReverse).equals(original)) {
            return true;
        }
        return false;
    }

}
