package me.skinnynoonie.astar;

import me.skinnynoonie.astar.close.HashSetClosedPositionsCollection;
import me.skinnynoonie.astar.distance.EuclideanDistanceCalculator;
import me.skinnynoonie.astar.open.HashMapOpenNodesQueue;
import me.skinnynoonie.astar.position.Position2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Example {
    public static void main(String[] args) {
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 2, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1},
                {1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 3},
                {1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
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
                EuclideanDistanceCalculator.TWO_DIMENSION,
                HashMapOpenNodesQueue::new,
                HashSetClosedPositionsCollection::new
        );

        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                if (map[x][y] == 2) {
                    startX = x;
                    startY = y;
                } else if (map[x][y] == 3) {
                    endX = x;
                    endY = y;
                }
            }
        }

        long start = System.currentTimeMillis();
        List<? extends Position2D> path =
                defaultPathfinder.findPath(new Position2D(startX, startY), new Position2D(endX, endY));
        System.out.println(System.currentTimeMillis() - start);

        path.forEach(position -> map[(int) position.getX()][(int) position.getY()] = 9);
        Stream.of(map).forEach(
                sublist -> System.out.println(Arrays.toString(sublist))
        );
    }
}
