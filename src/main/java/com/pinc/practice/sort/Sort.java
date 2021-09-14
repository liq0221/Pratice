package com.pinc.practice.sort;

public abstract class Sort {

    private int[] arr;

    public Sort(int[] arr) {
        this.arr = arr;
    }

    abstract void sort();

    public void swap(int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
        // 例：arr[i] = 甲 arr[j] = 乙
        // arr[i] = 甲 ^ 乙
        // arr[j] = 甲 ^ 乙 ^ 乙 = 甲
        // arr[i] = 甲 ^ 乙 ^ 甲 = 乙
        // ^(异或)：同位相加不进位
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public void output() {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

}
