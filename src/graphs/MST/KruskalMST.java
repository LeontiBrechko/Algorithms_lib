package graphs.mst;

import graphs.graph_impl.WeightedEdge;
import graphs.graph_impl.WeightedEdgeGraph;
import union_find.WeightedQuickUnion;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Leonti on 2016-04-01.
 */
public class KruskalMST {
    private final WeightedEdgeGraph wg;
    private final ArrayList<WeightedEdge> edges;
    private final PriorityQueue<WeightedEdge> priorityQueue;
    private final WeightedQuickUnion wqu;

    public KruskalMST(WeightedEdgeGraph wg) {
        this.wg = wg;
        edges = new ArrayList<>(this.wg.getV() - 1);
        priorityQueue = new PriorityQueue<>();
        wqu = new WeightedQuickUnion(this.wg.getV());

        for (WeightedEdge e : this.wg.getEdges()) {
            priorityQueue.offer(e);
        }

        int u, v;
        while (!priorityQueue.isEmpty() && edges.size() < this.wg.getV() - 1) {
            WeightedEdge e = priorityQueue.poll();
            u = e.getAnyVertex();
            v = e.getAnotherVertex(u);
            if (!wqu.isConnected(u, v)) {
                wqu.uniteSites(u, v);
                edges.add(e);
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
        KruskalMST mst = new KruskalMST(graph);
        for (WeightedEdge e : mst.getEdges()) {
            System.out.println(e.getAnyVertex() + " " + e.getAnotherVertex(e.getAnyVertex()) + " " + e.getWeight());
        }
    }
}
