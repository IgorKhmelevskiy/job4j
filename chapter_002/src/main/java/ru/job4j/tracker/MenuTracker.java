package ru.job4j.tracker;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];
    private int position = 0;

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[position++] = new AddAction(0, "Добавить новую заявку.");
        this.actions[position++] = new ShowAllAction(1, "Показать все имеющиеся заявки.");
        this.actions[position++] = new EditAction(2, "Редактировать заявку.");
        this.actions[position++] = new DeleteAction(3, "Удалить заявку.");
        this.actions[position++] = new FindByIdAction(4, "Найти заявку по ID.");
        this.actions[position++] = new FindByNameAction(5, "Найти заявку по имени.");
        this.actions[position++] = new ExitAction(6, "Выйти из программы.");
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    public class AddAction extends BaseAction {
        AddAction(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println();
            System.out.println("------------ Добавление новой заявки ------------");
            String name = input.ask("Введите имя заявки: ");
            String description = input.ask("Введите описание заявки: ");
            Item item = new Item(name, description);
            tracker.add(item);
            System.out.println("----- Новая заявка (ID: " + item.getId() + ") добавлена. -----");
            System.out.println();
        }
    }

    private class ShowAllAction extends BaseAction {
        ShowAllAction(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println();
            System.out.println("------------ Показать все заявки ------------");
            Item[] items = tracker.findAll();
            for (int index = 0; index < items.length; index++) {
                System.out.printf("Заявка №%d: Имя: " + items[index].name + ", Описание: " + items[index].description + ", ID: " + items[index].getId() + System.lineSeparator(), index + 1);
            }
            System.out.println("------------ Показаны все заявки ------------");
            System.out.println();
        }
    }

    public class EditAction extends BaseAction {
        EditAction(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println();
            System.out.println("------------ Редактирование заявки ------------");
            String id = input.ask("Введите ID редактируемой заявки: ");
            String name = input.ask("Введите новое имя заявки: ");
            String description = input.ask("Введите новое описание заявки: ");
            Item item = new Item(name, description);
            if (tracker.replace(id, item)) {
                System.out.println("--------------- Заявка заменена ---------------");
            } else {
                System.out.println("--------- Заявка с таким ID не найдена --------");
            }
            System.out.println();
        }
    }

    public class DeleteAction extends BaseAction {
        DeleteAction(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println();
            System.out.println("--------------- Удаление заявки ----------------");
            String id = input.ask("Введите ID удаляемой заявки: ");
            if (tracker.delete(id)) {
                System.out.println("---------------- Заявка удалена ----------------");
            } else {
                System.out.println("---------- Заявка с таким ID не найдена --------");
            }
            System.out.println();
        }
    }

    public class FindByIdAction extends BaseAction {
        FindByIdAction(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println();
            System.out.println("------------- Поиск заявки по ID ---------------");
            String id = input.ask("Введите ID искомой заявки: ");
            Item item = tracker.findById(id);
            if (item.getName() != null) {
                System.out.println("Искомая заявка: ");
                item.out();
                System.out.println();
            } else {
                System.out.println("---------- Заявка с таким ID не найдена --------");
            }
            System.out.println();
        }
    }

    public class FindByNameAction extends BaseAction {
        FindByNameAction(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println();
            System.out.println("------------- Поиск заявки по имени -------------");
            String name = input.ask("Введите имя для поиска: ");
            Item[] item = tracker.findByName(name);
            if (item.length > 0) {
                System.out.println("Искомые заявки: ");
                for (int i = 0; i < item.length; i++) {
                    item[i].out();
                }
            } else {
                System.out.println("-------- Нет заявок с таким именем ---------");
            }
            System.out.println();
            System.out.println();
        }
    }

    public class ExitAction extends BaseAction {
        ExitAction(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) { }
    }

}
