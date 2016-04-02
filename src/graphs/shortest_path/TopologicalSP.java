package graphs.shortest_path;

import graphs.graph_impl.DirectedWeightEdge;
import graphs.graph_impl.WeightedEdgeDigraph;

import java.util.ArrayDeque;

/**
 * Created by Leonti on 2016-04-01.
 */
public class TopologicalSP {
    private final double[] weightTo;
    private final DirectedWeightEdge[] edgeTo;

    public TopologicalSP(WeightedEdgeDigraph digraph, int s) {
        weightTo = new double[digraph.getV()];
        edgeTo = new DirectedWeightEdge[digraph.getV()];

        for (int i = 0; i < digraph.getV(); i++) {
            weightTo[i] = Double.MAX_VALUE;
        }
        weightTo[s] = 0.;

        ArrayDeque<Integer> topologicalOrder = getTopologicalOrder(digraph, s);
        for (Integer u : topologicalOrder) {
            for (DirectedWeightEdge e : digraph.getAdj(u)) {
                relax(e);
            }
        }
    }

    private ArrayDeque<Integer> getTopologicalOrder(WeightedEdgeDigraph digraph, int s) {
        boolean[] isVisited = new boolean[digraph.getV()];
        ArrayDeque<Integer> topologicalOrder = new ArrayDeque<>(digraph.getV());
        for (int i = 0; i < digraph.getV(); i++) {
            if (!isVisited[i]) {
                dfs(digraph, isVisited, topologicalOrder, i);
            }
        }
        return topologicalOrder;
    }

    private void dfs(WeightedEdgeDigraph digraph, boolean[] isVisited,
                     ArrayDeque<Integer> topologicalOrder, int u) {
        isVisited[u] = true;
        for (DirectedWeightEdge e : digraph.getAdj(u)) {
            if (!isVisited[e.getTo()]) {
                isVisited[e.getTo()] = true;
                dfs(digraph, isVisited, topologicalOrder, e.getTo());
            }
        }
        topologicalOrder.push(u);
    }

    private void relax(DirectedWeightEdge e) {
        int u = e.getFrom();
        int v = e.getTo();
        if (weightTo[v] > weightTo[u] + e.getWeight()) {
            weightTo[v] = weightTo[u] + e.getWeight();
            edgeTo[v] = e;
        }
    }

    public double[] getWeights() {return weightTo;}

    public static void main(String[] args) {
        WeightedEdgeDigraph graph = new WeightedEdgeDigraph(8);
        graph.addEdge(new DirectedWeightEdge(0, 1, 5.0));
        graph.addEdge(new DirectedWeightEdge(0, 4, 9.0));
        graph.addEdge(new DirectedWeightEdge(0, 7, 8.0));
        graph.addEdge(new DirectedWeightEdge(1, 2, 12.0));
        graph.addEdge(new DirectedWeightEdge(1, 3, 15.0));
        graph.addEdge(new DirectedWeightEdge(1, 7, 4.0));
        graph.addEdge(new DirectedWeightEdge(2, 3, 3.0));
        graph.addEdge(new DirectedWeightEdge(2, 6, 11.0));
        graph.addEdge(new DirectedWeightEdge(3, 6, 9.0));
        graph.addEdge(new DirectedWeightEdge(4, 5, 4.0));
        graph.addEdge(new DirectedWeightEdge(4, 7, 5.0));
        graph.addEdge(new DirectedWeightEdge(5, 2, 1.0));
        graph.addEdge(new DirectedWeightEdge(5, 6, 13.0));
        graph.addEdge(new DirectedWeightEdge(7, 5, 6.0));
        graph.addEdge(new DirectedWeightEdge(7, 2, 7.0));
        TopologicalSP sp = new TopologicalSP(graph, 0);
        for (Double w : sp.getWeights()) {
            System.out.println(w);
        }
    }
}
