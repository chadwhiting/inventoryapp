package org.deviantcode.controllers;

import org.deviantcode.models.Item;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.LinkedHashSet;

import static org.testng.Assert.assertEquals;

@Test(groups = "unit")
public class InventoryControllerTests {
    @Test
    public void testShowAllInventory() {
        InventoryController inventoryController = new InventoryController();
        final Collection<Item> expected = new LinkedHashSet<>();
        Item testItem = new Item();
        testItem.setName("Test Item");
        testItem.setDescription("This is a test item");
        expected.add(testItem);
        Collection<Item> actual = inventoryController.showInventory();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetInventory() {
        InventoryController inventoryController = new InventoryController();
        final Collection<Item> expected = new LinkedHashSet<>();
        Item testItem = new Item();
        testItem.setName("Test Item");
        testItem.setDescription("This is a test item");
        expected.add(testItem);
        Collection<Item> actual = (Collection<Item>)ReflectionTestUtils.invokeGetterMethod(inventoryController, "getInventory");
        assertEquals(actual, expected);
    }

    @Test
    public void testGetItem() {
        InventoryController inventoryController = new InventoryController();
        final Item expected = new Item();
        expected.setName("Some Item");
        expected.setDescription("This is an individual item");
        Item actual = inventoryController.getItem(1234L);
        assertEquals(actual, expected);
    }
}
