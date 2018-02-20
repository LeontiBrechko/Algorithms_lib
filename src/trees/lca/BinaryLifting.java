package trees.lca;

import java.util.ArrayList;

/**
 * https://neerc.ifmo.ru/wiki/index.php?title=Метод_двоичного_подъема
 */
public class BinaryLifting {
    private int n;
    private int log2n;
    private int[] parent;
    private int[] depth;
    private int[][] dp;

    public BinaryLifting(ArrayList<Integer>[] adj) {
        if (adj.length <= 0)
            throw new IllegalArgumentException("Adjacency lists should contain at least one element");

        n = adj.length;
        log2n = 31 - Integer.numberOfLeadingZeros(n);
        parent = new int[n];
        depth = new int[n];
        dp = new int[n][log2n + 1];
        for (int i = 0; i < n; i++) parent[i] = i;

        boolean[] isVisited = new boolean[n];
        isVisited[0] = true;
        dfs(0, 0, isVisited, adj);

        for (int i = 0; i < n; i++) dp[i][0] = parent[i];
        for (int j = 1; j <= log2n; j++) {
            for (int i = 0; i < n; i++) {
                dp[i][j] = dp[dp[i][j - 1]][j - 1];
            }
        }
    }

    public int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = log2n; i >= 0; i--) {
            if (depth[u] - depth[v] >= (1 << i)) {
                u = dp[u][i];
            }
        }

        if (u == v) return u;

        for (int i = log2n; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i];
                v = dp[v][i];
            }
        }

        return parent[u];
    }

    private void dfs(int u, int d, boolean[] isVisited, ArrayList<Integer>[] adj) {
        for (Integer v : adj[u]) {
            if (!isVisited[v]) {
                parent[v] = u;
                depth[v] = d + 1;
                isVisited[v] = true;
                dfs(v, d + 1, isVisited, adj);
            }
        }
    }
}
