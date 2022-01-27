package com.pinc.practice.sort;

/**
 * 计数排序
 */
public class CountSort extends Sort {

    private int[] arr;

    public CountSort(int[] arr) {
        super(arr);
        this.arr = arr;
    }

    @Override
    public void sort() {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }
}

