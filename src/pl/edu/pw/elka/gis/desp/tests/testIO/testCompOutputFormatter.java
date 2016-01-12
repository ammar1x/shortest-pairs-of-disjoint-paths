package pl.edu.pw.elka.gis.desp.tests.testIO;


import pl.edu.pw.elka.gis.desp.comp.CompMethod;
import pl.edu.pw.elka.gis.desp.comp.EdgedDisjointedPaths;
import pl.edu.pw.elka.gis.desp.comp.Path;
import pl.edu.pw.elka.gis.desp.io.CompOutputFormatter;
import pl.edu.pw.elka.gis.desp.model.DirectedEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * test output formatter.
 */
public class testCompOutputFormatter {

    static Random rand = new Random();

    public static DirectedEdge randomDirectEdge() {
        return new DirectedEdge(rand.nextInt(1000), rand.nextInt(1223), rand.nextDouble()*2323);
    }

    public static List<DirectedEdge> randomListDirectEdge(int len) {
        List<DirectedEdge> edges = new ArrayList<DirectedEdge>();
        for (int i = 0; i < len; i++) {
            edges.add(randomDirectEdge());
        }
        return  edges;
    }

    public static Path randomPath(int len) {
        return new Path(randomListDirectEdge(len));
    }

    public static void main(String[] args) {
        Path fpath = randomPath(10), spath = null;
        EdgedDisjointedPaths edgedDisjointedPaths = new EdgedDisjointedPaths(fpath, spath);
        CompOutputFormatter formatter = new CompOutputFormatter();
        String ouput = formatter.formatComp(CompMethod.Naive, edgedDisjointedPaths, 10);
        System.out.println(ouput);
    }


}
