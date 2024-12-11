package me.skinnynoonie.astar.distance;

import me.skinnynoonie.astar.position.Position2D;
import me.skinnynoonie.astar.position.Position3D;

public final class EuclideanDistanceCalculator {
    public static final DistanceCalculator<Position2D> TWO_DIMENSION =
            (posOne, posTwo) -> pythagorasAddition(
                    posTwo.getX() - posOne.getX(),
                    posTwo.getY() - posOne.getY()
            );
    public static final DistanceCalculator<Position3D> THREE_DIMENSION =
            (posOne, posTwo) -> pythagorasAddition(
                    posTwo.getX() - posOne.getX(),
                    posTwo.getY() - posOne.getY(),
                    posTwo.getZ() - posOne.getZ()
            );

    private static double pythagorasAddition(double... numbers) {
        double sumOfTermsSquared = 0.0;
        for (double number : numbers) {
            sumOfTermsSquared += number * number;
        }
        return Math.sqrt(sumOfTermsSquared);
    }

    private EuclideanDistanceCalculator() {
        throw new UnsupportedOperationException("can not instantiate util class");
    }
}
