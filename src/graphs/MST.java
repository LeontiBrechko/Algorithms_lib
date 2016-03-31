package graphs;

import union_find.WeightedQuickUnion;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Leonti on 2016-03-30.
 */
public class MST {
    private final int V;
    private final ArrayList<WeightedEdge> edges;

    public MST(WeightedEdgeGraph WG) {
        this.V = WG.getV();
        this.edges = new ArrayList<>(this.V - 1);
//        kruskalMST(WG);
        lazyPrimMST(WG);
    }

    private void kruskalMST(WeightedEdgeGraph WG) {
        PriorityQueue<WeightedEdge> priorityQueue = new PriorityQueue<>();

        for (WeightedEdge e : WG.getEdges()) {
            priorityQueue.offer(e);
        }

        WeightedQuickUnion wqu = new WeightedQuickUnion(V);
        while (!priorityQueue.isEmpty() && edges.size() < V - 1) {
            WeightedEdge e = priorityQueue.poll();
            int u = e.getAnyVertex();
            int v = e.getAnotherVertex(u);
            if (!wqu.isConnected(u, v)) {
                wqu.uniteSites(u, v);
                edges.add(e);
            }
        }
    }

    private void lazyPrimMST(WeightedEdgeGraph WG) {
        boolean[] isVisited = new boolean[V];
        PriorityQueue<WeightedEdge> priorityQueue = new PriorityQueue<>();

        visit(WG, 0, isVisited, priorityQueue);

        int u, v;
        while (!priorityQueue.isEmpty()) {
            WeightedEdge e = priorityQueue.poll();
            u = e.getAnyVertex();
            v = e.getAnotherVertex(u);
            if (isVisited[u] && isVisited[v])
                continue;
            edges.add(e);
            if (!isVisited[u])
                visit(WG, u, isVisited, priorityQueue);
            if (!isVisited[v])
                visit(WG, v, isVisited, priorityQueue);
        }

    }

    private void visit(WeightedEdgeGraph WG, int u,
                       boolean[] isVisited, PriorityQueue<WeightedEdge> priorityQueue) {
        isVisited[u] = true;
        for (WeightedEdge e : WG.getAdj(u)) {
            if (!isVisited[e.getAnotherVertex(u)]) {
                priorityQueue.offer(e);
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
        MST mst = new MST(graph);
        for (WeightedEdge e : mst.getEdges()) {
            System.out.println(e.getAnyVertex() + " " + e.getAnotherVertex(e.getAnyVertex()) + " " + e.getWeight());
        }
    }
}
