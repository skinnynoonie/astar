package me.skinnynoonie.astar;

import me.skinnynoonie.astar.distance.EuclideanDistanceCalculator;
import me.skinnynoonie.astar.position.Position2D;

import java.util.ArrayList;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 3},
                {1, 0, 0, 2, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0},
        };

        DefaultPathfinder<Position2D> defaultPathfinder = new DefaultPathfinder<>(
                position -> {
                    int x = (int) position.getX();
                    int y = (int) position.getY();

                    List<Position2D> traversableNeighbours = new ArrayList<>();
                    if (x > 0 && map[x - 1][y] != 1) {
                        traversableNeighbours.add(new Position2D(x - 1, y));
                    }
                    if (y > 0 && map[x][y - 1] != 1) {
                        traversableNeighbours.add(new Position2D(x, y - 1));
                    }
                    if (x < map.length - 1 && map[x + 1][y] != 1) {
                        traversableNeighbours.add(new Position2D(x + 1, y));
                    }
                    if (y < map[0].length - 1 && map[x][y + 1] != 1) {
                        traversableNeighbours.add(new Position2D(x, y + 1));
                    }
                    return traversableNeighbours;
                },
                EuclideanDistanceCalculator.TWO_DIMENSION
        );

        defaultPathfinder.findPath(new Position2D(1, 3), new Position2D(0, 7))
                .forEach(System.out::println);
    }
}
