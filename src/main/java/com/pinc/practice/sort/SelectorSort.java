package com.pinc.practice.sort;

/**
 * 选择排序
 * 时间复杂度O(N²)
 */
public class SelectorSort extends Sort{

    private int[] arr;

    public SelectorSort(int[] arr) {
        super(arr);
        this.arr = arr;
    }

    @Override
    public void sort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(i , j);
                }
            }
        }
    }
}
