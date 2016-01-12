package pl.edu.pw.elka.gis.desp.model;


/**
 * Data type for directed edge.
 */
public class DirectedEdge {

    final private int src;
    final private int dst;
    final private double weight;

    /**
     * Construct a directed edge type
     * @param src node this edge point from
     * @param dst node this edge point to
     * @param weight weight of the edge
     */
    public DirectedEdge(int src, int dst, double weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    /**
     * Return weight of this edge.
     * @return weight of this edge
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Return src node of this edge.
     * @return src node
     */
    public int getSrc() {
        return src;
    }

    /**
     * Return dst node of this edge.
     * @return dst node
     */
    public int getDst() {
        return dst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectedEdge edge = (DirectedEdge) o;

        if (dst != edge.dst) return false;
        if (src != edge.src) return false;
        if (Double.compare(edge.weight, weight) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = src;
        result = 31 * result + dst;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Return String representation of the edge.
     * @return string representation of the edge
     */
    public String toString() {
        return String.format("%d - %d %f", src, dst, weight);
    }

    /**
     * Return if two directed edges are equal ignoring the direction.
     * @param o direct edge to compare to
     * @return true if two edges are equal ignoring direction, otherwise false
     */
    public boolean equalIgnoreDirection(DirectedEdge o) {
        if(this.src == o.src && this.dst == o.dst)
            return true;
        if(this.src == o.dst && this.dst == o.src)
            return true;
        return false;
    }
}
