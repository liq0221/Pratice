package com.pinc.practice.sort;

/**
 * 冒泡排序
 * 时间复杂度O(N²)
 */
public class BubbleSort extends Sort{

    private int[] arr;

    public BubbleSort(int[] arr) {
        super(arr);
        this.arr = arr;
    }

    @Override
    public void sort() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }
}
