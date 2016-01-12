package pl.edu.pw.elka.gis.desp.tests.testApp;

import pl.edu.pw.elka.gis.desp.comp.*;
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
        KShortestDisjoint ksdj = null;
        if (compMethod == CompMethod.Naive) {
             ksdj = new KShortestDisjointNaive(wdg, src, dst);
        } else {
            ksdj = new KShortestDisjointBetter(wdg, src, dst);
        }
        ksdj.run();
        paths = ksdj.getPaths();
        long time = System.currentTimeMillis() - s;
        return new TestResult(paths, time);
    }

}