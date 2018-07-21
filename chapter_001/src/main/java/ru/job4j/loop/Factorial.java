package ru.job4j.loop;

/**
 * @author Igor Khmelevskiy (mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {

    /**
     * Метод подсчета факториала
     * @param n До какого числа считать факториал
     * @return Факториал
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result = result * (i + 1);
        }
        return result;
    }
}
