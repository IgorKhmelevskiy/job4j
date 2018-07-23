package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BubbleSortTest {

    @Test
    public void sort() {
        BubbleSort array = new BubbleSort();
        int[] input = new int[] {5, 1, 2, 7, 3};
        int[] result = array.sort(input);
        int[] expected = {1, 2, 3, 5, 7};
        assertThat(result, is(expected));
    }
}