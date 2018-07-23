package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Igor Khmelevskiy (igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicateTest {

    @Test
    public void whenRemoveDuplicatesTheArrayWithoutDiplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] startArray = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] resultArray = arrayDuplicate.remove(startArray);
        String[] expectedArray = {"Привет", "Мир", "Супер"};
        assertThat(resultArray, is(expectedArray));
    }
}