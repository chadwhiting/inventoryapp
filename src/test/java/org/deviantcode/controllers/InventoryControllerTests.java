package org.deviantcode.controllers;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(groups = "unit")
public class InventoryControllerTests {
    @Test
    public void testShowAllInventory() {
        InventoryController inventoryController = new InventoryController();
        final String expected = "Inventory";
        String actual = inventoryController.inventory();
        assertEquals(actual, expected);
    }
}
