package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Igor Khmelevskiy (mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    @Test
    public void add() {
        Tracker tracker = new Tracker();
        Item item = new Item("test 1", "testDescription");
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void replace() {
        Item[] items = {
                new Item("test 1", "description 1"),
                new Item("test 2", "description 2"),
                new Item("test 3", "description 3"),
        };
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        Item replaceItem = new Item("test", "replacement");
        Item[] expectedItems = {
                items[0], replaceItem, items[2]
        };
        tracker.replace(items[1].getId(), replaceItem);
        Item[] allItems = tracker.findAll();
        assertThat(expectedItems, is(allItems));
    }

    @Test
    public void delete() {
        Item[] items = {
                new Item("test 1", "description 1"),
                new Item("test 2", "description 2"),
                new Item("test 3", "description 3"),
        };
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        Item[] itemsExpected = {
                items[1], items[2]
        };
        tracker.delete(items[0].getId());
        Item[] result = tracker.findAll();
        assertThat(itemsExpected, is(result));
    }

    @Test
    public void findAll() {
        Item[] items = {
                new Item("test 1", "description 1"),
                new Item("test 2", "description 2"),
                new Item("test 3", "description 3"),
        };
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        Item[] allItems = tracker.findAll();
        assertThat(items, is(allItems));
    }

    @Test
    public void findByName() {
        Item[] items = {
                new Item("test 1", "description 1"),
                new Item("test 2", "description 2"),
                new Item("test 3", "description 3"),
                new Item("test 1", "description 4")
        };
        Tracker tracker = new Tracker();
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        tracker.add(items[3]);
        Item[] itemsExpected = {
                items[0], items[3]
        };
        Item[] result = tracker.findByName("test 1");
        assertThat(result, is(itemsExpected));
    }

    @Test
    public void findById() {
        Tracker tracker = new Tracker();
        Item findItem = new Item("test 1", "testDescription 1");
        tracker.add(findItem);
        Item find = tracker.findById(findItem.getId());
        assertThat(find, is(findItem));
    }
}