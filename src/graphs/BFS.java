package graphs;

import graphs.graph_impl.Graph;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by Leonti on 2016-03-14.
 */
public class BFS {
    public static void BFS(Graph g, int s) {
        ArrayDeque<Integer>[] adj = g.getAdj();
        HashMap<String, Object>[] d = g.getD();
        ArrayDeque<Integer> Q = new ArrayDeque<>(g.getV());
        d[s].replace("color", "GRAY");
        d[s].replace("d", 0);
        Q.offer(s);
        while (!Q.isEmpty()) {
            int u = Q.poll();
            for (int v : adj[u]) {
                if (d[v].get("color").equals("WHITE")) {
                    d[v].replace("color", "GRAY");
                    d[v].replace("d", (Integer) d[u].get("d") + 1);
                    d[v].replace("pi", u);
                    Q.offer(v);
                }
            }
            d[u].replace("color", "BLACK");
        }
    }

    public static void printPath (Graph g, int s, int v, HashMap<String, Object>[] d) {
        if (s == v) {
            System.out.println(s);
        } else if (d[v] == null) {
            System.out.println("No path");
        } else {
            printPath(g, s, (int) d[v].get("pi"), d);
            System.out.println(v);
        }
    }
}
