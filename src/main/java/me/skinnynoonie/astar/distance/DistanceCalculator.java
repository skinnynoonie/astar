package me.skinnynoonie.astar.distance;

import me.skinnynoonie.astar.Position;

public interface DistanceCalculator<P extends Position> {
    double calculate(P posOne, P posTwo);
}
