package graphs.graph_impl;

/**
 * Created by Leonti on 2016-03-31.
 */
public class DirectedWeightEdge {
    private final int u;
    private final int v;
    private final double weight;

    public DirectedWeightEdge(int u, int v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getFrom() {
        return u;
    }

    public int getTo() {
        return v;
    }

    public double getWeight() {
        return weight;
    }
}
