package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StartUITest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private String printMenu() {
        return (new StringBuilder()
                .append("***** Меню программы *****" + System.lineSeparator())
                .append("1. Добавить новую заявку." + System.lineSeparator())
                .append("2. Показать все имеющиеся заявки." + System.lineSeparator())
                .append("3. Редактировать заявку." + System.lineSeparator())
                .append("4. Удалить заявку." + System.lineSeparator())
                .append("5. Найти заявку по ID." + System.lineSeparator())
                .append("6. Найти заявку по имени." + System.lineSeparator())
                .append("7. Выйти из программы." + System.lineSeparator() + System.lineSeparator() + System.lineSeparator()).toString());
    }

    @Test
    public void whenUserAddsItemTrackerGetsNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"1", "test name", "test descrition", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenThackerHasUpdatedItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test description", 123L));
        Input input = new StubInput(new String[]{"3", item.getId(), "test replace", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteFirstItemSecondGoesToFirstPlaceAndSecondPlaceIsNull() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name 1", "test description 1", 123L));
        Item item1 = tracker.add(new Item("test name 2", "test description 2", 1234L));
        Input input = new StubInput(new String[]{"4", item.getId(), "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name 2"));
        assertThat(tracker.findAll().length, is(1));
    }

    @Test
    public void whenIfShowAllThenPrintAll() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name 1", "test description 1", 123L));
        Item item1 = tracker.add(new Item("test name 2", "test description 2", 1234L));
        Item item2 = tracker.add(new Item("test name 3", "test description 3", 1234L));
        System.setOut(new PrintStream(out));
        Input input = new StubInput(new String[]{"2", "7"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is((printMenu()) + (new StringBuilder()
                .append("------------ Показать все заявки ------------").append(System.lineSeparator())
                .append("Заявка №1: Имя: " + item.getName() + ", Описание: " + item.getDescription() + ", ID: " + item.getId()).append(System.lineSeparator())
                .append("Заявка №2: Имя: " + item1.getName() + ", Описание: " + item1.getDescription() + ", ID: " + item1.getId()).append(System.lineSeparator())
                .append("Заявка №3: Имя: " + item2.getName() + ", Описание: " + item2.getDescription() + ", ID: " + item2.getId()).append(System.lineSeparator())
                .append("------------ Показаны все заявки ------------").append(System.lineSeparator()).append(System.lineSeparator()) + (printMenu())
                .toString())));
    }
}
