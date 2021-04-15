package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Rob337 {
    // f 是偷根节点  g是不偷根节点
    Map<TreeNode<Integer>,Integer> f = new HashMap<>();
    Map<TreeNode<Integer>,Integer> g = new HashMap<>();

    public int rob(TreeNode<Integer> root){
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }
    public void dfs(TreeNode<Integer> node){
        if(node == null)return;
        dfs(node.left);
        dfs(node.right);
        f.put(node,node.val + g.getOrDefault(node.left,0) + g.getOrDefault(node.right,0));
        g.put(node,Math.max(f.getOrDefault(node.left,0),g.getOrDefault(node.left,0))+ Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0))) ;
    }


    public int rob1(TreeNode<Integer> root) {
        int[] rootStatus = dfs1(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs1(TreeNode<Integer> node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs1(node.left);
        int[] r = dfs1(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);

        ///  {selected, notSelected}
        return new int[]{selected, notSelected};
    }


    public static void main(String[] args) {
        Integer[] integers = {3,4,5,1,3,null,1};
        TreeNode<Integer> t = new Trees<Integer>().array2tree(integers);
        new Trees<Integer>().inOrder(t);
        new Trees<Integer>().preOrder(t);
        System.out.println(new Rob337().rob(t));
        System.out.println(new Rob337().rob1(t));
    }
}
