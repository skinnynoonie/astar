package me.skinnynoonie.astar;

import me.skinnynoonie.astar.close.ClosedPositionsCollection;
import me.skinnynoonie.astar.distance.DistanceCalculator;
import me.skinnynoonie.astar.movement.MovementController;
import me.skinnynoonie.astar.open.OpenNodesQueue;
import me.skinnynoonie.astar.position.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public final class DefaultAStarPathfinder<P extends Position> implements AStarPathfinder<P> {
    private final MovementController<P> movementController;
    private final DistanceCalculator<P> distanceCalculator;
    private final Supplier<OpenNodesQueue<P>> openNodesQueueFactory;
    private final Supplier<ClosedPositionsCollection<P>> closedPositionsCollectionFactory;

    public DefaultAStarPathfinder(
        MovementController<P> movementController,
        DistanceCalculator<P> distanceCalculator,
        Supplier<OpenNodesQueue<P>> openNodesQueueFactory,
        Supplier<ClosedPositionsCollection<P>> closedPositionsCollectionFactory
    ) {
        this.movementController = movementController;
        this.distanceCalculator = distanceCalculator;
        this.openNodesQueueFactory = openNodesQueueFactory;
        this.closedPositionsCollectionFactory = closedPositionsCollectionFactory;
    }

    @Override
    public List<? extends P> findPath(P startPos, P endPos) throws RuntimeException {
        OpenNodesQueue<P> openNodesQueue = this.openNodesQueueFactory.get();
        ClosedPositionsCollection<P> closedPositionsCollection = this.closedPositionsCollectionFactory.get();

        openNodesQueue.add(new Node<>(startPos, null, 0.0, 0.0));

        while (openNodesQueue.hasNodes()) {
            Node<P> currentNode = openNodesQueue.getAndRemoveFirst();
            P currentPos = currentNode.getPosition();

            if (currentPos.equals(endPos)) {
                return this.getPathFromStartToEnd(currentNode);
            }

            closedPositionsCollection.add(currentPos);

            for (P neighbourPos : this.movementController.getTraversableNeighbours(currentPos)) {
                if (closedPositionsCollection.contains(neighbourPos)) {
                    continue;
                }

                double neighbourGCost = currentNode.getGCost() +
                        this.distanceCalculator.calculate(currentPos, neighbourPos);

                Node<P> neighbourNode = openNodesQueue.get(neighbourPos);
                if (neighbourNode != null) {
                    if (neighbourGCost < neighbourNode.getGCost()) {
                        neighbourNode.setParent(currentNode);
                        neighbourNode.setGCost(neighbourGCost);
                        openNodesQueue.update(neighbourNode);
                    }
                    continue;
                }

                double neighbourHCost = this.distanceCalculator.calculate(neighbourPos, endPos);
                openNodesQueue.add(new Node<>(neighbourPos, currentNode, neighbourGCost, neighbourHCost));
            }
        }

        throw new PathNotFoundException();
    }

    private List<P> getPathFromStartToEnd(Node<P> endNode) {
        LinkedList<P> startToEndPath = new LinkedList<>();
        Node<P> childNode = endNode;
        while (childNode != null) {
            startToEndPath.addFirst(childNode.getPosition());
            childNode = childNode.getParent();
        }
        return startToEndPath;
    }
}
