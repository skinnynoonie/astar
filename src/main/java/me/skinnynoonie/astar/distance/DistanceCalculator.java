package me.skinnynoonie.astar.distance;

import me.skinnynoonie.astar.position.Position;

/**
 * Calculates the distance between two positions.
 */
public interface DistanceCalculator<P extends Position> {
    /**
     * Calculates the distance between two positions.
     *
     * @param posOne Position one (can be referred to as the "start")
     * @param posTwo Position two (can be referred to as the "end")
     * @return The distance between the two positions.
     */
    double calculate(P posOne, P posTwo);
}
