package ru.job4j.array;

/**
 * @author Igor Khmelevskiy(mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Turn {
    /**
     * Метод переворачивает массив
     * @param array Исходный массив
     * @return Перевернутый массив
     */
    public int[] turn(int[] array) {
        for (int i = 0; i < array.length - i; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}
