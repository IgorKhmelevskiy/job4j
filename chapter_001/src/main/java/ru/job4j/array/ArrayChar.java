package ru.job4j.array;

/**
 * Обертка над строкой
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Метод проверяет, что слово начинается с префикса
     * @param prefix префикс
     * @return результат
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int i = 0; i < value.length; i++) {
            if (value[i] == data[i]) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }
}
