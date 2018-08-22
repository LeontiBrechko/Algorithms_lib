package graphs.shortest_path;

import graphs.graph_impl.DirectedWeightEdge;
import graphs.graph_impl.WeightedEdgeDigraph;
import lb.util.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by Leonti on 2016-03-31.
 */
public class DijkstraSP {
    private final double[] weightTo;
    private final DirectedWeightEdge[] edgeTo;
    private final TreeSet<Pair> tree;

    public DijkstraSP(WeightedEdgeDigraph digraph, int s) {
        weightTo = new double[digraph.getV()];
        edgeTo = new DirectedWeightEdge[digraph.getV()];
        tree = new TreeSet<>();

        Arrays.fill(weightTo, Double.POSITIVE_INFINITY);

        weightTo[s] = 0.0;
        tree.add(new Pair(s, 0.0));

        Pair next;
        while (!tree.isEmpty()) {
            next = tree.pollFirst();
            if (next == null) throw new IllegalStateException();
            for (DirectedWeightEdge e: digraph.getAdj(next.key))
                relax(e);
        }
    }

    public double weightTo(int u) {
        return weightTo[u];
    }

    public ArrayDeque<DirectedWeightEdge> pathTo(int u) {
        ArrayDeque<DirectedWeightEdge> path = new ArrayDeque<>();
        for (DirectedWeightEdge e = edgeTo[u]; e != null; e = edgeTo[e.getFrom()]) {
            path.push(e);
        }
        return path;
    }

    private void relax(DirectedWeightEdge e) {
        int u = e.getFrom();
        int v = e.getTo();
        if (weightTo[v] > weightTo[u] + e.getWeight()) {
            weightTo[v] = weightTo[u] + e.getWeight();
            edgeTo[v] = e;
            Pair next = new Pair(v, weightTo[v]);
            tree.remove(next);
            tree.add(next);
        }
    }

    public DirectedWeightEdge[] getEdges() {
        return edgeTo;
    }

    public static void main(String[] args)
    {
        WeightedEdgeDigraph graph = new WeightedEdgeDigraph(9);
        graph.addEdge(new DirectedWeightEdge(0, 1, 4));
        graph.addEdge(new DirectedWeightEdge(0, 7, 8));
        graph.addEdge(new DirectedWeightEdge(1, 2, 8));
        graph.addEdge(new DirectedWeightEdge(1, 7, 11));
        graph.addEdge(new DirectedWeightEdge(2, 3, 7));
        graph.addEdge(new DirectedWeightEdge(2, 5, 4));
        graph.addEdge(new DirectedWeightEdge(2, 8, 2));
        graph.addEdge(new DirectedWeightEdge(3, 4, 9));
        graph.addEdge(new DirectedWeightEdge(3, 5, 14));
        graph.addEdge(new DirectedWeightEdge(4, 5, 10));
        graph.addEdge(new DirectedWeightEdge(5, 6, 2));
        graph.addEdge(new DirectedWeightEdge(6, 7, 1));
        graph.addEdge(new DirectedWeightEdge(6, 8, 6));
        graph.addEdge(new DirectedWeightEdge(7, 8, 7));

        graph.addEdge(new DirectedWeightEdge(1, 0, 4));
        graph.addEdge(new DirectedWeightEdge(7, 0, 8));
        graph.addEdge(new DirectedWeightEdge(2, 1, 8));
        graph.addEdge(new DirectedWeightEdge(7, 1, 11));
        graph.addEdge(new DirectedWeightEdge(3, 2, 7));
        graph.addEdge(new DirectedWeightEdge(5, 2, 4));
        graph.addEdge(new DirectedWeightEdge(8, 2, 2));
        graph.addEdge(new DirectedWeightEdge(4, 3, 9));
        graph.addEdge(new DirectedWeightEdge(5, 3, 14));
        graph.addEdge(new DirectedWeightEdge(5, 4, 10));
        graph.addEdge(new DirectedWeightEdge(6, 5, 2));
        graph.addEdge(new DirectedWeightEdge(7, 6, 1));
        graph.addEdge(new DirectedWeightEdge(8, 6, 6));
        graph.addEdge(new DirectedWeightEdge(8, 7, 7));
        DijkstraSP sp = new DijkstraSP(graph, 0);
        for (DirectedWeightEdge e : sp.getEdges())
        {
            if (e != null)
            {
                System.out.println(e.getFrom() + " " + e.getTo() + " " + e.getWeight());
            }
        }
    }
}
