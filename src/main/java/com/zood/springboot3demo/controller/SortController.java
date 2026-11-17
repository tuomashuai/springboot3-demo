package com.zood.springboot3demo.controller;

import com.zood.springboot3demo.model.SortResponse;
import com.zood.springboot3demo.service.BubbleSortService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 冒泡排序接口。
 */
@RestController
@RequestMapping("/api/sort")
public class SortController {

    private final BubbleSortService bubbleSortService;

    public SortController(BubbleSortService bubbleSortService) {
        this.bubbleSortService = bubbleSortService;
    }

    /**
     * 对 JSON 数组执行冒泡排序，例如：POST /api/sort/bubble body: [3,1,2]
     */
    @PostMapping("/bubble")
    public SortResponse bubble(@RequestBody List<Integer> numbers) {
        int[] input = numbers == null ? new int[0] : numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sorted = bubbleSortService.sort(input);
        return new SortResponse(numbers, toList(sorted));
    }

    /**
     * 对查询参数执行冒泡排序，例如：GET /api/sort/bubble?numbers=3,1,2
     */
    @GetMapping("/bubble")
    public SortResponse bubbleByQuery(@RequestParam(required = false) List<Integer> numbers) {
        int[] input = numbers == null ? new int[0] : numbers.stream().mapToInt(Integer::intValue).toArray();
        int[] sorted = bubbleSortService.sort(input);
        return new SortResponse(numbers, toList(sorted));
    }

    private static List<Integer> toList(int[] array) {
        Integer[] boxed = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            boxed[i] = array[i];
        }
        return List.of(boxed);
    }
}
