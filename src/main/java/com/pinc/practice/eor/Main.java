package com.pinc.practice.eor;

public class Main {

    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 2, 4, 4, 4, 3, 3, 3, 3, 1, 1, 1, 1, 5, 5, 5, 5};
        int[] arr1 = {2, 2, 2, 2, 4, 4, 4, 3, 3, 3, 3, 1, 1, 1, 5, 5, 5, 5};
        ZCYTest zcyTest = new ZCYTest();
        zcyTest.generateNumber1(arr);
        zcyTest.generateNumber2(arr1);
        zcyTest.generateRightNum(100);
    }
}
