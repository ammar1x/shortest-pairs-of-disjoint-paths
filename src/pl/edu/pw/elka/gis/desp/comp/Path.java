package pl.edu.pw.elka.gis.desp.comp;

import pl.edu.pw.elka.gis.desp.model.DirectedEdge;

import java.util.List;

/**
 * Represent a path in a weighted diagraph. The first Edge indicate the src node,
 * the second one the target node.
 */
public class Path {

    private double distance;
    private final List<DirectedEdge> edges;

    /**
     * Create a path data type.
     * @param distance the distance of the path
     * @param edges the edges comprising the path
     */
    public Path(double distance, List<DirectedEdge> edges) {
        this.distance = distance;
        this.edges = edges;
    }

    /**
     * Create a path data type. The distance is computed from the list of edges.
     * @param edges the edges comprising the path
     */
    public Path(List<DirectedEdge> edges) {
        this.distance = 0;
        this.edges = edges;
        for(DirectedEdge edge: edges) {
            this.distance += edge.getWeight();
        }
    }

    /**
     * Return the distance of the path.
     * @return the distance of the path
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Return the edges of the path.
     * @return the distance of the path.
     */
    public List<DirectedEdge> getEdges() {
        return edges;
    }


}
