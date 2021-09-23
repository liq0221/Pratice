package com.pinc.practice.sort;

public class Main {

    public static void main(String[] args) {
        int[] arr = {2, 5, 11, 8, 4, 9};
//        System.out.println("============选择排序============");
//        Sort s = new SelectorSort(arr);
//        s.sort();
//        s.output();
//        System.out.println("============冒泡排序============");
//        Sort s1 = new BubbleSort(arr);
//        s1.sort();
//        s1.output();
        System.out.println("============插入排序============");
        Sort s2 = new BubbleSort(arr);
        s2.sort();
        s2.output();
    }
}
