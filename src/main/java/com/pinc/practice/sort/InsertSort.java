package com.pinc.practice.sort;

/**
 * 插入排序
 * 时间复杂度O(N²)
 */
public class InsertSort extends Sort{

    private int[] arr;

    public InsertSort(int[] arr) {
        super(arr);
        this.arr = arr;
    }

    @Override
    public void sort() {
        int insertValue;
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            int j = i - 1;
            for (; (j > 0 && arr[j] > insertValue); j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = insertValue;
        }
    }
}
