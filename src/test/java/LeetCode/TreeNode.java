package LeetCode;

public class TreeNode<T extends Comparable<T>> {

    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;
    TreeNode() {}
    TreeNode(T val) { this(val,null,null); }
    TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
     }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

}