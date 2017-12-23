package Chapter2;

import CTCILibrary.LinkedListNode;

/**
 * 给定一个有环链表，实现一个算法返回环路的开头节点
 */
public class FindCircleBeginning {

    /**
     * fast每次走两步，slow每次走一步
     * 设链表的head到环路的开头节点距离为K
     * 当slow走K步到达环路的开头节点时，fast已经走了2K步，领先slow K步，
     * 此时fast和slow的距离为 LOOP_SIZE - K。
     * slow再走LOOP_SIZE - K步，fast走 2 *（LOOP_SIZE - K）步，两者相遇在A点，此时A点与环路的开头节点相差K步。
     * 这时，让一个节点（比如slow）从head开始走，另一个节点（fast）从A点开始走，
     * 当他们两个共同走K步时，两个节点相遇在B点，B即为环路的开头节点。
     */
    public static LinkedListNode findBeginning(LinkedListNode head) {

        LinkedListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }

        if(fast == null || fast.next == null)
            return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        int list_length = 100;
        int k = 10;

        // Create linked list
        LinkedListNode[] nodes = new LinkedListNode[list_length];
        for (int i = 0; i < list_length; i++) {
            nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null);
        }

        // Create loop;
        nodes[list_length - 1].next = nodes[list_length - k];

        LinkedListNode loop = findBeginning(nodes[0]);
        if (loop == null) {
            System.out.println("No Cycle.");
        } else {
            System.out.println(loop.data);
        }
    }
}
