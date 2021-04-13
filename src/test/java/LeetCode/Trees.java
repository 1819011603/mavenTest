package LeetCode;



import java.util.*;

public class Trees<T extends Comparable<T>> {
    private TreeNode<T> root;

    // leetcode Integer数组建树
    public  TreeNode<T>  array2tree(T[] objects){
        if(root != null)root = null;
        if (objects == null || objects.length == 0)return null;
        arrayToTree(objects);
        return root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void preOrder(TreeNode<T> cur){

        Stack<TreeNode<T>> s = new Stack<>();
        while(cur != null || !s.empty()){
            if(cur != null) {
                System.out.print(cur.getVal() + " ");
                s.push(cur);
                cur = cur.left;
            }else {
                cur = s.pop();
                cur = cur.right;
            }
        }
        System.out.println();

    }
    public void inOrder(TreeNode<T> cur){

        Stack<TreeNode<T>> stack = new Stack<>();

        while (cur != null || !stack.empty()){
            if( cur != null){
                stack.push(cur);
                cur = cur.getLeft();
            }else {
                cur = stack.pop();
                System.out.print(cur.getVal() + " ");
                cur = cur.getRight();

            }
        }
        System.out.println();
    }
    public void behindOrder(TreeNode<T> cur){
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> pre = null;
        while (!stack.empty() ||  cur!=null){
            if(cur != null){
                stack.push(cur);
                cur = cur.getLeft();
            }else{
                cur = stack.peek();
                if(cur.getRight() != null && cur.getRight()!=pre){
                    cur = cur.getRight();
                }else {
                    stack.pop();
                    System.out.print(cur.getVal() + " ");
                    pre = cur;
                    cur =null;

                }
            }
        }
        System.out.println();
    }

    public void arrayToTree(T[] objects){
        LinkedList<TreeNode<T>> list = new LinkedList<>();
        int i = 1,j,k;

        root = new TreeNode<>( objects[0]);
        list.push(root);
        TreeNode<T> t;
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
                    t.setLeft(new TreeNode<>(objects[i++]));
                    list.addLast(t.getLeft());
                }
                if(i < objects.length && objects[i] != null){
                    assert t != null;
                    t.setRight(new TreeNode<>(objects[i++]));
                    list.addLast(t.getRight());
                }else i++;

            }else {
                if(i < objects.length){
                    assert t != null;
                    t.setRight(new TreeNode<>( objects[i++]));
                    list.addLast(t.getRight());
                }

            }

        }

    }
}
