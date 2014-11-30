package org.deviantcode.controllers;

import org.deviantcode.db.ItemDAO;
import org.deviantcode.models.Item;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.easymock.EasyMock.*;

@Test(groups = "unit")
public class InventoryControllerTests {
    @Test
    public void testGetAll() {
        IMocksControl mocksControl = EasyMock.createControl();
        final String itemName = "Test Item";
        Item mockItem = mocksControl.createMock(Item.class);
        expect(mockItem.getName()).andReturn(itemName);
        final List<Item> mockItems = mocksControl.createMock(List.class);
        expect(mockItems.get(0)).andReturn(mockItem);

        ItemDAO mockDAO = mocksControl.createMock(ItemDAO.class);
        expect(mockDAO.getAll()).andReturn(mockItems);

        mocksControl.replay();

        InventoryController inventoryController = new InventoryController();
        ReflectionTestUtils.setField(inventoryController, "itemDAO", mockDAO);

        List<Item> actualList = inventoryController.getAll();
        Item actual = actualList.get(0);
        final Item expected = new Item();
        expected.setName(itemName);
        assertEquals(actual.getName(), expected.getName());
    }

    @Test
    public void testGetInventoryMethod() {
        IMocksControl mocksControl = EasyMock.createControl();
        final String itemName = "Test Item";
        Item mockItem = mocksControl.createMock(Item.class);
        expect(mockItem.getName()).andReturn(itemName);
        final List<Item> mockItems = mocksControl.createMock(List.class);
        expect(mockItems.get(0)).andReturn(mockItem);

        ItemDAO mockDAO = mocksControl.createMock(ItemDAO.class);
        expect(mockDAO.getAll()).andReturn(mockItems);

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
    public void testGet() {
        final String itemName = "Test Item";
        IMocksControl mocksControl = EasyMock.createControl();

        Item mockItem = mocksControl.createMock(Item.class);
        expect(mockItem.getName()).andReturn(itemName);

        ItemDAO mockDAO = mocksControl.createMock(ItemDAO.class);
        expect(mockDAO.get(anyObject(String.class))).andReturn(mockItem);

        mocksControl.replay();

        InventoryController inventoryController = new InventoryController();
        ReflectionTestUtils.setField(inventoryController, "itemDAO", mockDAO);


        final Item expected = new Item();
        expected.setName(itemName);
        Item actual = inventoryController.get(itemName);
        assertEquals(actual.getName(), expected.getName());
    }

    @Test
    public void testCreate() {
        final String name = "Test Item";
        final String descr = "A Test Description";
        final String manufacturer = "Test Manufacturer";
        final String make = "Tester";
        final String model = "Testv1";
        final String color = "Black";
        final String serialno = "123456789";
        final BigDecimal price = new BigDecimal(123.42);

        IMocksControl mocksControl = EasyMock.createControl();

        ItemDAO mockDAO = mocksControl.createMock(ItemDAO.class);
        expect(mockDAO.get(anyObject(String.class))).andReturn(null);
        mockDAO.create(
                anyObject(String.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(BigDecimal.class));
        expectLastCall();

        Item mockItem = mocksControl.createMock(Item.class);
        expect(mockItem.getName()).andReturn(name);
        expect(mockItem.getDescr()).andReturn(descr);
        expect(mockItem.getManufacturer()).andReturn(manufacturer);
        expect(mockItem.getMake()).andReturn(make);
        expect(mockItem.getModel()).andReturn(model);
        expect(mockItem.getColor()).andReturn(color);
        expect(mockItem.getSerialNo()).andReturn(serialno);
        expect(mockItem.getPrice()).andReturn(price);

        expect(mockDAO.get(anyObject(String.class))).andReturn(mockItem);

        mocksControl.replay();

        InventoryController inventoryController = new InventoryController();
        ReflectionTestUtils.setField(inventoryController, "itemDAO", mockDAO);

        final Item expected = new Item();
        expected.setName(name);
        expected.setDescr(descr);
        expected.setManufacturer(manufacturer);
        expected.setMake(make);
        expected.setModel(model);
        expected.setColor(color);
        expected.setSerialNo(serialno);
        expected.setPrice(price);
        Item actual = inventoryController.create(name, descr, manufacturer, make, model, color, serialno, price);
        assertEquals(actual.getName(), expected.getName());
        assertEquals(actual.getDescr(), expected.getDescr());
        assertEquals(actual.getManufacturer(), expected.getManufacturer());
        assertEquals(actual.getMake(), expected.getMake());
        assertEquals(actual.getModel(), expected.getModel());
        assertEquals(actual.getColor(), expected.getColor());
        assertEquals(actual.getSerialNo(), expected.getSerialNo());
        assertEquals(actual.getPrice(), expected.getPrice());
    }

    @Test
    public void testUpdate() {
        final String name = "Test Item";
        final String descr = "A Test Description";
        final String manufacturer = "Test Manufacturer";
        final String make = "Tester";
        final String model = "Testv1";
        final String color = "Black";
        final String serialno = "123456789";
        final BigDecimal price = new BigDecimal(123.42);

        IMocksControl mocksControl = EasyMock.createControl();
        Item mockItem = mocksControl.createMock(Item.class);
        expect(mockItem.getName()).andReturn(name);
        expect(mockItem.getDescr()).andReturn(descr);
        expect(mockItem.getManufacturer()).andReturn(manufacturer);
        expect(mockItem.getMake()).andReturn(make);
        expect(mockItem.getModel()).andReturn(model);
        expect(mockItem.getColor()).andReturn(color);
        expect(mockItem.getSerialNo()).andReturn(serialno);
        expect(mockItem.getPrice()).andReturn(price);

        ItemDAO mockDAO = mocksControl.createMock(ItemDAO.class);
        expect(mockDAO.get(anyObject(String.class))).andReturn(mockItem);
        mockDAO.update(
                anyObject(Item.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(String.class),
                anyObject(BigDecimal.class));
        expectLastCall();

        expect(mockDAO.get(anyObject(String.class))).andReturn(mockItem);

        mocksControl.replay();

        InventoryController inventoryController = new InventoryController();
        ReflectionTestUtils.setField(inventoryController, "itemDAO", mockDAO);

        final Item expected = new Item();
        expected.setName(name);
        expected.setDescr(descr);
        expected.setManufacturer(manufacturer);
        expected.setMake(make);
        expected.setModel(model);
        expected.setColor(color);
        expected.setSerialNo(serialno);
        expected.setPrice(price);
        Item actual = inventoryController.create(name, descr, manufacturer, make, model, color, serialno, price);
        assertEquals(actual.getName(), expected.getName());
        assertEquals(actual.getDescr(), expected.getDescr());
        assertEquals(actual.getManufacturer(), expected.getManufacturer());
        assertEquals(actual.getMake(), expected.getMake());
        assertEquals(actual.getModel(), expected.getModel());
        assertEquals(actual.getColor(), expected.getColor());
        assertEquals(actual.getSerialNo(), expected.getSerialNo());
        assertEquals(actual.getPrice(), expected.getPrice());
    }
}
