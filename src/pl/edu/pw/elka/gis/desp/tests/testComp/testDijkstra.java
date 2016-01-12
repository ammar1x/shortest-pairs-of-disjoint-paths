package pl.edu.pw.elka.gis.desp.tests.testComp;


import pl.edu.pw.elka.gis.desp.comp.DijkstraSP;
import pl.edu.pw.elka.gis.desp.comp.Path;
import pl.edu.pw.elka.gis.desp.comp.ShortestPath;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

public class testDijkstra {

    public static void test1() {
        WeightedDiagraph g = new WeightedDiagraph(5);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 1);
        g.addEdge(3, 2, 1);
        g.addEdge(3, 4, 3);
        g.addEdge(2, 4, 1);
        ShortestPath sp = new DijkstraSP(g, 1);
        sp.run();
        Path p = sp.pathTo(4);
        System.out.println(p);
        assert p.getDistance() == 3;
    }



    public static void test2() {
        WeightedDiagraph g = new WeightedDiagraph(6);
        g.addEdge(1, 2, 4);
        g.addEdge(1, 3, 1);
        g.addEdge(3, 2, 1);
        g.addEdge(2, 5, 2);
        g.addEdge(5, 3, 4);
        g.addEdge(2, 4, 1);
        g.addEdge(5, 4, 3);
        ShortestPath sp = new DijkstraSP(g, 1);
        sp.run();
        Path p = sp.pathTo(4);
        System.out.println(p);
        assert p.getDistance() == 3;
    }


    public static void main(String[] args) {
        test1();
        test2();
        System.out.println("ok");
    }

}
