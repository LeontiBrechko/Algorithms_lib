package trees.search;

import trees.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by leonti on 2017-10-15.
 */
public class AVLTree extends BinarySearchTree {
    public AVLTree() {
    }

    @Override
    public TreeNode insert(int key) {
        TreeNode newNode = super.insert(key);
        balanceTree(newNode);
        return newNode;
    }

    @Override
    public TreeNode delete(int key) {
        TreeNode nodeToDelete = find(key);
        if (nodeToDelete == null) return null;

        TreeNode current;
        if (nodeToDelete.getLeft() == null) {
            current = nodeToDelete.getParent();
            replace(nodeToDelete, nodeToDelete.getRight());
        } else if (nodeToDelete.getRight() == null) {
            current = nodeToDelete.getParent();
            replace(nodeToDelete, nodeToDelete.getLeft());
        } else {
            TreeNode next = next(nodeToDelete);
            current = next;
            if (next.getParent() != nodeToDelete) {
                current = next.getParent();
                replace(next, next.getRight());
                next.setRight(nodeToDelete.getRight());
                next.getRight().setParent(next);
            }

            replace(nodeToDelete, next);
            next.setLeft(nodeToDelete.getLeft());
            next.getLeft().setParent(next);
            updateHeight(next);
        }

        balanceTree(current);

        return nodeToDelete;
    }

    private void balanceTree(TreeNode current) {
        int balance;
        while (current != null) {
            balance = getBalance(current);

            if (balance > 1) {
                if (current.getLeft().getLeft() != null && (current.getLeft().getRight() == null ||
                        current.getLeft().getLeft().getHeight() > current.getLeft().getRight().getHeight()))
                    rotateRight(current);
                else {
                    rotateLeft(current.getLeft());
                    rotateRight(current);
                }
            } else if (balance < -1) {
                if (current.getRight().getRight() != null && (current.getRight().getLeft() == null ||
                        current.getRight().getRight().getHeight() > current.getRight().getLeft().getHeight()))
                    rotateLeft(current);
                else {
                    rotateRight(current.getRight());
                    rotateLeft(current);
                }
            }

            updateHeight(current);
            current = current.getParent();
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Random random = new Random(32);

        for (int i = 0; i < 10; i++) {
            int next = random.nextInt(100);
            System.out.println("inserting " + next);
            tree.insert(next);
            System.out.println(tree);
        }

        List<Integer> list = tree.rangeSearch(0, 100).stream().map(TreeNode::getKey).collect(Collectors.toList());
        System.out.println(list);

        for (int i = 0; i < 10; i++) {
            tree.rotateLeft(tree.find(list.get(new Random().nextInt(list.size()))));
            System.out.println(tree.rangeSearch(0, 100).stream().map(TreeNode::getKey).collect(Collectors.toList()));
            if (!list.equals(tree.rangeSearch(0, 100).stream().map(TreeNode::getKey).collect(Collectors.toList())))
                throw new RuntimeException();
        }
    }
}
