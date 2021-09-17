package com.pinc.practice.queue;

public class Main {

    public static void main(String[] args) {
        System.out.println("==========队列(数组实现)==========");
        MyQueue_Array myQueue_array = new MyQueue_Array(2);
        myQueue_array.push(1);
        System.out.println(myQueue_array.pop());
        myQueue_array.push(2);
        myQueue_array.push(3);
        System.out.println(myQueue_array.pop());
        System.out.println(myQueue_array.pop());
        System.out.println("==========队列(栈实现)==========");
        MyQueue_Stack myQueue_stack = new MyQueue_Stack();
        myQueue_stack.push(1);
        myQueue_stack.push(2);
        myQueue_stack.push(3);
        System.out.println(myQueue_stack.pop());
        System.out.println(myQueue_stack.pop());
        System.out.println(myQueue_stack.peek());
        myQueue_stack.push(4);
        System.out.println(myQueue_stack.pop());
        System.out.println(myQueue_stack.pop());

    }
}
