package me.skinnynoonie.astar;

import me.skinnynoonie.astar.close.HashSetClosedPositionsCollection;
import me.skinnynoonie.astar.distance.DistanceCalculators;
import me.skinnynoonie.astar.movement.MovementController;
import me.skinnynoonie.astar.open.BinaryHeapHashMapOpenNodesQueue;
import me.skinnynoonie.astar.position.Position2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class Example {
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

        MovementController<Position2D> movementController = position -> {
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
        };

        DefaultAStarPathfinder<Position2D> pathfinder = new DefaultAStarPathfinderBuilder<Position2D>()
                .setMovementController(movementController)
                .setDistanceCalculator(DistanceCalculators.MANHATTAN_TWO_DIMENSION)
                .setClosedPositionsCollectionFactory(HashSetClosedPositionsCollection::new)
                .setOpenNodesQueueFactory(BinaryHeapHashMapOpenNodesQueue::new)
                .build();

        pathfinder
                .findPath(new Position2D(1, 1), new Position2D(10, 14))
                .forEach(position -> map[(int) position.getX()][(int) position.getY()] = 9);
        Stream.of(map).map(Arrays::toString).forEach(System.out::println);
    }
}
