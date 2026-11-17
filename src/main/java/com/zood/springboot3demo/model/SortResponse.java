package com.zood.springboot3demo.model;

import java.util.List;

/**
 * 冒泡排序响应体。
 *
 * @param input  排序前的原始数据
 * @param result 排序后的结果
 */
public record SortResponse(List<Integer> input, List<Integer> result) {

    public SortResponse {
        input = input == null ? List.of() : List.copyOf(input);
        result = result == null ? List.of() : List.copyOf(result);
    }
}
