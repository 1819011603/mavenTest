package LeetCode;

/**
 * @author 18190
 * @Date: 2021/10/2  15:31
 * @VERSION 1.0
 */
public class L_23 {


    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists_DAC(lists,0,lists.length-1);
    }
    public static ListNode mergeKLists_DAC(ListNode[] lists,int left_index,int right_index){
        if(left_index>right_index){
            return null;
        }
        if(left_index==right_index){
            return lists[left_index];
        }
        int mid = (left_index + right_index)/2;
        return mergeTwoLists(mergeKLists_DAC(lists,left_index,mid),mergeKLists_DAC(lists,mid+1,right_index));
    }
    public static ListNode mergeTwoLists(ListNode node1,ListNode node2){
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(node1!=null&&node2!=null) {
            if(node1.val< node2.val){
                cur.next = node1;
                node1 = node1.next;
            }
            else {
                cur.next = node2;
                node2=node2.next;
            }
            cur = cur.next;
        }
        if(node1==null){
            cur.next = node2;
        }
        else {
            cur.next = node1;
        }
        return dummy.next;
    }


    public ListNode mergeKLists1(ListNode[] lists) {
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
