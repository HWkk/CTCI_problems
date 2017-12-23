package Chapter2;

import CTCILibrary.LinkedListNode;

import java.util.HashSet;

/**
 * 删除未排序的单向链表中的重复元素
 */
public class RemoveDuplicatedElement {

    public static void removeDuplicatedElementUsingSet(LinkedListNode head) {

        LinkedListNode cur = head, pre = null;
        HashSet<Integer> set = new HashSet<>();
        while (cur != null) {
            if(set.contains(cur.data)) {
                pre.next = cur.next;
            } else {
                set.add(cur.data);
                pre = cur;
            }
            cur = cur.next;
        }
    }

    public static void removeDuplicatedElement(LinkedListNode head) {
        LinkedListNode cur = head;
        while(cur != null) {
            LinkedListNode runner = cur.next, pre = cur;
            while (runner != null) {
                if(runner.data == cur.data) {
                    pre.next = runner.next;
                } else {
                    pre = runner;
                }
                runner = runner.next;
            }
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
        LinkedListNode head = first;
        LinkedListNode second = first;
        for (int i = 1; i < 8; i++) {
            second = new LinkedListNode(i % 2, null, null);
            first.setNext(second);
            second.setPrevious(first);
            first = second;
        }
        System.out.println(head.printForward());
        LinkedListNode clone = head.clone();
        removeDuplicatedElementUsingSet(head);
        System.out.println(head.printForward());
        System.out.println(clone.printForward());
        removeDuplicatedElement(clone);
        System.out.println(clone.printForward());
    }
}
