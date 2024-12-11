package me.skinnynoonie.astar;

import java.util.List;

public interface Pathfinder<P extends Position> {
    List<? extends P> findPath(P start, P end) throws PathNotFoundException;
}
