package Chapter3;

import CTCILibrary.AssortedMethods;

import java.util.Stack;

/**
 * 为stack添加一个min方法，使其能在O(1)的时间内找到栈的最小值。
 * 再每个节点上添加一个属性，记录其被push到栈中时，栈中的最小值。每次取最小值取栈顶的最小值即可。
 */
public class StackWithMin extends Stack<NodeWithMin>{

    public void push(int value) {
        int min = Math.min(value, min());
        super.push(new NodeWithMin(min, value));
    }

    public int min() {
        if(isEmpty())
            return Integer.MAX_VALUE;
        return peek().min;
    }

    public static void main(String[] args) {
        StackWithMin stack = new StackWithMin();
        StackWithMin2 stack2 = new StackWithMin2();
        for (int i = 0; i < 15; i++) {
            int value = AssortedMethods.randomIntInRange(0, 100);
            stack.push(value);
            stack2.push(value);
            System.out.print(value + ", ");
        }
        System.out.println('\n');
        for (int i = 0; i < 15; i++) {
            System.out.println("Popped " + stack.pop().value + ", " + stack2.pop());
            System.out.println("New min is " + stack.min() + ", " + stack2.min());
        }
    }
}

class NodeWithMin {
    int min;
    int value;

    public NodeWithMin(int min, int value) {
        this.min = min;
        this.value = value;
    }
}

/**
 * 把最小值放入另一个栈中，减少空间开销。
 */
class StackWithMin2 extends Stack<Integer>{

    Stack<Integer> minStack;
    public StackWithMin2() {
        minStack = new Stack<>();
    }

    public void push(int value) {
        if(value <= min())
            minStack.push(value);
        super.push(value);
    }

    public int min() {
        if(minStack.isEmpty())
            return Integer.MAX_VALUE;
        return minStack.peek();
    }

    public Integer pop() {
        int value = super.pop();
        if(value  == min())
            minStack.pop();
        return value;
    }
}

