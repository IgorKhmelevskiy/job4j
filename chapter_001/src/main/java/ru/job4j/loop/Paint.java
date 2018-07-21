package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * @author Igor Khmelevskiy (mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * Метод прорисовки правой стороны пирамиды
     * @param height высота пирамиды
     * @return правая сторона
     */
    public String rightTrl(int height) {
        return this.loopBy(height, height, (row, column) -> row >= column);

        /*
        StringBuilder screen = new StringBuilder();
        int width = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
        */
    }

    /**
     * Метод прорисовки левой стороны пирамиды
     * @param height высота пирамиды
     * @return левая сторона
     */
    public String leftTrl(int height) {
        return this.loopBy(height, height, (row, column) -> row >= height - column - 1);

        /*
        StringBuilder screen = new StringBuilder();
        int width = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= width - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
        */
    }

    /**
     * Метод прорисовки всей пирамиды
     * @param height высота пирамиды
     * @return пирамида в инфографике
     */
    public String pyramid(int height) {
        return this.loopBy(height, 2 * height - 1, (row, column) -> row >= height - column - 1 && row + height - 1 >= column);

        /*
        StringBuilder screen = new StringBuilder();
        int width = height * 2 - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
        */
    }

    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != width; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
