package pl.edu.pw.elka.gis.desp.comp;

import pl.edu.pw.elka.gis.desp.model.DirectedEdge;

import java.util.DoubleSummaryStatistics;


/**
 * A data type representing two paths with disjointed edges.
 */
public class EdgedDisjointedPaths {

    private final Path fpath, spath;
    private final double totalDistance;

    /**
     * Create a new object.
     * @param fpath the first path
     * @param spath the second path
     */
    public EdgedDisjointedPaths(Path fpath, Path spath) {
        this.fpath = fpath;
        this.spath = spath;
        this.totalDistance = (fpath == null) ? Double.POSITIVE_INFINITY : fpath.getDistance()
                            + ((spath == null) ? Double.POSITIVE_INFINITY : spath.getDistance());
    }

    /**
     * Return the first path.
     * @return the first path
     */
    public Path first() {
        return fpath;
    }

    /**
     * Return the second path.
     * @return the second path
     */
    public Path second() {
        return spath;
    }

    /**
     * Return the total distance
     * @return total distance
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    @Override
    public String toString() {
        return "EdgedDisjointedPaths{" +
                "fpath=" + fpath +
                ", spath=" + spath +
                ", totalDistance=" + totalDistance +
                '}';
    }
}
