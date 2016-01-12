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
        TestResult tr = TestResult.runTest(compMethod, weightedDiagraph, 1, 2);
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

    public static void testNegativeWeightsGraph(int v, CompMethod compMethod) {
        WeightedDiagraph weightedDiagraph = Generator.negativeWeights(v);
        TestResult tr = TestResult.runTest(compMethod, weightedDiagraph, 0, (v - 1));
        System.out.println(of.formatComp(compMethod, tr.paths, tr.time));
    }

    public static void runTests(Object... args) throws Exception {
        Method[] ms = VerificationTests.class.getMethods();
        for(Method m: ms) {
            if(m.getName().startsWith("test")) {
                System.out.println("-------------------------------------------");
                System.out.println("running test " + m.getName().substring("test".length()));
                System.out.println();
                m.invoke(null, 10, CompMethod.Naive);
                m.invoke(null, 10, CompMethod.Better);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        runTests();
    }

}
