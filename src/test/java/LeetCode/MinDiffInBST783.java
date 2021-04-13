package LeetCode;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
*/

public class MinDiffInBST783 {

    static int pre;
    static int min ;
    public int minDiffInBST(TreeNode<Integer> root){
        min = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);

        return min;
    }
    public void dfs(TreeNode<Integer> root){
        if(root == null)return;
        dfs(root.getLeft());
        if(pre!=-1){
            min = Math.min(min,root.getVal() - pre);
        }
        pre = root.getVal();
        dfs(root.getRight());

    }

    public static void main(String[] args) {
        Integer[] objects = {5,0,70,null,2,6,null,1,3,null,8,null,null,null,4,null,11,null,null,9,12,null,10,null,13};
//        Object[] objects = {27,null,34,null,58,50,null,44};
        Trees<Integer> trees = new Trees<>();
        TreeNode<Integer> treeNode = trees.array2tree(objects);
        System.out.println(new MinDiffInBST783().minDiffInBST(treeNode));
        trees.preOrder(treeNode);
        trees.inOrder(treeNode);
        trees.behindOrder(treeNode);

        Double[] objects1 = {5.6,0.7,70.1,null,2.0,6.1,null,1.1,3.1,null,8.1,null,null,null,4.0,null,11.1,null,null,9.11,12.0,null,10.0,null,13.1};
//        Comparable[] objects = {27,null,34,null,58,50,null,44};
        Trees<Double> trees1 = new Trees<>();
        TreeNode<Double> treeNode1 = trees1.array2tree( objects1);
        System.out.println(new MinDiffInBST783().minDiffInBST(treeNode));
        trees1.preOrder(treeNode1);
        trees1.inOrder(treeNode1);
        trees1.behindOrder(treeNode1);
    }
}
