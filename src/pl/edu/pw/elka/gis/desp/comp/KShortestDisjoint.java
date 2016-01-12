package pl.edu.pw.elka.gis.desp.comp;


import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

/**
 * An interface for the algorithms computing the shortest path with disjoint edges.
 */
public interface KShortestDisjoint {
    /**
     * Run the algorithm for given ingredients.
     */
    public void run();

    /**
     * Return calculated paths.
     * @return two edge-disjoint paths
     */
    public EdgedDisjointedPaths getPaths();

}
