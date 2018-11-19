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

    public void addAction(UserAction action) {
        this.actions[position++] = action;
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

    class AddAction extends BaseAction {
        public AddAction(int key, String name) {
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

    class ShowAllAction extends BaseAction {
        private ShowAllAction(int key, String name) {
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

    class EditAction extends BaseAction {
        public EditAction(int key, String name) {
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

    class DeleteAction extends BaseAction {
        public DeleteAction(int key, String name) {
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

    class FindByIdAction extends BaseAction {
        public FindByIdAction(int key, String name) {
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

    class FindByNameAction extends BaseAction {
        public FindByNameAction(int key, String name) {
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

    class ExitAction extends BaseAction {
        public ExitAction(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) { }
    }

}
