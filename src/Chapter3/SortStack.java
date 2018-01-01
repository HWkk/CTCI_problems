package Chapter3;

import CTCILibrary.AssortedMethods;

import java.util.Stack;

/**
 * 仅使用一个额外的栈，对给定的栈按升序进行排序（栈顶元素最大）
 */
public class SortStack extends Stack<Integer> {

    public Stack<Integer> sortUsingStack() {
        Stack<Integer> sortedStack = new Stack<>();
        while (!isEmpty()) {
            int temp = pop();
            while (!sortedStack.isEmpty() && sortedStack.peek() > temp)
                push(sortedStack.pop());
            sortedStack.push(temp);
        }
        return sortedStack;
    }

    public static void main(String [] args) {
        for (int k = 1; k < 2; k++) {
            SortStack s = new SortStack();
            for (int i = 0; i < 10 * k; i++) {
                int r = AssortedMethods.randomIntInRange(0,  1000);
                s.push(r);
            }
            Stack sorted = s.sortUsingStack();
            while (!sorted.isEmpty())
                System.out.println(sorted.pop());
        }
    }
}
