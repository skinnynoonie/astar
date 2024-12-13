package me.skinnynoonie.astar.open;

import me.skinnynoonie.astar.Node;
import me.skinnynoonie.astar.position.Position;

import java.util.HashMap;
import java.util.Iterator;

public final class HashMapOpenNodesQueue<P extends Position> implements OpenNodesQueue<P> {
    private final HashMap<P, Node<P>> openNodes;

    public HashMapOpenNodesQueue() {
        this.openNodes = new HashMap<>();
    }

    @Override
    public void add(Node<P> node) {
        this.openNodes.put(node.getPosition(), node);
    }

    @Override
    public void update(Node<P> node) {
    }

    @Override
    public Node<P> getAndRemoveFirst() {
        Iterator<Node<P>> openNodesIterator = this.openNodes.values().iterator();
        Node<P> currentNode = openNodesIterator.next();
        while(openNodesIterator.hasNext()) {
            Node<P> openNode = openNodesIterator.next();
            if (openNode.getFCost() < currentNode.getFCost()) {
                currentNode = openNode;
            }
        }
        this.openNodes.remove(currentNode.getPosition());
        return currentNode;
    }

    @Override
    public Node<P> get(P position) {
        return this.openNodes.get(position);
    }

    @Override
    public boolean hasNodes() {
        return !this.openNodes.isEmpty();
    }
}
