package pl.edu.pw.elka.gis.desp.tests.testApp;

import pl.edu.pw.elka.gis.desp.comp.*;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

public class TestResult {
    public EdgedDisjointedPaths paths;
    public long time;
    public double timed;

    public TestResult(EdgedDisjointedPaths paths, long time, double timed) {
        this.paths = paths;
        this.time = time;
        this.timed  = timed;
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
        return new TestResult(paths, time, time);
    }

    public static TestResult profile(CompMethod compMethod, WeightedDiagraph wdg, int src, int dst,
                                     int n) {

        EdgedDisjointedPaths paths = null;
        KShortestDisjoint ksdj = null;
        long total = 0;
        if (compMethod == CompMethod.Naive) {
            ksdj = new KShortestDisjointNaive(wdg, src, dst);
        } else {
            ksdj = new KShortestDisjointBetter(wdg, src, dst);
        }
        for (int i = 0; i < n; i++) {
            long s = System.nanoTime();
            ksdj.run();
            paths = ksdj.getPaths();
            long time = System.nanoTime() - s;
            total += time;
        }

        return new TestResult(paths, (long) (total/(n*1e6)), (total*1.0/(n*1e6)));
    }




}