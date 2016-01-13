package pl.edu.pw.elka.gis.desp.comp;


import pl.edu.pw.elka.gis.desp.model.DirectedEdge;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BellmanFordSP implements ShortestPath{

    private WeightedDiagraph wdg;
    private final int src;
    private final DirectedEdge[] prev;
    private final double[] distance;
    /**
     * Create & initialize Bellman Ford Shortest path algorithm
     * @param g input graph
     * @param src the src node
     */
    public BellmanFordSP(WeightedDiagraph g, int src) {
        this.wdg = g;
        this.src = src;
        distance = new double[g.getV()];
        prev = new DirectedEdge[g.getV()];
        init();
    }

    public void setDiagraph(WeightedDiagraph wdg) {
        this.wdg = wdg;
    }

    private void init() {
        for (int i = 0; i < wdg.getV(); i++) {
            distance[i] = Double.POSITIVE_INFINITY;
            prev[i] = null;
        }
        distance[src] = 0;
    }


    private void relaxEdge(DirectedEdge e) {
        int v = e.getSrc();
        int w = e.getDst();
        if(distance[w] > distance[v] + e.getWeight()) {
            distance[w] = distance[v] + e.getWeight();
            prev[w] = e;
        }
    }

    @Override
    public void run() {
        init();
        for (int i = 0; i < wdg.getV(); i++) {
            for(DirectedEdge e: wdg.adjacentTo(i)) {
                relaxEdge(e);
            }
        }
    }

    @Override
    public Path pathTo(int dstNode) {
        if(!hasPath(dstNode)) {
            return null;
        }
        int s = dstNode;
        List<DirectedEdge> ledgs = new ArrayList<DirectedEdge>();
        while(prev[s] != null) {
            ledgs.add(prev[s]);
            s = prev[s].getSrc();
        }
        Collections.reverse(ledgs);
        return new Path(ledgs);
    }


    private boolean hasPath(int dstNode) {
        return distance[dstNode] < Double.POSITIVE_INFINITY && distance[dstNode] > 0;
    }
}
