package LeetCode;



import java.util.*;

public class Trees {
    static TreeNode root;

    // leetcode Integer数组建树
    public static TreeNode  array2tree(Object[] objects){
        if(root != null)root = null;
        if (objects == null || objects.length == 0)return null;
        arrayToTree(objects);
        return Trees.root;
    }
//    private static TreeNode array2tree(Object[] objects, int i){
//        if(i >= objects.length || objects[i] == null)return null;
//        TreeNode root1 = null;
//        if(objects[i] instanceof Integer){
//            root1 = new TreeNode((Integer) objects[i],array2tree(objects,i*2+1),array2tree(objects,i*2+2));
//            if(i == 0){
//                Tree.root = root1;
//            }
//        }
//        return root1;
//
//    }
    public static void inOrder(TreeNode root){
        if(root != null){
            inOrder(root.getLeft());
            System.out.print(root.getVal() + " ");
            inOrder(root.getRight());
        }

    }
    public static void arrayToTree(Object[] objects){
        LinkedList<TreeNode> list = new LinkedList<>();
        int i = 1,j,k;

        Trees.root = new TreeNode((Integer) objects[0]);
        list.push(Trees.root);
        TreeNode t;
        while ( i < objects.length && list.size() != 0){
            t = list.poll();
            j = i;
            while (i < objects.length && objects[i] == null){
                i++;
            }
            k = (i-j)>>1;
            while (k-- != 0){
                t = list.poll();
            }
            if(i % 2 == 1){
                if(i < objects.length){
                    assert t != null;
                    t.setLeft(new TreeNode((Integer) objects[i++]));
                    list.addLast(t.getLeft());
                }
                if(i < objects.length && objects[i] != null){
                    assert t != null;
                    t.setRight(new TreeNode((Integer) objects[i++]));
                    list.addLast(t.getRight());
                }else i++;

            }else {
                if(i < objects.length){
                    assert t != null;
                    t.setRight(new TreeNode((Integer) objects[i++]));
                    list.addLast(t.getRight());
                }

            }

        }

    }
}
