package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private String printMenu() {
        return (new StringBuilder()
                .append("0. Добавить новую заявку." + System.lineSeparator())
                .append("1. Показать все имеющиеся заявки." + System.lineSeparator())
                .append("2. Редактировать заявку." + System.lineSeparator())
                .append("3. Удалить заявку." + System.lineSeparator())
                .append("4. Найти заявку по ID." + System.lineSeparator())
                .append("5. Найти заявку по имени." + System.lineSeparator())
                .append("6. Выйти из программы." + System.lineSeparator()) + System.lineSeparator().toString());
    }

    @Test
    public void whenUserAddsItemTrackerGetsNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "test descrition", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenThackerHasUpdatedItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test description"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace name", "test replace description", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace name"));
        assertThat(tracker.findById(item.getId()).getDescription(), is("test replace description"));
    }

    @Test
    public void whenDeleteFirstItemSecondGoesToFirstPlaceAndSecondPlaceIsNull() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name 1", "test description 1"));
        Item item1 = tracker.add(new Item("test name 2", "test description 2"));
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name 2"));
        assertThat(tracker.findAll().length, is(1));
    }

    @Test
    public void whenIfShowAllThenPrintAll() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name 1", "test description 1"));
        Item item1 = tracker.add(new Item("test name 2", "test description 2"));
        Item item2 = tracker.add(new Item("test name 3", "test description 3"));
        System.setOut(new PrintStream(out));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is((printMenu()) + (new StringBuilder()
                .append("------------ Показать все заявки ------------").append(System.lineSeparator())
                .append("Заявка №1: Имя: " + item.getName() + ", Описание: " + item.getDescription() + ", ID: " + item.getId()).append(System.lineSeparator())
                .append("Заявка №2: Имя: " + item1.getName() + ", Описание: " + item1.getDescription() + ", ID: " + item1.getId()).append(System.lineSeparator())
                .append("Заявка №3: Имя: " + item2.getName() + ", Описание: " + item2.getDescription() + ", ID: " + item2.getId()).append(System.lineSeparator())
                .append("------------ Показаны все заявки ------------").append(System.lineSeparator()).append(System.lineSeparator())
                .toString())));
        System.setOut(stdout);
    }

    @Test
    public void whenEnterIdThenFindById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name 1", "test description 1"));
        Item item1 = tracker.add(new Item("test name 2", "test description 2"));
        Item item2 = tracker.add(new Item("test name 3", "test description 3"));
        System.setOut(new PrintStream(out));
        Input input = new StubInput(new String[]{"4", item1.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is((printMenu()) + (new StringBuilder()
                .append("------------- Поиск заявки по ID ---------------").append(System.lineSeparator())
                .append("Искомая заявка: ").append(System.lineSeparator())
                .append("Имя: " + item1.getName() + ", Описание: " + item1.getDescription() + ", ID: " + item1.getId()).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString()
        )));
        System.setOut(stdout);
    }

    @Test
    public void whenAnotherIdThenNotFound() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name 1", "test description 1"));
        Item item1 = tracker.add(new Item("test name 2", "test description 2"));
        Item item2 = tracker.add(new Item("test name 3", "test description 3"));
        System.setOut(new PrintStream(out));
        Input input = new StubInput(new String[]{"4", item1.getId() + 1, "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is((printMenu()) + (new StringBuilder()
                .append("------------- Поиск заявки по ID ---------------").append(System.lineSeparator())
                .append("---------- Заявка с таким ID не найдена --------").append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString()
        )));
        System.setOut(stdout);
    }

    @Test
    public void whenEnterNameThenFindByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name 1", "test description 1"));
        Item item1 = tracker.add(new Item("test name 2", "test description 2"));
        Item item2 = tracker.add(new Item("test name 2", "test description 3"));
        System.setOut(new PrintStream(out));
        Input input = new StubInput(new String[]{"5", item1.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is((printMenu()) + (new StringBuilder()
                .append("------------- Поиск заявки по имени -------------").append(System.lineSeparator())
                .append("Искомые заявки: ").append(System.lineSeparator())
                .append("Имя: " + item1.getName() + ", Описание: " + item1.getDescription() + ", ID: " + item1.getId()).append(System.lineSeparator())
                .append("Имя: " + item2.getName() + ", Описание: " + item2.getDescription() + ", ID: " + item2.getId()).append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString()
        )));
        System.setOut(stdout);
    }

    @Test
    public void whenAnotherNameThenNotFound() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name 1", "test description 1"));
        Item item1 = tracker.add(new Item("test name 2", "test description 2"));
        Item item2 = tracker.add(new Item("test name 3", "test description 3"));
        System.setOut(new PrintStream(out));
        Input input = new StubInput(new String[]{"5", item1.getName() + "a", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is((printMenu()) + (new StringBuilder()
                .append("------------- Поиск заявки по имени -------------").append(System.lineSeparator())
                .append("-------- Нет заявок с таким именем ---------").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString()
        )));
        System.setOut(stdout);
    }

}
