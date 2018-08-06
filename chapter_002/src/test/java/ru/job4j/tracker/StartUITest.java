package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
        Input input = new StubInput(new String[]{"3", item.getId(), "test replace", "заменили заявку", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteFirstItemSecondGoesToFirstPlaceAndSecondPlaceIsNull() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name 1", "test description 1", 123L));
        Item item1 = tracker.add(new Item("test name 2", "test description 2", 1234L));
        Input input = new StubInput(new String[]{"4", item.getId(), "удалили заявку", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name 2"));
        assertThat(tracker.findAll().length, is(1));
    }
}
