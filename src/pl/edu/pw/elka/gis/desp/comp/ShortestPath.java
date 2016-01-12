package pl.edu.pw.elka.gis.desp.comp;

import pl.edu.pw.elka.gis.desp.model.DirectedEdge;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

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

    /**
     * Set the Diagraph for which the algorithm compute the shortest path.
     * @param clone weighted diagraph object
     */
    void setDiagraph(WeightedDiagraph clone);
}
