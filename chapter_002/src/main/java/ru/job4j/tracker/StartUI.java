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
    private final Input input;
    private final Tracker tracker;
    private boolean exit = false;

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
        menu.fillRange();
        menu.fillActions(this);
        if (!this.exit) {
            menu.show();
            menu.select(input.ask("Выберите:", menu.range));
        }
    }

    public void stop() {
        this.exit = true;
    }

    /**
     * Запуск программы.
     * @param args
     */

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}