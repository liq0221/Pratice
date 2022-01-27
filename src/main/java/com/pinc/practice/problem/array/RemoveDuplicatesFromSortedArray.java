package com.pinc.practice.problem.array;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {

    }

    public static int solution(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 1 || nums[i - 1] != num) {
                nums[i++] = num;
            }
        }
        return i;
    }
}
