package com.pinc.practice.eor;

public class ZCYTest {

    /**
     * 数组中仅有一个奇数次数的数，其余都是偶数，找到这个奇数次数的数
     * e.g:{1,1,1,2,2,2,2,3,3,3,3,4,4,4,4} 找出数字1
     * @param arr
     */
    public void generateNumber1(int[] arr) {
        int eor = 0;
        // 偶数次数相同的数异或的最后结果是0
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }System.out.println("这个奇数次数的数为：" + eor);
    }

    /**
     * ☆☆☆☆☆
     * 找到一个整数二进制最右侧为1的数
     * @param num
     */
    public void generateRightNum(int num) {
        // num = 000001100100
        // ~num = 111110011011
        // (111110011011
        //  000000000001)
        //  111110011100 [(~num) + 1]的结果
        // 进行与运算
        // 000001100100
        // 111110011100
        // 000000000100
        int result = num & ((~num) + 1);
        System.out.println(num + "二进制最右侧为1的数为：" + result);
    }

    /**
     * 数组中有两个奇数次数的数，其余都是偶数，找到这两个奇数次数的数
     * e.g:{1,1,1,2,2,2,3,3,3,3,4,4,4,4} 找出数字 1 和 2
     * @param arr
     */
    public void generateNumber2(int[] arr) {
        int eor = 0;
        // 0.如果一组数有两个奇数次数的数(a,b) 则 a^b <> 0,
        // 1.整个数组进行异或 得出结果eor = a ^ b
        // 2.所以可以将这组数分为最右侧为1和0的两组数 (A组和B组)
        // 3.将A组的数进行异或运算 可以得到a或b(_eor)
        // 4.再将得到的a或b与整个数组进行异或运算 得出另一个数
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        int onlyRight = eor & ((~eor) + 1);
        int _eor = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & onlyRight) != 0) {
                _eor ^= arr[i];
            }
        }
        System.out.println("一个奇数次数的数为：" + _eor + " " + "另一个奇数次数的数为：" + (eor ^ _eor));
    }
}
