package graphs.graph_impl;

/**
 * Created by Leonti on 2016-04-05.
 */
public class FlowEdge {
    private final int u;
    private final int v;
    private final double capacity;
    private double flow;

    public FlowEdge(int u, int v, double capacity) {
        this.u = u;
        this.v = v;
        this.capacity = capacity;
    }

    public int getFrom() {
        return u;
    }

    public int getTo() {
        return v;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFlow() {
        return flow;
    }

    public int getAnotherEdge(int u) {
        return u == this.u ? this.v : this.u;
    }

    public double residualCapacity(int vertex) {
        return vertex == u ? flow : capacity - flow;
    }

    public void addResidualFlowTo(int vertex, double value) {
        if (vertex == u) {
            flow -= value;
        } else {
            flow += value;
        }
    }
}
