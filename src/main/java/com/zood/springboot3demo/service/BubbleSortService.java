package com.zood.springboot3demo.service;

import com.zood.springboot3demo.util.BubbleSort;
import org.springframework.stereotype.Service;

/**
 * 冒泡排序服务。
 */
@Service
public class BubbleSortService {

    /**
     * 对整数数组执行升序冒泡排序。
     *
     * @param numbers 待排序数组，可为 null 或空
     * @return 排序结果数组（不修改传入数组）
     */
    public int[] sort(int[] numbers) {
        return BubbleSort.sortCopy(numbers);
    }
}
