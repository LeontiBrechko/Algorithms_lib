package trees.search;

import java.util.ArrayList;

/**
 * Created by leonti on 2017-10-15.
 */
public class AVL {
    public Node find(long key, Node current) {
        if (current.key == key) return current;
        else if (key > current.key) return current.right == null ? current : find(key, current.right);
        else return current.left == null ? current : find(key, current.left);
    }

    public Node next(Node current) {
        if (current.right != null) return leftDescendant(current.right);
        else return rightAncestor(current);
    }

    public ArrayList<Node> rangeSearch(int l, int r, Node root) {
        ArrayList<Node> res = new ArrayList<>();
        Node current = find(l, root);
        while (current != null && current.key <= r) {
            if (current.key >= l) res.add(current);
            current = next(current);
        }
        return res;
    }

    public void insert(long key, Node root) {
        Node parent = find(key, root);
        if (key > parent.key) parent.right = new Node(key, parent);
        else parent.left = new Node(key, parent);

        Node current = key > parent.key ? parent.right : parent.left;

    }

    public void remove(Node node) {
        if (node.right == null) {
            Node parent = node.parent;
            if (parent !=  null) {
                if (node.parent.right.key == node.key) node.parent.right = node.left;
                else node.parent.left = node.left;
            }
            if (node.left != null) node.left.parent = parent;
        } else {
            Node next = next(node);
            next.right.parent = next.parent.equals(node) ? next : next.parent;

            next.parent = node.parent;
            next.left = node.left;
            next.right = node.right.equals(next) ? next.right : node.right;
        }
    }

    private Node leftDescendant(Node current) {
        while (current.left != null) current = current.left;
        return current;
    }

    private Node rightAncestor(Node current) {
        while (current.parent != null && current.key > current.parent.key) current = current.parent;
        return current.parent;
    }

    private void rebalance(Node current) {
        Node parent = current.parent;
        if (current.left.height > current.right.height + 1) rebalanceRight(current);
        if (current.right.height > current.left.height + 1) rebalanceLeft(current);
        adjustHeight(current);
        if (parent != null) rebalance(parent);
    }

    private void rebalanceRight(Node current) {
        Node left = current.left;

    }

    private void rebalanceLeft(Node current) {

    }

    private void adjustHeight(Node current) {
        current.height = 1 + Math.max(current.left.height, current.right.height);
    }

    public static class Node {
        private long key;
        private int height;
        private Node parent;
        private Node left;
        private Node right;

        public Node(long key, Node parent) {
            this.key = key;
            this.parent = parent;
        }
    }
}
