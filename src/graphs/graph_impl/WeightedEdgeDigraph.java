package graphs.graph_impl;

import java.util.ArrayList;

/**
 * Created by Leonti on 2016-03-31.
 */
public class WeightedEdgeDigraph {
    private final int V;
    private final ArrayList<DirectedWeightEdge>[] adj;

    public WeightedEdgeDigraph(int V) {
        this.V = V;
        adj = new ArrayList[this.V];
        for (int i = 0; i < this.V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public int getV() {
        return V;
    }

    public void addEdge(DirectedWeightEdge e) {
        adj[e.getFrom()].add(e);
    }

    public ArrayList<DirectedWeightEdge> getAdj(int u) {
        return adj[u];
    }
}
