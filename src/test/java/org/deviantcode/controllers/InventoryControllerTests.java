package org.deviantcode.controllers;

import org.deviantcode.db.ItemDAO;
import org.deviantcode.models.Item;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.easymock.EasyMock.*;

@Test(groups = "unit")
public class InventoryControllerTests {
    @Test
    public void testShowAllInventory() {
        IMocksControl mocksControl = EasyMock.createControl();
        final String itemName = "Test Item";
        Item mockItem = mocksControl.createMock(Item.class);
        expect(mockItem.getName()).andReturn(itemName);
        final List<Item> mockItems = mocksControl.createMock(List.class);
        expect(mockItems.get(0)).andReturn(mockItem);

        ItemDAO mockDAO = mocksControl.createMock(ItemDAO.class);
        expect(mockDAO.getAllItems()).andReturn(mockItems);

        mocksControl.replay();

        InventoryController inventoryController = new InventoryController();
        ReflectionTestUtils.setField(inventoryController, "itemDAO", mockDAO);

        List<Item> actualList = inventoryController.showInventory();
        Item actual = actualList.get(0);
        final Item expected = new Item();
        expected.setName(itemName);
        assertEquals(actual.getName(), expected.getName());
    }

    @Test
    public void testGetInventory() {
        IMocksControl mocksControl = EasyMock.createControl();
        final String itemName = "Test Item";
        Item mockItem = mocksControl.createMock(Item.class);
        expect(mockItem.getName()).andReturn(itemName);
        final List<Item> mockItems = mocksControl.createMock(List.class);
        expect(mockItems.get(0)).andReturn(mockItem);

        ItemDAO mockDAO = mocksControl.createMock(ItemDAO.class);
        expect(mockDAO.getAllItems()).andReturn(mockItems);

        mocksControl.replay();

        InventoryController inventoryController = new InventoryController();
        ReflectionTestUtils.setField(inventoryController, "itemDAO", mockDAO);

        List<Item> actualList = (List<Item>)ReflectionTestUtils.invokeGetterMethod(inventoryController, "getInventory");
        Item actual = actualList.get(0);
        final Item expected = new Item();
        expected.setName(itemName);
        assertEquals(actual.getName(), expected.getName());
    }

    @Test
    public void testGetItem() {
        final String itemName = "Test Item";
        IMocksControl mocksControl = EasyMock.createControl();

        Item mockItem = mocksControl.createMock(Item.class);
        expect(mockItem.getName()).andReturn(itemName);

        ItemDAO mockDAO = mocksControl.createMock(ItemDAO.class);
        expect(mockDAO.getItemByName(anyObject(String.class))).andReturn(mockItem);

        mocksControl.replay();

        InventoryController inventoryController = new InventoryController();
        ReflectionTestUtils.setField(inventoryController, "itemDAO", mockDAO);


        final Item expected = new Item();
        expected.setName(itemName);
        Item actual = inventoryController.getItemByName(itemName);
        assertEquals(actual.getName(), expected.getName());
    }
}
