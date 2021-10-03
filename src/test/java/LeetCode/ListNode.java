package LeetCode;

/**
 * @author 18190
 * @Date: 2021/9/29  18:44
 * @VERSION 1.0
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val;next = null; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {

        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public void print(){
        ListNode l = this;

        System.out.print(l.val);
        l = l.next;
        while (l!=null){
            System.out.print(" " + l.val);
            l = l.next;
        }
        System.out.println();
    }

    // 链表递归的技巧
    public static ListNode prints(ListNode head){
        if (head == null)return null;
        System.out.println(head.val);
        head.next = prints(head.next);

        return head;

    }

    public  static ListNode arrayToLinkList(int[] nums){
        if (nums == null || nums.length == 0)return null;
        ListNode head = new ListNode(nums[0]),cur = head;
        for (int i = 1; i < nums.length; i++){
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return head;

    }
}
