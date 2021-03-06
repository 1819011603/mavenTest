package LeetCode;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/10/6  19:26
 * @VERSION 1.0
 */
public class L_21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(),cur = head;
        while (l1!= null && l2!= null){
            if (l1.val > l2.val){
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            }else {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            }
        }
        if (l1 == null){
            cur.next = l2;
        }else cur.next = l1;
        return head.next;
    }
}
