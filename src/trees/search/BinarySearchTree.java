package trees.search;

import trees.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by leonti on 2018-08-19.
 */
public class BinarySearchTree {
    protected TreeNode root;

    public BinarySearchTree() {
    }

    public TreeNode find(int key) {
        TreeNode current = root;

        int currentKey;
        while (current != null) {
            currentKey = current.getKey();
            if (key == currentKey) return current;
            else if (key > currentKey) {
                if (current.getRight() == null) return next(current);
                else current = current.getRight();
            } else {
                if (current.getLeft() == null) return current;
                else current = current.getLeft();
            }
        }

        return null;
    }

    public TreeNode next(TreeNode current) {
        if (current == null) return null;

        if (current.getRight() != null) return leftSuccessor(current.getRight());
        else return rightPredecessor(current);
    }

    public List<TreeNode> rangeSearch(int l, int r) {
        TreeNode current = find(l);

        List<TreeNode> res = new ArrayList<>();
        while (current != null && current.getKey() <= r) {
            if (current.getKey() >= l) res.add(current);
            current = next(current);
        }

        return res;
    }

    public TreeNode insert(int key) {
        TreeNode current = root;
        TreeNode parent = null;
        while (current != null) {
            parent = current;
            if (current.getKey() > key) current = current.getLeft();
            else if (current.getKey() < key) current = current.getRight();
            else return current;
        }

        TreeNode newNode = new TreeNode(key, parent);
        if (parent == null) root = newNode;
        else if (key < parent.getKey()) parent.setLeft(newNode);
        else parent.setRight(newNode);

        while (parent != null) {
            updateHeight(parent);
            parent = parent.getParent();
        }

        return newNode;
    }

    public TreeNode delete(int key) {
        TreeNode nodeToDelete = find(key);
        if (nodeToDelete == null) return null;

        if (nodeToDelete.getLeft() == null) replace(nodeToDelete, nodeToDelete.getRight());
        else if (nodeToDelete.getRight() == null) replace(nodeToDelete, nodeToDelete.getLeft());
        else {
            TreeNode next = next(nodeToDelete);
            if (next.getParent() != nodeToDelete) {
                replace(next, next.getRight());
                next.setRight(nodeToDelete.getRight());
                next.getRight().setParent(next);
            }

            replace(nodeToDelete, next);
            next.setLeft(nodeToDelete.getLeft());
            next.getLeft().setParent(next);
            updateHeight(next);
        }

        return nodeToDelete;
    }

    public boolean isBalanced() {
        if (root == null) return true;

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        TreeNode next;
        int balance;
        while (!queue.isEmpty()) {
            next = queue.poll();
            balance = getBalance(next);
            if (balance > 1 || balance < -1) return false;
            if (next.getLeft() != null) queue.offer(next.getLeft());
            if (next.getRight() != null) queue.offer(next.getRight());
        }

        return true;
    }

    protected TreeNode leftSuccessor(TreeNode node) {
        while (node != null) {
            if (node.getLeft() != null) node = node.getLeft();
            else break;
        }

        return node;
    }

    protected TreeNode rightPredecessor(TreeNode node) {
        while (node != null) {
            if (node.getParent() == null) return null;

            if (node == node.getParent().getLeft()) return node.getParent();
            else node = node.getParent();
        }

        return null;
    }

    protected void replace(TreeNode u, TreeNode v) {
        if (u.getParent() == null) root = v;
        else if (u.getParent().getLeft() == u) u.getParent().setLeft(v);
        else u.getParent().setRight(v);

        if (v != null) v.setParent(u.getParent());

        TreeNode parent = u.getParent();
        while (parent != null) {
            updateHeight(parent);
            parent = parent.getParent();
        }
    }

    protected int getHeightOfNode(TreeNode node) {
        return node == null ? 0 : node.getHeight();
    }

    protected void updateHeight(TreeNode node) {
        if (node != null)
            node.setHeight(Math.max(getHeightOfNode(node.getLeft()), getHeightOfNode(node.getRight())) + 1);
    }

    protected int getBalance(TreeNode node) {
        return node == null ? 0 : getHeightOfNode(node.getLeft()) - getHeightOfNode(node.getRight());
    }

    protected TreeNode rotateRight(TreeNode node) {
        if (node == null || node.getLeft() == null) return node;

        TreeNode parent = node.getParent();
        TreeNode left = node.getLeft();
        TreeNode leftRight = left.getRight();

        replace(node, left);

        left.setRight(node);
        node.setParent(left);

        node.setLeft(leftRight);
        if (leftRight != null) leftRight.setParent(node);

        updateHeight(node);
        updateHeight(left);
        updateHeight(parent);

        return left;
    }

    protected TreeNode rotateLeft(TreeNode node) {
        if (node == null || node.getRight() == null) return node;

        TreeNode parent = node.getParent();
        TreeNode right = node.getRight();
        TreeNode rightLeft = right.getLeft();

        replace(node, right);

        right.setLeft(node);
        node.setParent(right);

        node.setRight(rightLeft);
        if (rightLeft != null) rightLeft.setParent(node);

        updateHeight(node);
        updateHeight(right);
        updateHeight(parent);

        return right;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }

            current = stack.poll();
            res.append(current.getKey()).append(' ');
            current = current.getRight();
        }

        return res.toString();
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Random random = new Random(23);

        for (int i = 0; i < 100; i++) {
            int next = random.nextInt(1000);
            System.out.println("inserting " + next);
            tree.insert(next);
            System.out.println(tree);
            if (!tree.isBalanced()) throw new RuntimeException();
        }

        List<Integer> list = tree.rangeSearch(0, 1000).stream().map(TreeNode::getKey).collect(Collectors.toList());
        System.out.println(list);

        List<Integer> sortedList = new ArrayList<>(list);
        Collections.shuffle(list, random);
        for (Integer ai : list) {
            System.out.println("deleting " + ai);
            if (ai == 244) {
                int r = 4;
            }
            tree.delete(ai);
            System.out.println(tree);

            sortedList.remove(ai);
            if (!tree.isBalanced()) {
                throw new RuntimeException();
            }
            if (!sortedList.equals(tree.rangeSearch(0, 1000).stream().map(TreeNode::getKey).collect(Collectors.toList())))
                throw new RuntimeException();
        }
    }
}
