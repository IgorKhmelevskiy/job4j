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

    private String pattern = "dd MMMMM yyyy - HH:mm:ss";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("ru", "RU"));

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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            System.out.println();
            String answer = this.input.ask("--->> Выберите пункт меню: ");
            if (ADDNEWITEM.equals(answer)) {
                this.addItem();
            } else if (SHOWALLITEMS.equals(answer)) {
                this.showAll();
            } else if (EDITITEM.equals(answer)) {
                this.edit();
            } else if (DELETEITEM.equals(answer)) {
                this.delete();
            } else if (FINDITEMBYID.equals(answer)) {
                this.findById();
            } else if (FINDITEMBYNAME.equals(answer)) {
                this.findByName();
            } else if (EXITPROGRAM.equals(answer)) {
                exit = true;
            }
        }
        System.out.println();
    }

    /**
     * Метод добавляет новую заявку в хранилище.
     */
    private void addItem() {
        System.out.println();
        System.out.println("------------ Добавление новой заявки ------------");
        String name = this.input.ask("Введите имя заявки: ");
        String description = this.input.ask("Введите описание заявки: ");
        long create = System.currentTimeMillis();
        Item item = new Item(name, description, create);
        this.tracker.add(item);
        System.out.println("----- Новая заявка (ID: " + item.getId() + ") добавлена. -----");
        System.out.println();
    }

    /**
     * Метод показывает все заявки в хранилище.
     */
    private void showAll() {
        System.out.println();
        System.out.println("------------ Показать все заявки ------------");
        Item[] items = this.tracker.findAll();
        for (int index = 0; index < items.length; index++) {
            String date = simpleDateFormat.format(new Date(items[index].create));
            System.out.printf("Заявка №%d: Имя: " + items[index].name + ", Описание: " + items[index].description + ", Дата создания: " + date + ", ID: " + items[index].getId() + "\n", index + 1);
        }
        System.out.println("------------ Показаны все заявки ------------");
        System.out.println();
    }

    /**
     * Метод редактирует заявку.
     */
    private void edit() {
        System.out.println();
        System.out.println("------------ Редактирование заявки ------------");
        String id = this.input.ask("Введите ID редактируемой заявки: ");
        String name = this.input.ask("Введите новое имя заявки: ");
        String description = this.input.ask("Введите новое описание заявки: ");
        long create = System.currentTimeMillis();
        Item item = new Item(name, description, create);
        if (this.tracker.replace(id, item)) {
            System.out.println("--------------- Заявка заменена ---------------");
        } else {
            System.out.println("--------- Заявка с таким ID не найдена --------");
        }
        System.out.println();
    }

    /**
     * Метод удаляет заявку.
     */
    private void delete() {
        System.out.println();
        System.out.println("--------------- Удаление заявки ----------------");
        String id = this.input.ask("Введите ID удаляемой заявки: ");
        if (this.tracker.delete(id)) {
            System.out.println("---------------- Заявка удалена ----------------");
        } else {
            System.out.println("---------- Заявка с таким ID не найдена --------");
        }
        System.out.println();
    }

    /**
     * Метод находит заявку по ID.
     */
    private void findById() {
        System.out.println();
        System.out.println("------------- Поиск заявки по ID ---------------");
        String id = this.input.ask("Введите ID искомой заявкки: ");
        boolean check = false;
        Item item = this.tracker.findById(id);
        if (item.getName() != null) {
                check = true;
        }
        if (check) {
            String date = simpleDateFormat.format(new Date(item.create));
            System.out.println("Искомая заявка: ");
            System.out.println("Имя: " + item.name + ", Описание: " + item.description + ", Дата создания: " + date + ", ID: " + item.getId());
            System.out.println();
        } else {
            System.out.println("---------- Заявка с таким ID не найдена --------");
        }
        System.out.println();
    }

    /**
     * Метод находит заявку по имени.
     */

    private void findByName() {
        System.out.println();
        System.out.println("------------- Поиск заявки по имени -------------");
        String name = this.input.ask("Введите имя для поиска: ");
        boolean check = false;
        Item[] item = this.tracker.findByName(name);
        for (int j = 0; j < item.length; j++) {
            if (item[j].getName().equals(name)) {
                check = true;
                break;
            }
        }
        if (check) {
            System.out.println("Искомые заявки: ");
            for (int i = 0; i < item.length; i++) {
                String date = simpleDateFormat.format(new Date(item[i].create));
                System.out.println("Имя: " + item[i].name + ", Описание: " + item[i].description + ", Дата создания: " + date + ", ID: " + item[i].getId());
            }
        } else {
            System.out.println("-------- Нет заявок с таким именем ---------");
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Метод показывает меню.
     */
    private void showMenu() {
        System.out.println("***** Меню программы *****");
        System.out.println("1. Добавить новую заявку.");
        System.out.println("2. Показать все имеющиеся заявки.");
        System.out.println("3. Редактировать заявку.");
        System.out.println("4. Удалить заявку.");
        System.out.println("5. Найти заявку по ID.");
        System.out.println("6. Найти заявку по имени.");
        System.out.println("7. Выйти из программы.");
    }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
