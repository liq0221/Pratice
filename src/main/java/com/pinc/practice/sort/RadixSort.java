package com.pinc.practice.sort;

public class RadixSort {

    public void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    // 计算出数组中位数最大的那个数，返回其位数
    private int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        while (max != 0) {
            max /= 10;
            res++;
        }
        return res;
    }

    // 进行排序
    private void radixSort(int[] arr, int L, int R, int maxBits) {
        int radix = 10;
        int[] help = new int[R - L + 1];
        int[] count = new int[radix];
        int i, j = 0;
        for (int d = 1; d <= maxBits; d++) {
            // 计算数组中每个数的每一位出现的次数
            for (i = L; i <= R; i++) {
                count[getDigit(arr[i], d)]++;
            }
            // 将本位出现的次数和前一位出现的次数相加
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 根据每一位出现的次数  倒序将数组中的值放入help数组中
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    private int getDigit(int i, int d) {
       return ((i / (int) Math.pow(10, d-1)) % 10);
    }
}
