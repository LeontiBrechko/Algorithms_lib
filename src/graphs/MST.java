package graphs;

import union_find.WeightedQuickUnion;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

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
//        lazyPrimMST(WG);
        eagerPrimMST(WG);
    }

    public ArrayList<WeightedEdge> getEdges() {
        return edges;
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

    private void eagerPrimMST(WeightedEdgeGraph WG) {
        WeightedEdge[] shortestEdge = new WeightedEdge[V];
        double[] currentShortestWeight = new double[V];
        boolean[] isVisited = new boolean[V];
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < V; i++) {
            currentShortestWeight[i] = Double.MAX_VALUE;
        }

        for (int i = 0; i < V; i++) {
            if (!isVisited[i]) {
                visit(WG, i, currentShortestWeight, priorityQueue, isVisited, shortestEdge);
            }
        }

        for (WeightedEdge e : shortestEdge) {
            if (e != null) {
                edges.add(e);
            }
        }
    }

    private void visit(WeightedEdgeGraph WG, int s, double[] currentShortestWeight,
                       PriorityQueue<Pair> priorityQueue, boolean[] isVisited, WeightedEdge[] shortestEdge) {
        currentShortestWeight[s] = 0.;
        priorityQueue.offer(new Pair(s, currentShortestWeight[s]));
        int u;
        while (!priorityQueue.isEmpty()) {
            u = priorityQueue.poll().key;
            scan(WG, u, currentShortestWeight, priorityQueue, isVisited, shortestEdge);
        }
    }

    private void scan(WeightedEdgeGraph WG, int u, double[] currentShortestWeight,
                      PriorityQueue<Pair> priorityQueue, boolean[] isVisited, WeightedEdge[] shortestEdge) {
        isVisited[u] = true;
        int v;
        Pair currentPair;
        for (WeightedEdge e : WG.getAdj(u)) {
            v = e.getAnotherVertex(u);
            if (isVisited[v])
                continue;
            if (e.getWeight() < currentShortestWeight[v]) {
                currentShortestWeight[v] = e.getWeight();
                shortestEdge[v] = e;
                currentPair = new Pair(v, currentShortestWeight[v]);
                if (priorityQueue.contains(currentPair)) {
                    priorityQueue.remove(currentPair);
                }
                priorityQueue.offer(currentPair);
            }
        }
    }

    private static class Pair implements Comparable<Pair> {
        public int key;
        public double value;

        public Pair(int key, double value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.value < o.value) {
                return -1;
            } else if (this.value > o.value) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pair) {
                return this.key == ((Pair) obj).key;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return key;
        }
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
        MST mst = new MST(graph);
        for (WeightedEdge e : mst.getEdges()) {
            System.out.println(e.getAnyVertex() + " " + e.getAnotherVertex(e.getAnyVertex()) + " " + e.getWeight());
        }
    }
}
