package ru.job4j.array;

/**
 * @author Igor Khmelevskiy (mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MatrixCheck {
    /**
     * Метод проверяет, чтобы все значения по диагоналям были true или false
     * @param data Входной массив
     * @return Результат
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i][i] == data[i + 1][i + 1] && data[i][data.length - 1] == data[i + 1][data.length - (2 + i)]) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }
}
