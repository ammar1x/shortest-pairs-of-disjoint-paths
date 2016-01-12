package pl.edu.pw.elka.gis.desp.app;

import pl.edu.pw.elka.gis.desp.model.WeightedDiagraph;

import java.util.Random;

/**
 * Generate random weighted diagraph.
 */
public class Generator {

    static Random rand = new Random();

    /**
     * Generate random integer between from and to inclusive.
     * @param from lower bound
     * @param to upper bound
     * @return random integer
     */
    public int randomInt(int from, int to) {
        return (int) (rand.nextDouble() * (from-to) + to);
    }

    /**
     * Return a weighted diagraph based on Erdos-Renyi Model.
     * @param v number of nodes
     * @param p probability of connecting two edges
     * @return Weighted diagraph
     */
    public static WeightedDiagraph erdosRenyi(int v, double p) {
        WeightedDiagraph weightedDiagraph = new WeightedDiagraph(v);
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if( i != j && (rand.nextDouble() > p))
                    weightedDiagraph.addEdge(i, j, rand.nextDouble());
            }
        }
        return weightedDiagraph;
    }

    /**
     * A trivial graph is a graph with one node.
     * @return a trivial diagraph
     */
    public static WeightedDiagraph trivial() {
        WeightedDiagraph weightedDiagraph = new WeightedDiagraph(1);
        return weightedDiagraph;
    }

    /**
     * Return an empty weighted diagraph.
     * @param v number of nodes
     * @return empty weighted diagraph
     */
    public static WeightedDiagraph empty(int v) {
        return new WeightedDiagraph(v);
    }

    /**
     * Return complete weighted diagraph.
     * @param v number of nodes
     * @return complete weighted diagraph
     */
    public static WeightedDiagraph complete(int v) {
        WeightedDiagraph weightedDiagraph = new WeightedDiagraph(v);
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if(i != j)
                    weightedDiagraph.addEdge(i, j, rand.nextDouble());
            }
        }
        return weightedDiagraph;
    }

    /**
     * Return a tree.
     * @param v number of nodes
     * @return a tree
     */
    public static WeightedDiagraph tree(int v) {
        WeightedDiagraph weightedDiagraph = new WeightedDiagraph(v);
        for (int i = 0; i < v-1; i++) {
            weightedDiagraph.addEdge(i, i+1, rand.nextDouble());
        }
        return weightedDiagraph;
    }

    /**
     * Return a Weighted Diagraph with negative weights.
     * @param v number of nodes
     * @return Weighted Diagraph with negative weights
     */
    public static WeightedDiagraph negativeWeights(int v) {
        WeightedDiagraph weightedDiagraph = new WeightedDiagraph(v);
        double p = rand.nextDouble();
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if( i != j && (rand.nextDouble() > p))
                    weightedDiagraph.addEdge(i, j, rand.nextDouble());
            }
        }
        return weightedDiagraph;
    }

}
