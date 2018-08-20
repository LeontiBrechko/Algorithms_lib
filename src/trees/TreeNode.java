package trees;

/**
 * Created by leonti on 2018-08-19.
 */
public class TreeNode {
    private int key;
    private int height;
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int key, TreeNode parent) {
        this.key = key;
        this.parent = parent;
        height = 1;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
