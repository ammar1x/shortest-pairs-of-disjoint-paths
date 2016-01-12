package pl.edu.pw.elka.gis.desp.tests.testPerformance;

import com.sun.tools.javac.jvm.Gen;
import pl.edu.pw.elka.gis.desp.app.Generator;
import pl.edu.pw.elka.gis.desp.comp.CompMethod;
import pl.edu.pw.elka.gis.desp.comp.KShortestDisjoint;
import pl.edu.pw.elka.gis.desp.comp.KShortestDisjointNaive;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;
import pl.edu.pw.elka.gis.desp.tests.testApp.TestResult;

public class testPerformance {




    static void testRandomGraph(double density, CompMethod compMethod) {
        int size = 100;
        for(int i = 0; i < 10; size *= 2, i += 1) {
            WeightedDiagraph g = Generator.erdosRenyi(size, density);
            TestResult tr = TestResult.runTest(compMethod, g, 1, size - 1);
            System.out.printf("%d %d %d\n", g.getV(), g.getE(), tr.time);
        }
    }


    public static void main(String[] args) {

        System.out.println("------------------");
        System.out.println("density = " + 0.01);
        testRandomGraph(0.01, CompMethod.Naive);
        System.out.println("------------------");
        System.out.println("density = " + 0.1);
        testRandomGraph(0.02, CompMethod.Naive);
        System.out.println("------------------");
        System.out.println("density = " + 0.01);
        testRandomGraph(0.01, CompMethod.Better);
        System.out.println("------------------");
        System.out.println("density = " + 0.1);
        testRandomGraph(0.02, CompMethod.Better);

    }


}
