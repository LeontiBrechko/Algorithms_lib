package graphs.max_flow;

import graphs.graph_impl.FlowEdge;
import graphs.graph_impl.FlowNetwork;
import lb.util.Pair;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Leonti on 2016-04-05.
 */
public class FordFulkerson {
    private boolean[] isVisited;
    private FlowEdge[] edgeTo;
    private double value;

    public FordFulkerson(FlowNetwork G, int s, int t) {
        value = 0.;
        double bottle;
        while (hasAugmentingPathFattest(G, s, t)) {
            bottle = Double.MAX_VALUE;
            for (int u = t; u != s; u = edgeTo[u].getAnotherEdge(u)) {
                bottle = Math.min(bottle, edgeTo[u].residualCapacity(u));
            }

            for (int u = t; u != s; u = edgeTo[u].getAnotherEdge(u)) {
                edgeTo[u].addResidualFlowTo(u, bottle);
            }

            value += bottle;
        }
    }

    private boolean hasAugmentingPathBFS(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.getV()];
        isVisited = new boolean[G.getV()];

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(s);
        isVisited[s] = true;

        int u, v;
        while (!queue.isEmpty()) {
            u = queue.poll();
            for (FlowEdge e : G.getAdj(u)) {
                v = e.getAnotherEdge(u);
                if (e.residualCapacity(v) > 0 && !isVisited[v]) {
                    edgeTo[v] = e;
                    isVisited[v] = true;
                    queue.offer(v);
                }
            }
        }

        return isVisited[t];
    }

    private boolean hasAugmentingPathFattest(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.getV()];
        double[] capacityTo = new double[G.getV()];

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        priorityQueue.offer(new Pair(s, -1.));
        capacityTo[s] = Double.MAX_VALUE;

        int u, v;
        Pair next;
        while (!priorityQueue.isEmpty()) {
            u = priorityQueue.poll().key;
            if (u == t) {
                return edgeTo[t] != null;
            }
            for (FlowEdge e : G.getAdj(u)) {
                v = e.getAnotherEdge(u);
                if (e.residualCapacity(v) > 0 && capacityTo[v] < Math.min(capacityTo[u], e.residualCapacity(v))) {
                    capacityTo[v] = Math.min(capacityTo[u], e.residualCapacity(v));
                    edgeTo[v] = e;
                    next = new Pair(v, capacityTo[v]);
                    if (priorityQueue.contains(next)) {
                        priorityQueue.remove(next);
                    }
                    priorityQueue.offer(next);
                }
            }
        }

        return edgeTo[t] != null;
    }

    public double getValue() {
        return value;
    }

    public static void main(String[] args) {
        FlowNetwork flowNetwork = new FlowNetwork(8);
        flowNetwork.addEdge(new FlowEdge(0, 1, 10));
        flowNetwork.addEdge(new FlowEdge(0, 2, 5));
        flowNetwork.addEdge(new FlowEdge(0, 3, 15));
        flowNetwork.addEdge(new FlowEdge(1, 4, 10));
        flowNetwork.addEdge(new FlowEdge(1, 5, 15));
        flowNetwork.addEdge(new FlowEdge(1, 2, 4));
        flowNetwork.addEdge(new FlowEdge(2, 5, 8));
        flowNetwork.addEdge(new FlowEdge(2, 3, 4));
        flowNetwork.addEdge(new FlowEdge(3, 6, 16));
        flowNetwork.addEdge(new FlowEdge(4, 7, 10));
        flowNetwork.addEdge(new FlowEdge(4, 5, 15));
        flowNetwork.addEdge(new FlowEdge(5, 7, 10));
        flowNetwork.addEdge(new FlowEdge(5, 6, 15));
        flowNetwork.addEdge(new FlowEdge(6, 7, 10));
        flowNetwork.addEdge(new FlowEdge(6, 2, 6));
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, 0, 7);
        System.out.println(fordFulkerson.getValue());
    }
}
