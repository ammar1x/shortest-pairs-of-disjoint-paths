package pl.edu.pw.elka.gis.desp.comp;

import pl.edu.pw.elka.gis.desp.model.DirectedEdge;
import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.util.*;

public class DijkstraSP implements ShortestPath {

    private WeightedDiagraph g;
    private final int src;
    private double[] distance;
    private DirectedEdge[] prev;
    private PriorityQueue<Node> pq;

    private class Node implements Comparable<Node> {
        int nIndex;
        public Node(int i) { nIndex = i;}
        @Override
        public int compareTo(Node o) {
            if(distance[nIndex] > distance[o.nIndex] )
                return 1;
            else if(distance[nIndex] < distance[o.nIndex])
                return -1;
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            return nIndex == ((Node)o).nIndex;
        }

        @Override
        public int hashCode() {
            return nIndex;
        }
    }

    /**
     * Create & initialize Dijkstra Shortest path algorithm
     * @param g input graph
     * @param src the src node
     */
    public DijkstraSP(WeightedDiagraph g, int src) {
        this.g = g;
        this.src = src;
        distance = new double[g.getV()];
        prev = new DirectedEdge[g.getV()];
    }

    public void setDiagraph(WeightedDiagraph g) {
        this.g = g;
    }

    private void init() {
        pq = new PriorityQueue<Node>();
        for (int i = 0; i < g.getV(); i++) {
            distance[i] = Double.POSITIVE_INFINITY;
            prev[i] = null; // -1 as undefined
            pq.add(new Node(i));
        }
        distance[src] = 0;
    }

    @Override
    public void run() {
        init();
        while (!pq.isEmpty()) {
            int s = pq.poll().nIndex;
            relaxNode(s);
        }
    }

    private void relaxNode(int node) {
        for(DirectedEdge e : g.adjacentTo(node)) {
            relaxEdge(e);
        }
    }

    private void relaxEdge(DirectedEdge edge) {
        int v = edge.getSrc();
        int w = edge.getDst();
        if(distance[w] > distance[v] + edge.getWeight()) {
            distance[w] = distance[v] + edge.getWeight();
            prev[w] = edge;
            Node nw = new Node(w);
            if(!pq.contains(nw))
                pq.add(nw);
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
        return new Path(distance[dstNode], ledgs);
    }


    private boolean hasPath(int dstNode) {
        return distance[dstNode] < Double.POSITIVE_INFINITY;
    }

}
