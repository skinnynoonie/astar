package me.skinnynoonie.astar.position;

/**
 * An immutable position.
 * The dimensional space this position belongs to is held abstracted.
 */
public interface Position {
    /**
     * Checks if this position is equal to another position.
     * Only the actual positions will be checked (any metadata or non-positional data will be ignored).
     *
     * @param other The other position.
     * @return If this position is "positionally" equal to the other position.
     */
    @Override
    boolean equals(Object other);

    /**
     * Generates a hash code only on the actual position (any metadata or non-positional data will be ignored).
     *
     * @return A hash code representing this position.
     */
    @Override
    int hashCode();
}
