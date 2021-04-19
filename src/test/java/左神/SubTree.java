package 左神;

import Algorithm.KMP;
import LeetCode.TreeNode;
import LeetCode.Trees;

public class SubTree {
    Trees<Integer> trees = new Trees<>();
    // 子树必须是完全一样 不能多不能少
    public boolean subTree(TreeNode<Integer> tree, TreeNode<Integer> subTree){

        String s1 = trees.preOrderOnly(tree);
        String s2 = trees.preOrderOnly(subTree);
        System.out.println(s1);
        System.out.println(s2);
        return KMP.kmp(s1,s2) != -1;
    }

    public static void main(String[] args) {
        // leetcode建树 不知道去LeetCode可视化树那里去看
        Integer[] integers1 = {1,2,3,4,null,5,6,null,null,4,1,null,null,null,null,3,4};
        Integer[] integers2 = {1,3,4};// true
        Integer[] integers3 = {5,4,1,null,null,3};// false
        Integer[] integers4 = {5,4,1,null,null,3,4};// true
        Trees<Integer> trees1 = new Trees<>();
        TreeNode<Integer> t1 = trees1.array2tree(integers1);
        Trees<Integer> trees2 = new Trees<>();
        TreeNode<Integer> t2 = trees2.array2tree(integers2);
        System.out.println(new SubTree().subTree(t1,t2));
        System.out.println(new SubTree().subTree(t1,trees2.array2tree(integers3)));
        System.out.println(new SubTree().subTree(t1,trees2.array2tree(integers4)));

    }
}
