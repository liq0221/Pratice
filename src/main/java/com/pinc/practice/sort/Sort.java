package com.pinc.practice.sort;

public abstract class Sort {

    private int[] arr;

    public Sort(int[] arr) {
        this.arr = arr;
    }

    abstract void sort();

    public void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void output() {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

}
