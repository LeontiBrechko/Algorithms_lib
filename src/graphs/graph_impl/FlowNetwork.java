package graphs.graph_impl;

import java.util.ArrayList;

/**
 * Created by Leonti on 2016-04-05.
 */
public class FlowNetwork {
    private final int V;
    private final ArrayList<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        this.V = V;
        adj = new ArrayList[this.V];
        for (int i = 0; i < this.V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(FlowEdge e) {
        int u = e.getFrom();
        int v = e.getTo();
        adj[u].add(e);
        adj[v].add(e);
    }

    // TODO: 2016-04-05 return copy of list
    public ArrayList<FlowEdge> getAdj(int u) {
        return adj[u];
    }

    public int getV() {
        return V;
    }
}
