package me.skinnynoonie.astar;

import me.skinnynoonie.astar.distance.DistanceCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DefaultPathfinder<P extends Position> implements Pathfinder<P> {
    private final MovementController<P> movementController;
    private final DistanceCalculator<P> distanceCalculator;

    public DefaultPathfinder(
        MovementController<P> movementController,
        DistanceCalculator<P> distanceCalculator
    ) {
        this.movementController = movementController;
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public List<? extends P> findPath(P startPos, P endPos) throws RuntimeException {
        Map<P, Node<P>> openNodes = new HashMap<>();
        Set<P> closedPositions = new HashSet<>();

        openNodes.put(startPos, new Node<>(startPos, null, 0.0, 0.0));

        while (!openNodes.isEmpty()) {
            Node<P> currentNode = this.getNodeWithLowestFCost(openNodes);
            P currentPos = currentNode.getPosition();

            if (currentPos.equals(endPos)) {
                return this.getPathFromStartToEnd(currentNode);
            }

            openNodes.remove(currentPos);
            closedPositions.add(currentPos);

            for (P neighbourPos : this.movementController.getTraversableNeighbours(currentPos)) {
                if (closedPositions.contains(neighbourPos)) {
                    continue;
                }

                double neighbourGCost = currentNode.getGCost() +
                        this.distanceCalculator.calculate(currentPos, neighbourPos);

                Node<P> neighbourNode = openNodes.get(neighbourPos);
                if (neighbourNode != null) {
                    if (neighbourGCost < neighbourNode.getGCost()) {
                        neighbourNode.setParent(currentNode);
                        neighbourNode.setGCost(neighbourGCost);
                    }
                    continue;
                }

                double neighbourHCost = this.distanceCalculator.calculate(neighbourPos, endPos);
                openNodes.put(neighbourPos, new Node<>(neighbourPos, currentNode, neighbourGCost, neighbourHCost));
            }
        }

        throw new PathNotFoundException();
    }

    private Node<P> getNodeWithLowestFCost(Map<P, Node<P>> openNodes) {
        Iterator<Node<P>> openNodesIterator = openNodes.values().iterator();
        Node<P> currentNode = openNodesIterator.next();
        while(openNodesIterator.hasNext()) {
            Node<P> openNode = openNodesIterator.next();
            if (openNode.getFCost() < currentNode.getFCost()) {
                currentNode = openNode;
            }
        }
        return currentNode;
    }

    private List<P> getPathFromStartToEnd(Node<P> endNode) {
        List<P> endToStartPath = new ArrayList<>();
        Node<P> childNode = endNode;
        while (childNode != null) {
            endToStartPath.add(childNode.getPosition());
            childNode = childNode.getParent();
        }
        Collections.reverse(endToStartPath);
        return endToStartPath;
    }
}
