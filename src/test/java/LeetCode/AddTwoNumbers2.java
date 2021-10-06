package LeetCode;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/9/23  19:42
 * @VERSION 1.0
 */
public class AddTwoNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = l1;
        ListNode t1,t2;
        int ans = l1.val + l2.val;
        int u = ans / 10;
        l1.val = ans%10;
        while((t1 = l1.next) != null && (t2 = l2.next) != null){
            ans = t1.val + t2.val + u;
            u = ans/10;
            t1.val = ans%10;
            l1 = t1;
            l2 = t2;
        }
        if(l1.next == null){
            l1.next = l2.next;
        }
        while((t1 = l1.next) != null&& u == 1){
            ans = t1.val + u;
            u = ans /10;
            t1.val = ans%10;
            l1 = t1;
        }

        if( u == 1){
            l1.next = new ListNode( u);
        }


        return l;

    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(1);
        ListNode l3 = l2;
        l3.next = new ListNode(9);
        l3 = l3.next;

        l3.next = new ListNode(9);
        l3 = l3.next;
        l3.next = new ListNode(9);
        l3 = l3.next;
        l3.next = new ListNode(9);
        l3 = l3.next;
        l3.next = new ListNode(9);
        l3 = l3.next;
        l1.print();
        l2.print();

       new AddTwoNumbers2().addTwoNumbers(l1,l2);
       l1.print();
        System.out.println();
        l3.next = new ListNode(5);
        l1.print();
        l2.print();
        new AddTwoNumbers2().addTwoNumbers(l1,l2);
        l1.print();



    }
}
