package org.deviantcode.db;

import org.deviantcode.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Component
public class JDBCItemDAO implements ItemDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM INVENTORY");
        for (Map row : rows) {
            Item item = new Item();
            item.setName((String)row.get("name"));
            item.setDescr((String)row.get("descr"));
            item.setManufacturer((String)row.get("manufacturer"));
            item.setMake((String)row.get("make"));
            item.setModel((String)row.get("model"));
            item.setColor((String)row.get("color"));
            item.setSerialNo((String)row.get("serialno"));
            item.setPrice((BigDecimal) row.get("price"));
            item.setCreated((Date)row.get("created"));
            item.setLastUpdated((Date)row.get("lastupdated"));
            items.add(item);
        }
        return items;
    }

    @Override
    public Item get(String name) {
        Item result = null;
        List<Item> results =  jdbcTemplate.query("SELECT * FROM INVENTORY WHERE name = ?",
                new Object[] { name }, new BeanPropertyRowMapper<>(Item.class));
        if (results.size() > 0) {
            result = results.get(0);
        }
        return result;
    }

    @Override
    public void create(String name, String descr, String manufacturer, String make, String model, String color,
                       String serialno, BigDecimal price) {
        String sql = "INSERT INTO INVENTORY (name, descr, manufacturer, make, model, color, serialno, price) values (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, name, descr, manufacturer, make, model, color, serialno, price);
    }

    @Override
    public void update(Item item, String descr, String manufacturer, String make, String model, String color, String serialno, BigDecimal price) {
        String sql = "UPDATE INVENTORY SET descr = ?, manufacturer = ?, make = ?, model = ?, color = ?, serialno = ?, price = ? WHERE name = ?";
        jdbcTemplate.update(sql,
                (null == descr) ? item.getDescr() : descr,
                (null == manufacturer) ? item.getManufacturer() : manufacturer,
                (null == make) ? item.getMake() : make,
                (null == model) ? item.getModel() : model,
                (null == color) ? item.getColor() : color,
                (null == serialno) ? item.getSerialNo() : serialno,
                (null == price) ? item.getPrice() : price,
                item.getName());
    }

    @Override
    public void delete(String name) {
        jdbcTemplate.update("DELETE FROM INVENTORY WHERE name = ?", name);
    }
}
