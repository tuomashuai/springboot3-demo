package com.zood.springboot3demo.util;

import java.util.Arrays;

/**
 * 冒泡排序工具类。
 *
 * <p>核心思路：重复比较相邻元素，若顺序不符则交换，每轮把未排序区间中最大的元素“冒泡”到末尾。
 * 通过记录每轮是否发生交换来提前终止（最好情况时间复杂度 O(n)）。</p>
 */
public final class BubbleSort {

    private BubbleSort() {
    }

    /**
     * 升序冒泡排序（原地排序，会修改传入的数组）。
     *
     * @param array 待排序数组
     * @return 同一个数组引用，已按升序排列
     */
    public static int[] sort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            // 末尾已有 i 个元素就位，无需再比较
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break; // 本轮无交换，说明已经有序
            }
        }
        return array;
    }

    /**
     * 升序冒泡排序（不修改原数组，返回新数组）。
     *
     * @param array 待排序数组
     * @return 排序后的新数组
     */
    public static int[] sortCopy(int[] array) {
        if (array == null) {
            return null;
        }
        return sort(Arrays.copyOf(array, array.length));
    }
}
