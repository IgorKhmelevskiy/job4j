package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class MaxTest {

    @Test
    public void whenFirstLessThenSecond() {
        Max maximum = new Max();
        int result = maximum.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenThree() {
        Max maximum = new Max();
        int result = maximum.max(1, 7, 4);
        assertThat(result, is(7));
    }
}