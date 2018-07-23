package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixCheckTest {

    @Test
    public void whenDataMonoByTrueThenTrueOdd() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {{true, true, true}, {false, true, true}, {true, false, true}};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataMonoByTrueAndFalseThenFalseOdd() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][]{{true, true, true}, {false, false, true}, {true, false, true}};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }

    @Test
    public void whenDataMonoByTrueThenTrueEven() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][]{{true, false}, {false, true}};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataMonoByTrueAndFalseThenFalseEven() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][]{{true, true}, {false, false}};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
}