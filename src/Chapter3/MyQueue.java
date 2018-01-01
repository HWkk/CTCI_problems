package Chapter3;

import CTCILibrary.AssortedMethods;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用两个栈实现一个队列
 */
public class MyQueue<T> {

    Stack<T> stackWithNewValue;
    Stack<T> stackWithOldValue;

    public MyQueue() {
        stackWithNewValue = new Stack<>();
        stackWithOldValue = new Stack<>();
    }

    public void enqueue(T value) {
        stackWithNewValue.push(value);
    }

    public T dequeue() {
        shiftStack();
        return stackWithOldValue.pop();
    }

    public void shiftStack() {
        if(stackWithOldValue.isEmpty())
            while (!stackWithNewValue.isEmpty())
                stackWithOldValue.push(stackWithNewValue.pop());
    }

    public T peek() {
        shiftStack();
        return stackWithOldValue.peek();
    }

    public int size() {
        return stackWithOldValue.size() + stackWithNewValue.size();
    }

    public static void main(String[] args) {
        MyQueue<Integer> my_queue = new MyQueue<Integer>();

        // Let's test our code against a "real" queue
        Queue<Integer> test_queue = new LinkedList<Integer>();

        for (int i = 0; i < 100; i++) {
            int choice = AssortedMethods.randomIntInRange(0, 10);
            if (choice <= 5) { // enqueue
                int element = AssortedMethods.randomIntInRange(1, 10);
                test_queue.add(element);
                my_queue.enqueue(element);
                System.out.println("Enqueued " + element);
            } else if (test_queue.size() > 0) {
                int top1 = test_queue.remove();
                int top2 = my_queue.dequeue();
                if (top1 != top2) { // Check for error
                    System.out.println("******* FAILURE - DIFFERENT TOPS: " + top1 + ", " + top2);
                }
                System.out.println("Dequeued " + top1);
            }

            if (test_queue.size() == my_queue.size()) {
                if (test_queue.size() > 0 && test_queue.peek() != my_queue.peek()) {
                    System.out.println("******* FAILURE - DIFFERENT TOPS: " + test_queue.peek() + ", " + my_queue.peek() + " ******");
                }
            } else {
                System.out.println("******* FAILURE - DIFFERENT SIZES ******");
            }
        }
    }
}
