package LeetCode;

import javax.swing.tree.TreeNode;

public class IsValidBST98 {

    static boolean ans;
    static TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        ans = true;
        prev = null;
        dfs(root);
        return ans;
    }
    public void dfs(TreeNode root){
        if(root == null)return;
        dfs(root.left);

        if(prev !=null && prev.val >= root.val){

            ans = false;
            return;
        }
        prev = root;
        dfs(root.right);

    }
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){

        }
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val,TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

