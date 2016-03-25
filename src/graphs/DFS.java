package graphs;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by Leonti on 2016-03-14.
 */
public class DFS {
    static int time;
    public static void DFS(Graph g) {
        HashMap<String, Object>[] d = g.getD();
        for (int i = 0; i < g.getV(); i++) {
            d[i].replace("color", "WHITE");
            d[i].replace("pi", null);
        }
        for (int i = 0; i < g.getV(); i++) {
            if (d[i].get("color").equals("WHITE")) {
                DFSVisit(d, g.getAdj(), i);
            }
        }
    }

    private static void DFSVisit(HashMap<String, Object>[] d, ArrayDeque<Integer>[] adj,
                                 int u) {
        time++;
        d[u].replace("d", time);
        d[u].replace("color", "GRAY");
        for (int v : adj[u]) {
            if (d[v].get("color").equals("WHITE")) {
                d[v].replace("pi", u);
                DFSVisit(d, adj, v);
            }
        }
        d[u].replace("color", "BLACK");
        time++;
        d[u].replace("f", time);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 1);
        graph.addEdge(1, 4);
        graph.addEdge(4, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 5);
        DFS(graph);
        for (int i = 0; i < graph.getV(); i++) {
            System.out.println(graph.getD()[i].get("f"));
        }
    }
}
