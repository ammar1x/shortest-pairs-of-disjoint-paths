package pl.edu.pw.elka.gis.desp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Data type for representing weighted diagraph. This representation maintain a node indexed
 * list of lists of DirectedEdge objects.
 *
 */
public class WeightedDiagraph implements Cloneable {


    final private int v;
    private int e;
    private List<List<DirectedEdge>> adjacencyList = new ArrayList<List<DirectedEdge>>();

    /**
     * Create an empty diagraph with V nodes.
     */
    public WeightedDiagraph(int v) {
        this.v = v;
        this.e = 0;
        for (int i = 0; i < this.v; i++) {
            adjacencyList.add(new ArrayList<DirectedEdge>());
        }
    }

    /**
     * Return number of nodes.
     * @return number of nodes.
     */
    public int getV() {
        return v;
    }

    /**
     * Return number of edges.
     * @return number of edges.
     */
    public int getE() {
        return e;
    }

    /**
     * Add directed edge to the graph.
     * @param edge the edge to be added
     */
    public void addEdge(DirectedEdge edge) {
        adjacencyList.get(edge.getSrc()).add(edge);
        e++;
    }

    /**
     * Add directed edge from src to dst with given weight.
     * @param src the src node
     * @param dst the dst node
     * @param weight the weight of the edge
     */
    public void addEdge(int src, int dst, double weight) {
        addEdge(new DirectedEdge(src, dst, weight));
    }


    /**
     * Remove directed edge from src to dst with given weight.
     * @param src the src node
     * @param dst the dst node
     * @param weight the weight of the edge
     */
    public void removeEdge(int src, int dst, double weight) {
        removeEdge(new DirectedEdge(src, dst, weight));
    }

    /**
     * Remove directed edge from the graph.
     * @param directedEdge the edge to be removed
     */
    private void removeEdge(DirectedEdge directedEdge) {
        adjacencyList.get(directedEdge.getSrc()).remove(directedEdge);
        e--;
    }

    /**
     * Return nodes adjacent to node n
     * @param n node
     * @return nodes adjacent to node n
     */
    public List<DirectedEdge> adjacentTo(int n) {
        return adjacencyList.get(n);
    }

    private List<DirectedEdge> adjacentToCopy(int n) {
        return new ArrayList<DirectedEdge>(adjacentTo(n));
    }

    /**
     * Return a cloned object
     * @return weighted diagraph object
     */
    public WeightedDiagraph clone() {
        WeightedDiagraph we = new WeightedDiagraph(this.v);
        for (int i = 0; i < this.getV(); i++) {
            we.adjacencyList.add(adjacentToCopy(i));
        }
        return we;
    }

    @Override
    public String toString() {

        return "WeightedDiagraph{" +
                "adjacencyList=" + adjacencyList +
                ", v=" + v +
                ", e=" + e +
                '}';
    }
}
