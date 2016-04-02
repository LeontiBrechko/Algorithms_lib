package graphs.shortest_path;

import graphs.graph_impl.DirectedWeightEdge;
import graphs.graph_impl.WeightedEdgeDigraph;
import lb.util.Pair;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * Created by Leonti on 2016-03-31.
 */
public class DijkstraSP {
    private final double[] weightTo;
    private final DirectedWeightEdge[] edgeTo;
    private final PriorityQueue<Pair> priorityQueue;

    public DijkstraSP(WeightedEdgeDigraph digraph, int s) {
        weightTo = new double[digraph.getV()];
        edgeTo = new DirectedWeightEdge[digraph.getV()];
        priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < digraph.getV(); i++) {
            weightTo[i] = Double.MAX_VALUE;
        }
        weightTo[s] = 0.;

        priorityQueue.offer(new Pair(s, 0.));
        int u;
        while (!priorityQueue.isEmpty()){
            u = priorityQueue.poll().key;
            for (DirectedWeightEdge e : digraph.getAdj(u)) {
                relax(e);
            }
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
        Pair nextPair;
        if (weightTo[v] > weightTo[u] + e.getWeight()) {
            weightTo[v] = weightTo[u] + e.getWeight();
            edgeTo[v] = e;
            nextPair = new Pair(v, weightTo[v]);
            if (priorityQueue.contains(v)) {
                priorityQueue.remove(nextPair);
            }
            priorityQueue.offer(nextPair);
        }
    }

    public DirectedWeightEdge[] getEdges() {
        return edgeTo;
    }

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
        DijkstraSP sp = new DijkstraSP(graph, 0);
        for (DirectedWeightEdge e : sp.getEdges()) {
            if (e != null) {
                System.out.println(e.getFrom() + " " + e.getTo() + " " + e.getWeight());
            }
        }
    }
}
