package org.deviantcode.controllers;

import org.deviantcode.models.Item;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @RequestMapping("/all")
    public Collection<Item> showInventory() {
        return getInventory();
    }

    private Collection<Item> getInventory() {
        Collection<Item> items = new LinkedHashSet<>();
        Item testItem = new Item();
        testItem.setName("Test Item");
        testItem.setDescription("This is a test item");
        items.add(testItem);
        return items;
    }

    @RequestMapping("/{id}")
    public Item getItem(@PathVariable long id) {
        Item item = new Item();
        item.setName("Some Item");
        item.setDescription("This is an individual item");
        return item;
    }
}
