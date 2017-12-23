package Chapter2;

import CTCILibrary.LinkedListNode;

/**
 * 以给定值x为基准将链表分割成两部分，所有小于x的节点排在大于或等于x的节点之前
 */
public class PartitionList {

    public static LinkedListNode partition(LinkedListNode head, int x) {

        LinkedListNode smallHead = null, smallEnd = null, bigHead = null, bigEnd = null;

        while (head != null) {
            LinkedListNode next = head.next;
            head.next = null;

            if(head.data < x) {
                if(smallHead == null) {
                    smallHead = head;
                    smallEnd = smallHead;
                } else {
                    smallEnd.next = head;
                    smallEnd = head;
                }
            } else {
                if(bigHead == null) {
                    bigHead = head;
                    bigEnd = bigHead;
                } else {
                    bigEnd.next = head;
                    bigEnd = head;
                }
            }
            head = next;
        }
        if(smallHead == null)
            return bigHead;
        smallEnd.next = bigHead;
        return smallHead;
    }

    public static void main(String[] args) {
		/* Create linked list */
        int[] vals = {1, 3, 7, 5, 2, 9, 4};
        LinkedListNode head = new LinkedListNode(vals[0], null, null);
        LinkedListNode current = head;
        for (int i = 1; i < vals.length; i++) {
            current = new LinkedListNode(vals[i], null, current);
        }
        System.out.println(head.printForward());

		/* Partition */
        LinkedListNode h = partition(head, 5);

		/* Print Result */
        System.out.println(h.printForward());
    }

}
