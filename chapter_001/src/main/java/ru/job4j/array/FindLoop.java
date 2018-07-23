package ru.job4j.array;

/**
 * @author Igor Khmelevskiy (mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FindLoop {
    /**
     * Метод поиска элемента в массиве
     * @param data массив чисел
     * @param el искомый элемент
     * @return результат поиска
     */
    public int indexOf(int[] data, int el) {
        int result = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                result = index;
                break;
            }
        }
        return result;
    }
}
