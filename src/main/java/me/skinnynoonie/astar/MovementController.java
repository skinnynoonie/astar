package me.skinnynoonie.astar;

import java.util.List;

public interface MovementController<P extends Position> {
    List<? extends P> getTraversableNeighbours(P position);
}
