package pl.edu.pw.elka.gis.desp.tests.testApp;

import pl.edu.pw.elka.gis.desp.app.Generator;
import pl.edu.pw.elka.gis.desp.comp.CompMethod;
import pl.edu.pw.elka.gis.desp.io.CompOutputFormatter;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.lang.reflect.Method;

/**
 * Class for verification tests.
 */
public class VerificationTests {

    static CompOutputFormatter of = new CompOutputFormatter();

    public static void testEmptyGraph(int v, CompMethod compMethod) {
        WeightedDiagraph weightedDiagraph = Generator.empty(10);
        TestResult tr = TestResult.runTest(compMethod, weightedDiagraph, 1, 2);
        System.out.println(of.formatComp(compMethod, tr.paths, tr.time));
    }


    public static void testTrivialGraph(int v, CompMethod compMethod) {
        WeightedDiagraph weightedDiagraph = Generator.trivial();
        TestResult tr = TestResult.runTest(compMethod, weightedDiagraph, 0, 0);
        System.out.println(of.formatComp(compMethod, tr.paths, tr.time));
    }

    public static void completeGraph(int v, CompMethod compMethod) {
        WeightedDiagraph weightedDiagraph = Generator.complete(v);
        TestResult tr = TestResult.runTest(compMethod, weightedDiagraph, 0, (v - 1));
        System.out.println(of.formatComp(compMethod, tr.paths, tr.time));
    }

    public static void testCompleteGraph(int v, CompMethod compMethod) {
        completeGraph(10, compMethod);
        completeGraph(100, compMethod);
        completeGraph(1000, compMethod);
    }

    public static void testTreeGraph(int v, CompMethod compMethod) {
        WeightedDiagraph weightedDiagraph = Generator.tree(v);
        TestResult tr = TestResult.runTest(compMethod, weightedDiagraph, 0, (v - 1));
        System.out.println(of.formatComp(compMethod, tr.paths, tr.time));
    }

    public static void stestNegativeWeightsGraph(int v, CompMethod compMethod) {
        WeightedDiagraph weightedDiagraph = Generator.negativeWeights(v);
        TestResult tr = TestResult.runTest(compMethod, weightedDiagraph, 0, (v - 1));
        System.out.println(of.formatComp(compMethod, tr.paths, tr.time));
    }

    public static void testSpecialCase1(int v, CompMethod compMethod) {
        // Naive should fail, better should find two edge disjoint paths
        // nasz przyklad 1
        WeightedDiagraph weightedDiagraph = new WeightedDiagraph(5);
        weightedDiagraph.addEdge(1, 2, 3);
        weightedDiagraph.addEdge(1, 3, 1);
        weightedDiagraph.addEdge(3, 2, 1);
        weightedDiagraph.addEdge(2, 4, 1);
        weightedDiagraph.addEdge(3, 4, 3);
        TestResult tr = TestResult.runTest(compMethod, weightedDiagraph, 1, 4);
        System.out.println(of.formatComp(compMethod, tr.paths, tr.time));
    }



    public static void testSpecialCase2(int v, CompMethod compMethod) {
        // Naive & better both should find two edge-disjoint paths

        WeightedDiagraph weightedDiagraph = new WeightedDiagraph(6);
        weightedDiagraph.addEdge(1, 2, 3);
        weightedDiagraph.addEdge(1, 3, 1);
        weightedDiagraph.addEdge(3, 2, 1);
        weightedDiagraph.addEdge(2, 4, 1);
        weightedDiagraph.addEdge(2, 5, 2);
        weightedDiagraph.addEdge(5, 3, 4);
        weightedDiagraph.addEdge(5, 4, 3);
        TestResult tr = TestResult.runTest(compMethod, weightedDiagraph, 1, 4);
        System.out.println(of.formatComp(compMethod, tr.paths, tr.time));
    }


    public static void runTests(Object... args) throws Exception {
        Method[] ms = VerificationTests.class.getMethods();
        for(Method m: ms) {
            if(m.getName().startsWith("test")) {
                System.out.println("-------------------------------------------");
                System.out.println("running test " + m.getName().substring("test".length()));
                System.out.println();
                m.invoke(null, 10, CompMethod.Better);
                m.invoke(null, 10, CompMethod.Naive);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("************************************");
        System.out.println("*  running verification tests      *");
        System.out.println("************************************");
        runTests();
    }

}
