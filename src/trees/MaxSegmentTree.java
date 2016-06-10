package trees;

/**
 * Created by Leonti on 2016-06-09.
 */
public class MaxSegmentTree {
    private int[] baseArray;
    private int[] tree;

    public MaxSegmentTree(int a[]) {
        baseArray = a;
        tree = new int[4 * a.length + 1];
    }

    public void build(int v, int tl, int tr) {
        if (tl == tr) {
            tree[v] = baseArray[tl];
        } else {
            int tm = (tl + tr) >>> 1;
            build(v * 2, tl, tm);
            build(v * 2 + 1, tm + 1, tr);
            tree[v] = Math.max(tree[v * 2], tree[v * 2 + 1]);
        }
    }

    public int maxQuery(int v, int tl, int tr, int l, int r) {
        if (l > r) {
            return Integer.MIN_VALUE;
        }
        if (l == tl && r == tr) {
            return tree[v];
        }
        int tm = (tl + tr) >>> 1;
        return Math.max(maxQuery(v * 2, tl, tm, l, Math.min(tm, r)),
                maxQuery(v * 2 + 1, tm + 1, tr, Math.max(tm + 1, l), r));
    }

    public void update(int v, int tl, int tr, int pos, int val) {
        if (tl == tr) {
            tree[v] = val;
        } else {
            int tm = (tr + tl) >>> 1;
            if (pos <= tm) {
                update(v * 2, tl, tm, pos, val);
            } else {
                update(v * 2 + 1, tm + 1, tr, pos, val);
            }
            tree[v] = Math.max(tree[v * 2], tree[v * 2 + 1]);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 4, 2, 7};
        MaxSegmentTree simpleSegmentTree = new MaxSegmentTree(a);
        simpleSegmentTree.build(1, 0, a.length - 1);
        System.out.println(simpleSegmentTree.maxQuery(1, 0, a.length - 1, 1, 2));
    }
}
