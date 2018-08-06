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
        Item item = new Item("test 1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void replace() {
        Item first = new Item("test 1", "testDescription 1", 123L);
        Item second = new Item("test 2", "testDescription 2", 1234L);
        Item third = new Item("test 3", "testDescription 3", 12345L);
        Item replaceItem = new Item("test", "replacement", 321L);

        Tracker tracker = new Tracker();
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);

        Tracker expectedTracker = new Tracker();
        expectedTracker.add(first);
        expectedTracker.add(replaceItem);
        expectedTracker.add(third);

        tracker.replace(second.getId(), replaceItem);

        assertThat(tracker.findAll(), is(expectedTracker.findAll()));
    }

    @Test
    public void delete() {
        Item first = new Item("test 1", "testDescription 1", 123L);
        Item second = new Item("test 2", "testDescription 2", 1234L);
        Item third = new Item("test 3", "testDescription 3", 12345L);

        Tracker tracker = new Tracker();
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);

        Tracker expectedTracker = new Tracker();
        expectedTracker.add(second);
        expectedTracker.add(third);

        tracker.delete(first.getId());

        assertThat(tracker.findAll(), is(expectedTracker.findAll()));
    }

    @Test
    public void findAll() {
        Tracker tracker = new Tracker();
        Item first = new Item("test 1", "testDescription 1", 123L);
        tracker.add(first);
        Item second = new Item("test 2", "testDescription 2", 1234L);
        tracker.add(second);
        Item third = new Item("test 3", "testDescription 3", 12345L);
        tracker.add(third);

        Item[] allItems = tracker.findAll();
        assertThat(allItems[0], is(first));
        assertThat(allItems[1], is(second));
        assertThat(allItems[2], is(third));
    }

    @Test
    public void findByName() {
        Tracker tracker = new Tracker();
        Item first = new Item("test 1", "testDescription 1", 123L);
        tracker.add(first);
        Item second = new Item("test 2", "testDescription 2", 1234L);
        tracker.add(second);
        Item third = new Item("test 3", "testDescription 3", 12345L);
        tracker.add(third);
        Item find = tracker.findByName("test 2");
        assertThat(find, is(second));
    }

    @Test
    public void findById() {
        Tracker tracker = new Tracker();
        Item findItem = new Item("test 1", "testDescription 1", 123L);
        tracker.add(findItem);
        Item find = tracker.findById(findItem.getId());
        assertThat(find, is(findItem));
    }
}