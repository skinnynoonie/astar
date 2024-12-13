package me.skinnynoonie.astar;

import me.skinnynoonie.astar.close.ClosedPositionsCollection;
import me.skinnynoonie.astar.distance.DistanceCalculator;
import me.skinnynoonie.astar.movement.MovementController;
import me.skinnynoonie.astar.open.OpenNodesQueue;
import me.skinnynoonie.astar.position.Position;

import java.util.function.Supplier;

public final class DefaultAStarPathfinderBuilder<P extends Position> {
    private MovementController<P> movementController;
    private DistanceCalculator<P> distanceCalculator;
    private Supplier<OpenNodesQueue<P>> openNodesQueueFactory;
    private Supplier<ClosedPositionsCollection<P>> closedPositionsCollectionFactory;

    public DefaultAStarPathfinderBuilder<P> setMovementController(MovementController<P> movementController) {
        this.movementController = movementController;
        return this;
    }

    public DefaultAStarPathfinderBuilder<P> setDistanceCalculator(DistanceCalculator<P> distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
        return this;
    }

    public DefaultAStarPathfinderBuilder<P> setOpenNodesQueueFactory(
        Supplier<OpenNodesQueue<P>> openNodesQueueFactory
    ) {
        this.openNodesQueueFactory = openNodesQueueFactory;
        return this;
    }

    public DefaultAStarPathfinderBuilder<P> setClosedPositionsCollectionFactory(
        Supplier<ClosedPositionsCollection<P>> closedPositionsCollectionFactory
    ) {
        this.closedPositionsCollectionFactory = closedPositionsCollectionFactory;
        return this;
    }

    public DefaultAStarPathfinder<P> build() {
        return new DefaultAStarPathfinder<>(
                this.movementController,
                this.distanceCalculator,
                this.openNodesQueueFactory,
                this.closedPositionsCollectionFactory
        );
    }
}
