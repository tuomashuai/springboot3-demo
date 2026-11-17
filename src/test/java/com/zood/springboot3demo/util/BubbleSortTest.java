package com.zood.springboot3demo.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class BubbleSortTest {

    @Test
    void sortsRandomArrayInAscendingOrder() {
        int[] input = {5, 1, 4, 2, 8, 0, 3};
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 8}, BubbleSort.sort(input));
    }

    @Test
    void handlesEmptyAndSingleElement() {
        assertArrayEquals(new int[]{}, BubbleSort.sort(new int[0]));
        assertArrayEquals(new int[]{7}, BubbleSort.sort(new int[]{7}));
    }

    @Test
    void handlesNull() {
        assertNull(BubbleSort.sort(null));
        assertNull(BubbleSort.sortCopy(null));
    }

    @Test
    void keepsDuplicatesAndNegatives() {
        assertArrayEquals(new int[]{-3, -1, 0, 0, 2, 2, 9}, BubbleSort.sort(new int[]{2, 0, -1, 9, 2, 0, -3}));
    }

    @Test
    void sortCopyDoesNotMutateInput() {
        int[] input = {3, 2, 1};
        int[] copy = BubbleSort.sortCopy(input);
        assertArrayEquals(new int[]{1, 2, 3}, copy);
        assertArrayEquals(new int[]{3, 2, 1}, input);
    }

    @Test
    void sortReturnsSameReference() {
        int[] input = {3, 1, 2};
        assertSame(input, BubbleSort.sort(input));
    }

    @Test
    void matchesArraysSort() {
        int[] input = {9, 4, 7, 1, -2, 0, 4, 7};
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        assertArrayEquals(expected, BubbleSort.sort(input));
    }
}
