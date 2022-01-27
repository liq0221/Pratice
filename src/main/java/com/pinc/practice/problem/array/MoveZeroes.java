package com.pinc.practice.problem.array;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
public class MoveZeroes {

    public static void main(String[] args) {

    }

    public static void solution(int[] nums) {
        int left = 0, n = nums.length;
        for (int right = 0; right < n; ++right) {
            if (nums[right] != 0) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                ++left;
            }
        }
    }
}
