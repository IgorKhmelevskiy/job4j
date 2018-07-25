package ru.job4j.tracker;

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
    private static final String AddNewItem = "1";
    private static final String ShowAllItems = "2";
    private static final String EditItem = "3";
    private static final String DeleteItem = "4";
    private static final String FindItemById = "5";
    private static final String FindItemByName = "6";
    private static final String ExitProgram = "7";

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
            String answer = this.input.ask("Введите пункт меню: ");
            if (AddNewItem.equals(answer)) {
                this.addItem();
            } else if (ShowAllItems.equals(answer)) {
                this.showAll();
            } else if (EditItem.equals(answer)) {
                this.edit();
            } else if (DeleteItem.equals(answer)) {
                this.delete();
            } else if (FindItemById.equals(answer)) {
                this.findById();
            } else if (FindItemByName.equals(answer)) {
                this.findByName();
            } else if (ExitProgram.equals(answer)) {
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
            System.out.printf("Заявка №%d: Имя: " + items[index].name + ", Описание: " + items[index].description + ", Дата создания: " + items[index].create + ", ID: " + items[index].getId() + "\n", index+1);
        }
        System.out.println("-------- Показаны все заявки. -------");
        System.out.println();
    }

    /**
     * Метод редкатирует заявку.
     */
    private void edit() {
        System.out.println();
        System.out.println("------------ Редактирование заявки ------------");
        String id = this.input.ask("Введите ID редактируемой заявки: ");
        String name = this.input.ask("Введите новое имя заявки: ");
        String description = this.input.ask("Введите новое описание заявки: ");
        long create = System.currentTimeMillis();
        Item item = new Item(name, description, create);
        this.tracker.replace(id, item);
        System.out.println("---------- Заявка заменена ----------");
        System.out.println();
    }

    /**
     * Метод удаляет заявку.
     */

    private void delete() {
        System.out.println();
        System.out.println("--------- Удаление заявки ---------");
        String id = this.input.ask("Введите ID удаляемой заявки: ");
        this.tracker.delete(id);
        System.out.println("--------- Заявка удалена -----------");
        System.out.println();
    }

    /**
     * Метод находит заявку по ID.
     */
    private void findById() {
        System.out.println();
        System.out.println("---------- Поиск заявки по ID ----------");
        String id = this.input.ask("Введите ID искомой заявкки: ");
        Item item = this.tracker.findById(id);
        System.out.println("Имя: " + item.name + ", Описание: " + item.description + ", Дата создания: " + item.create + ", ID: " + item.getId());
        System.out.println();
    }

    /**
     * Метод находит заявку по имени.
     */
    private void findByName() {
        System.out.println();
        System.out.println("---------- Поиск заявки по имени ----------");
        String name = this.input.ask("Введите имя для поиска: ");
        Item item = this.tracker.findByName(name);
        System.out.println("Имя: " + item.name + ", Описание: " + item.description + ", Дата создания: " + item.create + ", ID: " + item.getId());
        System.out.println();
    }

    /**
     * Метод показывает меню.
     */
    private void showMenu() {
        System.out.println("*** Меню программы. ***");
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
