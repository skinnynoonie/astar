package me.skinnynoonie.astar.distance;

import me.skinnynoonie.astar.position.Position2D;
import me.skinnynoonie.astar.position.Position3D;

public final class DistanceCalculators {
    public static final DistanceCalculator<Position2D> EUCLIDEAN_TWO_DIMENSION =
            (posOne, posTwo) -> pythagorasAddition(
                    posTwo.getX() - posOne.getX(),
                    posTwo.getY() - posOne.getY()
            );
    public static final DistanceCalculator<Position3D> EUCLIDEAN_THREE_DIMENSION =
            (posOne, posTwo) -> pythagorasAddition(
                    posTwo.getX() - posOne.getX(),
                    posTwo.getY() - posOne.getY(),
                    posTwo.getZ() - posOne.getZ()
            );

    private static double pythagorasAddition(double... terms) {
        double sumOfTermsSquared = 0.0;
        for (double number : terms) {
            sumOfTermsSquared += number * number;
        }
        return Math.sqrt(sumOfTermsSquared);
    }

    public static final DistanceCalculator<Position2D> MANHATTAN_TWO_DIMENSION =
            (posOne, posTwo) -> Math.abs(posTwo.getX() - posOne.getX()) + Math.abs(posTwo.getY() - posOne.getY());
    public static final DistanceCalculator<Position3D> MANHATTAN_THREE_DIMENSION =
            (posOne, posTwo) ->
                    Math.abs(posTwo.getX() - posOne.getX()) +
                    Math.abs(posTwo.getY() - posOne.getY()) +
                    Math.abs(posTwo.getZ() - posOne.getZ());

    private DistanceCalculators() {
        throw new UnsupportedOperationException("can not instantiate util class");
    }
}
