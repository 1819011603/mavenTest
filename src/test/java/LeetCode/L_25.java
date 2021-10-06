package LeetCode;

/**
 * @author 18190
 * @Date: 2021/10/4  9:33
 * @VERSION 1.0
 */
public class L_25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)return head;
        return dfs(head,k);
    }

    public ListNode dfs(ListNode head, int k){
        if (head == null)return head;
        ListNode next = head.next;
        int i = 0;
        ListNode n,p = head ;
        // 够不够我不管 我先翻
        while (next != null && i != k-1){
            n = next.next;
            next.next = head;
            head = next;
            next = n;
            i++;
        }
        if (i < k-1){
            // 长度不够复原
            next = head.next;
            head.next = null; // 这是因为翻转之后最后一个节点指向倒数第二个节点  要复原 最后一个节点的next要指向null
            p.next = null; // 这是为了循环能够跳出 因为第一节点在翻转的时候还是指向第二个阶段 翻转之后第二节点又指向第一个节点 不设置为null while循环会一直出不来
            while (next != null && i != 0){
                n = next.next;
                next.next = head;
                head = next;// head 完成循环后是原先最后一个节点  是当前的一个节点
                next = n;
                i--;
            }

            // 不用继续递归了 直接return第一个节点p
            return p;
        }

        // 节点够了 p是原先的head 翻转之后就是最后一个节点 最后一个节点指向  下一个序列的第一个节点（也就是翻转之前长度为k的链表的最后一个节点）
        p.next = dfs(next,k);
        // return 翻转之后该序列的第一个节点（也就是翻转之前长度为k的链表的最后一个节点）
        return head;
    }

    public static void main(String[] args) {
        System.out.println(new L_25().reverseKGroup(ListNode.arrayToLinkList(new int[]{1,2,3,4,5}),3));
    }
}
