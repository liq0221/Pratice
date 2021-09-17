package com.pinc.practice.stack;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        SpecialStack stack = new SpecialStack();
        stack.push(5);
        stack.push(2);
        stack.push(3);
        stack.push(1);
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
    }
}
