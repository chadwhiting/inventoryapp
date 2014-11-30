package org.deviantcode.controllers;

import org.deviantcode.db.ItemDAO;
import org.deviantcode.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private ItemDAO itemDAO;

    @RequestMapping("/items")
    public List<Item> getAll() {
        return getInventory();
    }

    private List<Item> getInventory() {
        return itemDAO.getAll();
    }

    @RequestMapping(value = "/item/{name}", method = RequestMethod.GET)
    public Item get(@PathVariable String name) {
        return itemDAO.get(name);
    }

    @RequestMapping(value = "/item/{name}", method = RequestMethod.POST)
    public Item create(@PathVariable String name, @RequestParam(value="descr", required = false) String descr,
                           @RequestParam(value="manufacturer", required = false) String manufacturer,
                           @RequestParam(value="make", required = false) String make,
                           @RequestParam(value="model", required = false) String model,
                           @RequestParam(value="color", required = false) String color,
                           @RequestParam(value="serialno", required = false) String serialno,
                           @RequestParam(value="price", required = false) BigDecimal price) {
        Item item = itemDAO.get(name);
        if (null == item) {
            itemDAO.create(name, descr, manufacturer, make, model, color, serialno, price);
        } else {
            itemDAO.update(item, descr, manufacturer, make, model, color, serialno, price);
        }
        return itemDAO.get(name);
    }

    @RequestMapping(value = "/item/{name}", method = RequestMethod.DELETE)
    public Item delete(@PathVariable String name) {
        Item item =  itemDAO.get(name);
        if (null != item) {
            itemDAO.delete(name);
        }
        return item;
    }
}
