package pl.edu.pw.elka.gis.desp.comp;

import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

public class KShortestDisjointBetter  implements KShortestDisjoint {

    private final int src;
    private final int dst;
    private final WeightedDiagraph wdg;

    public KShortestDisjointBetter(WeightedDiagraph wdg, int src, int dst) {
        this.wdg = wdg;
        this.src = src;
        this.dst = dst;
    }

    @Override
    public void run() {

    }

    @Override
    public EdgedDisjointedPaths getPaths() {
        return null;
    }
}
