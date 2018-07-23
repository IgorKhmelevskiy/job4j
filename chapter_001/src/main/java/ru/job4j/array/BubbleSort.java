package ru.job4j.array;

/**
 * Igor Khmelevskiy (igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {

    /**
     * Метод сортировки массива пузырьком (перестановкой).
     * @param array Исходный массив.
     * @return Сортированный массив.
     */
    public int[] sort(int[] array) {
        int temp;
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }
}
