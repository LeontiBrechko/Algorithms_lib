package graphs.mst;

import graphs.graph_impl.WeightedEdge;
import graphs.graph_impl.WeightedEdgeGraph;
import lb.util.Pair;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Leonti on 2016-04-01.
 */
public class EagerPrimMST {
    private final WeightedEdgeGraph wg;
    private final ArrayList<WeightedEdge> edges;
    WeightedEdge[] lightestEdgeTo;
    double[] lightestWeightTo;
    boolean[] isVisited;
    PriorityQueue<Pair> priorityQueue;

    public EagerPrimMST(WeightedEdgeGraph wg) {
        this.wg = wg;
        edges = new ArrayList<>(this.wg.getV() - 1);

        lightestEdgeTo = new WeightedEdge[this.wg.getV()];
        lightestWeightTo = new double[this.wg.getV()];
        isVisited = new boolean[this.wg.getV()];
        priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < this.wg.getV(); i++) {
            lightestWeightTo[i] = Double.MAX_VALUE;
        }

        for (int i = 0; i < this.wg.getV(); i++) {
            if (!isVisited[i]) {
                visit(i);
            }
        }

        for (WeightedEdge e : lightestEdgeTo) {
            if (e != null) {
                edges.add(e);
            }
        }
    }

    private void visit(int s) {
        lightestWeightTo[s] = 0.;
        priorityQueue.offer(new Pair(s, lightestWeightTo[s]));
        int u;
        while (!priorityQueue.isEmpty()) {
            u = priorityQueue.poll().key;
            scan(u);
        }
    }

    private void scan(int u) {
        isVisited[u] = true;
        int v;
        Pair currentPair;
        for (WeightedEdge e : wg.getAdj(u)) {
            v = e.getAnotherVertex(u);
            if (isVisited[v])
                continue;
            if (e.getWeight() < lightestWeightTo[v]) {
                lightestWeightTo[v] = e.getWeight();
                lightestEdgeTo[v] = e;
                currentPair = new Pair(v, lightestWeightTo[v]);
                if (priorityQueue.contains(currentPair)) {
                    priorityQueue.remove(currentPair);
                }
                priorityQueue.offer(currentPair);
            }
        }
    }

    public ArrayList<WeightedEdge> getEdges() {
        return edges;
    }

    public static void main(String[] args) {
        WeightedEdgeGraph graph = new WeightedEdgeGraph(8);
        graph.addEdge(new WeightedEdge(0, 7, 0.16));
        graph.addEdge(new WeightedEdge(2, 3, 0.17));
        graph.addEdge(new WeightedEdge(1, 7, 0.19));
        graph.addEdge(new WeightedEdge(0, 2, 0.26));
        graph.addEdge(new WeightedEdge(5, 7, 0.28));
        graph.addEdge(new WeightedEdge(1, 3, 0.29));
        graph.addEdge(new WeightedEdge(1, 5, 0.32));
        graph.addEdge(new WeightedEdge(2, 7, 0.34));
        graph.addEdge(new WeightedEdge(4, 5, 0.35));
        graph.addEdge(new WeightedEdge(1, 2, 0.36));
        graph.addEdge(new WeightedEdge(4, 7, 0.37));
        graph.addEdge(new WeightedEdge(0, 4, 0.38));
        graph.addEdge(new WeightedEdge(6, 2, 0.40));
        graph.addEdge(new WeightedEdge(3, 6, 0.52));
        graph.addEdge(new WeightedEdge(6, 0, 0.58));
        graph.addEdge(new WeightedEdge(6, 4, 0.93));
        EagerPrimMST mst = new EagerPrimMST(graph);
        for (WeightedEdge e : mst.getEdges()) {
            System.out.println(e.getAnyVertex() + " " + e.getAnotherVertex(e.getAnyVertex()) + " " + e.getWeight());
        }
    }
}
