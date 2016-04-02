package graphs.shortest_path;

import graphs.graph_impl.DirectedWeightEdge;
import graphs.graph_impl.WeightedEdgeDigraph;
import lb.util.Pair;

import java.util.PriorityQueue;

/**
 * Created by Leonti on 2016-04-01.
 */
public class BellmanFordSP {
    private final double[] weightTo;
    private final DirectedWeightEdge[] edgeTo;
    private final PriorityQueue<Pair> pq;

    public BellmanFordSP(WeightedEdgeDigraph digraph, int s) {
        weightTo = new double[digraph.getV()];
        edgeTo = new DirectedWeightEdge[digraph.getV()];
        pq = new PriorityQueue<>();

        for (int i = 0; i < digraph.getV(); i++) {
            weightTo[i] = Double.MAX_VALUE;
        }
        weightTo[s] = 0.;
        pq.offer(new Pair(s, 0.));

        int u;
        for (int i = 0; i < digraph.getV(); i++) {
            while (!pq.isEmpty()) {
                u = pq.poll().key;
                for (DirectedWeightEdge e : digraph.getAdj(u)) {
                    relax(e);
                }
            }
        }
    }

    private void relax(DirectedWeightEdge e) {
        int u = e.getFrom();
        int v = e.getTo();
        if (weightTo[v] > weightTo[u] + e.getWeight()) {
            weightTo[v] = weightTo[u] + e.getWeight();
            edgeTo[v] = e;
            Pair next = new Pair(v, weightTo[u] + e.getWeight());
            if (pq.contains(next)) {
                pq.remove(next);
            }
            pq.offer(next);
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
        BellmanFordSP sp = new BellmanFordSP(graph, 0);
        for (Double w : sp.getWeights()) {
            System.out.println(w);
        }
    }
}
