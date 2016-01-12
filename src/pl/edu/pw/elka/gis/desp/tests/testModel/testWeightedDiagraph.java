package pl.edu.pw.elka.gis.desp.tests.testModel;


import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

public class testWeightedDiagraph {


    /**
     * test adding and removing edges.
     */
    public static void test1() {
        WeightedDiagraph wd = new WeightedDiagraph(10);
        wd.addEdge(0, 1, 10);
        wd.addEdge(1, 2, 12);
        assert wd.getE() == 2;
        wd.removeEdge(1, 2, 12);
        assert wd.getE() == 1;
    }

    /**
     * test cloning weighted diagraph.
     */
    public static void test2() {
        WeightedDiagraph org = new WeightedDiagraph(10);
        org.addEdge(1, 2, 3);
        org.addEdge(1, 3, 3);
        org.addEdge(1, 4, 3);
        org.addEdge(1, 5, 3);
        WeightedDiagraph clone = org.clone();
        assert clone.getE() == org.getE();
        // remove edge from the clone
        clone.removeEdge(1, 5, 3);
        assert clone.getE() == org.getE() - 1;
    }

    public static void main(String [] args) {
        test1();
        test2();
        System.out.println("ok");
    }
}
