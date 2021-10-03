package LeetCode;

/**
 * @author 18190
 * @Date: 2021/9/29  18:43
 * @VERSION 1.0
 */
public class L_19 {
    int i = 0;
    ListNode pre = null;
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        dfs(head,n);
        if (pre!=null){ // i!=n-1  也可  i==n-1 等价于pre==null  都表示此时要删除的节点是头结点
            pre.next = pre.next.next;
        }else head = head.next;
        return head;
    }

    public void dfs(ListNode head,int n){
        if (head.next != null){
            // i++  这是从前往后找有多少个节点的next不为空

            dfs(head.next, n);
            // 这是从后往前找有多少个节点的next不为空
            i++;
            if (i == n){
                // i== n 就表示包含当前节点在内的尾链表有n个节点有next节点 则表示当前节点是倒数第n+1个节点
                pre = head;
            }
        }





    }


    int cur=0;
    ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        head.next = removeNthFromEnd(head.next,n);
        cur++;
        if(n==cur) return head.next;
        return head;
    }

    public static void main(String[] args) {

        System.out.println(new L_19().removeNthFromEnd(ListNode.arrayToLinkList(new int[]{1,2,3,4,5}),2));
        System.out.println(new L_19().removeNthFromEnd(ListNode.arrayToLinkList(new int[]{1}),1));
        System.out.println(new L_19().removeNthFromEnd(ListNode.arrayToLinkList(new int[]{1,2}),1));
        System.out.println(new L_19().removeNthFromEnd(ListNode.arrayToLinkList(new int[]{1,2}),2));
        ListNode listNode = ListNode.arrayToLinkList(new int[]{1, 2, 3, 4, 5});
        ListNode.prints(listNode);

    }
}
