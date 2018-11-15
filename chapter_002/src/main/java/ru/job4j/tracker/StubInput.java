package ru.job4j.tracker;

public class StubInput implements Input {
    private final String[] value;
    private int position = 0;

    public StubInput(final String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    public int ask(String question, int[] range) {
        int menuPoint = Integer.valueOf(this.ask(question));
        boolean exists = false;
        for (int value : range) {
            if (value == menuPoint) {
                exists = true;
                break;
            }
        }
        if (exists) {
            return menuPoint;
        } else {
            throw new MenuOutException("Нет такого пункта в меню.");
        }
    }
}

