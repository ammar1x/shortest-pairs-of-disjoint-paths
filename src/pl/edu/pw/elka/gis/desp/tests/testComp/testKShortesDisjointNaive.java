package pl.edu.pw.elka.gis.desp.tests.testComp;


import pl.edu.pw.elka.gis.desp.comp.CompMethod;
import pl.edu.pw.elka.gis.desp.io.CompOutputFormatter;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;
import pl.edu.pw.elka.gis.desp.tests.testApp.TestResult;

public class testKShortesDisjointNaive {
    static CompOutputFormatter of = new CompOutputFormatter();



    public static void testSpecialCase1() {

        WeightedDiagraph weightedDiagraph = new WeightedDiagraph(5);
        weightedDiagraph.addEdge(1, 2, 3);
        weightedDiagraph.addEdge(1, 3, 1);
        weightedDiagraph.addEdge(3, 2, 1);
        weightedDiagraph.addEdge(2, 4, 1);
        weightedDiagraph.addEdge(3, 4, 3);
        TestResult tr = TestResult.runTest(CompMethod.Naive, weightedDiagraph, 1, 4);
        System.out.println(of.formatComp(CompMethod.Naive, tr.paths, tr.time));
    }

    public static void testSpecialCase2() {
        // Naive
        WeightedDiagraph weightedDiagraph = new WeightedDiagraph(6);
        weightedDiagraph.addEdge(1, 2, 3);
        weightedDiagraph.addEdge(1, 3, 1);
        weightedDiagraph.addEdge(3, 2, 1);
        weightedDiagraph.addEdge(2, 4, 1);
        weightedDiagraph.addEdge(2, 5, 2);
        weightedDiagraph.addEdge(5, 3, 4);
        weightedDiagraph.addEdge(5, 4, 3);
        TestResult tr = TestResult.runTest(CompMethod.Naive, weightedDiagraph, 1, 4);
        System.out.println(of.formatComp(CompMethod.Naive, tr.paths, tr.time));
        System.out.println(tr.paths);
        System.out.println(tr.paths.first().getEdges());
    }


    public static void main(String[] args) {
        testSpecialCase1();
        testSpecialCase2();
    }

}
