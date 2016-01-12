package pl.edu.pw.elka.gis.desp.tests.testApp;

import pl.edu.pw.elka.gis.desp.comp.CompMethod;
import pl.edu.pw.elka.gis.desp.comp.EdgedDisjointedPaths;
import pl.edu.pw.elka.gis.desp.comp.KShortestDisjointBetter;
import pl.edu.pw.elka.gis.desp.comp.KShortestDisjointNaive;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

public class TestResult {
    EdgedDisjointedPaths paths;
    long time;

    public TestResult(EdgedDisjointedPaths paths, long time) {
        this.paths = paths;
        this.time = time;
    }

    public static TestResult runTest(CompMethod compMethod, WeightedDiagraph wdg, int src, int dst) {
        long s = System.currentTimeMillis();
        EdgedDisjointedPaths paths = null;
        if (compMethod == CompMethod.Naive) {
            paths = new KShortestDisjointNaive().run(wdg, src, dst);
        } else {
            paths = new KShortestDisjointBetter().run(wdg, src, dst);
        }
        long time = System.currentTimeMillis() - s;
        return new TestResult(paths, time);
    }

}