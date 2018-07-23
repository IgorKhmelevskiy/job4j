package ru.job4j.array;

/**
 * @author Igor Khmelevskiy(mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TurnTest {

    @Test
    public void turn() {
        Turn array = new Turn();
        int[] input = new int[] {1, 2, 3, 4, 5};
        int[] resultArray = array.turn(input);
        int[] expectedArray = {5, 4, 3, 2, 1};
        assertThat(resultArray, is(expectedArray));
    }

    @Test
    public void oddTurn() {
        Turn array = new Turn();
        int[] input = new int[] {2, 6, 1, 4};
        int[] resultArray = array.turn(input);
        int[] expectedArray = {4, 1, 6, 2};
        assertThat(resultArray, is(expectedArray));
    }
}