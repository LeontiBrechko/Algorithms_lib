package trees.segment_tree;

/**
 * Created by Leonti on 2016-06-09.
 */
// 1-indexed
public class SimpleSegmentTree {
    private int[] baseArray;
    private int[] tree;

    public SimpleSegmentTree(int a[]) {
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
            tree[v] = tree[v * 2] + tree[v * 2 + 1];
        }
    }

    public int sumQuery(int v, int tl, int tr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == tl && r == tr) {
            return tree[v];
        }
        int tm = (tl + tr) >>> 1;
        return sumQuery(v * 2, tl, tm, l, Math.min(tm, r))
                + sumQuery(v * 2 + 1, tm + 1, tr, Math.max(tm + 1, l), r);
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
            tree[v] = tree[v * 2] + tree[v * 2 + 1];
        }
    }

//    private int arraySize(int n) {
//        2 ∗ 2floor((log2(n))+1.
//        int power = (int) Math.ceil(Math.log(n) / Math.log(2));
//        int level = power + 1;
//        int lastLevel = (int) Math.pow(2, power);
//        return (int) (2 * lastLevel * (1 - Math.pow(0.5, level)));
//    }
//
//    private void buildTree(int v, int tl, int tr) {
//        if (tl == tr) {
//            tree[v] = a[tl];
//        } else {
//            int tm = (tl + tr) >>> 1;
//            buildTree(v * 2 + 1, tl, tm);
//            buildTree(v * 2 + 2, tm + 1, tr);
//            tree[v] = tree[v * 2 + 1] + tree[v * 2 + 2];
//        }
//    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 5, 6};
        SimpleSegmentTree simpleSegmentTree = new SimpleSegmentTree(a);
        simpleSegmentTree.build(1, 0, a.length - 1);
        System.out.println(simpleSegmentTree.sumQuery(1, 0, a.length - 1, 1, 5));
    }
}
