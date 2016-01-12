package pl.edu.pw.elka.gis.desp.comp;

import pl.edu.pw.elka.gis.desp.model.DirectedEdge;

/**
 * An interface for the shortest path problem solver.
 */
public interface ShortestPath {


    /**
     * Run the algorithm.
     */
    void run();

    /**
     * Return the path to the destantion node
     * @param dstNode the destanation node
     * @return the path to the destanation node
     */
    Path pathTo(int dstNode);

}
