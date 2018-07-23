package ru.job4j.array;

/**
 * @author Igor Khmelevskiy (mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест-методы возведения в квадрат массива
 */
public class SquareTest {

    @Test
    public void when10then1to100() {
        Square array = new Square();
        int[] result = array.calculate(10);
        int[] expected = new int[] {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
        assertThat(result, is(expected));
    }

    @Test
    public void when5then1to25() {
        Square array = new Square();
        int[] result = array.calculate(5);
        int[] expected = new int[] {1, 4, 9, 16, 25};
        assertThat(result, is(expected));
    }
}