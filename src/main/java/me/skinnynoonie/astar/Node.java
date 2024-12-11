package me.skinnynoonie.astar;

public final class Node<P extends Position> {
    private final P position;
    private Node<P> parent;
    private double gCost;
    private double hCost;

    public Node(P position, Node<P> parent, double gCost, double hCost) {
        this.position = position;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
    }

    public P getPosition() {
        return this.position;
    }

    public Node<P> getParent() {
        return this.parent;
    }

    public void setParent(Node<P> parent) {
        this.parent = parent;
    }

    public double getGCost() {
        return this.gCost;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    public double getHCost() {
        return this.hCost;
    }

    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    public double getFCost() {
        return this.gCost + this.hCost;
    }
}
