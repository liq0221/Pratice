package com.pinc.practice.stack;

import java.util.Stack;

/**
 * 可以得到当前栈中的最小值
 */
public class SpecialStack {

    private Stack<Integer> dataStack;

    private Stack<Integer> popStack;

    public SpecialStack() {
        dataStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void push(int num) {
        if (popStack.isEmpty()) {
            popStack.push(num);
        }
        if (popStack.peek() < num) {
            popStack.push(popStack.peek());
        } else {
            popStack.push(num);
        }
        dataStack.push(num);
    }

    public int pop() {
        popStack.pop();
        return dataStack.pop();
    }

    public int getMin() {
        if (popStack.isEmpty()) {
            throw new RuntimeException("栈已空...");
        }
        return popStack.peek();
    }
}
