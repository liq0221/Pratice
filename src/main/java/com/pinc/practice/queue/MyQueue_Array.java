package com.pinc.practice.queue;

public class MyQueue_Array {

    private int[] arr;

    private int pushStep;

    private int popStep;

    private int size;

    private final int limit;

    public MyQueue_Array(int limit) {
        this.arr = new int[limit];
        this.pushStep = 0;
        this.popStep = 0;
        this.size = 0;
        this.limit = limit;
    }

    public void push(int num) {
        if (size == limit) {
            throw new RuntimeException("队列已满...");
        }
        arr[pushStep] = num;
        pushStep = nextIndex(pushStep);
        size++;
    }

    private int nextIndex(int i) {
        return i < limit - 1 ? i + 1 : 0;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("队列已空...");
        }
        int result = arr[popStep];
        popStep = nextIndex(popStep);
        size--;
        return result;
    }

    public int peek() {
        if (size == 0) {
            throw new RuntimeException("队列已空...");
        }
        return arr[popStep];
    }
}
