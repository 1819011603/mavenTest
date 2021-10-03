package LeetCode;

import com.sun.xml.internal.ws.api.WSDLLocator;

/**
 * @author 18190
 * @Date: 2021/10/1  21:40
 * @VERSION 1.0
 */
public class L_24 {


    public ListNode swapPairs(ListNode head) {
        if (head == null)return null;


        return dfs(head,head.next);
    }

    public ListNode dfs(ListNode pre,ListNode now){
        if (now == null)return pre;
        ListNode next = now.next;
        now.next = pre;
        pre.next = next;

        if (next != null)
        pre.next = dfs(next,next.next);

        return now;
    }


/*    ListNode pre_pre = null;
    public ListNode swapPairs(ListNode head) {
        if (head == null)return null;
        ListNode slow = head;
        ListNode fast = slow.next;
        if (fast == null)
            return slow;
        dfs(slow,fast);
        return fast;
    }

    public void dfs(ListNode pre,ListNode now){
        if (pre == null || now == null)return;
        ListNode next = now.next;
        now.next = pre;
        pre.next = next;
        if (pre_pre != null){
            pre_pre.next = now;
        }
        pre_pre = pre;
        if (next!=null)
            dfs(next,next.next);
    }*/

    public static void main(String[] args) {
        System.out.println(new L_24().swapPairs(ListNode.arrayToLinkList(new int[]{1,2,3,4})));
        System.out.println(new L_24().swapPairs(ListNode.arrayToLinkList(new int[]{1})));
    }
}
