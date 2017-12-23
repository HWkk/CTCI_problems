package Chapter2;

import CTCILibrary.LinkedListNode;

/**
 * 给定两个用链表表示的整数，每个结点包含一个数位。这些数位是反向存放的，求这两个整数的和，并用链表形式返回结果
 * 情况1：数位反向存放  即7->1->6 + 5->9->2 为617 + 295
 * 情况2：数位正向存放  即7->1->6 + 5->9->2 为716 + 592
 */
public class AddLists {

    /**
     * 反向存放的加法
     */
    public static LinkedListNode addListsReserve(LinkedListNode l1, LinkedListNode l2, int carry) {

        if(l1 == null && l2 == null && carry == 0)
            return null;

        LinkedListNode result = new LinkedListNode();
        int curValue = carry;

        if(l1 != null) curValue += l1.data;
        if(l2 != null) curValue += l2.data;
        result.data = curValue % 10;
        carry = curValue / 10;

        result.next = addListsReserve(l1 == null ? null : l1.next, l2 == null ? null : l2.next, carry);
        return result;
    }

    /**
     * 正向存放的相加
     */
    public static LinkedListNode addListsForward(LinkedListNode l1, LinkedListNode l2) {

        int length1 = length(l1), length2 = length(l2);
        if(length1 < length2) {
            l1 = padZeros(l1, length2 - length1);
        } else if (length2 < length1) {
            l2 = padZeros(l2, length1 - length2);
        }

        WrappedSumAndCarry wrappedSumAndCarry = addListsRecursion(l1, l2);
        if(wrappedSumAndCarry.carry == 0)
            return wrappedSumAndCarry.node;
        else {
            LinkedListNode node = new LinkedListNode(1, wrappedSumAndCarry.node, null);
            return node;
        }
    }

    static class WrappedSumAndCarry {
        LinkedListNode node = null;
        int carry = 0;
    }

    public static WrappedSumAndCarry addListsRecursion(LinkedListNode l1, LinkedListNode l2) {

        if(l1 == null && l2 == null) {
            WrappedSumAndCarry wrappedSumAndCarry = new WrappedSumAndCarry();
            return wrappedSumAndCarry;
        }

        WrappedSumAndCarry result = addListsRecursion(l1.next, l2.next);
        int sum = l1.data + l2.data + result.carry;
        LinkedListNode node = new LinkedListNode(sum % 10, result.node, null);
        result.node = node;
        result.carry = sum / 10;
        return result;
    }

    /**
     * 获得链表长度
     */
    public static int length(LinkedListNode node) {
        int count = 0;
        while(node != null) {
            count ++;
            node = node.next;
        }
        return count;
    }

    /**
     * 给链表的前面补上0
     */
    public static LinkedListNode padZeros(LinkedListNode node, int length) {
        for (int i = 0; i < length; i++) {
            LinkedListNode temp = new LinkedListNode(0, node, null);
            node = temp;
        }
        return node;
    }

    public static void main(String[] args) {
        LinkedListNode lA1 = new LinkedListNode(1, null, null);
        LinkedListNode lA2 = new LinkedListNode(4, null, lA1);
        LinkedListNode lA3 = new LinkedListNode(2, null, lA2);

        LinkedListNode lB1 = new LinkedListNode(9, null, null);
        LinkedListNode lB2 = new LinkedListNode(7, null, lB1);
        LinkedListNode lB3 = new LinkedListNode(1, null, lB2);

        LinkedListNode list3 = addListsReserve(lA1, lB1, 0);
        LinkedListNode list = addListsForward(lA1, lB1);

        System.out.println("  " + lA1.printForward());
        System.out.println("+ " + lB1.printForward());
        System.out.println("= " + list3.printForward());
        System.out.println("= " + list.printForward());
    }
}
