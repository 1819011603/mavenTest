package LeetCode;

/**
 * @author 18190
 * @Date: 2021/10/2  15:31
 * @VERSION 1.0
 */
public class L_23 {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0)return null;
        else if (n == 1)return lists[0];
        ListNode head = new ListNode(),cur = head;
        ListNode min = null,h;

        int i = 0;
        int k = -1;
        while (true){
            i = 0;
            for (int j = 0; j < n; j++){
                h = lists[j];
                if (h == null){
                    i++;

                }
               else if ((min == null || min.val > h.val)){
                    min = h;
                    k = j;
                }
            }

            if (i == n){
                break;
            }

            cur.next = lists[k];
            cur = cur.next;
            lists[k] = lists[k].next;
            min = null;

        }
        return head.next;


    }

    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[]{
                ListNode.arrayToLinkList(new int[]{1,4,5}),
                ListNode.arrayToLinkList(new int[]{1,3,4}),
                ListNode.arrayToLinkList(new int[]{2,6})
        };
        new L_23().mergeKLists(listNodes).print();
//        System.out.println(new L_23().mergeKLists(listNodes));

    }
}
