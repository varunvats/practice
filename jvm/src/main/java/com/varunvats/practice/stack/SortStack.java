package com.varunvats.practice.stack;

import java.util.Stack;

public class SortStack {

    public static <T extends Comparable<T>> void sort(Stack<T> stack) {
        if (stack == null)
            return;
        Stack<T> tempStack = new Stack<T>();
        sort(stack, tempStack);
    }

    private static <T extends Comparable<T>> void sort(Stack<T> stack, Stack<T> tempStack) {
        while (!stack.isEmpty()) {
            while (!stack.isEmpty() && (tempStack.isEmpty() || stack.peek().compareTo(tempStack.peek()) >= 0))
                tempStack.push(stack.pop());
            if (!stack.isEmpty()) {
                T toInsert = stack.pop();
                while (!tempStack.isEmpty() && tempStack.peek().compareTo(toInsert) > 0)
                    stack.push(tempStack.pop());
                tempStack.push(toInsert);
            }
        }

        while (!tempStack.isEmpty())
            stack.push(tempStack.pop());
    }
}
