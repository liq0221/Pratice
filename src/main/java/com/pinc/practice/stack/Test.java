package com.pinc.practice.stack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private static int size;

    private static volatile int counter = 0;

    private static AtomicInteger t = new AtomicInteger();
    public static void main(String[] args) throws Exception{
//        List<Integer> integers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
////        integers.add(5);
//        System.out.println(size);
//        for (Integer integer : integers1) {
//            System.out.println(integer);
//        }
//        List<Integer> aa = new ArrayList<>(10);
//        aa.add(2, 1);
//        int hashCode = 0;
//        for (int i = 0; i < 16; i++) {
//            hashCode = i * 0x61c88647 + 0x61c88647;
//            int idx = hashCode & 15;
//            System.out.println("斐波那契散列：" + idx + " 普通散列：" + (String.valueOf(i).hashCode() & 15));
//        }

//        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
//        Field field = objectThreadLocal.getClass().getDeclaredField("threadLocalHashCode");
//        field.setAccessible(true);
//        System.out.println(field.get(objectThreadLocal));
//        for (int i = 0; i < 10; i++) {
//            Thread thread = new Thread(() -> {
//                for (int i1 = 0; i1 < 10000; i1++) {
//                    add();
//                }
//            });
//            thread.start();
//        }
//        // 等 10 个线程运行完毕
//        Thread.sleep(1000);
//        System.out.println(counter);
//
//        System.out.println(solution(new int[]{2,2,1,1,2,2}));

        System.out.println(add1());
        System.out.println(add1());
        System.out.println(add1());
    }
    public static  void add() {
        counter++;
    }

    public static int add1() {
        return t.getAndAdd(1);
    }


//    public static int[] t1(int[] a, int[] b) {
//        a.
//        return new
//    }

    public static int solution(int[] nums) {
        int cnt = 0, major = 0;
        for (int num : nums) {
            if (cnt == 0) {
                major = num;
                cnt = 1;
            } else {
                cnt += major == num ? 1 : -1;
            }
        }
        return major;
    }


}
