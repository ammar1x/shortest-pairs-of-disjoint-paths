package pl.edu.pw.elka.gis.desp.comp;

import pl.edu.pw.elka.gis.desp.model.DirectedEdge;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.util.ArrayList;
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


    private void reverseAndNegatePath(WeightedDiagraph wg, Path p) {
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
        List<DirectedEdge> nedges1 = new ArrayList<DirectedEdge>();
        List<DirectedEdge> nedges2 = new ArrayList<DirectedEdge>();
        List<DirectedEdge> edges = fpath.getEdges();
        int pivot = 0;
        for (int i = 0; i < edges.size()-1; i++) {
            DirectedEdge ed1 = edges.get(i);
            DirectedEdge ed2 = edges.get(i+1);
            nedges1.add(ed1);
            if(ed1.getDst() != ed2.getSrc()) {
                nedges1.addAll(spath.getEdges().subList(i+1, edges.size()));
                pivot = i+1;
                break;
            }
        }
        if(pivot == 0) {
            return new EdgedDisjointedPaths(fpath, spath);
        } else {
            nedges2.addAll(spath.getEdges().subList(0, pivot));
            nedges2.addAll(fpath.getEdges().subList(pivot, edges.size()));
        }

        return new EdgedDisjointedPaths(new Path(nedges1), new Path(nedges2));
    }


    @Override
    public EdgedDisjointedPaths getPaths() {
        return paths;
    }
}
