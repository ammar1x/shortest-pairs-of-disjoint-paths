package pl.edu.pw.elka.gis.desp.comp;

import pl.edu.pw.elka.gis.desp.model.DirectedEdge;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.util.List;

public class KShortestDisjointBetter  implements KShortestDisjoint {

    private final int src;
    private final int dst;
    private final WeightedDiagraph wdg;
    private EdgedDisjointedPaths paths;

    public KShortestDisjointBetter(WeightedDiagraph wdg, int src, int dst) {
        this.wdg = wdg;
        this.src = src;
        this.dst = dst;
    }


    public void reverseAndNegatePath(WeightedDiagraph wg, Path p) {
        for(DirectedEdge e: p.getEdges()) {
            wg.reverseAndNegateEdge(e);
        }
    }

    @Override
    public void run() {
        ShortestPath sp1 = new DijkstraSP(wdg, src);
        ShortestPath sp2 = new BellmanFordSP(wdg, src);
        // 1. run Dijkstra
        sp1.run();
        Path fpath = sp1.pathTo(dst);
        if(fpath == null) {
            this.paths = null;
            return;
        }
        // 2. Reverse path
        reverseAndNegatePath(wdg, fpath);

        // 3. Run the second algorithm
        sp2.run();
        Path spath = sp2.pathTo(dst);
        if(spath == null) {
            this.paths = new EdgedDisjointedPaths(fpath, null);
            return;
        }
        // 4. Remove common edges
        List<DirectedEdge> commonEdges = fpath.getCommonEdges(spath);
        fpath.removeEdges(commonEdges);
        spath.removeEdges(commonEdges);

        // 5. Again, reverse the first path
        reverseAndNegatePath(wdg, fpath);

        // 6. find some paths with given nodes
        this.paths =  findTwoPaths(fpath, spath);
    }

    private EdgedDisjointedPaths findTwoPaths(Path fpath, Path spath) {
        // TODO find method to combine edges from two paths to create two distinct paths
        return new EdgedDisjointedPaths(fpath, spath);
    }


    @Override
    public EdgedDisjointedPaths getPaths() {
        return paths;
    }
}
