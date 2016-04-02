package graphs.graph_impl;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by Leonti on 2016-03-14.
 */
public class Graph {
    private final int V;
    private int E;
    private ArrayDeque<Integer>[] adj;
    private HashMap<String, Object>[] d;

    public Graph(int V) {
        this.V = V;
        adj = (ArrayDeque<Integer>[]) new ArrayDeque[V];
        d = (HashMap<String, Object>[]) new HashMap[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayDeque<>(V);
            d[i] = new HashMap<>();
            d[i].put("color", "WHITE");
            d[i].put("d", Integer.MAX_VALUE);
            d[i].put("pi", null);
            d[i].put("f", Integer.MAX_VALUE);
        }
    }

    public void addEdge(int u, int v) {
        if (u < 0 || u >= V || v < 0 || u >= V) {
            throw new IndexOutOfBoundsException("Wrong vertices: " + u + " " + v);
        }
        E++;
        adj[u].add(v);
    }

    public ArrayDeque<Integer>[] getAdj() {
        return adj;
    }

    public HashMap<String, Object>[] getD() {
        return d;
    }

    public int getV() {
        return V;
    }
}
