package ru.job4j.tracker;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

/**
 * @author Igor Khmelevskiy (mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class StartUI {
    private int[] range = new int[] {0, 1, 2, 3, 4, 5, 6};
    private final Input input;
    private final Tracker tracker;

    /**
     * Конструктор, инициализирующий поля.
     * @param input Ввод данных.
     * @param tracker Хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Выберите:", range));
        } while (!"y".equals(this.input.ask("Выйти?(y): ")));
    }


    /**
     * Запуск программы.
     * @param args
     */

    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}