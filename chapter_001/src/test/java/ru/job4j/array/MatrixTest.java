package ru.job4j.array;

/**
 * @author Igor Khmelevskiy (mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void multiple() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(2);
        int[][] expected = {{1, 2}, {2, 4}};
        assertThat(table, is(expected));
    }
}