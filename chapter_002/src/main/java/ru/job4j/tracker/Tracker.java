package ru.job4j.tracker;

import java.util.*;

/**
 * @author Igor Khmelevskiy (mailto:igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    private final Item[] items = new Item[100];
    private static final Random RN = new Random();
    private int position;

    /**
     * Метод добавляет заявку
     * @param item заявка
     * @return заявка с позицией и ID
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    } ;

    /**
     * Метод генерирует ID заявки рандомом
     * @return ID заявки
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод заменяет заявку с указанным ID
     * @param id ID заявки, которую надо заменить
     * @param item Новая заявка
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < position; index++) {
            if (this.items[index].getId().equals(id)) {
                this.items[index] = item;
            }
        }
    };

    /**
     * Метод удаляет заявку с указанным ID
     * @param id ID заявки, которую надо удалить
     */
    public void delete(String id) {
        for (int index = 0; index < position; index++) {
            if (this.items[index].getId().equals(id)) {
                System.arraycopy(this.items,index + 1, this.items, index, position - index);
                this.items[position] = null;
                position--;
            }
        }
    };


    /**
     * Метод возвращает все заявки, которые есть
     * @return Массив заявок
     */
    public Item[] findAll() {
        Item[] itemsAll = new Item[position];
        for (int index = 0; index < position; index++) {
            if(this.items[index] != null) {
                itemsAll[index] = this.items[index];
            }
        }
        return itemsAll;
    };

    /**
     * Метод поиска заявки по имени
     * @param key Имя
     * @return найдення заявка
     */
    public Item findByName(String key) {
        Item namedItem = new Item();
        for (int index = 0; index < position; index++) {
            if (this.items[index].getName().equals(key)) {
                namedItem = this.items[index];
            }
        }
        return namedItem;
    }

    /**
     * Метод находит заявку по Id
     * @param id ID искомой заявки
     * @return заявка
     */
    public Item findById(String id) {
        Item item = null;
        for (int index = 0; index < position; index++) {
            if (this.items[index].getId().equals(id)) {
                item = this.items[index];
                break;
            }
        }
        return item;
    };


}
