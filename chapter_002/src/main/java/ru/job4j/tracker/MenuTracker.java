package ru.job4j.tracker;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = new AddAction();
        this.actions[1] = new ShowAllAction();
        this.actions[2] = new EditAction();
        this.actions[3] = new DeleteAction();
        this.actions[4] = new FindByIdAction();
        this.actions[5] = new FindByNameAction();
        this.actions[6] = new ExitAction();
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

    private class AddAction implements UserAction {
        @Override
        public int key() {
            return 0;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Добавить новую заявку.");
        }
    }

    private static class ShowAllAction implements UserAction {
        @Override
        public int key() {
            return 1;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Показать все имеющиеся заявки.");
        }
    }

    public class EditAction implements UserAction {
        @Override
        public int key() {
            return 2;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Редактировать заявку.");
        }
    }

    public class DeleteAction implements UserAction {
        @Override
        public int key() {
            return 3;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Удалить заявку.");
        }
    }

    public class FindByIdAction implements UserAction {
        @Override
        public int key() {
            return 4;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по ID.");
        }
    }

    public class FindByNameAction implements UserAction {
        @Override
        public int key() {
            return 5;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по имени.");
        }
    }

    public class ExitAction implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) { }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Выйти из программы.");
        }
    }

}
