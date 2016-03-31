package union_find;

/**
 * Created by Leonti on 2016-03-30.
 */
public class WeightedQuickUnion {
    private final int[] parent;
    private final int[] weight;

    public WeightedQuickUnion(int n) {
        parent = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    public int findRoot(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public boolean isConnected(int i, int j) {
        return findRoot(i) == findRoot(j);
    }

    public void uniteSites(int i, int j) {
        int rootI = findRoot(i);
        int rootJ = findRoot(j);
        if (rootI == rootJ) return;

        if (weight[rootI] < weight[rootJ]) {
            parent[rootI] = rootJ;
            weight[rootJ] += weight[rootI];
        }
        else {
            parent[rootJ] = rootI;
            weight[rootI] += weight[rootJ];
        }
    }
}
