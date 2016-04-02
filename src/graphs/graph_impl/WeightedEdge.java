package graphs.graph_impl;

/**
 * Created by Leonti on 2016-03-30.
 */
public class WeightedEdge implements Comparable<WeightedEdge> {
    private final int u;
    private final int v;
    private final double weight;

    public WeightedEdge(int u, int v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getAnyVertex() {
        return u;
    }

    public int getAnotherVertex(int u) {
        return u == this.u ? this.v : this.u;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        if (this.weight < o.weight) {
            return -1;
        } else if (this.weight > o.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}
