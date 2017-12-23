package Chapter2;

import CTCILibrary.LinkedListNode;

import java.util.Stack;

/**
 * 判断一个链表是否为回文
 */
public class IsListPalindrome {

    /**
     * 将链表前半部分元素压入栈，然后再和后半部分比较
     */
    public static boolean isListPalindromeUsingStack(LinkedListNode head) {

        LinkedListNode slow = head, fast = head;
        Stack<Integer> stack = new Stack<>();

        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null)
            slow = slow.next;

        while (slow != null) {
            if(stack.pop() != slow.data)
                return false;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 递归实现，对长为n的链表，通过递归的每次把length减少2，来找到位于中间的节点
     * 当length == 1时，即n为奇数，将当前节点的next装进封装类中，
     * length == 2时，n为偶数，将当前节点的next的next装进封装类中。
     * 有了封装类中的节点，就可以在递归调用中反向比较对应的节点值，只要出现一个false，就可以返回。否则返回true。
     */
    public static boolean isListPalindromeUsingRecursion(LinkedListNode head) {
        WrappedNodeAndResult result = isListPalindromeRecursion(head, length(head));
        return result.result;
    }

    /**
     * 递归过程
     */
    public static WrappedNodeAndResult isListPalindromeRecursion(LinkedListNode head, int length) {

        if(length == 1) {
            return new WrappedNodeAndResult(head.next, true);
        } else if (length == 2) {
            return new WrappedNodeAndResult(head.next.next, head.data == head.next.data);
        }

        WrappedNodeAndResult result = isListPalindromeRecursion(head.next, length - 2);
        if(result.result == false) {
            return result;
        }
        /**
         * head在递归调用的过程，相当于head = head.prev，
         * 而通过手动赋值result的node节点,result.node = result.node.next。
         * 相当于从中间扩散，一个一个的判断。
         */
        result.result = (result.node.data == head.data);
        result.node = result.node.next;
        return result;
    }

    /**
     * 封装类
     */
    static class WrappedNodeAndResult {
        LinkedListNode node = null;
        boolean result = true;

        public WrappedNodeAndResult(LinkedListNode node, boolean result) {
            this.node = node;
            this.result = result;
        }
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

    public static void main(String[] args) {
        int length = 10;
        LinkedListNode[] nodes = new LinkedListNode[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
        }

        for (int i = 0; i < length; i++) {
            if (i < length - 1) {
                nodes[i].setNext(nodes[i + 1]);
            }
            if (i > 0) {
                nodes[i].setPrevious(nodes[i - 1]);
            }
        }
        // nodes[length - 2].data = 9; // Uncomment to ruin palindrome

        LinkedListNode head = nodes[0];
        System.out.println(head.printForward());
        System.out.println(isListPalindromeUsingStack(head));
        System.out.println(isListPalindromeUsingRecursion(head));
    }

}
