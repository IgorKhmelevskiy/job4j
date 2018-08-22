package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StartUITest {
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
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        tracker.findAll();
        assertThat(new String(out.toByteArray()), is(new StringBuilder().append("Заявка №%d: Имя: test name 1, Описание: test description 1").append("Заявка №%d: Имя: test name 2, Описание: test description 2").append("Заявка №%d: Имя: test name 3, Описание: test description 3").toString()));
    }
}
