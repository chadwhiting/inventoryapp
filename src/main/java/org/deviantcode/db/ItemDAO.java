package org.deviantcode.db;

import org.deviantcode.models.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDAO {
    List<Item> getAll();
    Item get(String name);
    void create(String name, String descr, String manufacturer, String make, String model, String color, String serialno, BigDecimal price);
    void update(Item item, String descr, String manufacturer, String make, String model, String color, String serialno, BigDecimal price);
    void delete(String name);
}
