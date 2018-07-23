package ru.job4j.array;

/**
 * @author Igor Khmelevskiy(mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Check {
    /**
     * Метод проверяет, заполнен ли массив элементами true или false
     * @param data Входящий массив
     * @return Результат
     */
    public boolean mono(boolean[] data) {
        boolean result = false;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] == data[i + 1]) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }
}
