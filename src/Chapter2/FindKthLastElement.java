package Chapter2;

import CTCILibrary.AssortedMethods;
import CTCILibrary.LinkedListNode;

/**
 * 找到单向链表中倒数第k个元素，链表长度未知
 */
public class FindKthLastElement {

    /**
     * 递归实现
     * @param head 链表头节点
     * @param wi 将计数器封装到WrapperInteger中，作为其中一个返回值
     * @param k
     * @return 找到的倒数第K个节点
     */
    public static LinkedListNode findKthLastElementRecursion(LinkedListNode head, WrappedInteger wi, int k) {

        if(head == null) {
            wi.value = 0;
            return null;
        }
        LinkedListNode node = findKthLastElementRecursion(head.next, wi, k);
        wi.value++;
        if(wi.value == k) {
            return head;
        }
        return node;
    }

    /**
     * 将计数器和节点放入Object[2]数组中，不用写封装类。
     * @param head
     * @param k
     * @return
     */
    public static Object[] findKthLastElementRecursion(LinkedListNode head, int k) {

        if(head == null) {
            Object[] array = new Object[2];
            array[0] = 0;
            array[1] = null;
            return array;
        }
        Object[] result = findKthLastElementRecursion(head.next, k);
        result[0] = (int)result[0] + 1;
        if((int)result[0] == k) {
            result[1] = head;
        }
        return result;
    }

    /**
     * 错误实现 传入Integer并不能改变值，相当于传入int
     * @param head
     * @param i
     * @param k
     * @return
     */
    public static LinkedListNode findKthLastElementRecursion(LinkedListNode head, Integer i, int k) {

        if(head == null) {
            return null;
        }
        LinkedListNode node = findKthLastElementRecursion(head.next, i, k);
//        System.out.println(i.intValue());
//        sout这里输出都是0
        i = new Integer(i.intValue() + 1);
        if(i.intValue() == k) {
            return head;
        }
        return node;
    }

    /**
     * 迭代实现
     * @param head
     * @param k
     * @return
     */
    public static LinkedListNode findKthLastElementIterative(LinkedListNode head, int k) {

        LinkedListNode slowNode = head, fastNode = head;
        for (int i = 0; i < k - 1; i++) {
            if(fastNode == null) return null;
            fastNode = fastNode.next;
        }
        if(fastNode == null) return null;

        while (fastNode.next != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        return slowNode;
    }

    static class WrappedInteger {
        int value;
    }


    public static void main(String[] args) {
        LinkedListNode head = AssortedMethods.randomLinkedList(10, 0, 10);
        System.out.println(head.printForward());
        int nth = 3;
        WrappedInteger wi = new WrappedInteger();
        LinkedListNode n = findKthLastElementRecursion(head, wi, nth);
        if (n != null) {
            System.out.println(nth + "th to last node is " + n.data);
        } else {
            System.out.println("Null.  n is out of bounds.");
        }

        n = findKthLastElementIterative(head, nth);
        if (n != null) {
            System.out.println(nth + "th to last node is " + n.data);
        } else {
            System.out.println("Null.  n is out of bounds.");
        }

        n = findKthLastElementRecursion(head, new Integer(0), nth);
        if (n != null) {
            System.out.println(nth + "th to last node is " + n.data);
        } else {
            System.out.println("Null.  n is out of bounds.");
        }

        Object[] result = findKthLastElementRecursion(head, nth);
        if (result[1] != null) {
            n = (LinkedListNode)result[1];
            System.out.println(nth + "th to last node is " + n.data);
        } else {
            System.out.println("Null.  n is out of bounds.");
        }
    }
}
