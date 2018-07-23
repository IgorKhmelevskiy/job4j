package ru.job4j.array;

/**
 * @author Igor Khmelevskiy (mailto:igoekkhm@gmail.com)
 * @version $Id#
 * @since 0.1
 */
public class Square {
    /**
     * Метод возводит в квадрат элементы массива
     * @param bound Последовательный набор элементов от 1 до bound
     * @return Массив квадратов
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int i = 0; i < bound; i++) {
            result[i] = (i + 1) * (i + 1);
        }
        return result;
    }
}
