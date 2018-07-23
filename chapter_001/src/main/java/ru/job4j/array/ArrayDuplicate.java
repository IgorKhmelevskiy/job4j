package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Igor Khmelevskiy (igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicate {

    /**
     * Метод удаляет дубликаты из массива строк
     * @param array
     * @return
     */
    public String[] remove(String[] array) {
        int uni = array.length;
        for (int step = 0; step < uni; step++) {
            for (int check = step + 1; check < uni; check++) {
                if (array[step].equals(array[check])) {
                    array[check] = array[uni - 1];
                    uni--;
                    check--;
                }
            }
        }
        return Arrays.copyOf(array, uni);
    }
}
