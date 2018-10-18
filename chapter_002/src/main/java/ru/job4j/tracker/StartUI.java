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
    /**
     * Константы для:
     * 1 - Добавить заявку
     * 2 - Показать все заявки
     * 3 - Редактировать заявку
     * 4 - Удалить заявку
     * 5 - Найти заявку по ID
     * 6 - Найти заявку по имени
     * 7 - Выйти из программы
     */
    private static final String ADDNEWITEM = "1";
    private static final String SHOWALLITEMS = "2";
    private static final String EDITITEM = "3";
    private static final String DELETEITEM = "4";
    private static final String FINDITEMBYID = "5";
    private static final String FINDITEMBYNAME = "6";
    private static final String EXITPROGRAM = "7";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
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

    /**
     * Основной цикл программы.
     */
    public void init() {
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            int key = Integer.valueOf(input.ask("Выберите: "));
            menu.select(key);
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }


    /**
     * Запуск программы.
     * @param args
     */

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}