package graphs.graph_impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonti on 2016-03-30.
 */
public class WeightedEdgeGraph {
    private final int V;
    private final ArrayList<WeightedEdge>[] adj;

    public WeightedEdgeGraph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < this.V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(WeightedEdge e) {
        int u = e.getAnyVertex();
        int v = e.getAnotherVertex(u);
        adj[u].add(e);
        adj[v].add(e);
    }

    public List<WeightedEdge> getEdges() {
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (WeightedEdge e : adj[i]) {
                if (e.getAnotherVertex(i) > i) {
                    edges.add(e);
                }
            }
        }
        return edges;
    }

    public int getV() {
        return V;
    }

    public ArrayList<WeightedEdge> getAdj(int u) {
        return adj[u];
    }
}
