package com.list.config.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class ConfigserverApplicationTests extends AbstractClass {
//    private final String a = "123";

    @Test
    void contextLoads() {
        double a = 2.3645;
        double b = 2.68655;
        System.out.println(a + b);
        System.out.println(0.05 + 0.01);
        System.out.println(1.0 - 0.42);
        System.out.println(4.015 * 100);
        System.out.println(123.3 / 100);

    }

    @Test
    void test2() {
        int[] aaa = new int[]{1, 3, 6, 8, 4, 8, 9};
        System.out.println(Arrays.toString(aaa));
        maopao(aaa);
        System.out.println(Arrays.toString(aaa));
    }

    @Test
    void test3kuaisu() {
        int[] aaa = new int[]{1, 3, 6, 8, 4, 8, 9};
        System.out.println(Arrays.toString(aaa));
        kuaisu(0, aaa.length - 1, aaa);
        System.out.println(Arrays.toString(aaa));
    }

    @Test
    void testjiandanxuanze() {
//        int[] aaa = new int[]{1, 3, 6, 8, 4, 8, 9};
        int[] aaa = new int[]{1, 1, 1, 2};
        System.out.println(Arrays.toString(aaa));
        jiandanxuanze(aaa);
        System.out.println(Arrays.toString(aaa));
    }

    @Test
    void testdui() {
        int[] aaa = new int[]{1, 3, 6, 8, 4, 8, 9};
        System.out.println(Arrays.toString(aaa));
        for (int i = ((aaa.length - 1) - 1) / 2, j = 0; i >= 0; i--, j++) {
            dui(aaa, i, aaa.length - j);
        }
        System.out.println(Arrays.toString(aaa));
    }

    public void dui(int[] arr, int parentNode, int arrLength) {
        int temp;
        int maxNode = parentNode;
        while (maxNode < arrLength) {
            int left = maxNode * 2 + 1;
            int right = maxNode * 2 + 2;
            if (left < arrLength && arr[maxNode] < arr[left])
                maxNode = left;
            if (right < arrLength && arr[maxNode] < arr[right])
                maxNode = right;
            if (maxNode != parentNode) {
                temp = arr[parentNode];
                arr[parentNode] = arr[maxNode];
                arr[maxNode] = temp;
            }
        }
    }

    public void jiandanxuanze(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public void kuaisu(int start, int end, int[] arr) {
        if (start < end) {
            int l_point = start;
            int r_point = end;
            int BASE = arr[l_point];
            while (l_point < r_point) {
                while (l_point < r_point && arr[r_point] >= BASE)
                    r_point--;
                if (l_point < r_point)
                    arr[l_point++] = arr[r_point];
                while (l_point < r_point && arr[l_point] < BASE)
                    l_point++;
                if (l_point < r_point)
                    arr[r_point--] = arr[l_point];
            }
            arr[l_point] = BASE;
            kuaisu(start, l_point - 1, arr);
            kuaisu(l_point + 1, end, arr);
        }
    }

    public void maopao(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean hasChange = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    hasChange = true;
                }
            }
            if (!hasChange) break;
        }

    }


}
