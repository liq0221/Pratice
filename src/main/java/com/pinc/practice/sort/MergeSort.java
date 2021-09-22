package com.pinc.practice.sort;


public class MergeSort{

    public static void process(int[] arr, int L, int R) {
            if (L == R) {
                return;
            }
            int mid = L + ((R - L) >> 1);
            process(arr, L, mid);
            process(arr, mid + 1, R);
            merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int index = 0;

        while (p1 <= mid && p2 <= r) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= r) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 11, 8, 4, 9};
        process(arr, 0, arr.length - 1);
        for (int val : arr) {
            System.out.println(val);
        }
    }
}
