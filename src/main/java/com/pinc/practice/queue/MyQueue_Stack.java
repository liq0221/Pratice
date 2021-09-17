package com.pinc.practice.queue;

import java.util.Stack;

public class MyQueue_Stack {

    private Stack<Integer> pushStack;

    private Stack<Integer> popStack;

    public MyQueue_Stack() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    public void push(int num) {
        pushStack.push(num);
    }

    public int pop() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    public int peek() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.peek();
    }
}
