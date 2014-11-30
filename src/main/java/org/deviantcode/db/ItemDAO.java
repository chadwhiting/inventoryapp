package org.deviantcode.db;

import org.deviantcode.models.Item;

import java.util.List;

public interface ItemDAO {
    List<Item> getAllItems();
    Item getItemByName(String name);
}
