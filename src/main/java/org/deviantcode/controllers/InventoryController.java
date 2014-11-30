package org.deviantcode.controllers;

import org.deviantcode.db.ItemDAO;
import org.deviantcode.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private ItemDAO itemDAO;

    @RequestMapping("/all")
    public List<Item> showInventory() {
        return getInventory();
    }

    private List<Item> getInventory() {
        return itemDAO.getAllItems();
    }

    @RequestMapping("/item/{name}")
    public Item getItemByName(@PathVariable String name) {
        return itemDAO.getItemByName(name);
    }
}
