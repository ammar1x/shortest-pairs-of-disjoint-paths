package pl.edu.pw.elka.gis.desp.tests.testPerformance;

import com.sun.tools.javac.jvm.Gen;
import pl.edu.pw.elka.gis.desp.app.Generator;
import pl.edu.pw.elka.gis.desp.comp.CompMethod;
import pl.edu.pw.elka.gis.desp.comp.KShortestDisjoint;
import pl.edu.pw.elka.gis.desp.comp.KShortestDisjointNaive;
import pl.edu.pw.elka.gis.desp.model.DirectedEdge;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;
import pl.edu.pw.elka.gis.desp.tests.testApp.TestResult;

import java.util.Random;

public class testPerformance {


    /**
     * Constant v, variable e
     * @param v
     */
    static void testConstantN(int v, double changeRate, int iters) {
        int size = v;
        System.out.println("running test with constant n = " + v);
        System.out.print("format: ");
        System.out.println("V E BetterTime NaiveTime");
        for(int i = 0; i < iters; size *= changeRate, i += 1) {
            WeightedDiagraph g = Generator.empty(v);
            addEdges(g, size);
            TestResult tr1 = TestResult.profile(CompMethod.Better, g, 1, v - 1, 2);
            TestResult tr = TestResult.profile(CompMethod.Naive, g, 1, v - 1, 2);
            System.out.printf("%d %d %f %f\n", g.getV(), g.getE(), tr1.timed, tr.timed);
        }
    }


    static void testConstantE(int e, int start, double changeRate, int iters) {
        System.out.println("running test with constant e = " + e);
        System.out.print("format: ");
        System.out.println("V BetterTime NaiveTime");
        int size = start;
        for(int i = 0; i < iters; size *= changeRate, i += 1) {
            WeightedDiagraph g = Generator.empty(size);
            addEdges(g, e);
            TestResult tr1 = TestResult.profile(CompMethod.Better, g, 1, size - 1, 1);
            TestResult tr = TestResult.profile(CompMethod.Naive, g, 1, size - 1, 1);
            System.out.printf("%d %f %f\n", g.getV(), tr1.timed, tr.timed);
        }
    }

    static void testRandomGraph(double p, int start, double changeRate, int iters) {
        int v = start;
        System.out.println("running test with erdos-renyi model p = " + p);
        System.out.print("format: ");
        System.out.println("V E BetterTime NaiveTime");
        for(int i = 0; i < iters; v *= changeRate, i += 1) {
            WeightedDiagraph g = Generator.erdosRenyi(v, p);
            TestResult tr = TestResult.profile(CompMethod.Naive, g, 1, v - 1, 2);
            TestResult tr1 = TestResult.profile(CompMethod.Better, g, 1, v - 1, 2);
            System.out.printf("%d %d %f %f\n", g.getV(), g.getE(), tr1.timed, tr.timed);
        }
    }


    public static void addEdges(WeightedDiagraph wg, int count) {
        int k = 0;
        Random rand = new Random();
        for (int j = 0; j < wg.getV(); j++) {
            for (int i = 0; i  < wg.getV(); i++) {
                if(i != j && rand.nextBoolean()) {
                    wg.addEdge(i, j, rand.nextDouble());
                    k++;
                    if(k >= count)
                        return;
                }

            }
        }

    }
    static void warmup(int ssize) {
        int size = ssize;
        for(int i = 0; i < 10; size *= 2, i += 1) {
            WeightedDiagraph g = Generator.empty(size);
            addEdges(g, 1000);
            TestResult tr = TestResult.runTest(CompMethod.Better, g, 1, size - 1);
            TestResult tr1 = TestResult.runTest(CompMethod.Naive, g, 1, size - 1);
        }
    }


    public static void main(String[] args) {

        for(int i = 0; i < 5; i++) {
            warmup(100);
        }
        testConstantE(5000, 500, 2,  10);

    }


}
