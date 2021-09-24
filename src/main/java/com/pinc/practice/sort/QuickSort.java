package com.pinc.practice.sort;

/**
 * 快速排序
 * 时间复杂度O(N*logN)
 * 满足递归程序计算时间复杂度的公式 logba == n^d
 */
public class QuickSort extends Sort{

    private int[] arr;

    public QuickSort(int[] arr) {
        super(arr);
        this.arr = arr;
    }

    @Override
    public void sort() {
       process(arr, 0, arr.length - 1);
    }

    // 每次都随机生成比较数 然后与R位置进行交换
    // [L...........R] 假设比较数为X
    // )L.......(X 初始位置
    // 递归每次找到等于X的范围  L....)XXX(....R
    // X左侧都是小于X的数 右侧都是大于X的
    // 最后递归结束快速排序完成
    private void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
        int[] lessAndMore = netherlandsFlag(arr, L, R);
        process(arr, L, lessAndMore[0] - 1);
        process(arr, lessAndMore[1] + 1, R);
    }


    private int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l == r) {
            return new int[]{l, l};
        }
        if (l > r) {
            return new int[]{-1, -1};
        }
        int less = l - 1;
        int more = r;
        int res = l;
        while (res < more) {
            if (arr[res] < arr[r]) {
                swap(arr, res++, ++less);
            }else if (arr[res] == arr[r]) {
                res++;
            }else {
                swap(arr, res, --more);
            }
        }
        swap(arr, r, more);
        return new int[]{less + 1, more};
    }
}
