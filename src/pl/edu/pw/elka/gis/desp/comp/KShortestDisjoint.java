package pl.edu.pw.elka.gis.desp.comp;


import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

/**
 * An interface for the algorithms computing the shortest path with disjoint edges.
 */
public interface KShortestDisjoint {
    /**
     * Run the algorithm for given ingredients.
     * @param weightedDiagraph the weighted diagraph
     * @param src the src node
     * @param dst the dst node
     * @return EdgeDisjointPaths object
     */
    public EdgedDisjointedPaths run(WeightedDiagraph weightedDiagraph, int src, int dst);
}
